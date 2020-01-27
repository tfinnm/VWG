package networking;

import java.io.*;
import java.net.*;
import javax.swing.*;

import game.*;

@SuppressWarnings("serial")
public class Server extends JFrame {

	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	private boolean end = false;

	/**
	 * constructor
	 */
	public Server(){
	}

	/**
	 * runs the server and launches other parts of the server when needed
	 */
	public void startRunning(){
		try{
			server = new ServerSocket(0223, 100);
			while(true){
				try{
					waitForConnection();
					setupStreams();
					GraphicsStart UI = new GraphicsStart();
					Thread UIT = new Thread(UI);
					UIT.start();
					whileConnected();
				}catch(EOFException eofException){
				} finally{
					closeConnection();
				}
			}
		} catch (IOException ioException){
			ioException.printStackTrace();
		}
	}
	/**
	 * waits for connection, then connects
	 * @throws IOException
	 */
	private void waitForConnection() throws IOException{
		connection = server.accept();
	}

	/**
	 * sets up streams to send and receive data
	 * @throws IOException
	 */
	private void setupStreams() throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
	}

	/**
	 * receives and proccesses data.
	 * @throws IOException
	 */
	private void whileConnected() throws IOException{
		do{
			try{
				String[] message = (String[]) input.readObject();
				System.out.print(message);
				//see client for what each section does.
				if (message[0].equals("mv")) {
					if (message[1].equals("0")) {
						ObjectHandler.players[2].setLocation(GraphicsMLoop.WIDTH-Double.parseDouble(message[2]), Double.parseDouble(message[3]));
					} else if (message[1].equals("1")) {
						ObjectHandler.players[3].setLocation(GraphicsMLoop.WIDTH-Double.parseDouble(message[2]), Double.parseDouble(message[3]));
					}
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
				} else if (message[0].equals("sp")) {
					if (message[2].equals("f")) {
						ObjectHandler.pylons[Integer.parseInt(message[1])].setEnemy();
					} else if (message[2].equals("n")) {
						ObjectHandler.pylons[Integer.parseInt(message[1])].setNuetral();
					} 
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
				} else if (message[0].equals("hl")) {
					if (message[1].equals("0")) {
						ObjectHandler.players[2].takeDamage(Integer.parseInt(message[2]),0);
					} else if (message[1].equals("1")) {
						ObjectHandler.players[2].takeDamage(Integer.parseInt(message[2]),0);
					}
				}

			}catch(ClassNotFoundException classNotFoundException){
				System.out.print("The user has sent an unknown object!");
			}
		}while(!end);
	}

	/**
	 * cleanly closes the conection
	 */
	public void closeConnection(){
		System.out.print("\n Closing Connections... \n");
		try{
			output.close(); //Closes the output path to the client
			input.close(); //Closes the input path to the server, from the client.
			connection.close(); //Closes the connection between you can the client
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}

	/**
	 * Send a mesage to the client
	 * @param message is the data to send
	 */
	public void send(String[] message){
		try{
			System.out.println(message);
			output.writeObject(message);
			output.flush();
		}catch(IOException ioException){
			System.out.print("\n ERROR: CANNOT SEND MESSAGE, PLEASE RETRY");
		}
	}

	/**
	 * not used
	 * TODO: remove
	 */
	public void endConnection() {
		end = true;
	}
}