package packge;

import java.awt.Button;

import javax.swing.JButton;

public class Infantry extends Division{
	public Infantry(boolean side){
		cost=10;
		supplied=true;
		moved=false;
		supply=2;
		bt=new JButton("X");
		this.side=side;
	}
}
