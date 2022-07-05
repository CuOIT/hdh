package entity;

import java.awt.Graphics2D;

import main.Panel;

public abstract class Entity {
	public Panel p;
	public int num;
	public int x,y;
	
	public Entity(Panel p,int num,int x,int y) {
		this.p=p;
		this.num=num;
		this.x=x;
		this.y=y;
	}
	public abstract void draw(Graphics2D g2);
}

