package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import javax.swing.ImageIcon;

import game.GraphicsMLoop;
import game.ObjectHandler;
import main.RunMePls;

public class player extends movable {

	//ammo and health variables, these control what the player can and can't currently do.
	private boolean alive = true;
	private int health = 100;
	private int healthLoss = 0;
	private int restoreHealthTo = 0;
	private int maxHealth = 0;
	private int ammo = 0;
	private int maxAmmo = 0;
	private int speed = 0;
	private int sprintspeed = 0;
	private int criticalHealth = 0;
	private int noSelfHealHealth = 0;
	private boolean sameTeam;
	private boolean light;

	//movement variables, these vars control movement.
	public boolean up = false;
	public boolean down = false;
	public boolean left = false;
	public boolean right = false;
	public boolean sprint = false;

	/**
	 * constructor.
	 * player type is also setup here
	 * @param UserX is the player's X coordinate
	 * @param UserY is the player's Y coordinate
	 * @param UserDiameter is the player size
	 * @param UserColor is the player's color (used in old graphics only)
	 * @param heavy determines player type
	 * @param friend determines team
	 */
	public player(double UserX, double UserY, double UserDiameter, Color UserColor, boolean heavy, boolean friend) {
		super(UserX, UserY, UserDiameter, UserColor);
		// TODO Auto-generated constructor stub
		sameTeam = friend;
		light = !heavy;
		if (heavy) {
			ammo = 100;
			maxAmmo = 100;
			speed = 5;
			sprintspeed = 7;
			health = 200;
			maxHealth = 200;
			restoreHealthTo = 100;
			criticalHealth = 35;
			noSelfHealHealth = 75;
		} else {
			ammo = 10;
			maxAmmo = 15;
			speed = 10;
			sprintspeed = 15;
			health = 100;
			maxHealth = 100;
			restoreHealthTo = 50;
			criticalHealth = 25;
			noSelfHealHealth = 35;
		}
	}

	@Override
	/**
	 * if old graphics this draws a circle at the player's location
	 * if new graphics this draws a sprite based on the player's team and type
	 * the enemy sprite is also flipped here to make the game nicer.
	 * @param g is the graphics renderer
	 */
	public void draw(Graphics g) {
		if (alive) {
			if (GraphicsMLoop.newG) {
				ImageIcon image;
				if (GraphicsMLoop.School) {
					if (light) {
						if (sameTeam) {
							image = new ImageIcon("lightFriendly.png");
						} else {
							image = new ImageIcon("lightEnemy.png");
						}						
					} else {
						if (sameTeam) {
							image = new ImageIcon("friendlyHeavysa.png");
						} else {
							image = new ImageIcon("EnemyHeavysa.png");
						}
					}
				} else {
					if (light) {
						image = new ImageIcon("light.gif");
					} else {
						if (sameTeam) {
							image = new ImageIcon("friendlyHeavy.png");
						} else {
							image = new ImageIcon("EnemyHeavy.png");
						}
					}
				}
				if (sameTeam) {
					g.drawImage(image.getImage(),(int)(getX()-getDiameter()),(int)(getY()-getDiameter()),(int)(getDiameter()*2),(int)(getDiameter()*2), null);
				} else {
					g.drawImage(image.getImage(),(int)(getX()-getDiameter()) + (int)(getDiameter()*2), (int)(getY()-getDiameter()), -(int)(getDiameter()*2), (int)(getDiameter()*2), null);
				}
			} else {
				g.setColor(getColor());
				g.fillOval((int) (getX()-getRadius()), (int) (getY()-getRadius()), (int) (getDiameter()), (int) (getDiameter()));
			}
		}
	}

	/**
	 * 
	 * @return the player's current health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * updates if the player is dead or alive and every 20 frames decreases the health amount by the health decline amount.
	 * @param frame
	 */
	public void updateHealth(int frame) {
		if (frame % 20 == 0) {
			health = health - healthLoss;
		}
		if (health < 0) {
			alive = false;
			setLocation(0,0);
		}
	}

	/**
	 * decreases the current health and increases the current health decline 
	 * @param instant is how much health to remove
	 * @param loss is how much health decline to add
	 */
	public void takeDamage(int instant, int loss) {
		health = health - instant;
		healthLoss += loss;
	}

	/**
	 * 
	 * @return the player's current amount of health loss
	 */
	public int getLoss() {
		return healthLoss;
	}

	/**
	 * 
	 * @return if the player is alive
	 */
	public boolean getAlive() {
		return alive;
	}

	/**
	 * sets the players amount of health decline
	 * @param loss is the new health decline
	 */
	public void setLoss(int loss) {
		healthLoss = loss;
	}

	/**
	 * 
	 * @return the player's max health
	 */
	public int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * moves the player based on the player's movement variables.
	 * player can not enter walls or leave the map, they simply can not move into this positions.
	 * after moving the player's new location is sent through the network.
	 * also can sprint if holding down sprint key and has enough team power.
	 * also can not move when under the critical health point.
	 * @param rightEdge is the WIDTH of the screen
	 * @param bottomEdge is the Height of the screen
	 * @param i the player's index in the player array
	 */
	public void move(double rightEdge, double bottomEdge, int i) {
		if (sprint) {
			if (ObjectHandler.power > 1) {
				ObjectHandler.power -= 1;
			} else {
				sprint = false;
			}
		}

		if (health > criticalHealth) {
			if (up) {
				if (((getY()-getRadius())-speed) > 0) {
					// only allow up if Y is less than 150, or greater than 850, or if x is greater than 1050, or y is less than 550 and greater than 450, or if x is less than 950
					if ((((getY()+getRadius())+speed) < 150) || (((getY()-getRadius())-speed) > 850) || ((getX()-getRadius()) > 1050) || ((((getY()+getRadius())+speed) < 550) && (((getY()-getRadius())-speed) > 450)) || ((getX()+getRadius()) < 950)) {
						if (!sprint) {
							setY(getY()-speed);
						} else if (((getY()-getRadius())-sprintspeed) > 0) {
							setY(getY()-sprintspeed);
						}
					}
				}
			}
			if (down) {
				if (((getY()+getRadius())+speed) < GraphicsMLoop.HEIGHT) {
					if ((((getY()+getRadius())+speed) < 150) || (((getY()-getRadius())-speed) > 850) || ((getX()-getRadius()) > 1050) || ((((getY()+getRadius())+speed) < 550) && (((getY()-getRadius())-speed) > 450)) || ((getX()+getRadius()) < 950)) {
						if (!sprint) {
							setY(getY()+speed);
						} else if (((getY()+getRadius())+sprintspeed) < GraphicsMLoop.HEIGHT) {
							setY(getY()+sprintspeed);
						}
					}
				}
			}
			if (left) {
				if (((getX()-getRadius())-speed) > 0) {
					if ((((getX()-getRadius())-speed) > 1050) || ((getY()+getRadius()) < 150) || ((getY()-getRadius()) > 850) || (((getX()+getRadius())+speed) < 950) || (((getY()-getRadius()) > 450) && ((getY()+getRadius()) < 550))) {
						if (!sprint) {
							setX(getX()-speed);
						} else if (((getX()-getRadius())-sprintspeed) > 0) {
							setX(getX()-sprintspeed);
						}
					}
				}
			}
			if (right) {
				if ((((getX()+getRadius())+speed) < GraphicsMLoop.WIDTH)) {
					//TODO: global variable with bumper location formulas 
					if ((((getX()+getRadius())+speed) < 950) || ((getY()+getRadius()) < 150) || ((getY()-getRadius()) > 850) || (((getX()-getRadius())-speed) > 1050) || (((getY()-getRadius()) > 450) && ((getY()+getRadius()) < 550))) {
						if (!sprint) {
							setX(getX()+speed);
						} else if (((getX()-getRadius())-sprintspeed) < GraphicsMLoop.WIDTH) {
							setX(getX()+sprintspeed);
						}
					}
				}
			}

			String[] passArray = {"mv",String.valueOf(i),String.valueOf(getX()),String.valueOf(getY())};
			RunMePls.network.send(passArray);
		}
	}

	@Override
	/**
	 * the player doesn't really use this method
	 * TODO: remove.
	 * @param rightEdge is not used TODO: remove
	 * @param bottomEdge is not used TODO: remove
	 */
	public void move(double rightEdge, double bottomEdge) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @return the player's current ammo amount.
	 */
	public int getAmmo() {
		return ammo;
	}

	/**
	 * 
	 * @return the player's maximum amount of ammo.
	 */
	public int getMaxAmmo() {
		return maxAmmo;
	}

	/**
	 * sets the amount of ammo the player has
	 * @param newammo is the new ammo amount
	 */
	public void setAmmo(int newammo) {
		ammo = newammo;
	}
	/**
	 * 
	 * @return the health that healing will restore the player to
	 */
	public int getHealHealth() {
		return restoreHealthTo;
	}
	
	/**
	 * 
	 * @return the health at which the player can no longer heal themselves or others
	 */
	public int getNoHealHealth() {
		return noSelfHealHealth;
	}

	/**
	 * sets the players health
	 * @param hp is the new health
	 */
	public void setHealth(int hp) {
		health = hp;
	}

}
