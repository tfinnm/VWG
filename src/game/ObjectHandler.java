package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import objects.*;

public class ObjectHandler {
	
	//your team's power amount
	public static int power = 0;
	public static int maxPower = 100;
	
	//arrays and array lists containing all game objects
	public static player[] players = {new player(100,100,50,Color.GREEN,true,true),new player(100,GraphicsMLoop.HEIGHT-100,50,Color.GREEN,false,true),new player(GraphicsMLoop.WIDTH-100,100,50,Color.RED,true,false),new player( GraphicsMLoop.WIDTH-100,GraphicsMLoop.HEIGHT-100,50,Color.RED,false,false)};
	public static List<projectile> projectiles = new ArrayList<>();
	//public static Bumper[] walls = {new Bumper((GraphicsMLoop.WIDTH/2), 100+((GraphicsMLoop.HEIGHT/2-100)/2), 100, GraphicsMLoop.HEIGHT/2-200, Color.GRAY), new Bumper((GraphicsMLoop.WIDTH/2), ((GraphicsMLoop.HEIGHT/2)+50)+((GraphicsMLoop.HEIGHT/2-200)/2), 100, GraphicsMLoop.HEIGHT/2-200, Color.GRAY)};
	public static Bumper[] walls = {new Bumper(1000, 300, 100, GraphicsMLoop.HEIGHT/2-200, Color.GRAY), new Bumper(1000, 700, 100, GraphicsMLoop.HEIGHT/2-200, Color.GRAY)};
	public static pylon[] pylons = {new pylon(GraphicsMLoop.WIDTH/2,100),new pylon(GraphicsMLoop.WIDTH/2,GraphicsMLoop.HEIGHT-100)};
	
	/**
	 * contructor
	 */
	public ObjectHandler() {
	}

}
