package ming.concurrence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 
 * @author coming 2016-7-21
 */
public class TestFutureTask {
	
	public static void main(String[] args) {
		// ��һ�ַ�ʽ
/*		ExecutorService executor = Executors.newCachedThreadPool();
		Task task = new Task();
		FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
		executor.submit(futureTask);
		executor.shutdown();*/

		// �ڶ��ַ�ʽ��ע�����ַ�ʽ�͵�һ�ַ�ʽЧ�������Ƶģ�
		//ֻ����һ��ʹ�õ���ExecutorService��һ��ʹ�õ���Thread
		Task task = new Task();
		FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
		Thread thread = new Thread(futureTask);
		thread.start();
		 

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		System.out.println("���߳���ִ������");

		try {
			System.out.println("task���н��" + futureTask.get());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("��������ִ�����");
	}
}

