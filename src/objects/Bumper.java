package objects;

import java.awt.Color;
import java.awt.Graphics;

public class Bumper extends movable{

	private double height;
	private double width;

	/**
	 * Creates new bumper at 0,0 with color lime/green and a height and width of 50
	 */
	public Bumper() {
		super();
		height = 50;
		width = 50;
	}
	
	/**Creates a new bumper
	 * 
	 * @param x is x coordinate
	 * @param y is y coordinate
	 * @param userWidth is Width of bumper
	 * @param userHeight is Height of bumper
	 * @param userColor is the bumper's color
	 */
	public Bumper(double x, double y, double userWidth, double userHeight, Color userColor) {
		super(x,y,0,userColor);
		height = userHeight;
		width = userWidth;
	}
	
	/**
	 * Returns true if any part of the Ball is inside the bumper
	 * @param ball the Ball
	 * @return true if any part of the Ball is inside the bumper, false otherwise
	 */
	public boolean inBumper(movable ball) {
		for (int x = (int)(getX() - getWidth()/2); x <= (int)(getX() + getWidth()/2); x++) {
			for (int y = (int)(getY() - getHeight()/2); y <= (int)(getY() + getHeight()/2); y++) {
				if (getDistance(x, y, ball.getX(), ball.getY()) <= ball.getRadius()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**returns the bumper's height
	 * 
	 * @return bumper's height
	 */
	public double getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	/**returns the bumper's width
	 * 
	 * @return bumper's width
	 */
	public double getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	/**
	 * Calculates the distance between (x1, y1) and (x2, y2)
	 * @param x1 the x-coordinate of the first point
	 * @param y1 the y-coordinate of the first point
	 * @param x2 the x-coordinate of the second point
	 * @param y2 the y-coordinate of the second point
	 * @return the distance between (x1, y1) and (x2, y2)
	 */
	private double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	/**
	 * draws the bumper (rectangle)
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.fillRect((int)(getX()-(width/2)), (int)(getY()-(height/2)), (int)width, (int)height);
	}

	/**
	 * You cannot move a bumper with this.
	 * this is just an abstract method of movable.
	 */
	@Override
	public void move(double rightEdge, double bottomEdge) {
		System.out.println("no");
	}

	/**sets the bumper's width
	 * 
	 * @param inputWidth is the desire width for the bumper
	 */
	public void setWidth(int inputWidth) {
		width = inputWidth;
	}
	/**sets the bumper's height
	 * 
	 * @param inputWidth is the desired height for the bumper
	 */
	public void setHeight(int inputHeight) {
		height = inputHeight;
	}
	
	
}