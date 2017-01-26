public class CountTheDays {
    
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println( "Test" );   
        }
        else if (args.length == 6) {
            if (!Calendar.isValidDate(Long.parseLong(args[0]), Long.parseLong(args[1]), Long.parseLong(args[2])) || !Calendar.isValidDate(Long.parseLong(args[3]), Long.parseLong(args[4]), Long.parseLong(args[5]))) {
               System.out.println("nope");
               return;
            }
            System.out.println ( Calendar.daysBetween(Long.parseLong(args[0]), Long.parseLong(args[1]), 
                                    Long.parseLong(args[2]), Long.parseLong(args[3]), 
                                        Long.parseLong(args[4]), Long.parseLong(args[5])) );
        }   
    }
}