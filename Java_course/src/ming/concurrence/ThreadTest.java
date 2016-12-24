package ming.concurrence;


/**
 *
 * @author coming  2016-4-17
 */
public class ThreadTest implements Runnable{

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			System.out.println("im saying sth!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		ThreadTest test = new ThreadTest();
		Thread thread = new Thread(test);
		thread.setDaemon(true);//�����������ػ��̣߳�main�߳����н����󽫵���jvm�˳������̲߳���ִ�����
		thread.start();
		System.out.println("main over");
	}

}
