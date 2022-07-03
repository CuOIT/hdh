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
		
		public static Object obj=new Object();
		public Panel p;
		public int num;
		public int x;
		public int y;
		public int xLeftHand,yLeftHand,xRightHand,yRightHand;
		BufferedImage image,thinking,waiting,eating,out;
		public int eatCounter=0;
		public static int eatTotal=0,eatTotalInARound=0;

		public Philosopher(Panel p,int x,int y,int xRightHand,int yRightHand,int xLeftHand,int yLeftHand,int num) {
			this.num=num;
			this.p=p;
			this.x=x;
			this.y=y;
			this.xLeftHand=xLeftHand;
			this.yLeftHand=yLeftHand;
			this.xRightHand=xRightHand;
			this.yRightHand=yRightHand;
			getImage();
		}
			
		public void getImage() {
			try {
				waiting=ImageIO.read(getClass().getResourceAsStream("/res/waiting.png"));
				thinking=ImageIO.read(getClass().getResourceAsStream("/res/thinking.png"));
				eating=ImageIO.read(getClass().getResourceAsStream("/res/eating.png"));
				out=ImageIO.read(getClass().getResourceAsStream("/res/Boar_Left_1.png"));
			}catch(IOException e) {
				e.printStackTrace();
		}
			
	}
		
		public void eating() throws InterruptedException {
			image=waiting;
			if(p.solutionNum==4) {
				int j=num%2;
				takeFork((num+j)%5);
				sleep(300);
				takeFork((num+1-j)%5);
				}
			else if(p.solutionNum==5) {
				takeFork(num%5);
				sleep(100);
				if(p.forks[(num+1)%5].phi!=-1) 
					{
						image=thinking;
						p.forks[num%5].returnFork();
						p.forks[num%5].move(p.forks[num%5].xDefault,p.forks[num%5].yDefault);
					}
				else {
					takeFork((num+1)%5);
				}
			}
			else {
				takeFork(num%5);
				sleep(50);
				takeFork((num+1)%5);
			}
			if(p.forks[num%5].phi==num && p.forks[(num+1)%5].phi==num)
				{
					image=eating;
					eatCounter++;
					eatTotal++;
					eatTotalInARound++;
					sleep(2000);
					p.forks[num%5].returnFork();
					p.forks[(num+1)%5].returnFork();
					image=thinking;		
				}
		}
		
		private void waiting(int i) throws InterruptedException {
				sleep(10);
		}
		
		private void require() throws InterruptedException {
			synchronized(obj) {
			while(p.numberEating<=0) {
					obj.wait();
			}
			p.numberEating--;
			
			}
	
		}
		
		private void done() {
			synchronized(obj) {
				p.numberEating++;
				if(p.numberEating==1) obj.notify();
				else if(p.numberEating==2) obj.notifyAll();
			}
		}
		
		private void takeFork(int i) throws InterruptedException {
			synchronized(p.forks[i]) {
				while(!p.forks[i].takable(num)) {
					p.forks[i].wait();
				}
				p.forks[i].phi=num;
				if(i==num)
					p.forks[i].move(xRightHand,yRightHand);
				else 
					p.forks[i].move(xLeftHand,yLeftHand);
				System.out.println(num+" da lay dia");
		}
			}
		
		private void thinking() throws InterruptedException {
			sleep((long)(Math.random()*1000+2000));
		}
		
		public void goOutCheck() throws InterruptedException {
			synchronized(obj) {
				if(eatTotalInARound>=10 || eatTotal==0) {
					//wait va goi 1 ong vao an
					if(eatTotalInARound>=10) eatTotalInARound-=10;
					image=out;
					obj.notify();
					System.out.println(num+" ra ngoai");
					obj.wait();
				}
			}
		}
		
		public void run() {
			boolean run=true;
			try {
				sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				run=false;
			}
			switch(p.solutionNum) {
			case 0:
				System.out.println(p.solutionNum);
				while(run) {
					try {
					eating();
					thinking();
					} catch (InterruptedException e) {
						run = false;
					}
				}
				break;
			case 1:
				while(run) {
					try {
					goOutCheck();
					eating();
					thinking();
					} catch (InterruptedException e) {
						run = false;
					}
				}
				break;
			case 2:
				while(run) {
					try {
					require();
					eating();
					done();
					thinking();
					} catch (InterruptedException e) {
						run = false;
					}
				}
				break;
			case 3:
				while(run) {
					try {
						
					require();
					eating();
					done();
					thinking();
				
					} catch (InterruptedException e) {
						run = false;
					}
				}
				break;
			case 4:
				while(run) {
					try {
					eating();
					thinking();
					} catch (InterruptedException e) {
						run = false;
					}
				}
				break;
			case 5:
				while(run) {
					try {
					eating();
					thinking();
					} catch (InterruptedException e) {
						run = false;
					}
				}
				break;
			}
	//		while(run) {
	//			try {
	//			eating();
	//			thinking();
	//			} catch (InterruptedException e) {
	//				run = false;
	//			}
	//		}
			}
	
		public void draw(Graphics2D g2) {
			g2.drawImage(image,x,y,null);	
		}
}
	//	public void pairSemaphore(Fork f1,Fork f2) {
	//		if(f1.phi+f2.phi==-2) {
	//			f1.phi=num;
	//			f2.phi=num;
	//		};
	//	}