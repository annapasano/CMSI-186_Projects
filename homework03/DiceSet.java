/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  Anna Pasano
 *  Date          :  2017-02-20
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public DiceSet( int k, int n );                  // Constructor for a set of k dice each with n-sides
 *                   public int sum();                                // Returns the present sum of this set of dice
 *                   public void roll();                              // Randomly rolls all of the dice in this set
 *                   public void rollIndividual( int i );             // Randomly rolls only the ith die in this set
 *                   public int getIndividual( int i );               // Gets the value of the ith die in this set
 *                   public String toString();                        // Returns a stringy representation of this set of dice
 *                   public static String toString( DiceSet ds );     // Classwide version of the preceding instance method
 *                   public boolean isIdentical( DiceSet ds );        // Returns true iff this set is identical to the set ds
 *                   public static void main( String[] args );        // The built-in test program for this class
 *
 *  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
 *                   things about Java, and to add this header block and some JavaDoc comments.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-21  Anna Pasano  Adding methods
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class DiceSet {

  /**
   * private instance data
   */
   private int count;
   private int sides;
   private Die[] ds = null;

   // public constructor:
  /**
   * constructor
   * @param  count int value containing total dice count
   * @param  sides int value containing the number of pips on each die
   * @throws IllegalArgumentException if one or both arguments don't make sense
   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
   */
   public DiceSet( int count, int sides ) {
      if (count < 1) {
         throw new IllegalArgumentException();  
      }

      this.count = count; 
      this.sides = sides;

      ds = new Die[ count ];
      for (int i = 0; i <= ds.length - 1; i++) {
        ds[i] = new Die (sides);
      }
   }

  /**
   * @return the sum of all the dice values in the set
   */
   public int sum() {
      int sum = 0;
      for (int i = 0; i <= ds.length -1; i++) {
        sum = sum + ds[i].getValue();
      }
      return sum;
   }

  /**
   * Randomly rolls all of the dice in this set
   */
   public void roll() {
      for (int i = 0; i <= ds.length - 1; i++) {
        ds[i].roll();
      }
   }

  /**
   * Randomly rolls a single die of the dice in this set
   * @param  dieIndex int of which die to roll
   * @trhows IllegalArgumentException if the index is out of range
   */
   public void rollIndividual( int dieIndex ) {
      if (dieIndex > ds.length -1) {
         throw new IllegalArgumentException(); 
      }
      ds[dieIndex].roll();
   }

   public int getCount () {
      return this.count; 
   }

   public int getSides() {
      return this.sides;
   }

  /**
   * Gets the value of the die in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int getIndividual( int dieIndex ) {
    if (dieIndex > ds.length -1) {
      throw new IllegalArgumentException(); 
    }
      return ds[dieIndex].getValue();
   }

  /**
   * @return Public Instance method that returns a String representation of the DiceSet instance
   */
   public String toString() {
      String result = "";
      for (int i = 0; i <= ds.length -1; i++ ){
        result = result + "[" + ds[i].getValue() + "]";
      }
      return result;
   }

  /**
   * @return Class-wide version of the preceding instance method
   */

  public static String toString (DiceSet ds) {
    return ds.toString(); 
  }

  /**
   * @return  true iff this set is identical to the set passed as an argument
   */

   public boolean isIdentical( DiceSet ds ) {
      if ((this.getCount() != ds.getCount() ) || (this.getSides () != ds.getSides ())) {
         return false;  
      } else {
         boolean[] skip = new boolean [this.getCount()];
         for (int i = 0; i <= this.getCount()-1; i++){
            for (int j = 0; j <= ds.getCount()-1; j++) {
               if (!skip[j]) {
                  if (this.getIndividual(i) == ds.getIndividual(j)) {
                     skip[j] = true;
                     break;
                  }
               }
            }
         }
         for (int k = 0; k <= skip.length-1; k++) {
            if (!skip[k]) {
               return false;
            }
         } 
         return true;
      }
   }

  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
      // You do this part!
      DiceSet steve = new DiceSet(2,4);
      DiceSet marko = new DiceSet (2,4);
      DiceSet bob = new DiceSet (5,9);
      DiceSet tim = new DiceSet (3,8);
      DiceSet richard = new DiceSet (1,21);
      steve.roll();
      marko.roll();
      bob.roll();
      tim.roll();
      richard.roll();

      System.out.println("steve test: ");
      System.out.println("steve: " + steve);
      System.out.println("sum: " + steve.sum());
      steve.rollIndividual(0);
      System.out.println("roll individual (0): " + steve);
      System.out.println("steve = marko? " + steve.isIdentical(marko));


      System.out.println("");
      System.out.println("marko test: ");
      System.out.println("marko: " + marko);
      System.out.println("sum: " + marko.sum());
      marko.rollIndividual(1);
      System.out.println("roll individual (0): " + marko);
      System.out.println("marko = marko? " + marko.isIdentical(marko));

      System.out.println("");
      System.out.println("bob test: ");
      System.out.println("bob: " + bob);
      System.out.println("sum: " + bob.sum());
      bob.rollIndividual(4);
      System.out.println("roll individual (0): " + bob);
      System.out.println("bob = tim? " + bob.isIdentical(tim));

      System.out.println("");
      System.out.println("tim test: ");
      System.out.println("tim: " + tim);
      System.out.println("sum: " + tim.sum());
      tim.rollIndividual(2);
      System.out.println("roll individual (0): " + marko);
      System.out.println("tim = richard? " + tim.isIdentical(richard));
      
      System.out.println("");
      System.out.println("richard test: ");
      System.out.println("richard: " + richard);
      System.out.println("sum: " + richard.sum());
      richard.rollIndividual(0);
      System.out.println("roll individual (0): " + richard);
      System.out.println("richard = steve? " + richard.isIdentical(steve));
   }

}