package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Panel;

public class Fork {
	public Panel p;
	public int num;
	public BufferedImage image;
	public int x,y;
	public int xDefault,yDefault;
	public int phi=-1;
	public Fork(Panel p,int xDefault,int yDefault,int num) {
		this.num=num;
		this.p=p;
		this.xDefault=xDefault;
		this.yDefault=yDefault;
		this.x=xDefault;
		this.y=yDefault;
		getImage();
	
	}
	public void getImage() {
		try {
			image=ImageIO.read(getClass().getResourceAsStream("/res/fork.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void move(int xH,int yH) {
		
		x=xH;
		y=yH;
	}
	public void takeFork(int phiNum) throws InterruptedException {
			synchronized(this) {
			while(!takable(phiNum)) {
				this.wait();
			}
			takenFromPhiNum(phiNum);
			//	this.notifyAll();
		}
	}
	public boolean takable(int phiNum) {
		if(phi==-1) {
			return true;
		}
		else return false;
	}
	public void takenFromPhiNum(int phiNum) {
		phi=phiNum;
		if(num==phiNum)
			move(p.phis[phiNum].xRightHand,p.phis[phiNum].yRightHand);
		else 
			move(p.phis[phiNum].xLeftHand,p.phis[phiNum].yLeftHand);
	}
	public  void returnFork() {
		synchronized(this) {
		move(xDefault,yDefault);
		phi=-1;
		this.notifyAll();
		}
	}
	public void draw(Graphics2D g2) {
		g2.drawImage(image,x,y,null);
	}
}
