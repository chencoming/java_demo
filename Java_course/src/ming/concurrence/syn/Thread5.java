package ming.concurrence.syn;

/**
 * 
 * @author coming 2016-4-17
 */
public class Thread5 {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Thread5 mythread = new Thread5();
		final Inner inner = mythread.new Inner();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				mythread.mySynMethod(inner);
			}
		}, "mythread_t1");
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				mythread.myNormalMethod(inner);
			}
		}, "mythread_t2");
		t1.start();
		t2.start();
	}

	/**
	 * 内部类
	 * @author coming  2016-4-17
	 */
	class Inner {
		private void normalMethod1() {
			int i = 5;
			while (i-- > 0) {
				System.out.println(Thread.currentThread().getName()
						+ ":Inner.normalMethod1()=" + i);
				try {
					Thread.sleep(500);
				} catch (Exception e) {

				}
			}
		}

		private void normalMethod2() {
			int i = 5;
			while (i-- > 0) {
				System.out.println(Thread.currentThread().getName()
						+ ":Inner.normalMethod2()=" + i);
				try {
					Thread.sleep(500);
				} catch (Exception e) {
				}
			}
		}
	}

	private void mySynMethod(Inner inner) {
		synchronized (inner) {// 使用对象锁
			inner.normalMethod1();
		}
	}

	private void myNormalMethod(Inner inner) {
		synchronized (inner) {// 使用对象锁
			inner.normalMethod2();
		}
	}

}
