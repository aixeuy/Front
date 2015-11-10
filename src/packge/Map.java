package packge;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Map extends JApplet{
	public static int width;
	public static Nullpos deadpos;
	public static Army nullarm;
	public static Position[] posup=new Position[14];
	public static Position[] poslow=new Position[14];
	public static Position[] posmid=new Position[10];
	public static Division[] divt=new Division[10];
	public static Division[] divf=new Division[10];
	public static Army[] armyt=new Army[3];
	public static Army[] armyf=new Army[3];
	public static Division[] selected=new Division[4];
	public static int numofdivsel=0;
	public static boolean moved=false;
	public static int sidecode=3;
	public static int rest=0;
	public static boolean start=false;
	public JButton l;
	public JButton ll;
	public JButton u;
	public JButton uu;
	public JButton d;
	public JButton dd;
	public JButton r;
	public JButton rr;
	public JButton next;
	public JButton toA;
	public JButton toB;
	public JButton toC;
	public JButton lt;
	public JButton rt;
	public JButton ut;
	public JButton dt;
	public JButton nt;
	public Panel pl;
	public Panel pll;
	public Panel pu;
	public Panel puu;
	public Panel pd;
	public Panel pdd;
	public Panel pr;
	public Panel prr;
	public Panel pnext;
	public TextField lb;
	public TextField reminder;
	//Image imaget=getImage(this.getClass().getResource("true.jpg"));
	//Image imagef=getImage(this.getClass().getResource("false.jpg"));
	//Jpanel jp;
	public void init(){
		//setbt();
		this.setLayout(null);
		width=30;
		for(int i=0;i<14;i++){
			posup[i]=new Position(1,i);
			if(i<7){
				posup[i].owner=true;
			}
		}
		for(int i=0;i<14;i++){
			poslow[i]=new Position(-1,i);
			if(i<7){
				poslow[i].owner=true;
			}
		}
		for(int i=0;i<10;i++){
			posmid[i]=new Position(0,i);
			if(i<5){
				posmid[i].owner=true;
			}
		}
		for(int i=0;i<14;i++){
			posup[i].setconnection();
		}
		for(int i=0;i<14;i++){
			poslow[i].setconnection();
		}
		for(int i=0;i<10;i++){
			posmid[i].setconnection();
		}
		for(int i=0;i<3;i++){
			armyt[i]=new Army(i, true);
			armyf[i]=new Army(i, false);
		}
		setbt();
		deadpos=new Nullpos(0,0);
		nullarm=new Nullarm(-1,true);
		setdiv();
		}
	public void paint(Graphics g){
		
		Image imaget=getImage(this.getClass().getResource("true.jpg"));
		Image imagef=getImage(this.getClass().getResource("false.jpg"));
		//jp=new Jpanel(imaget,imagef);
		/*int x=0;int y=0;
		if(numofdivsel!=0){
			x=selected[numofdivsel-1].pos.x;
			y=selected[numofdivsel-1].pos.y;
			
		}g.clearRect(0, 0, 1000, 1000);*/
		int tlength=70;
		int twidth=5;
		int slength=10;
		int swidth=10;
		for(int i=2;i<12;i++){
			if(posup[i].owner){
				g.drawImage(imaget,-100+100*i,0,100,100,Color.blue,this);
			}
			else{
				g.drawImage(imagef,-100+100*i,0,100,100,Color.black,this);
			}
			//g.setColor(Color.white);
			if(posup[i].tranch[0]){
				g.drawRect(-100+100*i+swidth, 0+slength, twidth, tlength);
			}
			if(posup[i].tranch[1]){
				g.drawRect(-100+100*i+100-swidth-twidth, 0+slength, twidth, tlength);
			}
			if(posup[i].tranch[2]){
				g.drawRect(-100+100*i+slength, 0+swidth, tlength, twidth);
			}
			if(posup[i].tranch[3]){
				g.drawRect(-100+100*i+slength, 0+100-twidth-swidth, tlength, twidth);
			}
			//g.setColor(Color.black);
		}
		for(int i=2;i<12;i++){
			if(poslow[i].owner){
				g.drawImage(imaget,-100+100*i,600,100,100,Color.blue,this);
			}
			else{
				g.drawImage(imagef,-100+100*i,600,100,100,Color.black,this);
			}
			//g.setColor(Color.white);
			if(poslow[i].tranch[0]){
				g.drawRect(-100+100*i+swidth, 600+slength, twidth, tlength);
			}
			if(poslow[i].tranch[1]){
				g.drawRect(-100+100*i+100-swidth-twidth, 600+slength, twidth, tlength);
			}
			if(poslow[i].tranch[2]){
				g.drawRect(-100+100*i+slength, 600+swidth, tlength, twidth);
			}
			if(poslow[i].tranch[3]){
				g.drawRect(-100+100*i+slength, 600+100-twidth-swidth, tlength, twidth);
			}
			//g.setColor(Color.black);
		}
		for(int i=2;i<12;i++){
			if(posmid[i-2].owner){
				g.drawImage(imaget,-100+100*i,300,100,100,Color.blue,this);
			}
			else{
				g.drawImage(imagef,-100+100*i,300,100,100,Color.black,this);
			}
			//g.setColor(Color.white);
			if(posmid[i-2].tranch[0]){
				g.drawRect(-100+100*i+swidth, 300+slength, twidth, tlength);
			}
			if(posmid[i-2].tranch[1]){
				g.drawRect(-100+100*i+100-swidth-twidth, 300+slength, twidth, tlength);
			}
			if(posmid[i-2].tranch[2]){
				g.drawRect(-100+100*i+slength, 300+swidth, tlength, twidth);
			}
			if(posmid[i-2].tranch[3]){
				g.drawRect(-100+100*i+slength, 300+100-twidth-swidth, tlength, twidth);
			}
			//g.setColor(Color.black);
		}
		if(posup[0].owner){
			g.drawImage(imaget,100,200,100,100,Color.blue,this);
		}
		else{
			g.drawImage(imagef,100,200,100,100,Color.black,this);
		}
		//g.setColor(Color.white);
		if(posup[0].tranch[0]){
			g.drawRect(100+swidth, 200+slength, twidth, tlength);
		}
		if(posup[0].tranch[1]){
			g.drawRect(100+100-swidth-twidth, 200+slength, twidth, tlength);
		}
		if(posup[0].tranch[2]){
			System.out.print("y");
			g.drawRect(100+slength, 200+swidth, tlength, twidth);
		}
		if(posup[0].tranch[3]){
			g.drawRect(100+slength, 200+100-twidth-swidth, tlength, twidth);
		}
		//g.setColor(Color.black);
		if(posup[1].owner){
			g.drawImage(imaget,100,100,100,100,Color.blue,this);
		}
		else{
			g.drawImage(imagef,100,100,100,100,Color.black,this);
		}
		//g.setColor(Color.white);
		if(posup[1].tranch[2]){
			g.drawRect(100+slength, 100+swidth, tlength, twidth);
		}
		if(posup[1].tranch[3]){
			g.drawRect(100+slength, 100+100-twidth-swidth, tlength, twidth);
		}
		//g.setColor(Color.black);
		if(poslow[0].owner){
			g.drawImage(imaget,100,400,100,100,Color.blue,this);
		}
		else{
			g.drawImage(imagef,100,400,100,100,Color.black,this);
		}
		//g.setColor(Color.white);
		if(poslow[0].tranch[0]){
			g.drawRect(100+swidth, 400+slength, twidth, tlength);
		}
		if(poslow[0].tranch[1]){
			g.drawRect(100+100-swidth-twidth, 400+slength, twidth, tlength);
		}
		if(poslow[0].tranch[2]){
			g.drawRect(100+slength, 400+swidth, tlength, twidth);
		}
		if(poslow[0].tranch[3]){
			g.drawRect(100+slength, 400+100-twidth-swidth, tlength, twidth);
		}
		//g.setColor(Color.black);
		if(poslow[1].owner){
			g.drawImage(imaget,100,500,100,100,Color.blue,this);
		}
		else{
			g.drawImage(imagef,100,500,100,100,Color.black,this);
		}
		//g.setColor(Color.white);
		if(poslow[1].tranch[0]){
			g.drawRect(100+swidth,500+slength, twidth, tlength);
		}
		if(poslow[1].tranch[1]){
			g.drawRect(100+100-swidth-twidth, 500+slength, twidth, tlength);
		}
		if(poslow[1].tranch[2]){
			g.drawRect(100+slength, 500+swidth, tlength, twidth);
		}
		if(poslow[1].tranch[3]){
			g.drawRect(100+slength, 500+100-twidth-swidth, tlength, twidth);
		}
		//g.setColor(Color.black);
		if(posup[13].owner){
			g.drawImage(imaget,1000,200,100,100,Color.blue,this);
		}
		else{
			g.drawImage(imagef,1000,200,100,100,Color.black,this);
		}
		//g.setColor(Color.white);
		if(posup[13].tranch[0]){
			g.drawRect(1000+swidth, 200+slength, twidth, tlength);
		}
		if(posup[13].tranch[1]){
			g.drawRect(1000+100-swidth-twidth, 200+slength, twidth, tlength);
		}
		if(posup[13].tranch[2]){
			g.drawRect(1000+slength, 200+swidth, tlength, twidth);
		}
		if(posup[13].tranch[3]){
			g.drawRect(1000+slength, 200+100-twidth-swidth, tlength, twidth);
		}
		//g.setColor(Color.black);
		if(posup[12].owner){
			g.drawImage(imaget,1000,100,100,100,Color.blue,this);
		}
		else{
			g.drawImage(imagef,1000,100,100,100,Color.black,this);
		}
		//g.setColor(Color.white);
		if(posup[12].tranch[0]){
			g.drawRect(1000+swidth, 100+slength, twidth, tlength);
		}
		if(posup[12].tranch[1]){
			g.drawRect(1000+100-swidth-twidth, 100+slength, twidth, tlength);
		}
		if(posup[12].tranch[2]){
			g.drawRect(1000+slength, 100+swidth, tlength, twidth);
		}
		if(posup[12].tranch[3]){
			g.drawRect(1000+slength, 100+100-twidth-swidth, tlength, twidth);
		}
		//g.setColor(Color.black);
		if(poslow[13].owner){
			g.drawImage(imaget,1000,400,100,100,Color.blue,this);
		}
		else{
			g.drawImage(imagef,1000,400,100,100,Color.black,this);
		}
		//g.setColor(Color.white);
		if(poslow[13].tranch[0]){
			g.drawRect(1000+swidth, 400+slength, twidth, tlength);
		}
		if(poslow[13].tranch[1]){
			g.drawRect(1000+100-swidth-twidth, 400+slength, twidth, tlength);
		}
		if(poslow[13].tranch[2]){
			g.drawRect(1000+slength, 400+swidth, tlength, twidth);
		}
		if(poslow[13].tranch[3]){
			g.drawRect(1000+slength, 400+100-twidth-swidth, tlength, twidth);
		}
		//g.setColor(Color.black);
		if(poslow[12].owner){
			g.drawImage(imaget,1000,500,100,100,Color.blue,this);
		}
		else{
			g.drawImage(imagef,1000,500,100,100,Color.black,this);
		}
		//g.setColor(Color.white);
		if(poslow[12].tranch[0]){
			g.drawRect(1000+swidth, 500+slength, twidth, tlength);
		}
		if(poslow[12].tranch[1]){
			g.drawRect(1000+100-swidth-twidth, 500+slength, twidth, tlength);
		}
		if(poslow[12].tranch[2]){
			g.drawRect(1000+slength, 500+swidth, tlength, twidth);
		}
		if(poslow[12].tranch[3]){
			g.drawRect(1000+slength, 500+100-twidth-swidth, tlength, twidth);
		}
		//g.setColor(Color.black);
		
	g.drawRect(100, 0, 1000, 100);
	g.drawRect(100, 300, 1000, 100);
	g.drawRect(100, 600, 1000, 100);
	g.drawRect(100, 0, 100, 700);
	g.drawRect(1000, 0, 100, 700);
	g.drawLine(100, 200, 200, 200);
	g.drawLine(100, 500, 200, 500);
	g.drawLine(1000, 200, 1100, 200);
	g.drawLine(1000, 500, 1100, 500);
	for(int i=0;i<7;i++){
		g.drawLine(300+100*i, 0, 300+100*i, 100);
		g.drawLine(300+100*i, 300, 300+100*i, 400);
		g.drawLine(300+100*i, 600, 300+100*i, 700);
		Font Myfont=new Font("Old English Text MT",Font.BOLD,36);
		g.setFont(Myfont);
		g.drawString("A", 1120, 60);
		g.drawString("B", 1120, 360);
		g.drawString("C", 1120, 660);
		Myfont=new Font("Brush Script MT",Font.BOLD,36);
		g.setFont(Myfont);
		g.setColor(new Color(80,100,220));
		g.drawString("A", 50, 60);
		g.drawString("B", 50, 360);
		g.drawString("C", 50, 660);
		g.setColor(Color.black);
	}
	Image imagel=getImage(this.getClass().getResource("left.jpg"));
	Image imager=getImage(this.getClass().getResource("right.jpg"));
	if(numofdivsel!=0){
		g.clearRect(0, 35, 40, 705);
		g.clearRect(1160, 35, 40, 705);
		if(selected[numofdivsel-1].side){
			switch(selected[numofdivsel-1].army.index){
			case 0:g.drawImage(imagel,0,35,40,30,Color.blue,this);break;
			case 1:g.drawImage(imagel,0,335,40,30,Color.blue,this);break;
			case 2:g.drawImage(imagel,0,635,40,30,Color.blue,this);break;
			}
		}
		else{
			switch(selected[numofdivsel-1].army.index){
			case 0:g.drawImage(imager,1160,35,40,30,Color.blue,this);break;
			case 1:g.drawImage(imager,1160,335,40,30,Color.blue,this);break;
			case 2:g.drawImage(imager,1160,635,40,30,Color.blue,this);break;
			}
		}
	}	
	}
	public void setbt(){
		l=new JButton(new ImageIcon(getImage(this.getClass().getResource("l.png"))));
		ll=new JButton(new ImageIcon(getImage(this.getClass().getResource("ll.png"))));
		r=new JButton(new ImageIcon(getImage(this.getClass().getResource("r.png"))));
		rr=new JButton(new ImageIcon(getImage(this.getClass().getResource("rr.png"))));
		u=new JButton(new ImageIcon(getImage(this.getClass().getResource("u.png"))));
		uu=new JButton(new ImageIcon(getImage(this.getClass().getResource("uu.png"))));
		d=new JButton(new ImageIcon(getImage(this.getClass().getResource("d.png"))));
		dd=new JButton(new ImageIcon(getImage(this.getClass().getResource("dd.png"))));
		lt=new JButton();
		rt=new JButton();
		ut=new JButton();
		dt=new JButton();
		nt=new JButton();
		next=new JButton("next");
		toA=new JButton("toA");
		toB=new JButton("toB");
		toC=new JButton("toC");
		pl=new Panel();
		//pll=new Panel();
		//pr=new Panel();
		//prr=new Panel();
		//pu=new Panel();
		//puu=new Panel();
		//pd=new Panel();
		//pdd=new Panel();
		//pnext=new Panel();
		pl.setBounds(new Rectangle(1200,0,300,600));
		pl.setLayout(null);
		pl.setBackground(Color.white);
		l.setBounds(new Rectangle(0,170,60,20));
		ll.setBounds(new Rectangle(10,160,30,40));
		r.setBounds(new Rectangle(100,170,60,20));
		rr.setBounds(new Rectangle(120,160,30,40));
		u.setBounds(new Rectangle(70,100,20,60));
		uu.setBounds(new Rectangle(60,110,40,30));
		d.setBounds(new Rectangle(70,200,20,60));
		dd.setBounds(new Rectangle(60,220,40,30));
		next.setBounds(new Rectangle(40,280,80,20));
		toA.setBounds(new Rectangle(0,330,53,30));
		toB.setBounds(new Rectangle(53,330,53,30));
		toC.setBounds(new Rectangle(106,330,53,30));
		lt.setBounds(new Rectangle(50,400,20,20));
		rt.setBounds(new Rectangle(90,400,20,20));
		ut.setBounds(new Rectangle(70,380,20,20));
		dt.setBounds(new Rectangle(70,420,20,20));
		nt.setBounds(new Rectangle(70,400,20,20));
		//l.setIcon(new ImageIcon(getImage(this.getClass().getResource("l.png"))));
		l.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int max=1;
				for (int i=0;i<numofdivsel;i++){
					if(selected[i].supply>max){max=selected[i].supply;}
				}
				rest-=max;
				lbshow();
				for (int i=0;i<numofdivsel;i++){
					tomove(selected[i],0,true);
					}
			}
			
		});
		ll.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int max=1;
				for (int i=0;i<numofdivsel;i++){
					if(selected[i].supply>max){max=selected[i].supply;}
				}
				rest-=max;
				lbshow();
				for (int i=0;i<numofdivsel;i++){
					tomove(selected[i],0,false);
					}
			}
		});
		r.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int max=1;
				for (int i=0;i<numofdivsel;i++){
					if(selected[i].supply>max){max=selected[i].supply;}
				}
				rest-=max;
				lbshow();
				for (int i=0;i<numofdivsel;i++){
					tomove(selected[i],1,true);
					}
			}			
		});
		rr.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int max=1;
				for (int i=0;i<numofdivsel;i++){
					if(selected[i].supply>max){max=selected[i].supply;}
				}
				rest-=max;
				lbshow();
				for (int i=0;i<numofdivsel;i++){
					tomove(selected[i],1,false);
					}
			}	
		});
		u.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int max=1;
				for (int i=0;i<numofdivsel;i++){
					if(selected[i].supply>max){max=selected[i].supply;}
				}
				rest-=max;
				lbshow();
				for (int i=0;i<numofdivsel;i++){
					tomove(selected[i],2,true);
					}
			}
		});
		uu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int max=1;
				for (int i=0;i<numofdivsel;i++){
					if(selected[i].supply>max){max=selected[i].supply;}
				}
				rest-=max;
				lbshow();
				for (int i=0;i<numofdivsel;i++){
					tomove(selected[i],2,false);
					}
			}
		});
		d.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int max=1;
				for (int i=0;i<numofdivsel;i++){
					if(selected[i].supply>max){max=selected[i].supply;}
				}
				rest-=max;
				lbshow();
				for (int i=0;i<numofdivsel;i++){
					tomove(selected[i],3,true);
					}
			}
		});
		dd.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int max=1;
				for (int i=0;i<numofdivsel;i++){
					if(selected[i].supply>max){max=selected[i].supply;}
				}
				rest-=max;
				lbshow();
				for (int i=0;i<numofdivsel;i++){
					tomove(selected[i],3,false);
					}
			}
		});
		next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearselect();
				nexthelper();
				moved=false;
				for(int i=0;i<10;i++){
					checksupply(divt[i]);
					if(divt[i].pos.owner!=divt[i].side&&divt[i].pos.numofdiv>1){
						for(int j=3;j>-1;j--){
							if(divt[i].pos.next[j]!=null){
								if(divt[i].pos.next[j].owner==divt[i].side){
									move(divt[i],j,false);
									break;
								}
							}
						}
					}
					
				}
				for(int i=0;i<10;i++){
					checksupply(divf[i]);
					if(divt[i].pos.owner!=divt[i].side&&divt[i].pos.numofdiv>1){
						for(int j=3;j>-1;j--){
							if(divt[i].pos.next[j]!=null){
								if(divt[i].pos.next[i].owner==divt[i].side){
									move(divt[i],j,false);
									break;
								}
							}
						}
					}
				}
				rest=2*(int)(1+6*random());
				if(start){
				sidecode++;
				}
				start=true;
				if(sidecode>5){
					sidecode=0;
				}
				lbshow();
			}
			
		});
		toA.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(int i=0;i<numofdivsel;i++){
					if(selected[i].side){
						armyt[0].change(selected[i]);
					}
					else{
						armyf[0].change(selected[i]);
					}
					changesupply(selected[i]);
				}
				rest-=1;
				lbshow();
			}
		
		});
		toB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(int i=0;i<numofdivsel;i++){
					if(selected[i].side){
						armyt[1].change(selected[i]);
					}
					else{
						armyf[1].change(selected[i]);
					}
					changesupply(selected[i]);
				}
				rest-=1;
				lbshow();
			}
		
		});
		toC.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(int i=0;i<numofdivsel;i++){
					if(selected[i].side){
						armyt[2].change(selected[i]);
					}
					else{
						armyf[2].change(selected[i]);
					}
					changesupply(selected[i]);
				}
				rest-=1;
				lbshow();
			}
		
		});
		lt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selected[numofdivsel-1].pos.tranch[0]=true;
				moved=true;
				rest-=6;
				lbshow();
				repaint();
			}
		});
		rt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selected[numofdivsel-1].pos.tranch[1]=true;
				moved=true;
				rest-=6;
				lbshow();
				repaint();
			}
		});
		ut.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selected[numofdivsel-1].pos.tranch[2]=true;
				moved=true;
				rest-=6;
				lbshow();
				repaint();
			}
		});
		dt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selected[numofdivsel-1].pos.tranch[3]=true;
				moved=true;
				rest-=6;
				lbshow();
				repaint();
			}
		});
		nt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selected[numofdivsel-1].pos.tranch[0]=false;
				selected[numofdivsel-1].pos.tranch[1]=false;
				selected[numofdivsel-1].pos.tranch[2]=false;
				selected[numofdivsel-1].pos.tranch[3]=false;
				moved=true;
				rest-=6;
				lbshow();
				repaint();
			}
		});
		
		lb=new TextField("press next");
		lb.setBounds(new Rectangle(40,300,80,30));
		//reminder=new TextField("prepare");
		lb.setBounds(new Rectangle(40,300,80,30));
		pl.add(lb);
		pl.add(next);
		pl.add(toA);
		pl.add(toB);
		pl.add(toC);
		this.add(pl);
	}
	public void nexthelper(){
		this.remove(pl);
		pl.removeAll();
		pl.add(lb);
		pl.add(next);
		pl.add(toA);
		pl.add(toB);
		pl.add(toC);
		this.add(pl);
	}
	public void lbshow(){
		if(start){
		if(sidecode<3){
			lb.setFont(new Font("Brush Script MT",Font.BOLD,25));
			lb.setForeground(new Color(80,100,220));
		}
		else{
			lb.setFont(new Font("Old English Text MT",Font.BOLD,25));
			lb.setForeground(Color.black);
		}
		String toshow="A";
		switch(sidecode){
		case 0:toshow="A";toshow+=armyt[0].numofdiv;break;
		case 1:toshow="B";toshow+=armyt[1].numofdiv;break;
		case 2:toshow="C";toshow+=armyt[2].numofdiv;break;
		case 3:toshow="A";toshow+=armyf[0].numofdiv;break;
		case 4:toshow="B";toshow+=armyf[1].numofdiv;break;
		case 5:toshow="C";toshow+=armyf[2].numofdiv;break;
		}
		if(rest<10){
			lb.setText(toshow+":"+"0"+rest);
		}
		else{
			lb.setText(toshow+":"+rest);
		}
		}
	}
	public boolean recursivefind(boolean side,Position from,Position destination,int dir,Position[] covered){
		if(from.next[dir]==destination){
			return true;
		}
		if(from.next[dir].owner!=side){
			return false;
		}
		if(from.next[dir]==posup[2]){
			for(int i=0;i<6;i++){
				if(covered[i]==null){
					break;
				}
				if(from.next[dir]==covered[i]){
					return false;
				}
			}
			for(int i=0;i<6;i++){
				if(covered[i]==null){
					covered[i]=from.next[dir];
					break;
				}
			}
		}
		else if(from.next[dir]==posmid[0]){
			for(int i=0;i<6;i++){
				if(covered[i]==null){
					break;
				}
				if(from.next[dir]==covered[i]){
					return false;
				}
			}
			for(int i=0;i<6;i++){
				if(covered[i]==null){
					covered[i]=from.next[dir];
					break;
				}
			}
		}
		else if(from.next[dir]==poslow[2]){
			for(int i=0;i<6;i++){
				if(covered[i]==null){
					break;
				}
				if(from.next[dir]==covered[i]){
					return false;
				}
			}
			for(int i=0;i<6;i++){
				if(covered[i]==null){
					covered[i]=from.next[dir];
					break;
				}
			}
		}
		else if(from.next[dir]==posup[11]){
			for(int i=0;i<6;i++){
				if(covered[i]==null){
					break;
				}
				if(from.next[dir]==covered[i]){
					return false;
				}
			}
			for(int i=0;i<6;i++){
				if(covered[i]==null){
					covered[i]=from.next[dir];
					break;
				}
			}
		}
		else if(from.next[dir]==posmid[9]){
			for(int i=0;i<6;i++){
				if(covered[i]==null){
					break;
				}
				if(from.next[dir]==covered[i]){
					return false;
				}
			}
			for(int i=0;i<6;i++){
				if(covered[i]==null){
					covered[i]=from.next[dir];
					break;
				}
			}
		}
		else if(from.next[dir]==poslow[11]){
			for(int i=0;i<6;i++){
				if(covered[i]==null){
					break;
				}
				if(from.next[dir]==covered[i]){
					return false;
				}
			}
			for(int i=0;i<6;i++){
				if(covered[i]==null){
					covered[i]=from.next[dir];
					break;
				}
			}
		}
		for(int i=0;i<4;i++){
			if(from.next[dir].next[i]!=null){
				if(from.next[dir].next[i]!=from){
					if(recursivefind(side,from.next[dir],destination,i,covered)){
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean changesupply(Division div){
		if(div.pos.owner!=
				div.side||
				div.
				army.
				base.
				owner!=
				div.side){
			div.supplied=false;
			return false;
		}
		else if(div.army.base==div.pos){
			div.supplied=true;
			return true;
		}
		else{
			for(int i=0;i<4;i++){
				if(div.pos.next[i]!=null){
					if(recursivefind(div.side,div.pos,div.army.base,i,new Position[6])){
						div.supplied=true;
						return true;	
					}
				}
			}
			div.supplied=false;
		return false;
		}
	}
	public void checksupply(Division div){
		if(!div.moved){
		div.moved=div.tmpmoved;
		}
		div.tmpmoved=false;
		changesupply(div);
		if(div.supplied){////////////////////////////////////////////////////to write supplied
			//div.supplied=true;
			div.moved=false;
		}
		//else{
			//div.supplied=false;
		//}
		div.setbtcolor();
	}
	public void kill(Division div){
		div.army.remove(div);
		this.remove(div.pn);
		div.pos.removediv(div);
		div.pos.resetpos();
		div.pos=deadpos;/////////////////////////////////////////////////to write
	}
	public void tomove(Division div,int dir,boolean att){
		boolean toattack=false;
		Division target=null;
		moved=true;
		for(int i=0;i<div.pos.next[dir].numofdiv;i++){
			if(div.pos.next[dir].div[i].side!=div.side){
				toattack=true;		
				target=div.pos.next[dir].div[i];
				break;
			}
		}
		if(toattack){
			if(att){
				if(random()>0.5){
					boolean kill=true;
					for(int i=3;i>-1;i--){
						if(target.pos.next[i]!=null){
							if(target.pos.next[i]!=div.pos&&target.pos.next[i].owner==target.side){
								move(target,i,false);
								kill=false;
								break;
							}
						}
					}
					if(kill){
						for(int i=3;i>-1;i--){
							if(target.pos.next[i]!=null){
								if(target.pos.next[i].numofdiv==0&&tranchcheck(target,i)){
									move(target,i,false);
									kill=false;
									break;
								}
							}
						}
						if(kill){
							kill(target);
						}
					}
				}
			}
			else{
				if(random()>0.5&&(tranchcheck(div,dir)||div.pos.next[dir].owner==div.side)){
					move(div,dir,false);
					div.tmpmoved=true;
					}
			}
		}
		else{
			for(int i=0;i<div.pos.numofdiv;i++){
				if(div.pos.div[i].side!=div.side){
					toattack=true;		
					break;
				}
			}
			if(toattack){
					if(random()>0.5&&(tranchcheck(div,dir)||div.pos.next[dir].owner==div.side)){
						move(div,dir,true);
						div.tmpmoved=true;
						if(div.pos.numofdiv==0){
							div.pos.owner=div.side;
						}
					}
			}
			else{
				if((tranchcheck(div,dir)||div.pos.next[dir].owner==div.side)){
				move(div,dir,true);
				div.tmpmoved=true;
				}
			}
		}
		setdynbt(div);
	}
	public boolean tranchcheck(Division div,int dir){
		boolean canmove=false;
		if(div.supply==2){
			canmove=true;
		}
		else{
			int dir2=0;
			switch(dir){
			case 0:dir2=1;break;
			case 1:dir2=0;break;
			case 2:dir2=3;break;
			case 3:dir2=2;break;
			}
			if(!div.pos.next[dir].tranch[dir2]){
				canmove=true;
			}
		}
		return canmove;
	}
	public void move(Division div,int dir,boolean occupy){
			this.remove(div.pn);
			div.pos.removediv(div);
			div.pos.resetpos();
			div.pos=div.pos.next[dir];
			div.pos.adddiv(div);
			div.setbtpos();
			if(occupy){
				div.pos.owner=div.side;
			}
			this.add(div.pn);
	}
	public void setdiv(){
		for(int i=0;i<10;i++){
			if(random()<0.666667){
			divt[i]=new Infantry(true);
			}
			else{
			divt[i]=new Armor(true);	
			}
			divt[i].setbtcolor();
		}
		for(int i=0;i<10;i++){
			if(random()<0.666667){
			divf[i]=new Infantry(false);
			}
			else{
			divf[i]=new Armor(false);	
			}
			divf[i].setbtcolor();
		}
		addlistener(divt);
		addlistener(divf);
		for(int i=0;i<3;i++){
			divt[i].pos=posup[6];posup[6].adddiv(divt[i]);divt[i].setbtpos();this.add(divt[i].pn);
			armyt[0].add(divt[i]);
			divf[i].pos=posup[8];posup[8].adddiv(divf[i]);divf[i].setbtpos();this.add(divf[i].pn);
			armyf[0].add(divf[i]);
		}
		for(int i=0;i<3;i++){
			divt[i+3].pos=poslow[6];poslow[6].adddiv(divt[i+3]);divt[i+3].setbtpos();this.add(divt[i+3].pn);
			armyt[2].add(divt[i+3]);
			divf[i+3].pos=poslow[8];poslow[8].adddiv(divf[i+3]);divf[i+3].setbtpos();this.add(divf[i+3].pn);
			armyf[2].add(divf[i+3]);
		}
		for(int i=0;i<4;i++){
			divt[i+6].pos=posmid[4];posmid[4].adddiv(divt[i+6]);divt[i+6].setbtpos();this.add(divt[i+6].pn);
			armyt[1].add(divt[i+6]);
			divf[i+6].pos=posmid[6];posmid[6].adddiv(divf[i+6]);divf[i+6].setbtpos();this.add(divf[i+6].pn);
			armyf[1].add(divf[i+6]);
		}
	}
	public void addlistener(Division[] div){
		for(int i=0;i<10;i++){
			Al al=new Al(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					selectiondealer(div);
				}
			};
			al.div=div[i];
			div[i].bt.addActionListener(al);
		}
	}
	public void selectiondealer(Division div){
		/////////////////////////////////////////////////////////////to write
		if(numofdivsel!=0){
			if(selected[numofdivsel-1].army!=div.army||selected[numofdivsel-1].pos!=div.pos||moved){
			clearselect();
			}
			for(int i=0;i<numofdivsel;i++){
				if(selected[i]==div){
					clearselect();
					break;
				}
			}
		}
		addselect(div);
		setdynbt(div);
	}
	public void setdynbt(Division div){
		boolean canmove=true;
		if(!div.supplied&&div.moved){
			canmove=false;
		}
		if(rest<div.supply){
			canmove=false;
		}
		else if((!div.side&&sidecode<3)||(div.side&&sidecode>=3)){
			canmove=false;
		}
		else if(div.army.index!=sidecode&&div.army.index!=sidecode-3){
			canmove=false;
		}
		//else if(div.army.base.owner!=div.side&&div.army.index!=1){
			//canmove=false;
		//}
		this.remove(pl);
		pl.removeAll();
		pl.add(lb);
		pl.add(next);
		pl.add(toA);
		pl.add(toB);
		pl.add(toC);
		if(canmove||!start){
			if(div.supply==2){
				pl.add(nt);
				}
		if(div.pos.next[0]!=null){
		pl.add(l);
		pl.add(ll);
		if(div.pos.owner==div.side&&!div.tmpmoved&&div.supply==2){
		pl.add(lt);
		}
		}
		if(div.pos.next[1]!=null){
		pl.add(r);
		pl.add(rr);
		if(div.pos.owner==div.side&&!div.tmpmoved&&div.supply==2){
			pl.add(rt);
		}
		}
		if(div.pos.next[2]!=null){
		pl.add(u);
		pl.add(uu);
		if(div.pos.owner==div.side&&!div.tmpmoved&&div.supply==2){
			pl.add(ut);
		}
		}
		if(div.pos.next[3]!=null){
		pl.add(d);
		pl.add(dd);
		if(div.pos.owner==div.side&&!div.tmpmoved&&div.supply==2){
			pl.add(dt);
		}
		}
		}
		this.add(pl);
		repaint();
	}
	public void addselect(Division div){
		if(numofdivsel<5){
	div.selected=true;
	div.setbtcolor();
	selected[numofdivsel]=div;
	numofdivsel++;
		}
	}
	public void deleteselect(Division div){
		for(int i=0;i<numofdivsel;i++){
		if(selected[i]==div){
			selected[i]=selected[numofdivsel];
			numofdivsel--;
			break;
		}
		}
	}
	public void clearselect(){
		for(int i=0;i<numofdivsel;i++){
			selected[i].selected=false;
			selected[i].setbtcolor();
		}
		selected=new Division[4];
		numofdivsel=0;
	}
	public static double random(){
		Random r = new Random();
	    double rv =r.nextDouble();
	    return rv;
	}
	public static int ver(double d){
		int i=(int)d;
		d=d-i;
		if(d>0.5){
			return(i+1);
		}
		return i;
	}
}
