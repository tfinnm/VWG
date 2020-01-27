package objects;

import java.awt.Color;
import java.awt.Graphics;

import game.ObjectHandler;

public class projectile extends movable {

	//this variable marks the projectile for culling
	private boolean toRemove = false;
	
	/**
	 * Instantiates a new yellow projectile at the user defined X and Y with a user defined X and Y speed
	 * @param x is the x coordinate
	 * @param y is the y coordinate
	 * @param xSpeed is the horizontal speed
	 * @param ySpeed is the vertical speed
	 */
	public projectile(int x, int y, double xSpeed, double ySpeed) {
		super(x,y,10,Color.yellow);
		setXSpeed(xSpeed);
		System.out.println(xSpeed);
		setYSpeed(ySpeed);
		System.out.println(ySpeed);
	}
	
	/**
	 * removes a projectile if it has been marked to be culled
	 * @param i is the projectiles index in the array list
	 * @return if removed returns true
	 */
	public boolean remove(int i) {
		boolean removed = false;
		
		if (toRemove) {
			ObjectHandler.projectiles.remove(i);
			removed = true;
		}
		
		return removed;
		
	}
	
	/**
	 * sets the projectile to be culled
	 * @param tf should be true to remove a projectile, to cancel removal this should be false.
	 */
	public void setRemove(boolean tf) {
		toRemove = tf;
	}

	@Override
	/**
	 * draws a small yellow ball at the projectile's coordinates.
	 * @param g is the graphics renderer
	 */
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval((int)(getX()-getRadius()), (int)(getY()-getRadius()), (int)getDiameter(), (int)getDiameter());
	}

	@Override
	/**
	 * moves the projectile by it's X and Y speeds,
	 * if it hits a wall it is marked for culling then bounced to prevent errors.
	 * @param rightEdge is the width of the frame
	 * @param bottomEdge is the Height of the frame
	 */
	public void move(double rightEdge, double bottomEdge) {
		// TODO Auto-generated method stub
		setX(getX()+getxSpeed());
		setY(getY()+getYSpeed());
		if (getY()+getRadius() > bottomEdge) {
			setY(bottomEdge - getRadius());
			setYSpeed(-1*getYSpeed());
			setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
			toRemove = true;
		}
		if (getY()-getRadius() < 0) {
			setY(getRadius());
			setYSpeed(-1*getYSpeed());
			setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
			toRemove = true;
		}
		if (getX()+getRadius() > rightEdge) {
			setX(rightEdge - getRadius());
			setXSpeed(-1*getxSpeed());
			setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
			toRemove = true;
		}
		if (getX()-getRadius() < 0) {
			setX(getRadius());
			setXSpeed(-1*getxSpeed());
			setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
			toRemove = true;
		}
	}

	
}
