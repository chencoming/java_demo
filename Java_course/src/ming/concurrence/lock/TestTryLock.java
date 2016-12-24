package ming.concurrence.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestTryLock {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private Lock lock = new ReentrantLock();    //ע������ط�
    public static void main(String[] args)  {
        final TestTryLock test = new TestTryLock();
         
        new Thread(){// ������һ���߳�
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();
         
        new Thread(){// �����ڶ����߳�
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();
    }  
     
    public void insert(Thread thread) {
        if(lock.tryLock()) {
            try {
                System.out.println(thread.getName()+"�õ�����");
                for(int i=0;i<5;i++) {
                    arrayList.add(i);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }finally {
                System.out.println(thread.getName()+"�ͷ�����");
                lock.unlock();
            }
        } else {
            System.out.println(thread.getName()+"��ȡ��ʧ��");
        }
    }
}