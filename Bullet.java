package am;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Bullet {
	/*public int getX() {
		return x;
	}

	public void setX1(int x) {   //这边的x称为属性
		this.x = x;
	}*/

	//x y 等为成员变量
	int x;
	int y;
	int speed;
	ImageIcon icon;
	boolean isLive=true;
	PlanePanel panel;
	public Bullet(){
		
	}
	public Bullet(int x, int y, int speed, ImageIcon icon, PlanePanel panel) {
		super();
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.icon = icon;
		this.panel = panel;
	}
	
	//画子弹
	public void drawBullet(Graphics g){
		if(isLive){
			g.drawImage(icon.getImage(), x, y, panel);
		}
	}
	
	//子弹移动
	public void move(){
		y-=speed;
		//子弹飞出面板 则删除子弹并且isLive变成false
		if(y<-icon.getIconHeight()){
			//isLive=false;
			//panel.bs.remove(this);
			x=-3000;
			y=-5000;
		}
		panel.repaint();
	}
	

}
