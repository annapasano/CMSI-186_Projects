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
 *  @version 1.0.0  2017-03-22   Anna Pasano  Methods for SoccerSim and main 
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
   private double poleX;
   private double poleY;
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
      poleX = Math.random() * width;
      poleY = Math.random() * height;
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
         // check pole collision
         double distanceToPole = Math.sqrt( (Math.pow(poleX - balls[i].getX(),2)) + (Math.pow(poleY - balls[i].getY(),2)));
         if (distanceToPole <= balls[i].getRadius()) {
            return new Ball[] {balls[i]};
         }
      }
      return new Ball[] {};
   }

   public double getPoleX() {
      return poleX;
   }

   public double getPoleY() {
      return poleY;
   }

   // http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
   public static double round(double value, int places) {
      if (places < 0) throw new IllegalArgumentException();

      long factor = (long) Math.pow(10, places);
      value = value * factor;
      long tmp = Math.round(value);
      return (double) tmp / factor;
   }

   public static Ball[] handleArguments(String[] args) {
      Ball[] balls = new Ball[(int) args.length/4 ];
      for (int i = 0; i <= balls.length - 1 ; i++) {
         double x = Double.parseDouble(args[(i*4)]);
         double y = Double.parseDouble(args[(i*4)+1]);
         double dx = Double.parseDouble(args[(i*4)+2]);
         double dy = Double.parseDouble(args[(i*4)+3]);
         balls[i] = new Ball(x,y,dx,dy);
      }
      return balls;
   }

   public static void main(String[] args) {
      if (args.length % 4 == 1 && args.length >= 5) {
         // call handleArgs
         SoccerSim sim = new SoccerSim(handleArguments(args));
         
         // test initial placement
         // check initial collisions
         for (int i = 0; i <= sim.balls.length - 2; i++) {
            for (int j = i + 1; j <= sim.balls.length - 1; j++) {
               double distance = Math.sqrt( (Math.pow(sim.balls[j].getX() - sim.balls[i].getX(),2)) + (Math.pow(sim.balls[j].getY() - sim.balls[i].getY(),2)))/2;
               if (distance <= sim.balls[i].getRadius()) {
                  System.out.println("BALLS TOO CLOSE TOGETHER TO RUN SIMULATION");
                  return;
               }
            }
            // check initial pole collision
            double distanceToPole = Math.sqrt( (Math.pow(sim.getPoleX() - sim.balls[i].getX(),2)) + (Math.pow(sim.getPoleY() - sim.balls[i].getY(),2)));
            if (distanceToPole <= sim.balls[i].getRadius()) {
               System.out.println("BALL AND POLE TOO CLOSE TO RUN SIMULATION");
            }
         }


         // do sim
         // iterate through array and print out values of balls involved
         for (int time = 0; time < Double.parseDouble(args[args.length-1]); time++) {
            System.out.println(" TIME : " + time);
            for (int i = 0; i <= sim.balls.length-1; i++) {
               System.out.println (" BALL " + i + ": X: " + round(sim.balls[i].getX(),2) + " Y: " + round(sim.balls[i].getY(),2) + " dX: " + round(sim.balls[i].getXdir(),2) + " dY: " + round(sim.balls[i].getYdir(),2));
            }
            Ball[] collision = sim.tick();
            if (collision.length == 2) {
               double x = (collision[0].getX() + collision[1].getX())/2;
               double y = (collision[0].getY() + collision[1].getY())/2;
               System.out.println( " BALL COLLISION AT (" + round(x,2) + "," + round(y,2) + ") AT " + time + " SECONDS ");
               return;
            }
            if (collision.length == 1) {
               System.out.println( " COLLISION WITH POLE AT (" + round(sim.poleX,2) + "," + round(sim.poleY,2) + ") ");
               return;
            }
         }
         System.out.println( " NO COLLISION POSSIBLE ");
         return; 
      } else if (args.length == 0) {
         System.out.println(" USAGE: java SoccerSim ( [Ball X Pos] [Ball Y Pos] [Ball dX] [Ball dY] ) ... [SecondsToRun]");
      } else {
         System.out.println(" INVALID NUMBER OF ARGUMENTS ");
      }


   //    System.out.println("Tests");

   //    System.out.println("Test 1");
   //    Ball frodo = new Ball(0,0,10,10);
   //    Ball sam  = new Ball(100,100,-10,-10);

   //    SoccerSim gandalf = new SoccerSim(new Ball[] {frodo, sam});
      
   //    // sim
   //    for (int i = 0; i < 10; i++) {
   //       Ball[] collision = gandalf.tick();
   //       if (collision.length == 2) {
   //          System.out.println( " BALL COLLISION ");
   //          return;
   //       }
   //       if (collision.length == 1) {
   //          System.out.println( " COLLISION WITH POLE ");
   //          return;
   //       }
   //    }
   //    System.out.println( " NO COLLISION POSSIBLE ");
   //    return; 
   // }


   //    System.out.println("Test 2");
   //    Ball legolas = new Ball(10, 10, 5, 5);
   //    Ball aragorn  = new Ball(20,20,-5,-5);

   //    SoccerSim bilbo = new SoccerSim(new Ball[] {legolas, aragorn});
      
   //    // sim
   //    for (int i = 0; i < 10; i++) {
   //       Ball[] collision = bilbo.tick();
   //       if (collision.length == 2) {
   //          System.out.println( " BALL COLLISION ");
   //          return;
   //       }
   //       if (collision.length == 1) {
   //          System.out.println( " COLLISION WITH POLE ");
   //          return;
   //       }
   //    }
   //    System.out.println( " NO COLLISION POSSIBLE ");
   //    return; 
   // }
   //    System.out.println("Test 3");
   //    Ball galadriel = new Ball(2468, 2000, 456, 98);
   //    Ball arwen  = new Ball(3000, 1357, -12 , - 47);
   //    Ball gollum = new Ball(580, 890, 24, -59);


   //    SoccerSim elrond = new SoccerSim(new Ball[] {galadriel, arwen, gollum});
      
   //    // sim
   //    for (int i = 0; i < 20; i++) {
   //       Ball[] collision = elrond.tick();
   //       if (collision.length == 2) {
   //          System.out.println( " BALL COLLISION ");
   //          return;
   //       }
   //       if (collision.length == 1) {
   //          System.out.println( " COLLISION WITH POLE ");
   //          return;
   //       }
   //    }
   //    System.out.println( " NO COLLISION POSSIBLE ");
   //    return; 
   }
}

