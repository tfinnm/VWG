package tools;

public class MouseTools {
	
	
	/**
	 * Finds angle between two coordinate pairs
	 * (used only for aiming)
	 * (might be used later for rotating charectors)
	 * 
	 * @param x1 The x of the first coordinate pair
	 * @param y1 The y of the first coordinate pair
	 * @param x2 The x of the second coordinate pair
	 * @param y2 The y of the second coordinate pair
	 * @return the angle between them.
	 */
	public static double getMouseAngle(int x1, int y1, int x2, int y2) {
		return (Math.atan((double) (((double)(y1-y2)/(double)(x1-x2)))));
	}

}
