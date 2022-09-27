package game;


import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*; 

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BrickFrame extends JFrame {

	private JPanel panel;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrickFrame frame = new BrickFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public BrickFrame() {
		this.setTitle("Brick Game");
        ImageIcon icon = new ImageIcon("src/images/custom_java_icon.jpg");
        setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(100, 100);
        this.setResizable(false);
        this.setLayout(null);
        this.setBackground(Color.CYAN);
        panel = new BrickPanel();
        setContentPane(panel);
        pack();
	}

}


