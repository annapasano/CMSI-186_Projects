/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  StringStuffTester.java
 *  Purpose       :  A test harness file for testing out the methods in the "StringStuff.java" class
 *  Author        :  B.J. Johnson
 *  Date          :  2017-01-25
 *  Description   :  This file provides the "test harness" for checking out the methods which are part of
 *                   the homework02 assignment.  It also provides examples of proper documentation, and
 *                   uses the source file header template as specified in the "Greeter.java" template file
 *                   for use in CMSI 186, Spring 2017.
 *  Notes         :  TEMPLATE FILE ONLY: Your job is to fill in as many test cases as you think are needed
 *                   to thoroughly and completely test your StringStuff class.
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-25  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class StringStuffTester {

  /**
   * the main method which calls all of the test methods in the class
   * @param args String[] array containing command line parameters
   * @return void
   */
   public static void main ( String [] args ) {

      test_containsVowel();      // 5 tests
      test_isPalindrome();       // 5 tests
      test_evensOnly();          // 5 tests
      test_oddsOnly();           // 5 tests
      test_evensOnlyNoDupes();   // 5 tests
      test_oddsOnlyNoDupes();    // 5 tests
      test_reverse();            // 5 tests
   }

  /**
   * test method to test out the operation of the containsVowel method
   */
   public static void test_containsVowel() {

      System.out.println( "\nFIVE TESTS FOR containsVowel():" );

      System.out.print( "   Test for Australia (String): " );
      try { System.out.println( StringStuff.containsVowel( "Australia" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for diefied (String): " );
      try { System.out.println( StringStuff.containsVowel( "Diefied" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for ANNA (int) : " );
      try { System.out.println( StringStuff.containsVowel( "AnnA" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for FanDanGo  (int) : " );
      try { System.out.println( StringStuff.containsVowel( "FanDanGo" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for (int) : " );
      try { System.out.println( StringStuff.containsVowel( "mxsnclr" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }
   }


  /**
   * test method to test out the operation of the isPalindrome method
   */
   public static void test_isPalindrome() {

    System.out.println( "\nFIVE TESTS FOR isPalindrome():" );

      System.out.print( "   Test for Australia (String): " );
      try { System.out.println( StringStuff.isPalindrome( "Australia" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for diefied (String): " );
      try { System.out.println( StringStuff.isPalindrome( "diefied" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for ANNA (String): " );
      try { System.out.println( StringStuff.isPalindrome( "ANNA" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for FanDanGo (String): " );
      try { System.out.println( StringStuff.isPalindrome( "FanDanGo" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for mxsnclr (String): " );
      try { System.out.println( StringStuff.isPalindrome( "mxsnclr" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }
   }


  /**
   * test method to test out the operation of the evensOnly method
   */
   public static void test_evensOnly() {

    System.out.println( "\nFIVE TESTS FOR evensOnly():" );

    System.out.print( "   Test for Australia (String): " );
      try { System.out.println( StringStuff.evensOnly( "Australia" ).equals("THR") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for diefied (String): " );
      try { System.out.println( StringStuff.evensOnly( "diefied" ).equals("DFD") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for ANNA (String): " );
      try { System.out.println( StringStuff.evensOnly( "ANNA" ).equals("NN") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for FanDanGo (String): " );
      try { System.out.println( StringStuff.evensOnly( "FanDanGo" ).equals("FNDN") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for mxsnclr (String): " );
      try { System.out.println( StringStuff.evensOnly( "mxsnclr" ).equals("XNLR") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }
   }


  /**
   * test method to test out the operation of the oddsOnly method
   */
   public static void test_oddsOnly() {
    System.out.println( "\nFIVE TESTS FOR oddsOnly():" );

    System.out.print( "   Test for Australia (String): " );
      try { System.out.println( StringStuff.oddsOnly( "Australia" ).equals("AUSAIA") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for diefied (String): " );
      try { System.out.println( StringStuff.oddsOnly( "diefied" ).equals("IEIE") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for ANNA (String): " );
      try { System.out.println( StringStuff.oddsOnly( "ANNA" ).equals("AA") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for FanDanGo (String): " );
      try { System.out.println( StringStuff.oddsOnly( "FanDanGo" ).equals("ADNO") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for mxsnclr (String): " );
      try { System.out.println( StringStuff.oddsOnly( "mxsnclr" ).equals("MSC") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }
   }

  /**
   * test method to test out the operation of the evensOnlyNoDupes method
   */
   public static void test_evensOnlyNoDupes() {
    System.out.println( "\nFIVE TESTS FOR evensOnlyNoDupes():" );

    System.out.print( "   Test for Australia (String): " );
      try { System.out.println( StringStuff.evensOnlyNoDupes( "Australia" ).equals("THR") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for diefied (String): " );
      try { System.out.println( StringStuff.evensOnlyNoDupes( "diefied" ).equals("DFD") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for ANNA (String): " );
      try { System.out.println( StringStuff.evensOnlyNoDupes( "ANNA" ).equals("NN") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for FanDanGo (String): " );
      try { System.out.println( StringStuff.evensOnlyNoDupes( "FanDanGo" ).equals("FNDN") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for mxsnclr (String): " );
      try { System.out.println( StringStuff.evensOnlyNoDupes( "mxsnclr" ).equals("XNLR") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }
   }

  /**
   * test method to test out the operation of the oddsOnlyNoDupes method
   */
   public static void test_oddsOnlyNoDupes() {
    System.out.println( "\nFIVE TESTS FOR oddsOnlyNoDupes():" );

    System.out.print( "   Test for Australia (String): " );
      try { System.out.println( StringStuff.oddsOnlyNoDupes( "Australia" ).equals("AUSAIA") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for diefied (String): " );
      try { System.out.println( StringStuff.oddsOnlyNoDupes( "diefied" ).equals("IEIE") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for ANNA (String): " );
      try { System.out.println( StringStuff.oddsOnlyNoDupes( "ANNA" ).equals("AA") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for FanDanGo (String): " );
      try { System.out.println( StringStuff.oddsOnlyNoDupes( "FanDanGo" ).equals("ADNO") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for mxsnclr (String): " );
      try { System.out.println( StringStuff.oddsOnlyNoDupes( "mxsnclr" ).equals("MSC") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }
   }

  /**
   * test method to test out the operation of the reverse method
   */
   public static void test_reverse() {
    System.out.println( "\nFIVE TESTS FOR reverse():" );

    System.out.print( "   Test for Australia (String): " );
      try { System.out.println( StringStuff.reverse( "Australia" ).equals("ailartsuA") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for diefied (String): " );
      try { System.out.println( StringStuff.reverse( "diefied" ).equals("diefied") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for ANNA (String): " );
      try { System.out.println( StringStuff.reverse( "ANNA" ).equals("AnnA") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for FanDanGo (String): " );
      try { System.out.println( StringStuff.reverse( "FanDanGo" ).equals("oGnaDnaF") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for mxsnclr (String): " );
      try { System.out.println( StringStuff.reverse( "mxsnclr" ).equals("rlcnsxm") ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }
   }

}