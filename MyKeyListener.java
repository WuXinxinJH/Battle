package am;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener{
	PlaneFrame frame;
	public MyKeyListener(PlaneFrame f){
		this.frame=f;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		//���ͷ�Ӣ�ۻ�ս���Ĵ���
		// �����ڶ����������
		if(e.getKeyCode()==KeyEvent.VK_C){
			frame.dispose();
			PlaneFrame f=new PlaneFrame(frame.x,frame.y);
			Thread th=new Thread(f);
			th.start();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
