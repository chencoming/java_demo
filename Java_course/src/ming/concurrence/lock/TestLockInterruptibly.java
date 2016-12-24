package ming.concurrence.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLockInterruptibly {
    private Lock lock = new ReentrantLock();   
    public static void main(String[] args)  {
        TestLockInterruptibly test = new TestLockInterruptibly();
        MyThread thread1 = new MyThread(test);
        MyThread thread2 = new MyThread(test);
        thread1.start();
        thread2.start();
         
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
    }  
     
    public void insert(Thread thread) throws InterruptedException{
        lock.lockInterruptibly();   //ע�⣬�����Ҫ��ȷ�жϵȴ������̣߳����뽫��ȡ���������棬Ȼ��InterruptedException�׳�
        try {  
            System.out.println(thread.getName()+"�õ�����");
            long startTime = System.currentTimeMillis();
            for(    ;     ;) {
                if(System.currentTimeMillis() - startTime >= 9999)
                    break;
                //��������
            }
        }
        finally {
            System.out.println(Thread.currentThread().getName()+"ִ��finally");
            lock.unlock();
            System.out.println(thread.getName()+"�ͷ�����");
        }  
    }
}
 
class MyThread extends Thread {
    private TestLockInterruptibly test = null;
    public MyThread(TestLockInterruptibly test) {
        this.test = test;
    }
    @Override
    public void run() {
         
        try {
            test.insert(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"���ж�");
        }
    }
}