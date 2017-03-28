/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  Anna Pasano
 *  Date written  :  2017-03-22
 *  Description   :  This class provides a bunch of methods which may be useful for the SoccerSim class
 *                   for Homework 5.  
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-03-22   Anna Pasano  Ball methods 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Ball {
  /**
   *  Class field definintions go here
   */
   private static final double DEFAULT_TIMESLICE = 1;
   // private static final double MAXIMUM_FIELD_X_VALUE = 3960;
   // private static final double MAXIMUM_FIELD_Y_VALUE = 2520;
   private static final double DECREASE_IN_SPEED_PER_SECOND = .01;
   private static final double BALL_RADIUS = 4.45; 

   // private double timeSlice;
   private double xPos;
   private double yPos;
   private double xDir;
   private double yDir;
   // private double elapsedSeconds;
   private double radius; 



   /**
   *  Constructor goes here
   */
   public Ball(double xPos, double yPos, double xDir, double yDir) {
      this.xPos = xPos;
      this.yPos = yPos;
      this.xDir = xDir;
      this.yDir = yDir;

      radius = BALL_RADIUS;
   }

   public double getRadius() {
      return radius;
   }

   public double getX() {
      return xPos;
   }

   public double getY() {
      return yPos;
   }

   /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public void move() {
      // change position
      xPos += xDir;
      yPos += yDir;

      // decelerate
      xDir -= (xDir * DECREASE_IN_SPEED_PER_SECOND);
      yDir -= (xDir * DECREASE_IN_SPEED_PER_SECOND);
   }
}







