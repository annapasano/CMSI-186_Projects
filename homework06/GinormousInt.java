/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  GinormousInt.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-06-04
 *  Description   :  This class provides a bunch of methods which may be useful for the GinormousInt class
 *                   for Homework 06.
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-04-18  Anna Pasano    GinormousInt methods and main 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class GinormousInt {

   public static final GinormousInt ZERO = new GinormousInt("0");
   public static final GinormousInt ONE = new GinormousInt("1");
   public static final GinormousInt TEN = new GinormousInt("10");
   int[] gino;
   int size;
   int sign;

   public GinormousInt( String value ) {
      if (value.equals("0")) {
         gino = new int[1];
         gino[0] = 0;
         size = 1;
         sign = 0;
         return;
      } 
      if (value.equals("1")) {
         gino = new int[3];
         gino[0] = 1;
         size = 1;
         sign = gino[gino.length-1] = 1;
         return;
      }
      gino = new int[value.length()+2];
      size = gino.length-2;
      sign = gino[gino.length-1] = 1; // default positive
      gino[gino.length-2] = 0;
      for (int i = 0; i <= value.length()-1; i++) {
         if(value.charAt(value.length()-1-i) == '-') {
            sign = gino[gino.length-1] = -1;
            break;
         }
         gino[i] = Integer.parseInt(value.charAt(value.length()-1-i)+"");
      }
   }
   public GinormousInt( int[] value ) {
      gino = value;
   }
   // // mimics one of the several java.math.BigInteger constructors

   public GinormousInt flipSign() {
      sign = gino[gino.length-1] = gino[gino.length-1] * -1;
      return this;
   }

   public GinormousInt add( GinormousInt value ) {
      int[] answer;
      if (value.equals(ZERO)) {
         return this;
      }
      if (this.size >= value.size && this.sign + value.sign != 0) {
         answer = this.gino;
         answer[this.gino.length-1] = this.sign;
         for (int i = 0; i < value.size; i++) {
            // add column
            answer[i] = answer[i] + value.gino[i];
            // carry
            if (answer[i] > 9) {
               answer[i] = answer[i] - 10;
               answer[i+1] = answer[i+1] + 1;
            }
         }
         return new GinormousInt(answer);
      } else if (this.compareTo(value) == -1 && this.sign + value.sign == 2) {
         return value.add(this);
      } else if (this.compareTo(value) == 1 && this.sign + value.sign == -2) {
         return (this.flipSign().add(value.flipSign())).flipSign();
      } else if (this.compareTo(value) == -1 && this.sign + value.sign == -2) {
         return (value.flipSign().add(this.flipSign())).flipSign();
      } else if (this.sign > value.sign) {
         return this.subtract(value.flipSign());
      } else if (this.sign < value.sign) {
         return value.subtract(this.flipSign());
      }
      return null;
   }
   // returns a GinormousInt whose value is the sum of this plus the argument


   public GinormousInt subtract( GinormousInt value ) {
      if (this.compareTo(value) == 0) {
         return ZERO;
      }
      int[] answer;
      if (this.compareTo(value) == 1 && this.sign + value.sign == 2) {
         answer = this.gino;
         for (int i = 0; i < value.size; i++) {  
            // check for carry first
            if (answer[i] < value.gino[i]) {
               answer[i] = answer[i] + 10;
               answer[i+1] = answer[i+1] - 1;
            }
            // column subtract
            answer[i] = answer[i] - value.gino[i];
         }
         return new GinormousInt(answer);
      } else if (this.compareTo(value) == -1 && this.sign + value.sign == 2) {
         return value.subtract(this).flipSign();
      } else if (this.compareTo(value) == 1 && this.sign + value.sign == -2) {
         return value.flipSign().subtract(this.flipSign());
      } else if (this.compareTo(value) == -1 && this.sign + value.sign == -2) {
         return (this.flipSign().subtract(value.flipSign())).flipSign();
      } else if (this.sign > value.sign) {
         return this.add(value.flipSign());
      } else if (this.sign < value.sign) {
         return value.add(this.flipSign());
      }
      return null;
   }
   // // returns a GinormousInt whose value is the difference of this minus the argument


   public GinormousInt multiply( GinormousInt value ) {
      // if (this.equals(ZERO) || value.equals(ZERO)) {
      //    return ZERO;
      // } 
      if (this.equals(ONE)) {
         return value;
      } if (value.equals(ONE)) {
         return this;
      }
      if (this.size < value.size) {
         return value.multiply(this);
      }
      int[] answer = new int[this.size + value.size];
      answer[answer.length-1] = this.sign * value.sign;
      for (int i = 0; i < value.size; i++) {
         for (int j = 0; j < this.size; j++) {
            answer[j+i] = answer[j+i] + (this.gino[j] * value.gino[i]);
         }
         // carries
         for (int k = 0; k < answer.length; k++) {
            int temp = answer[k];
            if (answer[k] > 10) {
               answer[k] = answer[k] % 10;
               answer[k+1] = answer[k+1] + temp/10;
            }
         }
      }
      return new GinormousInt(answer);
   }
   // // returns a GinormousInt whose value is the product of this times the argument


   public GinormousInt divide( GinormousInt value ) {
      throw new UnsupportedOperationException(); 
      // int newSign = this.sign * value.sign;
      // if (this.sign == -1) {
      //    this.flipSign();
      // }
      // if (value.sign == -1) {
      //    value.flipSign();
      // }

      // if (this.compareTo(value) == -1) {
      //    return ZERO;
      // } else if (this.equals(value)) {
      //    return ONE;
      // } 
      // else {
      //    int i = 2;
      //    System.out.println(value.multiply(new GinormousInt(i+"")).compareTo(this));
      //    while (value.multiply(new GinormousInt(i+"")).compareTo(this) < 0) {
      //       System.out.println(i);
      //       i++;
      //    }
      //    i--;
      //    return new GinormousInt(i + "");
      // }
   }
   // // returns a GinormousInt whose value is the quotient of this divided by the argument


   public GinormousInt remainder( GinormousInt value ) {
      throw new UnsupportedOperationException(); 
   }
   // // returns a GinormousInt whose value is the remainder of this divided by the argument
   
   public String toString() {
      if (this.equals(ZERO)) {
         return "0";
      }
      if (this.equals(ONE)) {
         return "1";
      }
      String s = "";
      // grab all numbers except sign
      for (int i = 0; i <= gino.length-2; i++) {
         s = gino[i] + s;
      }
      // remove leading zeroes
      while (s.charAt(0) == '0' && s.length()-1 > 0) {
         s = s.substring(1);
      }
      // add sign
      if (gino[gino.length-1] == -1) {
         s = "-" + s;
      }
      return s;
   }
   // returns the decimal string represention of this GinormousInt
   
   public int compareTo( GinormousInt value ) {
      if (this.sign > value.sign) {
         return 1;
      } else if (this.sign < value.sign) {
         return -1;
      } else {
         if (this.size > value.size) {
            return 1 * this.sign;
         } else if (this.size < value.size) {
            return -1 * this.sign;
         } else {
            for (int i = gino.length-2; i >= 0; i--) {
               if (this.gino[i] > value.gino[i]) {
                  return 1 * this.sign;
               } else if (this.gino[i] < value.gino[i]) {
                  return -1 * this.sign;
               }
            }
            return 0;
         }
      }
   }
   // returns -1/0/1 as this GinormousInt is numerically less than/equal to/greater than the value passed as the argument

   public boolean equals( Object x ) {
      if (x instanceof GinormousInt) {
         GinormousInt y = (GinormousInt) x;
         if (y.size != this.size) {
            return false;
         }
         return this.compareTo(y) == 0;
      }
      return false;
   }
   // returns true iff x is a GinormousInt whose value is numerically equal to this GinormousInt

   public static void main(String[] args) {
      // making GinormousInt
      GinormousInt glen = new GinormousInt("438756324985763298457632964712864839");
      GinormousInt coco = new GinormousInt("13320938");
      System.out.println(glen);
      System.out.println(glen.compareTo(coco));
      System.out.println(coco.compareTo(glen));
      System.out.println(glen.compareTo(glen));
      
      GinormousInt test1 = ONE;
      GinormousInt test2 = ZERO;
      System.out.println(test1.add(test2));
      GinormousInt temp = test1.add(new GinormousInt("20"));
      System.out.println(temp);

      GinormousInt easyAdd1 = new GinormousInt("-1006");
      GinormousInt easyAdd2 = new GinormousInt("-2344");
      System.out.println(easyAdd1.add(easyAdd2));

      GinormousInt easyAdd3 = new GinormousInt("-1006");
      GinormousInt easyAdd4 = new GinormousInt("1");
      System.out.println(easyAdd3.add(easyAdd4));

      GinormousInt easyAdd5 = new GinormousInt("-100004");
      System.out.println(easyAdd4.add(ONE));
      System.out.println(easyAdd5.add(ZERO));

      GinormousInt testSub1 = new GinormousInt("1234");
      GinormousInt testSub2 = new GinormousInt("234");
      System.out.println(testSub1.subtract(testSub2));

      GinormousInt testSub3 = new GinormousInt("234");
      GinormousInt testSub4 = new GinormousInt("1234");
      System.out.println(testSub3.subtract(testSub4));


      GinormousInt testSub5 = new GinormousInt("-14");
      GinormousInt testSub6 = new GinormousInt("-12");
      System.out.println(testSub5.subtract(testSub6));

      GinormousInt testSub7 = new GinormousInt("10");
      GinormousInt testSub8 = new GinormousInt("1");
      System.out.println(testSub7.subtract(testSub8));

      GinormousInt testMul1 = new GinormousInt("12345");
      GinormousInt testMul2 = new GinormousInt("67");
      System.out.println(testMul1.multiply(testMul2));

      // GinormousInt testDiv1 = new GinormousInt("20");
      // GinormousInt testDiv2 = new GinormousInt("3");
      // System.out.println(testDiv1.divide(testDiv2));
   }
}