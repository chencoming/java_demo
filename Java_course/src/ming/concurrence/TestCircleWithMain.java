package ming.concurrence;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * （子线程循环10次，主线程循环100次）* 50
 * @author coming  2016-8-10
 */
public class TestCircleWithMain {

	Lock lock = new ReentrantLock();
	
	Condition mCon = lock.newCondition();
	Condition tCon = lock.newCondition();
	
	 volatile static boolean isStop = false;
	
	/**
	 * @param args
	 * @return void
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
	
		TestCircleWithMain main = new TestCircleWithMain();
		Thread t = new Thread(new MyThread(main));
		for(int i = 0; i < 50; i++){
			main.lock.lock();
			if(!t.isAlive()){
				t.start();
			}
			try {
				main.mCon.await();
				for(int j = 0; j< 100; j++){
					System.out.println("main is running : " + j);
				}
				System.out.println("====第" +  (i+1)  + "次循环结束.====");
//				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				main.tCon.signal();
				main.lock.unlock();
			}
		}
		isStop = true;
		
	}

	static class MyThread extends Thread{
		TestCircleWithMain main;
		
		public MyThread(TestCircleWithMain main){
			this.main = main;
		}
		
		public void run(){
			while(!isStop){
				main.lock.lock();
				try {
					for(int j = 0; j< 10; j++){
						System.out.println("child is running : " + j);
					}
					main.mCon.signal();
					main.tCon.await();
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					main.lock.unlock();
				}	
			}
		}
	}
	
	
}
