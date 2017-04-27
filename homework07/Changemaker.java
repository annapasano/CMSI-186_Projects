public class Changemaker {

    public static void main(String[] args) {
        if (args.length != 2) {
            printUsage();
            return;
        }

        try {

            String[] denominationStrings = args[0].split(",");
            int[] denominations = new int[denominationStrings.length];

            for (int i = 0; i < denominations.length; i++) {
                denominations[i] = Integer.parseInt(denominationStrings[i]);
                if (denominations[i] <= 0) {
                    System.out.println("Denominations must all be greater than zero.\n");
                    printUsage();
                    return;
                }

                for (int j = 0; j < i; j++) {
                    if (denominations[j] == denominations[i]) {
                        System.out.println("Duplicate denominations are not allowed.\n");
                        printUsage();
                        return;
                    }
                }
            }

            int amount = Integer.parseInt(args[1]);
            if (amount < 0) {
                System.out.println("Change cannot be made for negative amounts.\n");
                printUsage();
                return;
            }



            Tuple change = makeChangeWithDynamicProgramming(denominations, amount);
            if (change.isImpossible()) {
                System.out.println("It is impossible to make " + amount + " cents with those denominations.");
            } else {
                int coinTotal = change.total();
                System.out.println(amount + " cents can be made with " + coinTotal + " coin" +
                        getSimplePluralSuffix(coinTotal) + " as follows:");

                for (int i = 0; i < denominations.length; i++) {
                    int coinCount = change.getElement(i);
                    System.out.println("- "  + coinCount + " " + denominations[i] + "-cent coin" +
                            getSimplePluralSuffix(coinCount));
                }
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Denominations and amount must all be integers.\n");
            printUsage();
        }
    }

    public static Tuple makeChangeWithDynamicProgramming(int[] denominations, int amount) {
        // create 2 dimensional array [height = number of denominations][width = amount+1]
        int n = denominations.length;
        Tuple[][] table = new Tuple[n][amount+1];
        // do nested for loop
        for (int i = 0; i < n ; i++) {
            // System.out.print("\n");
            for (int j = 0 ; j < amount+1 ; j++) {
                // if j is 0, then it should be a tuple of zeros
                if (j == 0) {
                    // Tuple x = new Tuple(n);
                    // table[i][0] = x;
                    table[i][0] = new Tuple(n);
                }
                // if denomination[i] > j then impossible
                else if (denominations[i] > j) {
                    table[i][j] = Tuple.IMPOSSIBLE;
                }
                // if denomination[i] <= j then try makeChange
                else if (denominations[i] <= j) {
                    // if we can make change, create new tuple, set 1 to the tuple at index i
                    table[i][j] = new Tuple(n);
                    table[i][j].setElement(i,1);
                    // add this tuple with tuple at table[same row (i)][j-denominations[i]]
                    table[i][j] = addTuples(table[i][j],table[i][j-denominations[i]]);
                }
                // check above
                // when i > 0 check to see if solution above (table[i-1][j]) is better
                if (i > 0) {
                    // set table[i][j] to better solutions
                    table[i][j] = chooseBetterTuple(table[i-1][j], table[i][j]);
                }
                // System.out.print(table[i][j] + " ");
            }    
        }
        return table[n-1][amount];
    }

    public static Tuple addTuples(Tuple ferris, Tuple andy) {
        // if either tuple is = IMPOSSIBLE, return IMPOSSIBLE
        if (ferris.isImpossible() || andy.isImpossible()) {
            return Tuple.IMPOSSIBLE;
        }
        // else return thisTuple.add(thatTuple);
        return ferris.add(andy);
    }

    public static Tuple chooseBetterTuple(Tuple ferris, Tuple andy) {
        if (ferris.isImpossible()) {
            return andy;
        } else if (andy.isImpossible()) {
            return ferris;
        } else {
            return (ferris.total() < andy.total()) ? ferris : andy;
        }
    }

    private static void printUsage() {
        System.out.println("Usage: java DynamicChangemaker <denominations> <amount>");
        System.out.println("  - <denominations> is a comma-separated list of denominations (no spaces)");
        System.out.println("  - <amount> is the amount for which to make change");
    }

    private static String getSimplePluralSuffix(int count) {
        return count == 1 ? "" : "s";
    }

}