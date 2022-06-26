package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import entity.Fork;
import entity.Philosopher;
public class Panel extends JPanel implements Runnable{
	Thread thread;
	public int screenWidth=1200;
	public int screenHeight=900;
	public Fork[] forks=new Fork[5];
	public Philosopher[] phis=new Philosopher[5];
	public int numberEating=2;
	int FPS=60;
	
	public AssetSetter aSet=new AssetSetter(this);
	public Panel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		//set size of this class
		this.setBackground(Color.WHITE);
		this.setDoubleBuffered(true);
	//	this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setUp() {
		aSet.setPhis();
		aSet.setFork();
		System.out.println("Da xong");
		
	}
	public void startThread() {
		thread=new Thread(this);
		thread.start();
//		for(int i=0;i<5;i++)
		phis[0].start();
		phis[1].start();
		phis[2].start();
		phis[3].start();
		phis[4].start();
	}
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime=System.nanoTime();
		long currentTime;
		long timer=0;
		int drawCount=0;
		while(thread!=null) {
			currentTime =System.nanoTime();
			delta+=(currentTime-lastTime)/drawInterval;
			timer+=(currentTime-lastTime);
			lastTime=currentTime;
			if(delta>=1) {
			update();
			repaint();
			
			delta--;
			drawCount++;
			}
			if(timer>=1000000000)
			{   
				drawCount=0;
				timer=0;
			}
		}
	}

	public void update() {
//		System.out.println("Run");
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		BufferedImage image=null;
		try {
			image=ImageIO.read(getClass().getResourceAsStream("/res/dinning philosopher.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		g2.drawImage(image,0,0,null);
		for(int i=0;i<5;i++)
			phis[i].draw(g2);
		for(int i=0;i<5;i++) {
			forks[i].draw(g2);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,86F));
			String text= String.valueOf(forks[i].phi);
			int x=forks[i].x;
			int y=forks[i].y;
			g2.setColor(Color.blue);
			g2.drawString(text,x,y);
		}
			
		g2.dispose();
	}
}
