package objects;

import java.awt.Color;
import java.awt.Graphics;

import game.GraphicsMLoop;
import game.ObjectHandler;

public class pylon extends movable{


	//these variable determine which team holds the point.
	private boolean control = false;
	private boolean friendlyControl = false;
	private boolean enemyControl = false;
	
	/**
	 * creates an uncontrolled pylon at a user defied coordinate.
	 * @param x is the pylon's X coordinate
	 * @param y is the pylon's Y coordinate
	 */
	public pylon(int x, int y) {
		setX(x);
		setY(y);
	}

	@Override
	/**
	 * a circle at the pylon's coordinate with another circle around it showing the pylon's claim radius.
	 * draws blue for friendly control, red for enemy, and white for neutral. outside line is always white.
	 * 
	 * @param g is the graphics renderer
	 */
	public void draw(Graphics g) {
		if (!control) {
			g.setColor(Color.WHITE);
			g.fillOval((int) (getX()-25), (int) (getY()-25), 50, 50);
			g.drawOval((int) (getX()-100), (int) (getY()-100), 200, 200);
		} else if (friendlyControl) {
			g.setColor(Color.BLUE);
			g.fillOval((int) (getX()-25), (int) (getY()-25), 50, 50);
			g.setColor(Color.WHITE);
			g.drawOval((int) (getX()-100), (int) (getY()-100), 200, 200);
		} else if (enemyControl) {
			g.setColor(Color.RED);
			g.fillOval((int) (getX()-25), (int) (getY()-25), 50, 50);
			g.setColor(Color.WHITE);
			g.drawOval((int) (getX()-100), (int) (getY()-100), 200, 200);
		}
	}
	
	/*
	 * checks if friendly control and increases your team's power
	 */
	public void updatepower() {
		if (friendlyControl) {
			if (ObjectHandler.power < ObjectHandler.maxPower) {
				ObjectHandler.power++;
			}
		}
	}

	@Override
	/**
	 * You can't move the pylon.
	 * 
	 * @param rightEdge shouldn't be used
	 * @param bottomEdge shouldn't be used
	 * 
	 */
	public void move(double rightEdge, double bottomEdge) {
		System.out.println("Tried to move pylon");
	}
	
	/**
	 * sets the pylon to a friendly control.
	 */
	public void setFriendly() {
		control = true;
		friendlyControl = true;
		enemyControl = false;
	}
	/**
	 * sets the pylon to an enemy control.
	 */
	public void setEnemy() {
		control = true;
		friendlyControl = false;
		enemyControl = true;
	}
	
	/**
	 * resets the pylon to neutral.
	 */
	public void setNuetral() {
		control = false;
		friendlyControl = false;
		enemyControl = false;
	}

}
