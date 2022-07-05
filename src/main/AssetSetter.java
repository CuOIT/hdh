package main;

import java.awt.Color;
import entity.Fork;
import entity.Philosopher;

public class AssetSetter {
	Panel p;
	
	public AssetSetter(Panel p) {
		this.p=p;
	}

	public void setPhis() {
		for(int i=0;i<5;i++) {
			if (p.phis[i] != null)
				p.phis[i].interrupt();
		}
		Philosopher.eatTotal=0;
		Philosopher.eatTotalInARound=0;
		p.startTime=System.currentTimeMillis();
		p.phis[0]=new Philosopher(p, 490,130,  370,220,460,220, 0);
		p.phis[1]=new Philosopher(p, 650,270,  570,320,580,360, 1);
		p.phis[2]=new Philosopher(p, 590,480,  510,500,470,560, 2);
		p.phis[3]=new Philosopher(p, 310,480,  350,560,310,500, 3);
		p.phis[4]=new Philosopher(p, 280,270,  250,360,270,320, 4);
	}
	public void setFork() {
		
		p.forks[0]=new Fork(p,340,300,0);
		p.forks[1]=new Fork(p,490,300,1);
		p.forks[2]=new Fork(p,560,430,2);
		p.forks[3]=new Fork(p,420,560,3);
		p.forks[4]=new Fork(p,270,430,4);
		
	}
}
