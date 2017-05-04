/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  ChangeMaker.java
 * Purpose    :  Program to implement "Dynamic Programming" algorithm 
 * @author    :  B.J. Johnson 
 * @author    :  Anna Pasano 
 * Date       :  2017-05-4
 * Description:  This program contains methods that may be useful for the ChangeMaker class for homework 7 
 *               that is meant to be an optimizated coin changemaker, which is a "Dynamic Programming" 
 *               algorithm.
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-19  B.J. Johnson  Initial release; stolen blatently from Professor Don Murphy with his
 *                                    express permission and blessing; just added this comment block
 *  1.1.0  2017-05-02  Anna Pasano   Added methods and JavaDocs.
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class ChangeMaker {
 /**
   *  main method 
   *  @param args String array of the arguments from the command line
   *              args [0] is the individual coin denominations
   *              args [1] is the the amount 
   */
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
                    System.out.println("\nDenominations must all be greater than zero.\n");
                    printUsage();
                    return;
                }

                for (int j = 0; j < i; j++) {
                    if (denominations[j] == denominations[i]) {
                        System.out.println("\nDuplicate denominations are not allowed.\n");
                        printUsage();
                        return;
                    }
                }
            }

            int amount = Integer.parseInt(args[1]);
            if (amount < 0) {
                System.out.println("\nChange cannot be made for negative amounts.\n");
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
            System.out.println("\nDenominations and amount must all be integers.\n");
            printUsage();
        }
    }

  /**
    * Creates 2 dimensional array [height = number of denominations][width = amount+1]
    *
    * @param denominations 
    *           integer array of numbers 
    * @param amount 
    *           integer value to be made in change
    * @return Tuple 
    *            returns the Tuple of optimized denominations 
    */
    public static Tuple makeChangeWithDynamicProgramming(int[] denominations, int amount) {
        int n = denominations.length;
        if (badValues(denominations,amount)) {
            return Tuple.IMPOSSIBLE;
        } 
        else if (amount == 0) {
            return new Tuple(denominations.length);
        }
        Tuple[][] table = new Tuple[n][amount+1];
        for (int i = 0; i < n ; i++) {
            for (int j = 0 ; j < amount+1 ; j++) {
                if (j == 0) {
                    table[i][0] = new Tuple(n);
                }
                else if (denominations[i] > j) {
                    table[i][j] = Tuple.IMPOSSIBLE;
                }
                else if (denominations[i] <= j) {
                    table[i][j] = new Tuple(n);
                    table[i][j].setElement(i,1);
                    // add this tuple with tuple at table[same row (i)][j-denominations[i]]
                    table[i][j] = addTuples(table[i][j],table[i][j-denominations[i]]);
                }
                if (i > 0) {
                    table[i][j] = chooseBetterTuple(table[i-1][j], table[i][j]);
                }
                // System.out.print(table[i][j] + " ");
            }    
        }
        return table[n-1][amount];
    }
  /**
    * Adds the two Tuples together lined up by index 
    *
    * @param ferris 
    *           one tuple to be added 
    * @param bueller 
    *            the other tuple to be added
    * @return Tuple.IMPOSSIBLE
    *              if ferris or bueller is impossible 
    * @return ferris.add(bueller)
    *                      
    */ 
    public static Tuple addTuples(Tuple ferris, Tuple bueller) {
        if (ferris.isImpossible() || bueller.isImpossible()) {
            return Tuple.IMPOSSIBLE;
        }
        return ferris.add(bueller);
    }
  
  /**
    * Selects tuple with more optimized value
    *
    * @param ferris
    *           one tuple to be checked 
    * @param bueller 
    *           other tuple to be checked
    * @return bueller
    *           if ferris is impossible
    * @return ferris 
    *            if bueller is impossible
    * @return tuple with smaller sum
    */
    public static Tuple chooseBetterTuple(Tuple ferris, Tuple bueller) {
        if (ferris.isImpossible()) {
            return bueller;
        } else if (bueller.isImpossible()) {
            return ferris;
        } else {
            return (ferris.total() < bueller.total()) ? ferris : bueller;
        }
    }

  /**
    * Checks for bad values, specifically when being tested within the test harness 
    * Specifically, for denomination values that are not positive, duplicated denominations
    * and negative amounts.
    *
    * @param denominations
    *           integer array of numbers
    * @param amount 
    *           integer value to be made in change
    * @return true
    *           if denominations or amount hold bad values 
    */
    public static boolean badValues(int[] denominations, int amount) {
        // checks for negative or zero amount.
        if (amount < 0) {
            return true;
        }
        for (int i = 0; i < denominations.length; i++) {
            // check for bad denomination
            if (denominations[i] < 1) {
                return true;
            }
            // check for repeat number
            for (int j = i+1; j < denominations.length; j++) {
                if (denominations[i] == denominations[j]) {
                    return true;
                }
            }
        }
        return false;
    }

  /**
    * Prints the usage for user
    *
    * @return void
    */
    private static void printUsage() {
        System.out.println("Usage: java ChangeMaker <denominations> <amount>");
        System.out.println("  - <denominations> is a comma-separated list of denominations (no spaces)");
        System.out.println("  - <amount> is the amount for which to make change");
    }

  /**
    * Denotes plurality for formatting purposes 
    *
    * @param count 
    *          integer to check if equal to one 
    * @return empty string or "s"
    *           returns an empty string or "s" whether count is 1 or not
    *            
    */
    private static String getSimplePluralSuffix(int count) {
        return count == 1 ? "" : "s";
    }

}
