package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key_Handler implements KeyListener{

	Panel p;
	public boolean spacePressed=false;
	public Key_Handler(Panel p) {
		this.p=p;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code==KeyEvent.VK_SPACE){
//			p.state=(p.state+1)%2;
//			System.out.println(p.state );
			}

		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
}
