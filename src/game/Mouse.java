package game;

import java.awt.event.*;

import main.RunMePls;
import objects.projectile;

public class Mouse implements MouseListener {

	@Override
	/**
	 * does stuff when the mouse is clicked
	 */
	public void mouseClicked(MouseEvent arg0) {
		//if right click p2 shoots
		if(arg0.isMetaDown()) {
			if (ObjectHandler.players[1].getAlive()) {
				if (ObjectHandler.players[1].getAmmo() > 0) {
					ObjectHandler.players[1].setAmmo(ObjectHandler.players[1].getAmmo()-1);
					double angle = tools.MouseTools.getMouseAngle((int)ObjectHandler.players[1].getX(), (int)ObjectHandler.players[1].getY(), arg0.getX(), arg0.getY());
					double XSpeed = ((Math.cos(angle))*10);
					double YSpeed = ((Math.sin(angle))*10);
					if (ObjectHandler.players[1].getX() > arg0.getX()) {
						XSpeed = -XSpeed;
						YSpeed = -YSpeed;
					}
					ObjectHandler.projectiles.add(new projectile((int)ObjectHandler.players[1].getX(),(int)ObjectHandler.players[1].getY(),XSpeed,YSpeed));
					int ttm = ((int) (30/Math.sqrt((XSpeed*XSpeed)+(YSpeed*YSpeed))))+1; //Math.min(XSpeed, YSpeed));
					for (int i = 0; i < ttm; i++) {
						ObjectHandler.projectiles.get(ObjectHandler.projectiles.size()-1).move(GraphicsMLoop.WIDTH, GraphicsMLoop.HEIGHT);
					}
					String[] passArray = {"np","1",String.valueOf(XSpeed),String.valueOf(YSpeed)};
					RunMePls.network.send(passArray);
				}
			}
		//if left click p1 shoots 
		} else {
			if (ObjectHandler.players[0].getAlive()) {
				if (ObjectHandler.players[0].getAmmo() > 0) {
					ObjectHandler.players[0].setAmmo(ObjectHandler.players[0].getAmmo()-1);
					double angle = tools.MouseTools.getMouseAngle((int)ObjectHandler.players[0].getX(), (int)ObjectHandler.players[0].getY(), arg0.getX(), arg0.getY());
					double XSpeed = ((Math.cos(angle))*10);
					double YSpeed = ((Math.sin(angle))*10);
					if (ObjectHandler.players[0].getX() > arg0.getX()) {
						XSpeed = -XSpeed;
						YSpeed = -YSpeed;
					}
					ObjectHandler.projectiles.add(new projectile((int)ObjectHandler.players[0].getX(),(int)ObjectHandler.players[0].getY(),XSpeed,YSpeed));
					int ttm = ((int) (30/Math.sqrt((XSpeed*XSpeed)+(YSpeed*YSpeed))))+1; //Math.min(XSpeed, YSpeed));
					for (int i = 0; i < ttm; i++) {
						ObjectHandler.projectiles.get(ObjectHandler.projectiles.size()-1).move(GraphicsMLoop.WIDTH, GraphicsMLoop.HEIGHT);
					}
					String[] passArray = {"np","0",String.valueOf(XSpeed),String.valueOf(YSpeed)};
					RunMePls.network.send(passArray);
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
