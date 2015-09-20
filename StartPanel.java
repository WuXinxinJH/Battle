package am;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartPanel extends JPanel implements Runnable {
	static int x ;
	static int y;
    static JFrame jf;

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);

		g.drawImage(new ImageIcon("ͼƬ/startback.png").getImage(), 0, 0, this);
		g.drawImage(new ImageIcon("ͼƬ/start_aircraft.png").getImage(), x, 450,this);

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (x<400) {
			x++;
			/*
			 * if (x > 400) { x = 0; }
			 */

			// ���� �����쳣
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// ���»滭
			repaint();
			
			
			if (x == 400){
				//���JFrame ���Ͻ�����
				int x=jf.getLocation().x;
				int y=jf.getLocation().y;
				jf.dispose();
		    	PlaneFrame frame = new PlaneFrame(x, y);
				Thread th=new Thread(frame);
		    	th.start();
		    	break;
			}
			
		}
	}

	public static void main(String[] args) {
		
		jf = new JFrame();
		jf.setSize(400, 654);
		jf.setLocation(400, 50);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setIconImage(new ImageIcon("ͼƬ/icon.jpg").getImage());
		jf.setUndecorated(true);

		StartPanel panel = new StartPanel();
		jf.add(panel);
		jf.setVisible(true);

		Thread th = new Thread(panel);
		th.start();
	}

}
