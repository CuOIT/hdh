package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse_Listener extends MouseAdapter{

	Panel p;
	UI_Button button;
	public Mouse_Listener(Panel p,UI_Button button) {
		super();
		this.button=button;
		this.p=p;
	}
	public Mouse_Listener() {
		// TODO Auto-generated constructor stub
	}
	public void mouseClicked(MouseEvent e ) {
		button.p.startSolution(button.num);
		System.out.println(e.getX()+ " "+e.getY());
	}
	public void mouseEntered(MouseEvent e) {
	}
}
