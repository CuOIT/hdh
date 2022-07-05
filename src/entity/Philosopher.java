package entity;
import java.awt.Graphics2D;
//import java.awt.Graphics;
//import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.Panel;
	public class Philosopher extends Entity implements Runnable{
		public Thread t=new Thread();
		public static Object obj=new Object();
		public int xLeftHand,yLeftHand,xRightHand,yRightHand;
		BufferedImage image,thinking,waiting,eating,out;
		public int eatCounter=0;
		public static int eatTotal=0,eatTotalInARound=0;

		public Philosopher(Panel p,int x,int y,int xRightHand,int yRightHand,int xLeftHand,int yLeftHand,int num) {
			super(p,num,x,y);
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
				out=ImageIO.read(getClass().getResourceAsStream("/res/goOut.png"));
			}catch(IOException e) {
				e.printStackTrace();
		}
			
	}
		
		public void start() {
			t=new Thread(this);
			t.start();
		}
		
		public void run() {
			boolean run=true;
			try {
				t.sleep(500);
			} catch (InterruptedException e1) {
				run=false;
			}
			switch(p.solutionNum) {
			case 0:
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
			}

		public void eating() throws InterruptedException {
			image=waiting;
			if(p.solutionNum==4) {
				int j=num%2;
				p.forks[(num+j)%5].takeFork(num);
				t.sleep(600);
				p.forks[(num+1-j)%5].takeFork(num);
				}
			else if(p.solutionNum==5) {
				p.forks[num%5].takeFork(num);
				t.sleep(100);
				if(!p.forks[(num+1)%5].takable(num)) 
					{
						image=thinking;
						p.forks[num%5].returnFork();
						p.forks[num%5].move(p.forks[num%5].xDefault,p.forks[num%5].yDefault);
					}
				else {
					p.forks[(num+1)%5].takeFork(num);
				}
			}
			else {
				p.forks[num%5].takeFork(num);
				t.sleep(50);
				p.forks[(num+1)%5].takeFork(num);
			}
			if(p.forks[num%5].phi==num && p.forks[(num+1)%5].phi==num)
				{
					System.out.println("[ "+t.getId()+" ] dang an dia so "+num+((num+1)%5));
					image=eating;
					eatCounter++;
					eatTotal++;
					eatTotalInARound++;
					t.sleep((long)(Math.random()*1000+2000));					
					p.forks[num%5].returnFork();
					p.forks[(num+1)%5].returnFork();
					image=thinking;		
				}
		}
		
//		private void waiting(int i) throws InterruptedException {
//				t.sleep(10);
//		}
		
		private void thinking() throws InterruptedException {
			t.sleep((long)(Math.random()*1000+2000));
	
		}
		
		private void require() throws InterruptedException {
			synchronized(obj) {
			while(p.numberEating<=0) {
					obj.wait();
			}
			p.numberEating--;
			
			}
	
		}
		
		private void done() throws InterruptedException{
			synchronized(obj) {
				p.numberEating++;
				if(p.numberEating==1) obj.notify();
				else if(p.numberEating==2) obj.notifyAll();
			}
		}
		
		public void goOutCheck() throws InterruptedException {
			//Luan phien kick 1 ong ra ngoai
			synchronized(obj) {
				if(eatTotalInARound>=10 || eatTotal==0) {
					if(eatTotalInARound>=10) eatTotalInARound-=10;
					image=out;
					obj.notify();
					//System.out.println("[ "+t.getId()+" ]"+" ra ngoai");
					obj.wait();
				}
			}
		}
			
		public void draw(Graphics2D g2) {
			g2.drawImage(image,x,y,null);	
		}
	
		public void interrupt() {
			t.interrupt();
		}
	}
	