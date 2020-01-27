package game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import main.RunMePls;

@SuppressWarnings("serial")
public class GraphicsMLoop extends JPanel {

	//heights
	public static final int WIDTH = 2000;
	public static final int HEIGHT = 1000;
	
	//sets graphics mode
	public static boolean newG;
	public static boolean School = true;
	
	//frame counter
	public int frame = 0;
	
	// Declare objects to be used
	private BufferedImage image;
	private Timer timer;
	private Graphics g;
	private boolean done = false;

	/**
	 * creates the loop
	 */
	public GraphicsMLoop() {

		image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();

		// create a Timer object and call the listener defined in the private class below
		// The listener can be called anything you chose
		timer = new Timer(5, new TimerListener());
		timer.setRepeats(true);
		timer.start();
		timer.setRepeats(true);

		//activates 
		addKeyListener(new Key());
		setFocusable(true);
		addMouseListener(new Mouse());
	}

	/**
	 * 
	 * listens for the timer to go off then rerenders the image and moves the ball
	 *	also updates all collisions and moves object.
	 *
	 */
	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			frame++;
			if (ObjectHandler.players[2].getAlive() || ObjectHandler.players[3].getAlive()) {
				if (ObjectHandler.players[0].getAlive() || ObjectHandler.players[1].getAlive()) {
					// draw a background color to cover up all the previous graphics
					if (newG) {
						ImageIcon bgimg = new ImageIcon("bg.jpg");
						g.drawImage(bgimg.getImage(),0,0,WIDTH,HEIGHT, null);
					} else {
						g.setColor(new Color(9, 4, 106));
						g.fillRect(0,0, WIDTH, HEIGHT);
					}
						
					//UI
					g.setColor(Color.RED);
					g.fillRect(0, 0, ObjectHandler.players[0].getHealth()/2, 25);
					g.fillRect(0, HEIGHT-25, ObjectHandler.players[1].getHealth(), 25);
					g.setColor(Color.BLACK);
					if (ObjectHandler.players[0].getAlive()) {
						g.drawString("HP: "+ObjectHandler.players[0].getHealth()+"/"+ObjectHandler.players[0].getMaxHealth(), 0, 25);
					} else {
						g.drawString("DEAD", 0, 25);
					}
					if (ObjectHandler.players[1].getAlive()) {
						g.drawString("HP: "+ObjectHandler.players[1].getHealth()+"/"+ObjectHandler.players[1].getMaxHealth(), 0, HEIGHT);
					} else {
						g.drawString("DEAD", 0, HEIGHT);
					}
					if (ObjectHandler.players[1].getAlive()) {
						g.setColor(Color.gray);
						g.drawRect(0, HEIGHT-50, 100, 25);
						g.fillRect(0, HEIGHT-50, ObjectHandler.players[1].getAmmo()*(100/ObjectHandler.players[1].getMaxAmmo()), 25);
						g.setColor(Color.black);
						g.drawOval((int)(ObjectHandler.players[1].getX()-500), (int)(ObjectHandler.players[1].getY()-500), 1000, 1000);
						g.drawString("Ammo: "+ObjectHandler.players[1].getAmmo()+"/"+ObjectHandler.players[1].getMaxAmmo(), 0, HEIGHT-25);
					}
					if (ObjectHandler.players[0].getAlive()) {
						g.setColor(Color.gray);
						g.drawRect(0, 25, 100, 25);
						g.fillRect(0, 25, ObjectHandler.players[0].getAmmo()*(100/ObjectHandler.players[0].getMaxAmmo()), 25);
						g.setColor(Color.black);
						g.drawOval((int)(ObjectHandler.players[0].getX()-500), (int)(ObjectHandler.players[0].getY()-500), 1000, 1000);
						g.drawString("Ammo: "+ObjectHandler.players[0].getAmmo()+"/"+ObjectHandler.players[0].getMaxAmmo(), 0, 50);
					}
					g.drawRect(5, 55, 10, HEIGHT-110);
					g.setColor(Color.CYAN);
					g.fillRect(5, 55, 10, ObjectHandler.power*((HEIGHT-110)/ObjectHandler.maxPower));

					//objects
					for (int i = 0; i < ObjectHandler.players.length; i++) {
						ObjectHandler.players[i].move(WIDTH, HEIGHT, i);
						boolean p1 = (Math.sqrt(Math.pow(ObjectHandler.players[0].getX()-ObjectHandler.players[i].getX(),2)+Math.pow(ObjectHandler.players[0].getY()-ObjectHandler.players[i].getY(),2)) < (500+ObjectHandler.players[i].getRadius()));
						boolean p2 = (Math.sqrt(Math.pow(ObjectHandler.players[1].getX()-ObjectHandler.players[i].getX(),2)+Math.pow(ObjectHandler.players[1].getY()-ObjectHandler.players[i].getY(),2)) < (500+ObjectHandler.players[i].getRadius()));
						if (p1 || p2) {
							ObjectHandler.players[i].draw(g);
						}
						ObjectHandler.players[i].updateHealth(frame);
						//System.out.println("Health of player "+i+": "+ObjectHandler.players[i].getHealth());
					}
					
					for (int i = 0; i < ObjectHandler.pylons.length; i++) {
						ObjectHandler.pylons[i].draw(g);
						ObjectHandler.pylons[i].updatepower();
					}
					
					for (int i = 0; i < ObjectHandler.walls.length; i++) {
						ObjectHandler.walls[i].draw(g);
					}
					
					for (int i = 0; i < ObjectHandler.projectiles.size(); i++) {
						//System.out.println("Pre:"+ObjectHandler.projectiles.get(i).getX()+" "+ObjectHandler.projectiles.get(i).getY());
						ObjectHandler.projectiles.get(i).move(WIDTH, HEIGHT);
						//System.out.println("Post:"+ObjectHandler.projectiles.get(i).getX()+" "+ObjectHandler.projectiles.get(i).getY());
						boolean p1 = (Math.sqrt(Math.pow(ObjectHandler.players[0].getX()-ObjectHandler.projectiles.get(i).getX(),2)+Math.pow(ObjectHandler.players[0].getY()-ObjectHandler.projectiles.get(i).getY(),2)) < (500+ObjectHandler.projectiles.get(i).getRadius()));
						boolean p2 = (Math.sqrt(Math.pow(ObjectHandler.players[1].getX()-ObjectHandler.projectiles.get(i).getX(),2)+Math.pow(ObjectHandler.players[1].getY()-ObjectHandler.projectiles.get(i).getY(),2)) < (500+ObjectHandler.projectiles.get(i).getRadius()));
						if (p1 || p2) {
							ObjectHandler.projectiles.get(i).draw(g);
						}
					}
					for (int i = 0; i < ObjectHandler.projectiles.size(); i++) {
						if (ObjectHandler.projectiles.size() > 0) {
							for(int j = 0; j < ObjectHandler.players.length; j++) {
								if (Math.sqrt(Math.pow(ObjectHandler.players[j].getX()-ObjectHandler.projectiles.get(i).getX(),2)+Math.pow(ObjectHandler.players[j].getY()-ObjectHandler.projectiles.get(i).getY(),2)) < (ObjectHandler.players[j].getRadius()+ObjectHandler.projectiles.get(i).getRadius())) {
									if (ObjectHandler.players[j].getAlive()) {
										if (j == 1 || j == 0) {
											int td = 0;
											int dm = (int)(Math.random()*11);
											if  ((Math.random()*4) > 3) {
												td = 1;
											}
											ObjectHandler.players[j].takeDamage(dm, td);
											String[] passArray = {"dmg",String.valueOf(j),String.valueOf(dm),String.valueOf(td)};
											RunMePls.network.send(passArray);
										}
										ObjectHandler.projectiles.get(i).setRemove(true);
										//								if (!(i == 0)) {
										//									i = 0;
										//									j = 0;
										//								}
									}
								}
							}
							for (int j = 0; j < ObjectHandler.walls.length; j++) {
								if (ObjectHandler.walls[j].inBumper(ObjectHandler.projectiles.get(i))) {
									ObjectHandler.projectiles.get(i).setRemove(true);
								}
							}
							for (int j = 0; j < ObjectHandler.pylons.length; j++) {
								if (Math.sqrt(Math.pow(ObjectHandler.pylons[j].getX()-ObjectHandler.projectiles.get(i).getX(),2)+Math.pow(ObjectHandler.pylons[j].getY()-ObjectHandler.projectiles.get(i).getY(),2)) < (25+ObjectHandler.projectiles.get(i).getRadius())) {
									ObjectHandler.projectiles.get(i).setRemove(true);
								}
							}
						}
					}
					for (int i = 0; i < ObjectHandler.projectiles.size(); i++) {
						if (ObjectHandler.projectiles.get(i).remove(i)) {
							i = 0;
							System.out.println(ObjectHandler.projectiles);
						}
					}
				} else if (!done) {
					done = true;
					JOptionPane.showMessageDialog(null, "You Lose!", "Victor's War Games", JOptionPane.ERROR_MESSAGE, null);
				}
			} else if (!done) {
				done = true;
				JOptionPane.showMessageDialog(null, "You Win!", "Victor's War Games", JOptionPane.INFORMATION_MESSAGE, null);
			}

			// This is the last line of actionPerformed
			repaint();
		}
	}


	/**
	 *  draws the image
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

}