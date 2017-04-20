/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Fibonacci.java
 *  @see
 *  @author       :  Anna Pasano
 *  Date written  :  2017-06-04
 *  Description   :  This class provides the methods to determine a Fibonnaci number, 
                     specifically catered to handle Ginormous Integers
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-04-18  Anna Pasano    Fibonacci for Ginormous Int.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Fibonacci {
   public static GinormousInt fibonacci(int n) {
      if (n <= 2) {
         return new GinormousInt("1");
      } else {
         GinormousInt n1 = new GinormousInt("0");
         GinormousInt n2 = new GinormousInt("1");
         for (int i = 2; i < n; i++) {
            GinormousInt n3 = n1.add(n2);
            System.out.println(n3);
            n1 = n2;
            n2 = n3;
         }
         return n1.add(n2);
      }
    }
    public static void main(String[] args) {
       System.out.println(fibonacci(Integer.parseInt(args[0])));
    }
}