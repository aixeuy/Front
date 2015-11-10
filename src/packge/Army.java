package packge;

public class Army {
int numofdiv;
int index;
boolean controler,side;
int maxnum;
String name;
Position base;
public Army(int index,boolean side){
	this.index=index;
	this.side=side;
	this.controler=side;
	numofdiv=0;
	if(index==1){
		maxnum=4;
	}
	else{
		maxnum=3;
	}
	switch(index){
	case 0:name="A";if(side){base=Map.posup[2];}else{base=Map.posup[11];};break;
	case 1:name="B";if(side){base=Map.posmid[0];}else{base=Map.posmid[9];};break;
	case 2:name="C";if(side){base=Map.poslow[2];}else{base=Map.poslow[11];};break;
	default:name=null;base=Map.deadpos;
	}
}
public boolean add(Division div){
	if(div.side==side&&numofdiv<maxnum){
		numofdiv++;
		if(side){
			div.army=Map.armyt[index];
		}
		else{
			div.army=Map.armyf[index];
		}
		return true;
	}
	else{
		return false;
	}
}
public void change(Division div){
	Army arm=div.army;
	div.army.remove(div);
	if(!add(div)){
		arm.add(div);
	}
}
public boolean remove(Division div){
		numofdiv--;
		div.army=Map.nullarm;
		return true;
}
}
