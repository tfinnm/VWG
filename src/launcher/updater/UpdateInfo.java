/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package launcher.updater;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Thomas Otero H3R3T1C
 */
public class UpdateInfo extends JFrame{

    private JEditorPane infoPane;
    private JScrollPane scp;
    private JButton ok;
    private JButton cancel;
    private JPanel pan1;
    private JPanel pan2;

    public UpdateInfo(String info) {
        initComponents();
        infoPane.setText(info);
    }

    private void initComponents() {

        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("New Update Found");
        pan1 = new JPanel();
        pan1.setLayout(new BorderLayout());

        pan2 = new JPanel();
        pan2.setLayout(new FlowLayout());

        infoPane = new JEditorPane();
        infoPane.setContentType("text/html");
        infoPane.setEditable(false);

        scp = new JScrollPane();
        scp.setViewportView(infoPane);

        ok = new JButton("Update");
        ok.addActionListener( new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                try {
					update();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });

        cancel = new JButton("Cancel");
        cancel.addActionListener( new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                UpdateInfo.this.dispose();
            }
        });
        pan2.add(ok);
        pan2.add(cancel);
        pan1.add(pan2, BorderLayout.SOUTH);
        pan1.add(scp, BorderLayout.CENTER);
        this.add(pan1);
        pack();
        show();
        this.setSize(600, 400);
    }
    private void update() throws Exception
    {
    	try {
			Desktop.getDesktop().browse(new URI(Updater.getURL()));
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

}
