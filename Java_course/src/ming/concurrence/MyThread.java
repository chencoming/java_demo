package ming.concurrence;

/**
 * �����̵߳ķ���֮һ���̳�Thread����дrun����
 * @author coming  2016-4-19
 */
public class MyThread extends Thread {

	/**
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
		MyThread th = new MyThread();
//		Thread thread = new Thread(th);
		th.start();
		System.out.println("main over");
	}
	
	@Override
	public void run(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("im a thread");
	}

}
