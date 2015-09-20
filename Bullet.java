package am;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Bullet {
	/*public int getX() {
		return x;
	}

	public void setX1(int x) {   //��ߵ�x��Ϊ����
		this.x = x;
	}*/

	//x y ��Ϊ��Ա����
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
	
	//���ӵ�
	public void drawBullet(Graphics g){
		if(isLive){
			g.drawImage(icon.getImage(), x, y, panel);
		}
	}
	
	//�ӵ��ƶ�
	public void move(){
		y-=speed;
		//�ӵ��ɳ���� ��ɾ���ӵ�����isLive���false
		if(y<-icon.getIconHeight()){
			//isLive=false;
			//panel.bs.remove(this);
			x=-3000;
			y=-5000;
		}
		panel.repaint();
	}
	

}
