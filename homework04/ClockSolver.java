/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
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
 *  @version 1.0.0  2017-03-13  Anna Pasano    Methods and main
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class ClockSolver {
  /**
   *  Class field definintions go here
   */
   private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private final double DEFAULT_TIME_SLICE_SECONDS = 60.0;
   private final double EPSILON_VALUE              = 0.1;      // small value for double-precision comparisons

   public double angle = 0.0;
   public double timeslice = DEFAULT_TIME_SLICE_SECONDS;

  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
   public ClockSolver() {
      super();
   }

  /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   */
   public void handleInitialArguments( String args[] ) {
     // args[0] specifies the angle for which you are looking
     //  your simulation will find all the angles in the 12-hour day at which those angles occur
     // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds
     // you may want to consider using args[2] for an "angle window"

      // System.out.println( "\n   Hello world, from the ClockSolver program!!\n\n" ) ;
      if( 0 == args.length ) {
         System.out.println( "   Sorry you must enter at least one argument\n" +
                             "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                             "   Please try again..........." );
         System.exit( 1 );
      } else if (args.length == 1) {
         try {
            angle = Clock.validateAngleArg(args[0]);
         } catch (NumberFormatException e) {
            System.out.println("Not a valid angle.");
            System.exit( 1 );
         } catch (IllegalArgumentException e) {
            System.out.println("Not a number.");
            System.exit( 1 );
         }

      } else {
         // angle = Double.parseDouble(args[0])
         // timeslice = Double.parseDouble(args[1])
         try {
            angle = Clock.validateAngleArg(args[0]);
            timeslice = Clock.validateTimeSliceArg(args[1]); 
         } catch (NumberFormatException e) {
            System.out.println("Not a valid angle or timeslice.");
            System.exit( 1 );
         } catch (IllegalArgumentException e) {
            System.out.println("Not a number.");
            System.exit( 1 );
         }
         if (timeslice == -1.0 || timeslice > MAX_TIME_SLICE_IN_SECONDS) {
            System.out.print("Requested timeslice outside limits.");
            System.exit( 1 );
         }
      }
   }

   public static void test(Clock clk, double ang) {
      while( clk.getTotalSeconds() < 43200 ) {
         if (Math.abs(clk.getHandAngle() - ang)  < 2.5 || Math.abs(360.0 - clk.getHandAngle() - ang) < 2.5) {
            System.out.println(clk.toString());
         }
         clk.tick();
      }
   }
  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   */
   public static void main( String args[] ) {
      ClockSolver cse = new ClockSolver();
      cse.handleInitialArguments( args );
      Clock rick = new Clock(cse.timeslice);
      while( rick.getTotalSeconds() < 43200 ) {
         if (Math.abs(rick.getHandAngle() - cse.angle)  < 2.5 || Math.abs(360.0 - rick.getHandAngle() - cse.angle) < 2.5) {
            System.out.println(rick.toString());
         }
         rick.tick();
      }

      System.out.println("\n\n~~~~Extra Tests~~~~");
      System.out.println("\nTest 1:\nAngle: 30.0 ... TimeSlice: 10.0");
      test(new Clock(10), 30.0);
      System.out.println("\nTest 2:\nAngle: 135 ... TimeSlice: 22.2");
      test(new Clock(22.2), 135);
      System.out.println("\nTest 3:\nAngle: 17 ... TimeSlice: 0.0625 ");
      test(new Clock(0.0625), 17 );
      System.out.println("\nTest 4:\nAngle: 45.33 ... TimeSlice: 3.00");
      test(new Clock(3.00), 45.33);
      System.out.println("\nTest 5:\nAngle: 98.7 ... TimeSlice: 987.6");
      test(new Clock(987.6), 98.7);
      System.out.println("\nTest 6:\nAngle: 96.69 ... TimeSlice: 22.2");
      test(new Clock(22.2), 96.69);
      System.out.println("\nTest 7:\nAngle: 34 ... TimeSlice: 34.43");
      test(new Clock(34.43), 34);
      try {
         Clock.validateAngleArg("-.001");
      } catch (NumberFormatException e) {
         System.out.println("-.001 is Not a valid angle.");
      }
      if (Clock.validateTimeSliceArg("1891") == -1.0) {
         System.out.println("1891 is Not a valid timeslice.");
      }
      try {
         Clock.validateAngleArg("470");
      } catch (NumberFormatException e) {
         System.out.println("470 is Not a valid angle.");
      }
      // if (Clock.validateTimeSliceArg("x") == -1.0) {
      //    System.out.println("x is Not a valid timeslice.");
      // }
      // try {
      //    Clock.validateAngleArg("z");
      // } catch (NumberFormatException e) {
      //    System.out.println("z is Not a valid angle.");
      // }
      cse.handleInitialArguments( new String[] {"10","x"} );
       System.exit( 0 );
   }
}