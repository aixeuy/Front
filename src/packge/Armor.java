package packge;

import java.awt.Button;

import javax.swing.JButton;

public class Armor extends Division{
	public Armor(boolean side){
		cost=5;
		supplied=true;
		moved=false;
		supply=1;
		bt=new JButton("O");
		this.side=side;
	}
}
