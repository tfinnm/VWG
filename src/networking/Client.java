package networking;

import java.io.*;
import java.net.*;
import javax.swing.*;

import game.*;

@SuppressWarnings("serial")
public class Client extends JFrame{
	
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String serverIP;
	private Socket connection;
	private boolean end = false;
	
	/*
	 * connects to a user defined host
	 * @param host is the server
	 */
	public Client(String host) {
		serverIP = host;
	}
	
	/**
	 * main client controller,
	 * launches the other methods here when needed.
	 */
	public void startRunning(){
		try{
			connectToServer();
			setupStreams();
			GraphicsStart UI = new GraphicsStart();
			Thread UIT = new Thread(UI);
			UIT.start();
			whileConnected();
		}catch(EOFException eofException){
		}catch(IOException ioException){
			ioException.printStackTrace();
		}finally{
			closeConnection();
		}
	}
	
	/**
	 * connects to server
	 * @throws IOException
	 */
	private void connectToServer() throws IOException{
		connection = new Socket(InetAddress.getByName(serverIP), 0223);
	}
	
	/**
	 * sets up the streams
	 * @throws IOException
	 */
	private void setupStreams() throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
	}
	
	/**
	 * runs while connected to the server
	 * Processes received commands and packets
	 * @throws IOException
	 */
	private void whileConnected() throws IOException{
		do{
			try{
				String[] message = (String[]) input.readObject();
				System.out.print(message);
				//moves a player
				if (message[0].equals("mv")) {
					if (message[1].equals("0")) {
						ObjectHandler.players[2].setLocation(GraphicsMLoop.WIDTH-Double.parseDouble(message[2]), Double.parseDouble(message[3]));
					} else if (message[1].equals("1")) {
						ObjectHandler.players[3].setLocation(GraphicsMLoop.WIDTH-Double.parseDouble(message[2]), Double.parseDouble(message[3]));
					}
				//makes a new projectile
				} else if (message[0].equals("np")) {
					if (message[1].equals("0")) {
						ObjectHandler.projectiles.add(new objects.projectile((int)ObjectHandler.players[2].getX(), (int)ObjectHandler.players[2].getY(), (-1*Double.parseDouble(message[2])), Double.parseDouble(message[3])));
					} else if (message[1].equals("1")) {
						ObjectHandler.projectiles.add(new objects.projectile((int)ObjectHandler.players[3].getX(), (int)ObjectHandler.players[3].getY(), (-1*Double.parseDouble(message[2])), Double.parseDouble(message[3])));
					}
					int ttm = ((int) (30/Math.sqrt(((-1*Double.parseDouble(message[2]))*(-1*Double.parseDouble(message[2])))+(Double.parseDouble(message[3])*Double.parseDouble(message[3])))))+1;
					for (int i = 0; i < ttm; i++) {
						ObjectHandler.projectiles.get(ObjectHandler.projectiles.size()-1).move(GraphicsMLoop.WIDTH, GraphicsMLoop.HEIGHT);
					}
				//sets control of a pylon
				} else if (message[0].equals("sp")) {
					if (message[2].equals("f")) {
						ObjectHandler.pylons[Integer.parseInt(message[1])].setEnemy();
					} else if (message[2].equals("n")) {
						ObjectHandler.pylons[Integer.parseInt(message[1])].setNuetral();
					} 
				//deals damage to a player
				} else if (message[0].equals("dmg")) {
					if (message[1].equals("0")) {
						ObjectHandler.players[2].takeDamage(Integer.parseInt(message[2]), Integer.parseInt(message[3]));
					} else if (message[1].equals("1")) {
						ObjectHandler.players[3].takeDamage(Integer.parseInt(message[2]), Integer.parseInt(message[3]));
					} else if (message[1].equals("2")) {
						ObjectHandler.players[0].takeDamage(Integer.parseInt(message[2]), Integer.parseInt(message[3]));
					} else if (message[1].equals("3")) {
						ObjectHandler.players[1].takeDamage(Integer.parseInt(message[2]), Integer.parseInt(message[3]));
					}
				//changes health of a player
				} else if (message[0].equals("hl")) {
					if (message[1].equals("0")) {
						ObjectHandler.players[2].takeDamage(Integer.parseInt(message[2]),0);
					} else if (message[1].equals("1")) {
						ObjectHandler.players[2].takeDamage(Integer.parseInt(message[2]),0);
					}
				}
				
				
			}catch(ClassNotFoundException classNotFoundException){
			}
		}while(!end);	
	}
	
	/**
	 * Closes the connection cleanly
	 */
	private void closeConnection(){
		try{
			output.close();
			input.close();
			connection.close();
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
	/**
	 * send message to server
	 * @param message is the data to send
	 */
	public void sendMessage(String[] message){
		try{
			System.out.print(message);
			output.writeObject(message);
			output.flush();
		}catch(IOException ioException){
		}
	}
	
	/*
	 * not used
	 * TODO: remove
	 */
	public void endConnection() {
		end = true;
	}
	
}