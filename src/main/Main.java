
	package main;
	import	javax.swing.JFrame;
	public class Main {
		public static JFrame window;
		public static void main(String[] args) {
		window=new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Dinning Philosopher");
		Panel panel=new Panel();
		panel.setUp();
		window.add(panel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		
		panel.startThread();
		}
	}
