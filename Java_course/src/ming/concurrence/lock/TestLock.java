package ming.concurrence.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
	private ArrayList<Integer> arrayList = new ArrayList<Integer>();
	Lock lock = new ReentrantLock(); // 注意这个地方

	public static void main(String[] args) {
		final TestLock test = new TestLock();

		System.out.println(Thread.currentThread().getName() + "的优先级为："
				+ Thread.currentThread().getPriority());
		new Thread() {
			public void run() {
				test.insert(Thread.currentThread());
			};
		}.start();
		

		new Thread() {
			public void run() {
				test.insert(Thread.currentThread());
			};
		}.start();
		
	}

	public void insert(Thread thread) {
		lock.lock();
		try {
			System.out.println(thread.getName() + "得到了锁");
			System.out.println(Thread.currentThread().getName() + "的优先级为："
					+ Thread.currentThread().getPriority());
			for (int i = 0; i < 5; i++) {
				arrayList.add(i);
				System.out.println(thread.getName() + " ------  " + i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(thread.getName() + "释放了锁");
			lock.unlock();
		}
	}
}