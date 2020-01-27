package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import game.GraphicsMLoop;

public abstract class explosive extends movable {

	private int explosionFrame = 0;
	private Timer explosionTimer;
	
	public explosive() {
		super();
	}

	public explosive(double UserX, double UserY, double UserDiameter, Color UserColor) {
		super(UserX, UserY, UserDiameter, UserColor);
	}

	public void explode() {
		explosionTimer = new Timer(5, new Explosion());
		explosionTimer.setRepeats(true);
		explosionTimer.start();
		explosionTimer.setRepeats(true);
	}
	
	private class Explosion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			explosionFrame++;
		}
		
	}

}
