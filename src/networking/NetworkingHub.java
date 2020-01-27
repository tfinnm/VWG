package networking;

import javax.swing.JOptionPane;

public class NetworkingHub implements Runnable {

	/**
	 * constructor
	 */
	public NetworkingHub() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * asks user with a popup if they want to host, join, etc.
	 */
	public static void Prompt() {
		String[] choices = {"Join a game!","Host a game!","Join locally hosted game!"};
		String Choice = (String)JOptionPane.showInputDialog(null, "Howdy! \n How would you like to play today?", "Victor's War Games", JOptionPane.PLAIN_MESSAGE, null, choices, choices[0]);
		System.out.print(Choice);
		if (Choice == null) {
			System.exit(0);
		} else if (Choice == choices[0]) {
			joinPrompt();
		} else if (Choice == choices[1]) {
			host();
		} else if (Choice == choices[2]) {
			join("localhost");
		}
	}

	/**
	 * asks the user with a popup for the IP of the server they want to connect to.
	 */
	public static void joinPrompt() {
		String host = (String)JOptionPane.showInputDialog(null, "Please enter the host's IP!", "Victor's War Games", JOptionPane.PLAIN_MESSAGE);
		System.out.print(host);
		if (!(host == null)) {
			if (!(host.equals(""))) {
				join(host);
			} else {
				joinPrompt();
			}
		}
	}
	public static Client client;
	/**
	 * joins a game
	 * @param host is the server to connect to
	 */
	public static void join(String host) {
		networkType = 2;
		client = new Client(host);
		client.startRunning();
	}
	public static int networkType = 0;
	public static Server server;
	/**
	 * starts a server (and a game)
	 */
	public static void host() {
		networkType = 1;
		server = new Server();
		server.startRunning();
	}
	
	/**
	 * routes messages to either the server or client
	 * @param message is the data/packet to send
	 */
	public void send(String[] message) {
		if (networkType == 1) {
			server.send(message);
		} else if (networkType == 2) {
			client.sendMessage(message);
		}
	}

	@Override
	/**
	 * allows networking to run in it's own thread
	 */
	public void run() {
	
	}

}
