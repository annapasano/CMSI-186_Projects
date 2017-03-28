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

public class SoccerSim {
  /**
   *  Class field definintions go here
   */
   private static final double MAXIMUM_FIELD_X_VALUE = 3960;
   private static final double MAXIMUM_FIELD_Y_VALUE = 2520;
   private static final double DEFAULT_TIMESLICE = 1;

   private double timeSlice;
   private double elapsedSeconds;
   private double height;
   private double width;
   private Ball[] balls;

   /**
   *  Constructor goes here
   */
   public SoccerSim(Ball[] balls) {
      height = MAXIMUM_FIELD_Y_VALUE;
      width = MAXIMUM_FIELD_X_VALUE;
      timeSlice = DEFAULT_TIMESLICE;
      elapsedSeconds = 0;
      this.balls = balls;
   }

   public Ball[] tick() {
      // move balls
      for (int i = 0; i <= balls.length - 1; i++) {
         balls[i].move();
      }

      // check collisions
      for (int i = 0; i <= balls.length - 2; i++) {
         for (int j = i + 1; j <= balls.length - 1; j++) {
            double distance = Math.sqrt( (Math.pow(balls[j].getX() - balls[i].getX(),2)) + (Math.pow(balls[j].getY() - balls[i].getY(),2)))/2;
            if (distance <= balls[i].getRadius()) {
               return new Ball[] {balls[i],balls[j]};
            }
         }
      }
      return new Ball[] {};
   }

   public static void main(String[] args) {
      System.out.println("Tests");
      Ball peter = new Ball(0,0,10,10);
      Ball jon = new Ball(100,100,-10,-10);
      SoccerSim bradley = new SoccerSim(new Ball[] {peter,jon});
      
      // sim
      for (int i = 0; i < 10; i++) {
         Ball[] collision = bradley.tick();
         if (collision.length != 0) {
            System.out.println("COLLISION");
            return;
         }
      }
   }

}







