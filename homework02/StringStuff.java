/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  StringStuff.java
 *  Purpose       :  A file full of stuff to do with the Java String class
 *  Author        :  Anna Pasano
 *  Date          :  2017-02-06
 *  Description   :  This file presents a bunch of String-style helper methods.  Although pretty much
 *                   any and every thing you'd want to do with Strings is already made for you in the
 *                   Jave String class, this exercise gives you a chance to do it yourself [DIY] for some
 *                   of it and get some experience with designing code that you can then check out using
 *                   the real Java String methods [if you want]
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-19  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2017-02-06  Anna Pasano  Fill in methods to make the program actually work
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Set;
import java.util.LinkedHashSet;

public class StringStuff {

  /**
   * Method to determine if a string contains one of the vowels: A, E, I, O, U, and sometimes Y.
   * Both lower and upper case letters are handled.  In this case, the normal English rule for Y means
   * it gets included.
   *
   * @param s String containing the data to be checked for &quot;vowel-ness&quot;
   * @return  boolean which is true if there is a vowel, or false otherwise
   */
   public static boolean containsVowel( String s ) {
      String str = s.toUpperCase();
      for (int i = 0; i <= str.length() - 1; i++) {
         char temp = str.charAt(i);
         if (temp == 'A' || temp == 'E' || temp == 'I' || temp == 'O' || temp == 'U') {
            return true;
         }
      }
      return false;
   }

  /**
   * Method to determine if a string is a palindrome.  Does it the brute-force way, checking
   * the first and last, second and last-but-one, etc. against each other.  If something doesn't
   * match that way, returns false, otherwise returns true.
   *
   * @param s String containing the data to be checked for &quot;palindrome-ness&quot;
   * @return  boolean which is true if this a palindrome, or false otherwise
   */
   public static boolean isPalindrome( String s ) {
      String str = s.toUpperCase ();
      for (int i = 0; i <= str.length() /2; i++) {
         char firstChar = str.charAt (i);
         char lastChar = str.charAt(str.length() - 1 - i);
         if (firstChar != lastChar) {
            return false;            
         }
      }
      return true;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet.  The letters B, D, F, H, J, L, N, P, R, T, V, X, and Z are even,
   * corresponding to the numbers 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, and 26.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input
   */
   public static String evensOnly(String s ) {
      String str = s.toUpperCase();
      String evens = "";
      for (int i = 0; i <= str.length() - 1; i++) {
         char temp = str.charAt(i);
         if (temp == 'B' || temp == 'D' || temp == 'F' || temp == 'H' || temp == 'J'|| temp == 'L' || temp == 'N' || temp == 'P' || temp == 'R' || temp == 'T'|| temp == 'V' || temp == 'X' || temp == 'Z') {
            evens = evens + temp;
         }
      }
      return evens;
   }


  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet.  The letters A, C, E, G, I, K, M, O, Q, S, U, W, and Y are odd,
   * corresponding to the numbers 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, and 25.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input
   */
   public static String oddsOnly(String s ) {
      String str = s.toUpperCase();
      String odds = "";
      for (int i = 0; i <= str.length() - 1; i++) {
         char temp = str.charAt(i);
         if (temp == 'A' || temp == 'C' || temp == 'E' || temp == 'G' || temp == 'I'|| temp == 'K' || temp == 'M' || temp == 'O' || temp == 'Q' || temp == 'S'|| temp == 'U' || temp == 'W' || temp == 'Y') {
            odds = odds + temp;
         }
      }
      return odds;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input without duplicates
   */
   public static String evensOnlyNoDupes( String s ) {
      String str = s.toUpperCase();
      String evens = "";
      for (int i = 0; i <= str.length() - 1; i++) {
         char temp = str.charAt(i);
         if (temp == 'B' || temp == 'D' || temp == 'F' || temp == 'H' || temp == 'J'|| temp == 'L' || temp == 'N' || temp == 'P' || temp == 'R' || temp == 'T'|| temp == 'V' || temp == 'X' || temp == 'Z') {
            int j = 0;
            do{
               if (evens.length() > 0 && evens.charAt(j) == temp) {
                  break;
               }
               if (evens.length() == 0 || j == evens.length()-1) {
                  evens = evens + temp;     
               }
               j++;
            }while(j <= evens.length() - 1);
         }
      }
      return evens;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input without duplicates
   */
   public static String oddsOnlyNoDupes( String s ) {
      String str = s.toUpperCase();
      String odds = "";
      for (int i = 0; i <= str.length() - 1; i++) {
         char temp = str.charAt(i);
         if (temp == 'A' || temp == 'C' || temp == 'E' || temp == 'G' || temp == 'I'|| temp == 'K' || temp == 'M' || temp == 'O' || temp == 'Q' || temp == 'S'|| temp == 'U' || temp == 'W' || temp == 'Y') {
            int j = 0;
            do{
               if (odds.length() > 0 && odds.charAt(j) == temp) {
                  break;
               }
               if (odds.length() == 0 || j == odds.length()-1) {
                  odds = odds + temp;     
               }
               j++;
            }while(j <= odds.length() - 1);
         }
      }
      return odds;
   }

  /**
   * Method to return the reverse of a string passed as an argument
   *
   * @param s String containing the data to be reversed
   * @return  String containing the reverse of the input string
   */
   public static String reverse( String s ) {
      String rev = "";
      for (int i = s.length() - 1; i >= 0; i-- ) {
         rev = rev + s.charAt (i);
      }
      return rev;
   }

  /**
   * Main method to test the methods in this class
   *
   * @param args String array containing command line parameters
   */
   public static void main( String args[] ) {
      if (args.length == 0) {
         System.out.println("No word given");
         return;
      }
      String s = args[0];

      System.out.println("word: " + s);
      System.out.println("Contains Vowel: " + containsVowel(s));
      System.out.println("isPalindrome: " + isPalindrome(s));
      System.out.println("evensOnly: " + evensOnly(s));
      System.out.println(" oddsOnly: " + oddsOnly(s));
      System.out.println("evensOnlyNoDupes: " + evensOnlyNoDupes(s));
      System.out.println("oddsOnlyNoDupes: " + oddsOnlyNoDupes(s));
      System.out.println("reverse: " + reverse (s));
   }
}
