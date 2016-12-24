package ming.concurrence.syn;

/**
 * ��ʹ��synchronized(this)ʱ���������ĳһ��ͬ�����뱻ĳ���߳�ִ��ʱ��˵�����̻߳����
 * ��ʵ���Ķ������������߳��޷���ö������������������ĸ�ͬ��synchronized(this)�����
 * ������synchronized���������ܱ�ִ��
 * @author coming  2016-4-17
 */
public class ThreadRun4OneSyn {

	/**
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
		final ThreadRun4OneSyn mythread = new ThreadRun4OneSyn();
		Thread th1 = new Thread(new Runnable() {
			@Override
			public void run() {
				mythread.synMethod1();
			}
		}, "th1");
		
		Thread th2 = new Thread(new Runnable() {
			@Override
			public void run() {
				mythread.synMethod2();
			}
		}, "th2");
		
		Thread th3 = new Thread(new Runnable() {
			@Override
			public void run() {
				mythread.synMethod3();
			}
		}, "th3");
		
		th1.start();
		th2.start();
		th3.start();
	}
	
	/**
	 * ͬ������һ
	 */
	public void synMethod1(){
		//ThreadRun4OneSyn object = new ThreadRun4OneSyn();
		synchronized(this){//��ʹ��synchronized(object)�򲻻ᵼ������ͬ�������
			int i=5;
			while(i-->0){
				System.out.println(Thread.currentThread().getName()+" : "+i);
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ͬ��������
	 */
	public void synMethod2(){
		synchronized(this){
			int i=5;
			while(i-->0){
				System.out.println(Thread.currentThread().getName()+" : "+i);
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ͬ��������
	 */
	public synchronized void synMethod3(){
		synchronized(this){
			int i=5;
			while(i-->0){
				System.out.println(Thread.currentThread().getName()+" : "+i);
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
