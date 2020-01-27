package launcher;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;

import game.GraphicsMLoop;
import launcher.updater.*;
import networking.NetworkingHub;

@SuppressWarnings("serial")
public class Launcher extends JPanel implements ActionListener, Runnable {

	private static JMenuBar menubar;
	private static JCheckBoxMenuItem graphics;
	private static JProgressBar status;
	
	public static void main(String[] args) {
//		Launcher Launch = new Launcher();
//		Thread LaunchT = new Thread(Launch);
//		LaunchT.start();
		String[] pass = {"true",""};
		main.RunMePls.main(pass);
	}
	
	public void run() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				GUI();
				launcher.updater.Main.main(new String[0]);
			}
		});
	}
	
	public Launcher() {
		
		menubar = new JMenuBar();
		JMenu menu = new JMenu("Options");
		menu.setMnemonic(KeyEvent.VK_O);
		JMenuItem dconnect = new JMenuItem("Direct Connect",
                KeyEvent.VK_D);
		dconnect.addActionListener(this);
		dconnect.setActionCommand("dc");
		menu.add(dconnect);
		JMenuItem about = new JMenuItem("About",
                KeyEvent.VK_A);
		menu.add(about);
		menu.addSeparator();
		graphics = new JCheckBoxMenuItem("New Graphics Mode");
		graphics.setSelected(true);
		graphics.setMnemonic(KeyEvent.VK_G);
		menu.add(graphics);
		menubar.add(menu);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		ImageIcon icon = new ImageIcon("");

		BorderLayout playout = new BorderLayout();
		JPanel panel1 = new JPanel(playout);
		tabbedPane.addTab("Play", icon, panel1,
				"Join or Start a Game.");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_P);
		
		JPanel panel2 = new JPanel();
		tabbedPane.addTab("Learn", icon, panel2,
				"Learn How To Play.");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_L);

		JEditorPane panel3p = new JEditorPane();
        panel3p.setContentType("text/html");
        panel3p.setEditable(false);
        JScrollPane panel3 = new JScrollPane(panel3p);
      	panel3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      	panel3.setPreferredSize(new Dimension(300,600));
        try {
			panel3p.setText(getData("http://localhost/update"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tabbedPane.addTab("Change Log", icon, panel3,
				"View Recent Updates.");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_C);
		add(tabbedPane);
		
		
		
		BorderLayout headerlayout = new BorderLayout();
		JPanel panel1n = new JPanel(headerlayout);
		panel1.add(panel1n,BorderLayout.NORTH);
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon("VWGIcon.png"));
		JLabel logo2 = new JLabel();
		logo2.setIcon(new ImageIcon("VWGIcon.png"));
		
		JLabel headertext = new JLabel();
		headertext.setText("Victor's War Games");
		headertext.setFont(new Font("Sans", Font.BOLD, 100));
		
		panel1n.add(logo,BorderLayout.WEST);
		panel1n.add(headertext,BorderLayout.CENTER);
		//panel1n.add(logo2,BorderLayout.EAST);
		
		GridLayout bottomlayout = new GridLayout(1,3);
		bottomlayout.setHgap(100);
		JPanel panel1s = new JPanel(bottomlayout);
		panel1.add(panel1s,BorderLayout.SOUTH);
		JButton joinbutton = new JButton("Join Game");
		joinbutton.addActionListener(this);
		joinbutton.setActionCommand("c");
		JButton hostbutton = new JButton("Start Game");
		hostbutton.addActionListener(this);
		hostbutton.setActionCommand("h");
		status = new JProgressBar();
		panel1s.add(joinbutton);
		panel1s.add(status);
		panel1s.add(hostbutton);
		
		JList hostSelect = new JList();
		panel1.add(hostSelect, BorderLayout.CENTER);
	}
	
	public static void GUI() {
		//Create and set up the window.
		JFrame frame = new JFrame("Play Victor's War Games");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Add content to the window.
		frame.add(new Launcher());
		frame.setJMenuBar(menubar);

		//Display the window.
		frame.pack();
		frame.setVisible(true);
		frame.setIconImage(new ImageIcon("VWGIcon.png").getImage());
		frame.setResizable(false);
	}
    private static String getData(String address)throws Exception
    {
        URL url = new URL(address);
        
        InputStream html = null;

        html = url.openStream();
        
        int c = 0;
        StringBuffer buffer = new StringBuffer("");

        while(c != -1) {
            c = html.read();
            
        buffer.append((char)c);
        }
        return buffer.toString();
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.print(arg0.getActionCommand());
		System.out.print(graphics.getState());
		if(arg0.getActionCommand().equals("dc")) {
			String[] pass = {String.valueOf(graphics.getState()), "\\\\dc"};
			main.RunMePls.main(pass);
		} else if(arg0.getActionCommand().equals("h")) {
			String[] pass = {String.valueOf(graphics.getState()), "\\\\h"};
			main.RunMePls.main(pass);
			status.setIndeterminate(true);
			status.setString("Waiting for connection.");
			status.setStringPainted(true);
		} else if(arg0.getActionCommand().equals("c")) {
		String[] pass = {String.valueOf(graphics.getState()), "\\\\dc"};
		main.RunMePls.main(pass);
		}
		System.out.print(GraphicsMLoop.newG);
		
	}


}
