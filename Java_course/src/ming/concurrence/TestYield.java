package ming.concurrence;

/**
 *
 * @author coming  2016-4-23
 */
public class TestYield implements Runnable{

	/**
	 * @param args
	 * @return void
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		TestYield join1 = new TestYield();
		Thread th1 = new Thread(join1,"join1");
		th1.start();
		th1.join();
		System.out.println("main is over");
	} 

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " is running");
		try {
			System.out.println("creating innerThread......");
			InnerThread join2 = new InnerThread();
			Thread th2 = new Thread(join2,"join2");
			th2.start();
			Thread.yield();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " finish running");
		
	}
	
	static class InnerThread implements Runnable{
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " is running");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " finish running");
		}
		
	}

}
