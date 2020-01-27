package game;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GraphicsStart implements Runnable {

	/**
	 * constructor
	 */
	public GraphicsStart() {
	}

	@Override
	/**
	 * starts all the graphicz
	 */
	public void run() {
		JFrame frame = new JFrame("Victor's War Games");
		frame.setSize(GraphicsMLoop.WIDTH, GraphicsMLoop.HEIGHT);
		frame.setLocation(200, 100);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(true); //TODO: change back to false;
		if (GraphicsMLoop.newG) {
			ImageIcon logo = new ImageIcon("VWGIcon.png");
			frame.setIconImage(logo.getImage());
		}
		//launcher.Launcher.frame
		frame.setContentPane(new GraphicsMLoop());
		frame.setVisible(true);
	}

}
