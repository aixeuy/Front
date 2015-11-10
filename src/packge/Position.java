package packge;

import java.awt.Color;
import java.awt.Rectangle;

public class Position {
int numofdiv;
int id;
int index;
boolean owner;
boolean[] tranch;
Division[] div;
Position[] next;
int x=-10,y=-10;
public Position(int id,int index){//id 1up 0mid -1low
	owner=false;                          
	numofdiv=0;                   
	this.id=id;                   
	this.index=index;
	if(index>=2&&index<12){
		x=-100+100*index;
		if(id==1){
			y=0;
		}
		else{
			y=600;
		}
	}
	else if(id==0){
		x=-100+100*(2+index);
		y=300;
	}
	else if(id==1){
		switch(index){
		case 1:x=100;y=100;break;
		case 0:x=100;y=200;break;
		case 12:x=1000;y=100;break;
		case 13:x=1000;y=200;break;
		}
	}
	else{
		switch(index){
		case 1:x=100;y=500;break;
		case 0:x=100;y=400;break;
		case 12:x=1000;y=500;break;
		case 13:x=1000;y=400;break;
		}
	}
	div=new Division[10];
	tranch=new boolean[4];
	tranch[0]=false;
	tranch[1]=false;
	tranch[2]=false;
	tranch[3]=false;
	next=new Position[4];//0left 1right 2up 3down
}
public void setconnection(){
	////////////////////////////left and right
	if((id==1)&&(index>=2&&index<=10)){ //2 3 4 5 6 7 8 9 1011
		next[1]=Map.posup[index+1];     //1                 12
	}                                   //0                 13
	if((id==-1)&&(index>=2&&index<=10)){//0 1 2 3 4 5 6 7 8 9
		next[1]=Map.poslow[index+1];    //0                 13
	}                                   //1                 12
	if((id==1)&&(index>=3&&index<=11)){ //2 3 4 5 6 7 8 9 1011
		next[0]=Map.posup[index-1];     
	}
	if((id==-1)&&(index>=3&&index<=11)){
		next[0]=Map.poslow[index-1];
	}
	if(id==0&&(index>=0&&index<=8)){
		next[1]=Map.posmid[index+1];
	}
	if(id==0&&(index>=1&&index<=9)){
		next[0]=Map.posmid[index-1];
	}
	///////////////////////////////////up up
	if(id==1&&(index>=0&&index<=1)){
		next[2]=Map.posup[index+1];
	}
	if(id==-1&&(index>=0&&index<=1)){
		next[3]=Map.poslow[index+1];
	}
	if(id==1&&(index>=12&&index<=13)){
		next[2]=Map.posup[index-1];
	}
	if(id==-1&&(index>=12&&index<=13)){
		next[3]=Map.poslow[index-1];
	}
	///////////////////////////////////up down
	if(id==1&&(index>=1&&index<=2)){
		next[3]=Map.posup[index-1];
	}
	if(id==-1&&(index>=1&&index<=2)){
		next[2]=Map.poslow[index-1];
	}
	if(id==1&&(index>=11&&index<=12)){
		next[3]=Map.posup[index+1];
	}
	if(id==-1&&(index>=11&&index<=12)){
		next[2]=Map.poslow[index+1];
	}
	///////////////////////////////////special
	if(id==0&&index==0){
		next[2]=Map.posup[0];
		next[3]=Map.poslow[0];
	}
	if(id==0&&index==9){
		next[2]=Map.posup[13];
		next[3]=Map.poslow[13];
	}
	if(id==1&&index==0){
		next[3]=Map.posmid[0];
	}
	if(id==1&&index==13){
		next[3]=Map.posmid[9];
	}
	if(id==-1&&index==0){
		next[2]=Map.posmid[0];
	}
	if(id==-1&&index==13){
		next[2]=Map.posmid[9];
	}
}
public void adddiv(Division div){
	this.div[numofdiv]=div;
	numofdiv++;
}
public void resetpos(int x,int y){
	if(numofdiv>=4){
	for(int i=0;i<numofdiv;i++){
		int h=Map.ver((100)/(numofdiv+0.0));
		div[i].pn.setBounds(new Rectangle(x,y+i*h,Map.width,h));
		div[i].pn.add(div[i].bt);
	}
	}
	else{
		for(int i=0;i<numofdiv;i++){
			div[i].pn.setBounds(new Rectangle(x,y+i*Map.width,Map.width,Map.width));
			div[i].pn.add(div[i].bt);
		}
	}
}
public void resetpos(){
	int x,y;
	x=0;y=0;
	if(id==0){
		x=100+index*100+50-Map.width/2;
		y=300;
	}
	else{
		switch(index){
		case 0:x=100+50-Map.width/2;y=200;break;
		case 1:x=100+50-Map.width/2;y=100;break;
		case 13:x=1000+50-Map.width/2;y=200;break;
		case 12:x=1000+50-Map.width/2;y=100;break;
		default:y=0;x=100+100*(index-2)+50-Map.width/2;break;
		}
		if(id==-1){
			y=600-y;
		}
	}
	resetpos(x,y);
}
public void removediv(Division div){
	for(int i=0;i<numofdiv;i++){
		if(this.div[i]==div){
			this.div[i]=this.div[numofdiv-1];
			numofdiv--;
			break;
		}
	}///////////////////////////////////////////////////to write//also change num,repaint
}
}
