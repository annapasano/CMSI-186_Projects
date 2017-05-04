/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  AnnaCustomChangeMakerTestHarness.java
 * Purpose    :  Main program for the ChangeMaker class
 * @author    :  Anna Pasano
 * Date       :  2017-05-04
 * Description:  This program provides an additional custom test harness for running tests to verify correct operaion of the
 *                "ChangeMaker.java" class.  This class is intended to be used as part of homework 7, the
 *                coin changemaker, which is a "Dynamic Programming" algorithm.
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  
 *  1.1.0  2017-05-04  Anna Pasano  Added a few of my own more test cases using denominations of other countries
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class AnnaCustomChangeMakerTestHarness {

   public static final int MAX_DENOM_VALUE = 220;

   private static int attempts = 0;
   private static int successes = 0;
   private static int testCount = 0;

   private static ArrayList<Integer> list = new ArrayList<Integer>();

  /**
   *  main method just calls all the individual test methods
   *   test_thisthing(), test_thatthing(), test_theotherthing() and so on
   *   each test_whatever handles its own output and keeps adding to the pass/fail
   *   at the end, main prints out a tally of how many tests run/passed
   */
   public static void main( String[] args ) {
      attempts  = 0;
      successes = 0;

      System.out.println( "\n\n  TESTING CHANGEMAKER PROGRAM FUNCTIONALITY" +
                          "\n  =========================================" );

      test_PolishZloty();      // 2,5,20,50
      test_IndianRupee();      // 10,20,50,100
      test_BulgarianLev();     // 2,3,5,10


      System.out.println( "creating random tuple: " + randomTuple( 7, true ) );
      System.out.println( "creating random tuple: " + randomTuple( 7, false ) );
      test_BogusDenomintaions1();
      test_BogusDenomintaions2();
      test_BogusDenomintaions3();

      System.out.println( "\n\nResults:\n      " + successes + "/" + attempts + " tests passed." );
   }

  /**
   *  method to display a success or failure
   *  @param  value  boolean value of something that evaluates success/failure
   *    note that this can be any expression, including a test or method, which is why it's here
   *    note that this method also increments the attempts and tracks the number of successes
   *    there is no returned value, only a print out line which displays the result
   */
   private static void displaySuccessIfTrue( boolean value ) {
      attempts++;
      successes += value ? 1 : 0;

      System.out.println(value ? "success" : "failure");
   }

  /**
   *  method to display a failure
   */
   private static void displayFailure() {
      displaySuccessIfTrue( false );
   }

  /**
   *  method to run tests on USA denominations of coins; we all know what they are...
   *   don't forget the 50 cent piece and the Sarah B. Anthony dollar [100-cent] coin!!
   */
   public static void test_PolishZloty() {

      int[] zlotyDenominations  = new int[] { 2, 5, 20, 50 };
      Tuple result = null;

      System.out.println( "\n\n  TESTING TO FIND OPTIMAL SOLUTION TO MAKING 154 cents USING Polish Zloty" );
      System.out.println( "  ==================================================================" );
      System.out.println( "\n    Test" + makeTwoDigits() + ": testing optimal solution for 154 cents: " );

      result = ChangeMaker.makeChangeWithDynamicProgramming( zlotyDenominations, 154 );

      try {
        System.out.print( "      expecting Tuple: <2,0,0,3>: " );
        displaySuccessIfTrue( "<2,0,0,3>".equals( result.toString() ) ) ;
      } catch (Exception e) {
         e.printStackTrace();
         displayFailure();
      }

      System.out.println( "\n    Test" + makeTwoDigits() + ": testing that result using getElement(0): " );
      try {
         System.out.print( "      expecting  2 2-cent coins: " );
         displaySuccessIfTrue( 2 == result.getElement(0));
      } catch (Exception e) {
         e.printStackTrace();
         displayFailure();
      }

      System.out.println( "\n    Test" + makeTwoDigits() + ": testing that result using getElement(1): " );
      try {
         System.out.print( "      expecting 0 5-cent coins: " );
         displaySuccessIfTrue( 0 == result.getElement(1));
      } catch (Exception e) {
         e.printStackTrace();
         displayFailure();
      }

      System.out.println( "\n    Test" + makeTwoDigits() + ": testing that result using getElement(2): " );
      try {
         System.out.print( "      expecting 0  20-cent coins: " );
         displaySuccessIfTrue( 0 == result.getElement(2));
      } catch (Exception e) {
         e.printStackTrace();
         displayFailure();
      }

      System.out.println( "\n    Test" + makeTwoDigits() + ": testing that result using getElement(3): " );
      try {
         System.out.print( "      expecting 3  50-cent coins: " );
         displaySuccessIfTrue( 3 == result.getElement(3));
      } catch (Exception e) {
         e.printStackTrace();
         displayFailure();
      }

      System.out.println( "\n\n  TESTING TO FIND OPTIMAL SOLUTION USING NON-STANDARD DENOMINATIONS" );
      System.out.println( "  =================================================================" );
      int[] newDenominations  = new int[] { 156, 6, 18};
      System.out.println( "\n    Test" + makeTwoDigits() + ": testing optimal solution for 394 cents using " + Arrays.toString( newDenominations ) + ": " );
      result = ChangeMaker.makeChangeWithDynamicProgramming( newDenominations, 394 );
      try {
         System.out.print( "      expecting Tuple: Impossible tuple: " );
         displaySuccessIfTrue( "Impossible tuple".equals( result.toString() ) );
      } catch (Exception e) {
         e.printStackTrace();
         displayFailure();
      }

      newDenominations  = new int[] { 55, 1, 33, 11 };
      System.out.println( "\n    Test" + makeTwoDigits() + ": testing optimal solution for 777 cents using " + Arrays.toString( newDenominations ) + ": " );
      result = ChangeMaker.makeChangeWithDynamicProgramming( newDenominations, 777 );
      try {
         System.out.print( "      expecting Tuple: <14,7,0,0>: " );
         displaySuccessIfTrue( "<14,7,0,0>".equals( result.toString() ) );
      } catch (Exception e) {
         e.printStackTrace();
         displayFailure();
      }

      newDenominations  = new int[] { 16,88,24,3 };
      System.out.println( "\n    Test" + makeTwoDigits() + ": testing optimal solution for 777 cents using " + Arrays.toString( newDenominations ) + ": " );
      result = ChangeMaker.makeChangeWithDynamicProgramming( newDenominations, 777 );
      try {
         System.out.print( "      expecting Tuple: <1,8,2,3>: " );
         displaySuccessIfTrue( "<1,8,2,3>".equals( result.toString() ) );
      } catch (Exception e) {
         e.printStackTrace();
         displayFailure();
      }

   }

   public static void test_IndianRupee() {

      int[] rupeeDenominations  = new int[] { 10, 20, 50, 100 };
      Tuple result = null;

      System.out.println( "\n\n  TESTING TO FIND OPTIMAL SOLUTION TO MAKING 444 cents USING Indian Rupeees" );
      System.out.println( "  ===============================================================" );

      System.out.println( "\n    Test" + makeTwoDigits() + ": testing optimal solution for 444 cents: " );
      result = ChangeMaker.makeChangeWithDynamicProgramming( rupeeDenominations, 444 );
      try {
        System.out.print( "      expecting Tuple: Impossible tuple: " );
        displaySuccessIfTrue( "Impossible tuple".equals( result.toString() ) ) ;
      } catch (Exception e) {
         e.printStackTrace();
         displayFailure();
      }

      System.out.println( "\n    Test" + makeTwoDigits() + ": testing optimal solution for 560 cents: " );
      result = ChangeMaker.makeChangeWithDynamicProgramming( rupeeDenominations, 560 );
      try {
        System.out.print( "      expecting Tuple: <1,0,1,5>: " );
        displaySuccessIfTrue( "<1,0,1,5>".equals( result.toString() ) ) ;
      } catch (Exception e) {
         e.printStackTrace();
         displayFailure();
      }

      System.out.println( "\n    Test" + makeTwoDigits() + ": testing optimal solution for 280 cents: " );
      result = ChangeMaker.makeChangeWithDynamicProgramming( rupeeDenominations, 280 );
      try {
        System.out.print( "      expecting Tuple: <1,1,1,2>: " );
        displaySuccessIfTrue( "<1,1,1,2>".equals( result.toString() ) ) ;
      } catch (Exception e) {
         e.printStackTrace();
         displayFailure();
      }

   }

   public static void test_BulgarianLev() {

      int[] levDenominations  = new int[] { 2, 3, 5, 10 };
      Tuple result = null;

      System.out.println( "\n\n  TESTING TO FIND OPTIMAL SOLUTION TO MAKING 99 cents USING BulgarianLev" );
      System.out.println( "  ================================================================" );

      System.out.println( "\n    Test" + makeTwoDigits() + ": testing optimal solution for 76 cents: " );
      result = ChangeMaker.makeChangeWithDynamicProgramming( levDenominations, 76 );
      try {
        System.out.print( "      expecting Tuple: <0,2,0,7> " );
        displaySuccessIfTrue( "<0,2,0,7>".equals( result.toString() ) ) ;
      } catch (Exception e) {
         e.printStackTrace();
         displayFailure();
      }

      System.out.println( "\n    Test" + makeTwoDigits() + ": testing optimal solution for 54 cents: " );
      result = ChangeMaker.makeChangeWithDynamicProgramming( levDenominations, 54 );
      try {
        System.out.print( "      expecting Tuple: <2,0,0,5>: " );
        displaySuccessIfTrue( "<2,0,0,5>".equals( result.toString() ) ) ;
      } catch (Exception e) {
         e.printStackTrace();
         displayFailure();
      }

      System.out.println( "\n    Test" + makeTwoDigits() + ": testing optimal solution for 169 cents: " );
      result = ChangeMaker.makeChangeWithDynamicProgramming( levDenominations, 169 );
      try {
        System.out.print( "      expecting Tuple: <2,0,1,16>: " );
        displaySuccessIfTrue( "<2,0,1,16>".equals( result.toString() ) ) ;
      } catch (Exception e) {
         e.printStackTrace();
         displayFailure();
      }

      System.out.println( "\n    Test" + makeTwoDigits() + ": testing optimal solution for 193 cents: " );
      result = ChangeMaker.makeChangeWithDynamicProgramming( levDenominations, 193 );
      try {
        System.out.print( "      expecting Tuple: <0,1,0,19>: " );
        displaySuccessIfTrue( "<0,1,0,19>".equals( result.toString() ) ) ;
      } catch (Exception e) {
         e.printStackTrace();
         displayFailure();
      }

   }


  /**
   *  silly little method to add zeros to the front of a number string
   *    to ensure it fills two places for test output alignment
   *  @return two-character string that is a two-place number from 00 to 99
   *  note: this method also increments the testCount private field
   */
   private static String makeTwoDigits() {
      testCount++;
      if( testCount < 10 ) {
         return new String( "0" + testCount );
      } else {
         return new Integer( testCount ).toString();
      }
   }

  /**
   *  method to generate a random Tuple containing a random number of values
   *  @param elementCount integer number of elements in the Tuple
   *  @param oneAllowed   boolean value which is true if the number "1" is allowed in the values
   *  Note that making change is more difficult [less possible] without a "1" in the list
   *  @return  a Tuple containing 'elementCount' of the random values ranging no higher than MAX_DENOM_VALUE
   */
   public static Tuple randomTuple( int elementCount, boolean oneAllowed ) {
      int start = oneAllowed ? 2 : 1;
      int[] myDenoms = new int[ elementCount ];
      for( int i = start; i <= MAX_DENOM_VALUE; i++ ) {
         list.add( new Integer( i ) );
      }
      Collections.shuffle( list );
      for( int i = 0; i < myDenoms.length; i++ ) {
         myDenoms[i] = list.get( i );
      }
      Tuple rwoDenominations = new Tuple( myDenoms );
      return rwoDenominations;
   }

  /**
   * method to test a bogus set of denominations which includes a negative amount
   */
   public static void test_BogusDenomintaions1() {
      System.out.println( "\n\n  TESTING TO RANDOM TUPLE GENERATION FOR THREE BOGUS DENOMINATION SETS" );
      System.out.println( "  ====================================================================" );

      int[] badDenominations = new int[] { 1, 5, -66, 9, 34 };
      System.out.println( "\n    Test " + makeTwoDigits() + ": testing first list of bogus denomintaions, containing a negative......" );
      System.out.println( "          expecting: IMPOSSIBLE " );
      System.out.print  ( "            and got: " );
      try {
         Tuple result = ChangeMaker.makeChangeWithDynamicProgramming( badDenominations, 8461 );
         displaySuccessIfTrue( "Impossible tuple".equals( result.toString() ) ) ;
      }
      catch( Exception e ) {
         displaySuccessIfTrue( false );
         e.printStackTrace();
         displayFailure();
      }
   }

  /**
   * method to test a bogus set of denominations which includes a zero
   */
   public static void test_BogusDenomintaions2() {
      int[] badDenominations = new int[] {93,12,0,15 };
      System.out.println( "\n    Test " + makeTwoDigits() + ": testing second list of bogus denomintaions, containing a zero......" );
      System.out.println( "          expecting: IMPOSSIBLE " );
      System.out.print  ( "            and got: " );
      try {
         Tuple result = ChangeMaker.makeChangeWithDynamicProgramming( badDenominations, 378 );
         displaySuccessIfTrue( "Impossible tuple".equals( result.toString() ) ) ;
      }
      catch( Exception e ) {
         displaySuccessIfTrue( false );
         e.printStackTrace();
         displayFailure();
      }
   }

  /**
   * method to test a bogus set of denominations which includes repeats
   */
   public static void test_BogusDenomintaions3() {
      int[] badDenominations = new int[] { 6,14,15,33,87,14 };
      System.out.println( "\n    Test " + makeTwoDigits() + ": testing third list of bogus denomintaions, containing repeats......" );
      System.out.println( "          expecting: IMPOSSIBLE " );
      System.out.print  ( "            and got: " );
      try {
         Tuple result = ChangeMaker.makeChangeWithDynamicProgramming( badDenominations, 4892 );
         displaySuccessIfTrue( "Impossible tuple".equals( result.toString() ) ) ;
      }
      catch( Exception e ) {
         displaySuccessIfTrue( false );
         e.printStackTrace();
         displayFailure();
      }
   }

}