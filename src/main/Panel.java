package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import entity.Fork;
import entity.Philosopher;
public class Panel extends JPanel implements Runnable{
	Thread thread;
//	public final int playState=1;
//	public final int pauseState=0;
//	int state=playState;
	public static int solutionNum;
	public int screenWidth=1200;
	public int screenHeight=700;
	public Fork[] forks=new Fork[5];
	public Philosopher[] phis=new Philosopher[5];
	public int numberEating=1;
	public Object obj=new Object();
	int FPS=60;
	public Key_Handler keyH=new Key_Handler(this);
	public AssetSetter aSet=new AssetSetter(this);
	long startTime=System.currentTimeMillis();
	public Mouse_Listener mouseL=new Mouse_Listener(this, new UI_Button("", -1, this));
	// Class dùng để khởi tạo vị trí của các Phi và các Fork
	public Panel() {
		this.addMouseListener(mouseL);
		this.setPreferredSize(new Dimension(850,700));
		this.setBackground(Color.WHITE);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setUp() {
		aSet.setPhis();
		aSet.setFork();
		
	}
	public void startThread() {
		thread=new Thread(this, "Renderer");
		thread.start();
	}
	public void startSolution0()
	{	
		aSet.setPhis();
		aSet.setFork();
		solutionNum=0;
		for(int i=0;i<5;i++) {
			phis[i].start();
		}
		System.out.println("Giai phap 0");
	}
	public void startSolution1() {
		System.out.println("Giai phap 1");

		aSet.setPhis();
		aSet.setFork();
		solutionNum=1;
		for(int i=0;i<5;i++) {
			phis[i].start();
	}
	}
	public void startSolution2() {

		aSet.setPhis();
		aSet.setFork();	
		solutionNum=2;
		numberEating=1;

		for(int i=0;i<5;i++) {
			phis[i].start();
	}
		System.out.println("Giai phap 2");
	}
	public void startSolution3() {

		
		aSet.setPhis();
		aSet.setFork();
		solutionNum=3;
		numberEating=2;

		for(int i=0;i<5;i++) {
			phis[i].start();
	}
		System.out.println("Giai phap 3");
	}
	public void startSolution4() {
		System.out.println("Giai phap 4");
		
		aSet.setPhis();
		aSet.setFork();
		solutionNum=4;

		for(int i=0;i<5;i++) {
			phis[i].start();
	}
	}
	public void startSolution5() {
		System.out.println("Giai phap 5");

	
		aSet.setPhis();
		aSet.setFork();
		solutionNum=5;

		for(int i=0;i<5;i++) {
			phis[i].start();
	}
	}
	
	public void startSolution(int i) {
		switch(i) {
		case 0:
			startSolution0();
			break;
		case 1:
			startSolution1();
			break;
		case 2:
			startSolution2();
			break;
		case 3:
			startSolution3();
			break;
		case 4:
			startSolution4();
			break;
		case 5:
			startSolution5();
			break;
		}
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

	public void update()  {
			
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
		// Draw background
		g2.drawImage(image,0,0,null);
		
		//Draw forks
		for(int i=0;i<5;i++) {
			forks[i].draw(g2);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,86F));
//			String text= String.valueOf(forks[i].phi);
//			int x=forks[i].x;
//			int y=forks[i].y;
//			g2.setColor(Color.blue);
//			g2.drawString(text,x,y);
		}
		
		//Draw philosophers
		for(int i=0;i<5;i++) {
			phis[i].draw(g2);
			int x=phis[i].x-50;
			int y=phis[i].y-50;
		}
		
		//Draw eatCounter
			for(int i=0;i<5;i++) {
				int x=20;
				int y=20+30*i;
				g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
				String text= "Eat counter of phi "+i+" :"+String.valueOf(phis[i].eatCounter);
				g2.setColor(Color.red);
				g2.drawString(text,x,y);
			}
		//Draw eatTotal
		String eatTotal_ui="Total eat : "+String.valueOf(Philosopher.eatTotal);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20));
		int x=20;
		int y=170;
		g2.setColor(Color.RED);
		g2.drawString(eatTotal_ui,x,y);
		
		
		String time="Time : "+String.valueOf((System.currentTimeMillis()-startTime)/1000);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20));
		int xt=20;
		int yt=200;
		g2.setColor(Color.red);
		g2.drawString(time,xt,yt);
				g2.dispose();
	}

	
}
