package packge;

import java.awt.Button;
import java.awt.Color;
import java.awt.Panel;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Division {
int cost;
boolean supplied;
boolean moved;
boolean tmpmoved;
int supply;
boolean side;
Panel pn;
JButton bt;
Position pos;
Army army;
boolean selected;
public void setbtcolor(){
	bt.setForeground(Color.black);
	if(side){
		if(supplied){
		bt.setBackground(new Color (160,200,255));
		}
		else{
			if(moved){
				bt.setBackground(Color.black);
			}
			else{
			bt.setBackground(Color.red);
			}
		}
	}
	else{
		if(supplied){
		bt.setBackground(Color.lightGray);
		}
		else{
			if(moved){
				bt.setBackground(Color.black);
			}
			else{
			bt.setBackground(Color.white);
			}
		}
	}
	if(side){
		if(selected){
			pn.setBackground(Color.red);
		}
		else{
		pn.setBackground(Color.white);
		}
	}
	else{
		if(selected){
			pn.setBackground(Color.black);
		}
		else{
		pn.setBackground(Color.red);
		}
	}
}
public Division(){
	selected=false;
	tmpmoved=false;
	cost=0;
	supplied=true;
	moved=false;
	supply=0;
	pn=new Panel();
	bt=new JButton();
}
public void setbtpos(){
	setbtcolor();
	int x,y;
	x=0;y=0;
	if(pos.id==0){
		x=100+pos.index*100+50-Map.width/2;
		y=300;
	}
	else{
		switch(pos.index){
		case 0:x=100+50-Map.width/2;y=200;break;
		case 1:x=100+50-Map.width/2;y=100;break;
		case 13:x=1000+50-Map.width/2;y=200;break;
		case 12:x=1000+50-Map.width/2;y=100;break;
		default:y=0;x=100+100*(pos.index-2)+50-Map.width/2;break;
		}
		if(pos.id==-1){
			y=600-y;
		}
	}
	if(pos.numofdiv>=4||pos.index<0){
	pos.resetpos(x, y);	
	}
	else{
	pn.setBounds(new Rectangle(x,y+(pos.numofdiv-1)*Map.width,Map.width,Map.width));
	pn.add(bt);
	}
}
}
