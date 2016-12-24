package ming.concurrence;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 三个线程，循环输出A、B、C
 * @author coming  2016-8-3
 */
public class TestCircleOuputByThread {

	public Lock lock = new ReentrantLock();
	
	public Condition acon = lock.newCondition();
	public Condition bcon = lock.newCondition();
	public Condition ccon = lock.newCondition();

	/**
	 * @param args
	 * @return void
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		TestCircleOuputByThread m =  new TestCircleOuputByThread();
		Thread a = new Thread(m.new Printer('A'));
		Thread b = new Thread(m.new Printer('B'));
		Thread c = new Thread(m.new Printer('C'));
		System.out.println("start...");
		a.start();
		if(a.isAlive()){
			b.start();
		}
		if(b.isAlive()){
			c.start();
		}
		
/*		c.join();
		b.join();
		a.join();*/
		System.out.println("main is done");
	}
	
	class Printer implements Runnable{
		
		private char output;
		
		public Printer(char c) {
			this.output = c;
		}
		
		public void run(){
			while(true){
				lock.lock();
				try {
					System.out.println(this.output);

					if(this.output == 'A'){
						bcon.signal();
						acon.await();
					}
					if(this.output == 'B'){
						ccon.signal();
						bcon.await();
					}
					if(this.output == 'C'){
						acon.signal();
						ccon.await();
					}
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					lock.unlock();
				}
			}
		}
	}

}
