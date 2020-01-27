package game;

import java.awt.event.*;
import main.*;

public class Key implements KeyListener {

	@Override
	/**
	 * sets movement variables when corresponding key is pressed
	 */
	public void keyPressed(KeyEvent e) {

		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			ObjectHandler.players[1].up = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			ObjectHandler.players[1].down = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			ObjectHandler.players[1].left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			ObjectHandler.players[1].right = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			ObjectHandler.players[0].up = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			ObjectHandler.players[0].down = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			ObjectHandler.players[0].left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			ObjectHandler.players[0].right = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_PAGE_UP) {
			ObjectHandler.players[1].sprint = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_Q) {
			ObjectHandler.players[0].sprint = true;
		}
	}

	@Override
	/**
	 * Opposite of keypressed
	 * also has extra one time actions
	 */
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			ObjectHandler.players[1].up = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			ObjectHandler.players[1].down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			ObjectHandler.players[1].left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			ObjectHandler.players[1].right = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			ObjectHandler.players[0].up = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			ObjectHandler.players[0].down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			ObjectHandler.players[0].left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			ObjectHandler.players[0].right = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_PAGE_UP) {
			ObjectHandler.players[1].sprint = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_Q) {
			ObjectHandler.players[0].sprint = false;
		}

		//reloads
		if (e.getKeyCode() == KeyEvent.VK_R) {
			while ((ObjectHandler.players[1].getAmmo() < ObjectHandler.players[1].getMaxAmmo()) && (ObjectHandler.power > 0)) {
				ObjectHandler.power -= 2;
				ObjectHandler.players[1].setAmmo(ObjectHandler.players[1].getAmmo()+1);
			}
			while ((ObjectHandler.players[0].getAmmo() < ObjectHandler.players[0].getMaxAmmo()) && (ObjectHandler.power > 0)) {
				ObjectHandler.power -= 2;
				ObjectHandler.players[0].setAmmo(ObjectHandler.players[0].getAmmo()+1);
			}
		}
		
		//melees
		if (e.getKeyCode() == KeyEvent.VK_E) {
			if ((Math.sqrt(Math.pow(ObjectHandler.players[0].getX()-ObjectHandler.players[2].getX(),2)+Math.pow(ObjectHandler.players[0].getY()-ObjectHandler.players[2].getY(),2)) < (ObjectHandler.players[0].getRadius()+ObjectHandler.players[2].getRadius()))) {
				if (ObjectHandler.power >= 50) {
					ObjectHandler.power -= 50;
					ObjectHandler.players[2].takeDamage(175, 0);
					String[] passArray = {"dmg","2","175","0"};
					RunMePls.network.send(passArray);
				}
			}
			if ((Math.sqrt(Math.pow(ObjectHandler.players[0].getX()-ObjectHandler.players[3].getX(),2)+Math.pow(ObjectHandler.players[0].getY()-ObjectHandler.players[3].getY(),2)) < (ObjectHandler.players[0].getRadius()+ObjectHandler.players[3].getRadius()))) {
				if (ObjectHandler.power >= 50) {
					ObjectHandler.power -= 50;
					ObjectHandler.players[2].takeDamage(175, 0);
					String[] passArray = {"dmg","3","75","0"};
					RunMePls.network.send(passArray);
				}
			}
		}

		//heals
		if (e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
			if ((ObjectHandler.power >= 75) && (ObjectHandler.players[1].getHealth() > ObjectHandler.players[1].getNoHealHealth())) {
				if (((Math.sqrt(Math.pow(ObjectHandler.players[0].getX()-ObjectHandler.players[1].getX(),2)+Math.pow(ObjectHandler.players[0].getY()-ObjectHandler.players[1].getY(),2)) < (ObjectHandler.players[0].getRadius()+ObjectHandler.players[1].getRadius()))) && (ObjectHandler.players[0].getHealth() < ObjectHandler.players[0].getMaxHealth())) {
					if (ObjectHandler.players[0].getLoss() > 0) {
						ObjectHandler.players[0].setLoss(0);
						ObjectHandler.power -= 75;
					} else if (ObjectHandler.players[0].getHealth() > ObjectHandler.players[0].getHealHealth()) {
						ObjectHandler.players[0].setHealth(ObjectHandler.players[0].getHealHealth());
						ObjectHandler.power -= 75;
					}
				} else if (ObjectHandler.players[1].getLoss() > 0) {
					ObjectHandler.players[1].setLoss(0);
					ObjectHandler.power -= 75;
				}
			}
		}

		//captures a pylon
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			for (int j = 0; j < ObjectHandler.pylons.length; j++) {
				if (Math.sqrt(Math.pow(ObjectHandler.pylons[j].getX()-ObjectHandler.players[0].getX(),2)+Math.pow(ObjectHandler.pylons[j].getY()-ObjectHandler.players[0].getY(),2)) < (100+ObjectHandler.players[0].getRadius())) {
					if ((Math.sqrt(Math.pow(ObjectHandler.pylons[j].getX()-ObjectHandler.players[2].getX(),2)+Math.pow(ObjectHandler.pylons[j].getY()-ObjectHandler.players[2].getY(),2)) < (100+ObjectHandler.players[2].getRadius())) || (Math.sqrt(Math.pow(ObjectHandler.pylons[j].getX()-ObjectHandler.players[3].getX(),2)+Math.pow(ObjectHandler.pylons[j].getY()-ObjectHandler.players[3].getY(),2)) < (100+ObjectHandler.players[3].getRadius()))) {
						ObjectHandler.pylons[j].setNuetral();
						String[] passArray = {"sp",String.valueOf(j),"n",null};
						RunMePls.network.send(passArray);
					} else {
						ObjectHandler.pylons[j].setFriendly();
						String[] passArray = {"sp",String.valueOf(j),"f",null};
						RunMePls.network.send(passArray);
					}
				}
				if (Math.sqrt(Math.pow(ObjectHandler.pylons[j].getX()-ObjectHandler.players[1].getX(),2)+Math.pow(ObjectHandler.pylons[j].getY()-ObjectHandler.players[1].getY(),2)) < (100+ObjectHandler.players[1].getRadius())) {
					if ((Math.sqrt(Math.pow(ObjectHandler.pylons[j].getX()-ObjectHandler.players[2].getX(),2)+Math.pow(ObjectHandler.pylons[j].getY()-ObjectHandler.players[2].getY(),2)) < (100+ObjectHandler.players[2].getRadius())) || (Math.sqrt(Math.pow(ObjectHandler.pylons[j].getX()-ObjectHandler.players[3].getX(),2)+Math.pow(ObjectHandler.pylons[j].getY()-ObjectHandler.players[3].getY(),2)) < (100+ObjectHandler.players[3].getRadius()))) {
						ObjectHandler.pylons[j].setNuetral();
						String[] passArray = {"sp",String.valueOf(j),"n",null};
						RunMePls.network.send(passArray);
					} else {
						ObjectHandler.pylons[j].setFriendly();
						String[] passArray = {"sp",String.valueOf(j),"f",null};
						RunMePls.network.send(passArray);
					}
				}
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
