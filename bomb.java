package am;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class bomb {
	int x,y,count;
	ImageIcon icon;
	PlanePanel panel;
	boolean isLive =true;
	public bomb(){
		
	}
	public bomb(int x, int y, ImageIcon icon, PlanePanel panel) {
		super();
		this.x = x;
		this.y = y;
		this.icon = icon;
		this.panel = panel;
	}
	//»­±¬Õ¨Îï
	public void drawbomb(Graphics g){
		if(isLive){
			g.drawImage(icon.getImage(), x, y, panel);
		}
	}
	public void show(){
		count++;
		if(count>50){
			isLive=false;
		}
	}
	
	
}
