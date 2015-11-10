package packge;

import java.awt.Rectangle;

public class Nullpos extends Position {
	public Nullpos(int id, int index) {
		super(id, index);
		this.id=0;
		this.index=-1;
		owner=false;                            
		numofdiv=0;                   
		div=new Division[10];
		next=new Position[4];//0left 1right 2up 3down
		// TODO Auto-generated constructor stub
	}
	public void resetpos(int x,int y){
	}
	public void resetpos(){
	}
}
