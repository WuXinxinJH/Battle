package am;

public class EnemyMoveThread extends Thread{
	Enemy e;
	public EnemyMoveThread(Enemy e){
		this.e=e;
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(e.isLive){
			e.move();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
