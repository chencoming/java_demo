package queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 
 * @author coming 2016-7-7
 */
public class TestBlockQueue {
	private int queueSize = 10;
	private ArrayBlockingQueue<Integer> queue = 
		new ArrayBlockingQueue<Integer>(	queueSize);

	public static void main(String[] args) {
		TestBlockQueue test = new TestBlockQueue();
		Producer producer = test.new Producer(test.queue);
		Consumer consumer = test.new Consumer(test.queue);

		System.out.println("start....");
		producer.start();
		consumer.start();
	}

	class Consumer extends Thread {
		private ArrayBlockingQueue<Integer> queue;
		
		public Consumer(ArrayBlockingQueue<Integer> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			consume();
		}

		private void consume() {
			while (true) {
				try {
						queue.take();
						System.out.println("从队列取走一个元素，队列剩余" 
								+ queue.size() + "个元素");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class Producer extends Thread {

		private ArrayBlockingQueue<Integer> queue;
		
		public Producer(ArrayBlockingQueue<Integer> queue) {
			this.queue = queue;
		}
		
		@Override
		public void run() {
			produce();
		}

		private void produce() {
			while (true) {
				try {
						queue.put(1);
						System.out.println("向队列中插入一个元素，队列剩余空间："
								+ (queueSize - queue.size()));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
