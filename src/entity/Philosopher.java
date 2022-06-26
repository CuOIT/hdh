package entity;
import java.awt.Graphics2D;
//import java.awt.Graphics;
//import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.Panel;
	public class Philosopher extends Thread {
		public Panel p;
		public int num;
		public int x;
		public int y;
		BufferedImage image,thinking,waiting,eating;
		//public boolean leftFork,rightFork;
		public int eatCounter=0;
	public Philosopher(Panel p,int x,int y,int num) {
//		leftFork=false;
//		rightFork=false;
		this.num=num;
		this.p=p;
		this.x=x;
		this.y=y;
		getImage();
	}
		
	public void getImage() {
		try {
			waiting=ImageIO.read(getClass().getResourceAsStream("/res/waiting.png"));
			thinking=ImageIO.read(getClass().getResourceAsStream("/res/thinking.png"));
			eating=ImageIO.read(getClass().getResourceAsStream("/res/eating.png"));
		}catch(IOException e) {
			e.printStackTrace();
	}
		
}
	public void eating()  {
		image=waiting;
		System.out.println(num+ "try to eat");
		require();
		takeFork(num%5);
		try {
			sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		takeFork((num+1)%5);
		if(p.forks[num%5].phi==num && p.forks[(num+1)%5].phi==num)
		{
			image=eating;
			eatCounter++;
			try {
				//System.out.println("Sleeping");
				sleep(1000);
				//System.out.println("Slept");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p.forks[num%5].phi=-1;
			p.forks[(num+1)%5].phi=-1;
			done();
			image=thinking;		
		}
		System.out.println(num+ " ate");
	}
	private void waiting(int i) {
	System.out.println(num+ "Waiting for fork "+i);
	}
	public void require() {
		while(p.numberEating<=0) waiting(num);
		p.numberEating--;
	}
	public void done() {
		p.numberEating++;
	}
	private void takeFork(int i) {
		// TODO Auto-generated method stub
			while(p.forks[i].phi!=-1) waiting(i);
			p.forks[i].phi=num;
	}
	void thinking() {
		Random rd=new Random();
		int rand=rd.nextInt(5000,6000);
		try {
			sleep(rand);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run() {
		while(true) {
		//	waiting();
			eating();
			thinking();
		}
	}
	public void draw(Graphics2D g2) {
		g2.drawImage(image,x,y,null);

	}
}