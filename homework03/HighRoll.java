/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  HighRoll.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  Anna Pasano
 *  Date          :  2017-02-20
 *  Description   :  This class provides everything needed to play a game of High Roller.  The
 *                   idea here is to have an implementing class that uses the DieSet.java class. 
 *  Notes         :  
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-21  Anna Pasano   Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.*;
import java.util.Scanner;

public class HighRoll {

   public static void main(String[] args) {
      int highscore = 0;
      DiceSet ds = createDiceSetPrompt();
      prompt(ds,highscore);
   }

   public static void test(int a) {
      System.out.println(a);
   }

   public static DiceSet createDiceSetPrompt(){
      Scanner sc = new Scanner(System.in);
      try{
         System.out.println("How many dice would you like?");
         int count = sc.nextInt();
         System.out.println("How many sides would you like the dice to have?");
         int sides = sc.nextInt();
         return new DiceSet (count, sides);
      } catch (InputMismatchException e) {
         System.out.println("Not a Valid Number");
         createDiceSetPrompt();
      } catch (IllegalArgumentException e) {
         System.out.println("Count must be a natural number and there must be greater than 4 sides.");
         createDiceSetPrompt();
      }
      return null;
   }

   public static void doOption( DiceSet ds, int highscore, int option) {
      Scanner sc = new Scanner(System.in);
      if (option > 6 || option < 1 ) {
         System.out.println( "Chose a valid Option");
         prompt(ds,highscore); 
      } else {
         if (option == 1 ) {
            ds.roll();
            System.out.println("Rolling Dice...");
            System.out.println(ds);
            prompt(ds,highscore);
         } else if (option == 2) {
            System.out.println("Which die index do we roll?");
            try { 
               int i = sc.nextInt(); 
               ds.rollIndividual(i);
            }
            catch (InputMismatchException e) {
               System.out.println("Not a valid index.");
               prompt(ds,highscore);
            } catch (IllegalArgumentException e) {
               System.out.println("Not a valid index.");
               prompt(ds,highscore);
            }
            System.out.println(ds);
            prompt(ds,highscore);
         } else if (option == 3) {
            System.out.println(ds+" sum: "+ds.sum());
            prompt(ds,highscore);
         } else if (option == 4) {
            highscore = (ds.sum () > highscore) ? ds.sum() : highscore;
            System.out.println("score saved!");
            prompt(ds,highscore);
         } else if (option == 5) {
            System.out.println(" highscore: " + highscore);
            prompt(ds,highscore);
         } else if (option == 6) {
            System.out.println("Enter 'Q' to quit.");
            if ("Q".equals(sc.nextLine())) {
               return;
            } else {
               prompt(ds,highscore);
            }
         } else {
            System.out.println(" Incorrect input");
            prompt(ds,highscore);
         }
      }
   }

   public static void prompt(DiceSet ds, int highscore) {
      Scanner sc = new Scanner(System.in);
      System.out.println("Option 1: ROLL ALL THE DICE");
      System.out.println("Option 2: ROLL A SINGLE DIE");
      System.out.println("Option 3: CALCULATE THE SCORE FOR THIS SET");
      System.out.println("Option 4: SAVE THIS SCORE AS HIGH SCORE");
      System.out.println("Option 5: DISPLAY THE HIGH SCORE");
      System.out.println("Option 6: ENTER 'Q' TO QUIT THE PROGRAM");

      try { 
         int i = sc.nextInt(); 
         doOption(ds,highscore, i);
      }
      catch (InputMismatchException e) {
         System.out.println("Not a Number");
         prompt(ds,highscore);
      }
   }
}



