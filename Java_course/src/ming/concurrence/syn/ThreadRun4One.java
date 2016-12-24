package ming.concurrence.syn;

/**
 * ͬһʱ��ֻ����һ���߳�ִ��synchronized�����
 * @author coming  2016-4-16
 */
public class ThreadRun4One implements Runnable{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		dosth();
		ThreadRun4One mythread = new ThreadRun4One();
		Thread th1 = new Thread(mythread, "th1");
		Thread th2 = new Thread(mythread, "th2");
		th1.start();
		th2.start();
	}
	
	/**
	 * 
	 */
	static void dosth(){
		ThreadRun4One  s = new ThreadRun4One();
		ThreadRun4One.InnerThread mythread = s. new InnerThread();
		Thread th1 = new Thread(mythread, "th1");
		Thread th2 = new Thread(mythread, "th2");
		th1.start();
		th2.start();
		
	}

	class InnerThread implements Runnable{
		@Override
		public void run() {
			synchronized (this) {
				for(int i = 0 ;i < 5; i++){
					System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
				}
			}
		}
	}

	@Override
	public void run() {
		synchronized (this) {
			for(int i = 0 ;i < 5; i++){
				System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
			}
		}		
	}
	

}
