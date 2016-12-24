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
	 * ������
	 * @author coming  2016-7-7
	 */
	class Producer extends Thread{

		@Override
		public void run() {
			while (true) {
				synchronized (queue) {
					while(queue.size() == queueSize){// �����������޷�������Ԫ�أ��ͷ���
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
	 * ������
	 * @author coming  2016-7-7
	 */
	class Consumer extends Thread{
		
		@Override
		public void run(){
			while (true) {
				synchronized (queue) {
					while(queue.size() == 0){// ����û��Ԫ�أ��ͷ���
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
