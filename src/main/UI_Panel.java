package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class UI_Panel extends JPanel {

	Panel p;
	public UI_Button button0,button1,button2,button3,button4,button5;
	public UI_Panel(Panel p) {
		this.p=p;
		button0=new UI_Button("Truong hop Deadlock", 0,p);
		button1=new UI_Button("Giai phap 1",1,p);
		button2=new UI_Button("Giai phap 2",2,p);
		button3=new UI_Button("Giai phap 3",3,p);
		button4=new UI_Button("Giai phap 4",4,p);
		button5=new UI_Button("Giai phap 5",5,p);
		
		setPreferredSize(new Dimension(400,700));
		setLayout(new GridLayout(6,1,50,50));
		setBackground(Color.white);
		add(button0);
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		add(button5);
		


	}
}
