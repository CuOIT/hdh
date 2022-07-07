
	package main;
	import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import	javax.swing.JFrame;
import javax.swing.JPanel;
	public class Main {
		
		public static JFrame window;
		public static void main(String[] args) {
		window=new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//window.setResizable(false);
		window.setTitle("Dinning Philosopher RUNNABLE");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println((int)screenSize.getWidth()+" "+(int)screenSize.getHeight());
		window.setPreferredSize(new Dimension(1200,700));
		window.setLayout(new FlowLayout());	
		Panel panel=new Panel();
		UI_Panel ui_panel=new UI_Panel(panel);
		panel.setUp();
		window.add(panel);
		window.add(ui_panel);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.addKeyListener(panel.keyH);
		panel.startThread();
		
		}
		
		}

