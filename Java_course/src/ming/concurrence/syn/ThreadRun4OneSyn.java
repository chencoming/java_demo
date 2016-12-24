package ming.concurrence.syn;

/**
 * 若使用synchronized(this)时，当对象的某一个同步代码被某个线程执行时，说明该线程获得了
 * 该实例的对象锁，其他线程无法获得对象锁，则导致无论是哪个同步synchronized(this)代码块
 * 或者是synchronized方法都不能被执行
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
	 * 同步方法一
	 */
	public void synMethod1(){
		//ThreadRun4OneSyn object = new ThreadRun4OneSyn();
		synchronized(this){//若使用synchronized(object)则不会导致其他同步块堵塞
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
	 * 同步方法二
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
	 * 同步方法三
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
