package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Panel;

public class Fork {
	public Panel p;
	public BufferedImage image;
	public int x,y;
	public boolean taken=false;
	public int phi=-1;
	public Fork(Panel p,int x,int y) {
		this.p=p;
		this.x=x;
		this.y=y;
		getImage();
	//	System.out.println("Done");
	}
	public void getImage() {
		try {
			image=ImageIO.read(getClass().getResourceAsStream("/res/fork.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g2) {
		g2.drawImage(image,x,y,null);
	}
}
