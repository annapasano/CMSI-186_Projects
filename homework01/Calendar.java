public class Calendar {

   public static boolean isLeapYear( long year ) {
      if (year%400 == 0) {
         return true; 
      } else if (year%4 == 0) {
         if (year%100 == 0) {
            return false;
         }
         return true;
      } 
      else {
         return false;
      }
   }

   public static void main(String[] args) {
      System.out.println("isLeapYear Test: " + isLeapYear(1900));
   }
}