package am;

public class BulletMoveThread extends Thread{
	Bullet bu;

	public BulletMoveThread(Bullet bu) {
		this.bu=bu;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(bu.isLive){
			bu.move();
			try {
				Thread.sleep(30);
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
