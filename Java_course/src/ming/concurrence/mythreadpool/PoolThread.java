package ming.concurrence.mythreadpool;

import java.util.concurrent.BlockingQueue;

/**
 * 
 * @author coming 2016-7-25
 */
public class PoolThread extends Thread {
	private BlockingQueue<Runnable> taskQueue = null;
	private boolean isStopped = false;

	public PoolThread(BlockingQueue<Runnable> queue) {
		taskQueue = queue;
	}

	public void run() {
		while (!isStopped()) {
			try {
				Runnable runnable = taskQueue.take();
				runnable.run();
			} catch (Exception e) {
				// д��־���߱����쳣,
				// �������̳߳�����.
			}
		}
	}

	public synchronized void toStop() {
		isStopped = true;
		this.interrupt(); // ��ϳ����̵߳� dequeue() ����.
	}

	public synchronized boolean isStopped() {
		return isStopped;
	}
}
