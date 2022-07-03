package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class UI_Button extends JButton {

	Panel p;
	public int num;
	public UI_Button(String text,int num,Panel p) {
		super(text);
		this.p=p;
		this.num=num;
	//	setFocusable(false);
	Mouse_Listener mouse_Listener= new Mouse_Listener(p,this);
	addMouseListener(mouse_Listener);
	}
}
