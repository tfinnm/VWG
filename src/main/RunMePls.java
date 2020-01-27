package main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import game.*;
import networking.*;
import tools.*;

public class RunMePls extends JPanel {

	public static NetworkingHub network;

	public static void main(String[] args) {

		GraphicsMLoop.newG = Boolean.valueOf(args[0]);
		//starts the networking
		network = new NetworkingHub();
		Thread networkt = new Thread(network);
		networkt.start();
		if (args[1].contentEquals("")||args[1].contentEquals("p")) {
			network.Prompt();
		} else if (args[1].contentEquals("\\\\dc")) {
			network.joinPrompt();
		} else if (args[1].contentEquals("\\\\h")) {
			network.host();
		} else {
			network.join(args[1]);
		}

	}

}
