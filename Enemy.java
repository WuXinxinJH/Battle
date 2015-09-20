package am;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Enemy {
	int x;
	int y;
	int speed;
	int score=100;
	ImageIcon icon;
	boolean isLive=true;
	PlanePanel panel;
	

	public Enemy(int x, int y, int speed, ImageIcon icon,PlanePanel panel) {
		super();
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.icon = icon;
		this.panel = panel;
	}
	//���л�
	public void drawEnemy(Graphics g){
		if(isLive){
			g.drawImage(icon.getImage(), x, y, panel);
		}
	}
	//�л��ƶ�
	public void move(){
		y+=speed;
		//�л��ɳ���� ��ɾ���л�����isLive���false
		if(y>panel.getHeight()){
			isLive=false;
			y=-icon.getIconHeight();
			x=(int)(Math.random()*(panel.getWidth()-icon.getIconWidth()));
		}
		hitHero();
		hitBullet();
		panel.repaint();
	}
	
	//�жϵл��Ƿ�ײ��Ӣ�ۻ�
	public void hitHero(){
		if(x>panel.hero_x-icon.getIconWidth()&&x<panel.hero_x+panel.icon.getIconWidth()&&y>panel.hero_y-icon.getIconHeight()&&y<panel.hero_y+panel.icon.getIconHeight()){
			//������ײ
			//������ը��
			bomb bo=new bomb(x,y,new ImageIcon("ͼƬ/bomb.png"),panel);
			panel.bos.add(bo);
			//ɾ���л�  ���ҽ�Ӣ�ۻ���isLive���ó�false
			isLive=false;
			panel.vs.remove(this);
			panel.hero_isLive=false;
		}
		
	}
	public void hitBullet(){
		// ѭ������һ��ÿ�ӵ� ���ӵ�������л�������Ƚ�
		/*
		 * ���迪ʼ�ӵ����ϳ���Ϊ 4 (0 1 2 3) get(3)
		 */
		//���������ײ �ӵ���ʧ �л���ʧ  �ӷ�
		for(int i=0;i<panel.bs.size();i++){
			Bullet b=panel.bs.get(i);
			if(b.x>this.x-b.icon.getIconWidth()&&b.x<this.x+this.icon.getIconWidth()&&b.y>this.y-b.icon.getIconHeight()&&b.y<this.y+this.icon.getIconHeight()){
				b.isLive=false;
				this.isLive=false;
				//ɾ���ӵ�
				b.x=-2000;
				b.y=-3000;
				bomb bo=new bomb(x,y,new ImageIcon("ͼƬ/bomb.png"),panel);
				//����ը����ӵ�������
				panel.bos.add(bo);
				//panel.bs.remove(b);  
				panel.vs.remove(this);
				panel.score+=score;
				
			}
		}
		
	}
		
}
