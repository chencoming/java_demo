package ming.concurrence;

import java.util.concurrent.CountDownLatch;

/**
 *
 * @author coming  2016-7-24
 */
public class TestCountDowLatch {

	private static CountDownLatch latch = new CountDownLatch(2);
	/**
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
		
		new Thread("Thread-01"){
			public void run(){
				System.out.println(Thread.currentThread().getName() +  " start running ......");
				try {
					Thread.sleep(2000);
					System.out.println(Thread.currentThread().getName() +  " done !");
					latch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
		new Thread("Thread-02"){
			public void run(){
				System.out.println(Thread.currentThread().getName() +  " start running ......");
				try {
					Thread.sleep(2000);
					System.out.println(Thread.currentThread().getName() +  " done !");
					latch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
		
		try {
			latch.await();
			System.out.println(" main thread done ! ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
