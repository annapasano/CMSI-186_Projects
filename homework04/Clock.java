/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28   Anna Pasano  Clock methods and main
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Clock {
  /**
   *  Class field definintions go here
   */
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
   private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;

   private double timeSlice;
   private double hourHand;
   private double minuteHand;
   private double elapsedSeconds;

  /**
   *  Constructor goes here
   */
   public Clock() {
      this.timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
      this.hourHand = 0.0;
      this.minuteHand = 0.0;
      this.elapsedSeconds = 0.0;
   }

   /**
   *  Constructor goes here
   */
   public Clock(double timeSlice) {
      this.timeSlice = timeSlice;
      this.hourHand = 0.0;
      this.minuteHand = 0.0;
      this.elapsedSeconds = 0.0;
   }

  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick() {
      hourHand = hourHand + (HOUR_HAND_DEGREES_PER_SECOND * timeSlice);
      if (hourHand > 12) {
         hourHand = hourHand - 12;
      }
      minuteHand = minuteHand + (MINUTE_HAND_DEGREES_PER_SECOND * timeSlice);
      if (minuteHand >= MAXIMUM_DEGREE_VALUE) {
         minuteHand = minuteHand - MAXIMUM_DEGREE_VALUE; 
      }
      elapsedSeconds = elapsedSeconds + timeSlice; 
      return elapsedSeconds;
   }

  /**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException
   */
   public static double validateAngleArg( String argValue ) throws NumberFormatException {
      double val = Double.parseDouble(argValue);
      if (val > MAXIMUM_DEGREE_VALUE || val < 0.0) {
         throw new NumberFormatException();
      }
      return val;
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public static double validateTimeSliceArg( String argValue ) {
      double val = Double.parseDouble(argValue);
      if (val < 0.0001) {
         return INVALID_ARGUMENT_VALUE;
      }
      return val;
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
      return hourHand;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
      return minuteHand;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
      return Math.abs(hourHand - minuteHand);
   }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
      return elapsedSeconds;
   }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String toString() {
      int hour = (int) hourHand / 30;
      int min = (int) minuteHand / 6;
      double sec = elapsedSeconds % 60;
      return hour + ":" + min + ":" + sec;
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {

      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );
      Clock clock = new Clock();
      System.out.println( "    New clock created: " + clock.toString() );
      System.out.println( "    Testing validateAngleArg()....");
      System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      
      System.out.println(" ");   
      System.out.println("Creating a new clock: ");
      Clock john = new Clock(90.0);

      john.tick();
      System.out.println("Current John: " + john.toString());
      System.out.println("hour hand angle: " + john.getHourHandAngle());
      System.out.println("minute hand angle: " + john.getMinuteHandAngle());
      System.out.println("hand angle: " + john.getHandAngle());
      System.out.println("elapsed seconds: " + john.getTotalSeconds());

      System.out.println(" ");   
      System.out.println("Creating a new clock: ");
      Clock paul = new Clock(.0625);

      paul.tick();
      System.out.println("Current Paul: " + paul.toString());
      System.out.println("hour hand angle: " + paul.getHourHandAngle());
      System.out.println("minute hand angle: " + paul.getMinuteHandAngle());
      System.out.println("hand angle: " + paul.getHandAngle());
      System.out.println("elapsed seconds: " + paul.getTotalSeconds());

      System.out.println(" ");   
      System.out.println("Creating a new clock: ");
      Clock george = new Clock(43200);

      george.tick();
      System.out.println("Current George: " + george.toString());
      System.out.println("hour hand angle: " + george.getHourHandAngle());
      System.out.println("minute hand angle: " + george.getMinuteHandAngle());
      System.out.println("hand angle: " + george.getHandAngle());
      System.out.println("elapsed seconds: " + george.getTotalSeconds());

      System.out.println(" ");   
      System.out.println("Creating a new clock: ");
      Clock ringo = new Clock(86400);

      ringo.tick();
      System.out.println("Current Ringo: " + ringo.toString());
      System.out.println("hour hand angle: " + ringo.getHourHandAngle());
      System.out.println("minute hand angle: " + ringo.getMinuteHandAngle());
      System.out.println("hand angle: " + ringo.getHandAngle());
      System.out.println("elapsed seconds: " + ringo.getTotalSeconds());

      System.out.println(" ");   
      System.out.println("Creating a new clock: ");
      Clock mick = new Clock(0.00003);

      mick.tick();
      System.out.println("Current Mick : " + mick.toString());
      System.out.println("hour hand angle: " + mick.getHourHandAngle());
      System.out.println("minute hand angle: " + mick.getMinuteHandAngle());
      System.out.println("hand angle: " + mick.getHandAngle());
      System.out.println("elapsed seconds: " + mick.getTotalSeconds());

      System.out.println(" ");   
      System.out.println("Creating a new clock: ");
      Clock keith = new Clock(120960.0);

      keith.tick();
      System.out.println("Current Keith: " + keith.toString());
      System.out.println("hour hand angle: " + keith.getHourHandAngle());
      System.out.println("minute hand angle: " + keith.getMinuteHandAngle());
      System.out.println("hand angle: " + keith.getHandAngle());
      System.out.println("elapsed seconds: " + keith.getTotalSeconds());

      System.out.println(" ");   
      System.out.println("Creating a new clock: ");
      Clock stevie = new Clock(0.123456789012345);

      stevie.tick();
      System.out.println("Current Stevie: " + stevie.toString());
      System.out.println("hour hand angle: " + stevie.getHourHandAngle());
      System.out.println("minute hand angle: " + stevie.getMinuteHandAngle());
      System.out.println("hand angle: " + stevie.getHandAngle());
      System.out.println("elapsed seconds: " + stevie.getTotalSeconds());

      System.out.println(" ");   
      System.out.println("Creating a new clock: ");
      Clock freddie = new Clock(9876543210987.0);

      freddie.tick();
      System.out.println("Current Freddie: " + freddie.toString());
      System.out.println("hour hand angle: " + freddie.getHourHandAngle());
      System.out.println("minute hand angle: " + freddie.getMinuteHandAngle());
      System.out.println("hand angle: " + freddie.getHandAngle());
      System.out.println("elapsed seconds: " + freddie.getTotalSeconds());

      System.out.println(" ");   
      System.out.println("Creating a new clock: ");
      Clock neil = new Clock(1.0);

      neil.tick();
      System.out.println("Current Neil: " + neil.toString());
      System.out.println("hour hand angle: " + neil.getHourHandAngle());
      System.out.println("minute hand angle: " + neil.getMinuteHandAngle());
      System.out.println("hand angle: " + neil.getHandAngle());
      System.out.println("elapsed seconds: " + neil.getTotalSeconds());
   }
}