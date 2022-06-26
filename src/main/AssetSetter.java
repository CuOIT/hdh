package main;

import entity.Fork;
import entity.Philosopher;

public class AssetSetter {
	Panel p;
	
	public AssetSetter(Panel p) {
		this.p=p;
	}
	
	public void setPhis() {
		p.phis[0]=new Philosopher(p, 490, 130, 0);
		p.phis[1]=new Philosopher(p, 650, 270, 1);
		p.phis[2]=new Philosopher(p, 590, 480, 2);
		p.phis[3]=new Philosopher(p, 310, 480, 3);
		p.phis[4]=new Philosopher(p, 280, 270, 4);

	}
	public void setFork() {
		p.forks[0]=new Fork(p,340,300);
		p.forks[1]=new Fork(p,490,300);
		p.forks[2]=new Fork(p,560,430);
		p.forks[3]=new Fork(p,420,560);
		p.forks[4]=new Fork(p,270,430);
		
	}
}
