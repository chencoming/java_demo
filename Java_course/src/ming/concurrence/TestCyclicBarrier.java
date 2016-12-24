package ming.concurrence;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * @author coming  2016-7-24
 */
public class TestCyclicBarrier {
	
	public final static int num = 4;
	
	public static CyclicBarrier barrier = new CyclicBarrier(num, new Runnable() {
		@Override
		public void run() {// when all threads reached barrier, a random thread would be picked up to run this method
			System.out.println("最后一个执行的线程是: " + Thread.currentThread().getName());
		}
	});

	/**
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
		for(int i = 0; i < num; i++ ){
			new Thread(){
				public void run(){
					try {
						System.out.println(Thread.currentThread().getName() + " is reading ......");
						Thread.sleep(1000);
						System.out.println(Thread.currentThread().getName() + " finish reading ......");
						barrier.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
		
		new Thread(){
			public void run(){
				try {
					barrier.await();
					System.out.println(Thread.currentThread().getName() + " is writing ......");
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName() + " finish writing ......");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
	}

}
