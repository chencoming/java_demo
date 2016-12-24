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
						System.out.println("�Ӷ���ȡ��һ��Ԫ�أ�����ʣ��" 
								+ queue.size() + "��Ԫ��");
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
						System.out.println("������в���һ��Ԫ�أ�����ʣ��ռ䣺"
								+ (queueSize - queue.size()));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
