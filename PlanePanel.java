package am;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlanePanel extends JPanel implements Runnable,MouseListener,MouseMotionListener{
	boolean hero_isLive=true;
	ImageIcon icon=new ImageIcon("图片/hero.png");
	ImageIcon min=new ImageIcon("图片/min.png");
	ImageIcon close=new ImageIcon("图片/close.png");
	int hero_x=200;
	int hero_y=400;
	PlaneFrame frame;
	PlanePanel panel;
	//定义一个集合用来存放敌机
	//vector集合查找速度没有List快 但可以避免线程异步的问题 线程安全的一个问题
	//< >存放泛型  泛型是指vs集合中只能存放Enemy,其他的不允许使用
	Vector<Enemy> vs=new Vector<Enemy>();
	Vector<Bullet> bs=new Vector<Bullet>();
	Vector<bomb> bos=new Vector<bomb>();
	public int score;
	
	public PlanePanel(PlaneFrame f){
		this.frame=f;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		 g.drawImage(new ImageIcon("图片/backmain.png").getImage(), 0, 0, this);
		 g.drawImage(min.getImage(), 325, 3, this);
		 g.drawImage(new ImageIcon("图片/close.png").getImage(), 360, 3, this);
		 
		 
		 //画英雄机
		 if(hero_isLive){
			 g.drawImage(icon.getImage(), hero_x, hero_y, this);
		 }
		 if(!hero_isLive){
			 g.drawImage(new ImageIcon("图片/hero_destory.png").getImage(),hero_x, hero_y, this);
			 //敌机集合清空
			 for(int i=0;i<vs.size();i++){//遍历每一个敌机，使敌机对象清空
				 Enemy e=vs.get(i);
				 e.isLive=false;//每个敌机isLive的值改成false
			 }
			 vs.clear();
			 //提示用户得分
			 g.setFont(new Font("宋体",Font.BOLD,20));
			 g.drawString("得分="+score, this.getWidth()/2-50, this.getHeight()/2);
		 
			 //按C重新开始
			 g.drawString("按C重新开始", this.getWidth()/2-70, this.getHeight()/2+20);
		 }
		 for(int i=0;i<vs.size();i++){
			 Enemy e=vs.get(i);
			 e.drawEnemy(g);
		 }
		 //画子弹
		 for(int i=0;i<bs.size();i++){
			 Bullet b=bs.get(i);
			 b.drawBullet(g);
		 }
		 
		 //画爆炸物
		 for(int i=0;i<bos.size();i++){
			 bomb bo=bos.get(i);
			 bo.drawbomb(g);
			 bo.show();
		 }
	}
	@Override
	public void run() {
		// 如果英雄机存活 创建子弹
		Bullet bu=null;
		while(hero_isLive){
			//x,y,speed,icon,panel
		    ImageIcon b=new ImageIcon("图片/Bullet.png");
		    int x=hero_x+icon.getIconWidth()/2-2;
		    int y=hero_y-b.getIconHeight()+18;
		    int speed=20;
		    //创建子弹
		    bu=new Bullet(x,y,speed,b,this);
		    this.bs.add(bu);
		    //将子弹添加到bs集合中
		    BulletMoveThread th=new BulletMoveThread(bu);
		   
		    th.start();
		    try {
		    	Thread.sleep(200);
		    	}
		    catch (InterruptedException e)
		    {
			    // TODO Auto-generated catch block
		    		e.printStackTrace();
		    }
		}
		
		
	}
			
		

	@Override
	public void mouseClicked(MouseEvent e) {
		// 触发缩小 关闭
		int x=e.getX();
		int y=e.getY();
		if(x>325&&x<325+min.getIconWidth()&&y>3&&y<3+min.getIconHeight()){
			//缩小窗口
			frame.setState(JFrame.ICONIFIED);//JFrame里提供的常量  缩小的意思
			
		}else if(x>360&&x<360+close.getIconWidth()&&y>3&&y<3+close.getIconHeight()){
			//关闭窗口
			System.exit(0);
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(MouseEvent  e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// 鼠标控制英雄机
		//得到鼠标 x y坐标
		if(hero_isLive){
			int x=e.getX();
			int y=e.getY();
			//根据鼠标坐标得到英雄机坐标  是英雄机是存活的
			//鼠标正好落在英雄机的中心位置
			hero_x=x-icon.getIconWidth()/2;
			hero_y=y-icon.getIconHeight()/2;
			//判断英雄机临界值
			if(x<1){
				hero_x=1;
			}else if(x>this.getWidth()-icon.getIconWidth()){
				hero_x=this.getWidth()-icon.getIconWidth();
				
			}else{
				hero_x=x;
			}
			if(y<35){
				hero_y=35;
			}else if(hero_y>this.getHeight()-icon.getIconHeight()){
				hero_y=this.getHeight()-icon.getIconHeight();
			}else{
				hero_y=y;
			}
			this.repaint();
		}
		
		
		
	} 
}


class PlaneFrame extends JFrame implements Runnable{
	int x,y;//窗体坐标 new PlaneFrame(frame.x,frame.y)
	PlanePanel panel;
	public PlaneFrame(int x,int y){
		this.setSize(400,654);
		this.x=x;
		this.y=y;
		this.setLocation(x, y);
		this.setIconImage(new ImageIcon("图片/icon.jpg").getImage());
		this.setUndecorated(true);
		panel=new PlanePanel(this);
		this.add(panel);
		this.setVisible(true);
		//给面板添加监听器
		panel.addMouseListener(panel);
		panel.addMouseMotionListener(panel);
		
		Thread th=new Thread(panel);
		th.start();
		
		//给面板添加键盘监听器
		panel.requestFocus();
		MyKeyListener myKey=new MyKeyListener(this);
		panel.addKeyListener(myKey);
	}
	
	
	
	@Override
	public void run() {
		// 如果英雄机存活 创建敌机
		while(panel.hero_isLive){
			//x,y,speed,icon,panel
			ImageIcon icon=new ImageIcon("图片/shoot0_0.png");
			//大类型的数据不能直接赋值给小类型数据 需要强制转换
			int x=(int) (Math.random()*(400-icon.getIconWidth())); //Math.random()  (0,1)*400  (0,400)
			int y=-icon.getIconHeight();
			int speed=10;
			//创建敌机
			Enemy e=new Enemy(x,y,speed,icon, panel);
			//将敌机添加到集合中
			panel.vs.add(e);
			//给敌机绑定运动的线程 :创建EnemyMoveThread对象
			EnemyMoveThread th=new EnemyMoveThread(e);
			th.start();
			try {
				Thread.sleep(600);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
}
