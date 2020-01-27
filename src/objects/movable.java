package objects;

import java.awt.Color;
import java.awt.Graphics;

public abstract class movable {
	// 1. Declaration of Variables
	
		private double x;			//x-coordinate of the center of the movable
		private double y;			//y-coordinate of the center of the movable
		private double diameter;	//diameter of the movable
		private double radius;		//radius of the movable = diameter/2
		private Color color;		//color of the movable
		private double xSpeed;		//x-speed = change in x-position
		private double ySpeed;		//y-speed = change in y-position

		
		// 2. Create a default constructor
		/**
		 * Default Constructor
		 * Creates a red movable at (0, 0) with a diameter of 25.  
		 * The default speed is 0.
		 */
		public movable() {
			diameter = 25;
			radius = diameter/2;
			xSpeed = 0;
			ySpeed = 0;
			x = 100;
			y = 100;
			color = Color.green;		
		}

		// 3. Write a constructor that allows the user to input the parameters (x, y, diameter, Color)
		// and sets the x and y-speed = 0.  Comment with javadoc.
		public movable(double UserX, double UserY, double UserDiameter, Color UserColor) {
			diameter = UserDiameter;
			radius = diameter/2;
			xSpeed = 0;
			ySpeed = 0;
			x = UserX;
			y = UserY;
			color = UserColor;		
		}

		// 4. Create getters and setters for all private variables
		// Comment with javadoc
		/**
		 * Returns the y-coordinate of the 
		 * @return the y-coordinate of the 
		 */
		public double getY() {
			return y;
		}



		/**
		 * Returns the vertical speed (change in pixels)
		 * @return the vertical speed (change in pixels)
		 */
		public double getYSpeed() {
			return ySpeed;
		}



		/**
		 * Sets the y-coordinate of the 
		 * @param y the y-coordinate of the 
		 */
		public void setY(double y) {
			this.y = y;
		}


		/**
		 * Sets the vertical speed (change in pixels)
		 * @param ySpeed the vertical speed (change in pixels)
		 */
		public void setYSpeed(double ySpeed) {
			this.ySpeed = ySpeed;
		}

		/**
		 * Returns the x-coordinate of the 
		 * @return the x-coordinate of the 
		 */
		public double getX() {
			return x;
		}



		/**
		 * Returns the horizontal speed (change in pixels)
		 * @return the horizontal speed (change in pixels)
		 */
		public double getxSpeed() {
			return xSpeed;
		}



		/**
		 * Sets the x-coordinate of the 
		 * @param x the x-coordinate of the 
		 */
		public void setX(double x) {
			this.x = x;
		}


		/**
		 * Sets the horizontal speed (change in pixels)
		 * @param xSpeed the horizontal speed (change in pixels)
		 */
		public void setXSpeed(double xSpeed) {
			this.xSpeed = xSpeed;
		}

		
		// 5. Finish the following methods
		// 6. Test using BouncingBallTester.java
		/**
		 * Comment...
		 * @param g
		 */
		public abstract void draw(Graphics g);
		
		/**
		 * Sets the center location of the movable
		 * @param x
		 * @param y
		 */
		public void setLocation(double UserX, double UserY) {
			x = UserX;
			y = UserY;
		}
		
		/**
		 * Sets the movable's color (if the sub class uses the color variable)
		 * @param userColor
		 */
		public void setColor(Color userColor) {
			color = userColor;
		}
		
		/**
		 * sets the Radius and the diameter based on the entered radius
		 * @param userRadius
		 */
		public void setRadius(Double userRadius) {
			radius = userRadius;
			diameter = 2*userRadius;
		}
		
		/**
		 * sets the diameter and radius based on the enter diameter
		 * @param userDiameter
		 */
		public void setDiameter(Double userDiameter) {
			diameter = userDiameter;
			radius = userDiameter/2;
		}
		
		/**
		 * returns the diameter as a double
		 * @return diameter as a double
		 */
		public double getDiameter() {
			return diameter;
		}
		
		/**
		 * returns the radius as a double
		 * @return radius as a double
		 */
		public double getRadius() {
			return radius;
		}
		
		/**
		 * returns the color of the movable
		 * @return color of movable
		 */
		public Color getColor() {
			return color;
		}
		
		
		/**
		 * Generates a speed between -<code>maxSpeed<code>
		 * and <code>maxSpeed<code>
		 * @param maxSpeed
		 */
		public void setRandomSpeed(double maxSpeed) {
			xSpeed = Math.random()*(maxSpeed*2)-maxSpeed;
			ySpeed = Math.random()*(maxSpeed*2)-maxSpeed;
		}

		
		//7. Write the move method to make the movable move around the screen
		// and bounce of the edges.
		/**
		 * Abstract for moving the movable in animations
		 * @param rightEdge right edge of window in pixels (note that it is a double), used to check if off screen
		 * @param bottomEdge bottom edge of window in pixels (note that it is a double), used to check if off screen
		 */
		public abstract void move(double rightEdge, double bottomEdge);

}
