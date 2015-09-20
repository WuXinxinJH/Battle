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
	ImageIcon icon=new ImageIcon("ͼƬ/hero.png");
	ImageIcon min=new ImageIcon("ͼƬ/min.png");
	ImageIcon close=new ImageIcon("ͼƬ/close.png");
	int hero_x=200;
	int hero_y=400;
	PlaneFrame frame;
	PlanePanel panel;
	//����һ������������ŵл�
	//vector���ϲ����ٶ�û��List�� �����Ա����߳��첽������ �̰߳�ȫ��һ������
	//< >��ŷ���  ������ָvs������ֻ�ܴ��Enemy,�����Ĳ�����ʹ��
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
		 g.drawImage(new ImageIcon("ͼƬ/backmain.png").getImage(), 0, 0, this);
		 g.drawImage(min.getImage(), 325, 3, this);
		 g.drawImage(new ImageIcon("ͼƬ/close.png").getImage(), 360, 3, this);
		 
		 
		 //��Ӣ�ۻ�
		 if(hero_isLive){
			 g.drawImage(icon.getImage(), hero_x, hero_y, this);
		 }
		 if(!hero_isLive){
			 g.drawImage(new ImageIcon("ͼƬ/hero_destory.png").getImage(),hero_x, hero_y, this);
			 //�л��������
			 for(int i=0;i<vs.size();i++){//����ÿһ���л���ʹ�л��������
				 Enemy e=vs.get(i);
				 e.isLive=false;//ÿ���л�isLive��ֵ�ĳ�false
			 }
			 vs.clear();
			 //��ʾ�û��÷�
			 g.setFont(new Font("����",Font.BOLD,20));
			 g.drawString("�÷�="+score, this.getWidth()/2-50, this.getHeight()/2);
		 
			 //��C���¿�ʼ
			 g.drawString("��C���¿�ʼ", this.getWidth()/2-70, this.getHeight()/2+20);
		 }
		 for(int i=0;i<vs.size();i++){
			 Enemy e=vs.get(i);
			 e.drawEnemy(g);
		 }
		 //���ӵ�
		 for(int i=0;i<bs.size();i++){
			 Bullet b=bs.get(i);
			 b.drawBullet(g);
		 }
		 
		 //����ը��
		 for(int i=0;i<bos.size();i++){
			 bomb bo=bos.get(i);
			 bo.drawbomb(g);
			 bo.show();
		 }
	}
	@Override
	public void run() {
		// ���Ӣ�ۻ���� �����ӵ�
		Bullet bu=null;
		while(hero_isLive){
			//x,y,speed,icon,panel
		    ImageIcon b=new ImageIcon("ͼƬ/Bullet.png");
		    int x=hero_x+icon.getIconWidth()/2-2;
		    int y=hero_y-b.getIconHeight()+18;
		    int speed=20;
		    //�����ӵ�
		    bu=new Bullet(x,y,speed,b,this);
		    this.bs.add(bu);
		    //���ӵ���ӵ�bs������
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
		// ������С �ر�
		int x=e.getX();
		int y=e.getY();
		if(x>325&&x<325+min.getIconWidth()&&y>3&&y<3+min.getIconHeight()){
			//��С����
			frame.setState(JFrame.ICONIFIED);//JFrame���ṩ�ĳ���  ��С����˼
			
		}else if(x>360&&x<360+close.getIconWidth()&&y>3&&y<3+close.getIconHeight()){
			//�رմ���
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
		// ������Ӣ�ۻ�
		//�õ���� x y����
		if(hero_isLive){
			int x=e.getX();
			int y=e.getY();
			//�����������õ�Ӣ�ۻ�����  ��Ӣ�ۻ��Ǵ���
			//�����������Ӣ�ۻ�������λ��
			hero_x=x-icon.getIconWidth()/2;
			hero_y=y-icon.getIconHeight()/2;
			//�ж�Ӣ�ۻ��ٽ�ֵ
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
	int x,y;//�������� new PlaneFrame(frame.x,frame.y)
	PlanePanel panel;
	public PlaneFrame(int x,int y){
		this.setSize(400,654);
		this.x=x;
		this.y=y;
		this.setLocation(x, y);
		this.setIconImage(new ImageIcon("ͼƬ/icon.jpg").getImage());
		this.setUndecorated(true);
		panel=new PlanePanel(this);
		this.add(panel);
		this.setVisible(true);
		//�������Ӽ�����
		panel.addMouseListener(panel);
		panel.addMouseMotionListener(panel);
		
		Thread th=new Thread(panel);
		th.start();
		
		//�������Ӽ��̼�����
		panel.requestFocus();
		MyKeyListener myKey=new MyKeyListener(this);
		panel.addKeyListener(myKey);
	}
	
	
	
	@Override
	public void run() {
		// ���Ӣ�ۻ���� �����л�
		while(panel.hero_isLive){
			//x,y,speed,icon,panel
			ImageIcon icon=new ImageIcon("ͼƬ/shoot0_0.png");
			//�����͵����ݲ���ֱ�Ӹ�ֵ��С�������� ��Ҫǿ��ת��
			int x=(int) (Math.random()*(400-icon.getIconWidth())); //Math.random()  (0,1)*400  (0,400)
			int y=-icon.getIconHeight();
			int speed=10;
			//�����л�
			Enemy e=new Enemy(x,y,speed,icon, panel);
			//���л���ӵ�������
			panel.vs.add(e);
			//���л����˶����߳� :����EnemyMoveThread����
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
