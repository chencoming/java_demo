package queue;

import java.util.PriorityQueue;

/**
 *
 * @author coming  2016-7-7
 */
public class MyProducerAndConsumer {
	
	private final int queueSize = 10;
	private PriorityQueue<Integer> queue = 
		new PriorityQueue<Integer>(queueSize);

	/**
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
		MyProducerAndConsumer my = new MyProducerAndConsumer();
		Producer producer = my.new Producer();
		Consumer consumer = my.new Consumer();
		System.out.println("start...");
		producer.start();
		consumer.start();
	}
	
	/**
	 * 生产者
	 * @author coming  2016-7-7
	 */
	class Producer extends Thread{

		@Override
		public void run() {
			while (true) {
				synchronized (queue) {
					while(queue.size() == queueSize){// 队列已满，无法插入新元素，释放锁
						try {
							System.out.println("queue has been full");
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
							queue.notify();
						}
					}
					queue.offer(1);
					queue.notify();
					System.out.println("producer insert an new element and now queue remain " 
							+  (queueSize - queue.size()) + " empty");
				}
			}
		}
		
		
	}

	/**
	 * 消费者
	 * @author coming  2016-7-7
	 */
	class Consumer extends Thread{
		
		@Override
		public void run(){
			while (true) {
				synchronized (queue) {
					while(queue.size() == 0){// 队列没有元素，释放锁
						try {
							System.out.println("queue has no elements");
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
							queue.notify();
						}
					}
					
					queue.poll();
					System.out.println("consumer take one away and now queue remain"
							+ queue.size());
					queue.notify();
				}
				
			}
		}
	}
}
