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
	//画敌机
	public void drawEnemy(Graphics g){
		if(isLive){
			g.drawImage(icon.getImage(), x, y, panel);
		}
	}
	//敌机移动
	public void move(){
		y+=speed;
		//敌机飞出面板 则删除敌机并且isLive变成false
		if(y>panel.getHeight()){
			isLive=false;
			y=-icon.getIconHeight();
			x=(int)(Math.random()*(panel.getWidth()-icon.getIconWidth()));
		}
		hitHero();
		hitBullet();
		panel.repaint();
	}
	
	//判断敌机是否撞到英雄机
	public void hitHero(){
		if(x>panel.hero_x-icon.getIconWidth()&&x<panel.hero_x+panel.icon.getIconWidth()&&y>panel.hero_y-icon.getIconHeight()&&y<panel.hero_y+panel.icon.getIconHeight()){
			//发生碰撞
			//创建爆炸物
			bomb bo=new bomb(x,y,new ImageIcon("图片/bomb.png"),panel);
			panel.bos.add(bo);
			//删除敌机  并且将英雄机的isLive设置成false
			isLive=false;
			panel.vs.remove(this);
			panel.hero_isLive=false;
		}
		
	}
	public void hitBullet(){
		// 循环遍历一个每子弹 用子弹坐标与敌机坐标相比较
		/*
		 * 假设开始子弹集合长度为 4 (0 1 2 3) get(3)
		 */
		//如果发生碰撞 子弹消失 敌机消失  加分
		for(int i=0;i<panel.bs.size();i++){
			Bullet b=panel.bs.get(i);
			if(b.x>this.x-b.icon.getIconWidth()&&b.x<this.x+this.icon.getIconWidth()&&b.y>this.y-b.icon.getIconHeight()&&b.y<this.y+this.icon.getIconHeight()){
				b.isLive=false;
				this.isLive=false;
				//删除子弹
				b.x=-2000;
				b.y=-3000;
				bomb bo=new bomb(x,y,new ImageIcon("图片/bomb.png"),panel);
				//将爆炸物添加到集合中
				panel.bos.add(bo);
				//panel.bs.remove(b);  
				panel.vs.remove(this);
				panel.score+=score;
				
			}
		}
		
	}
		
}
