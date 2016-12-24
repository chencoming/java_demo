package ming.concurrence.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test {
    private ReadWriteLock rwl = new ReentrantReadWriteLock();
     
    public static void main(String[] args)  {
        final Test test = new Test();
         
        new Thread(){ // ������
            public void run() {
                test.get(Thread.currentThread());
            };
        }.start();
        
        new Thread(){  // ������
            public void run() {
                test.get(Thread.currentThread());
            };
        }.start();
         
        new Thread(){  // д����
            public void run() {
                test.write(Thread.currentThread());
            };
        }.start();
        

         
    }  
     
    public void get(Thread thread) {
        rwl.readLock().lock();
        try {
            long start = System.currentTimeMillis();
            
             
            while(System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName()+"____��___");
            }
            System.out.println(thread.getName()+"���������");
        } finally {
            rwl.readLock().unlock();
        }
    }
    
    public void write(Thread thread) {
        rwl.writeLock().lock();
        try {
            long start = System.currentTimeMillis();
             
            while(System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName()+"____д___");
            }
            System.out.println(thread.getName()+"д�������");
        } finally {
            rwl.writeLock().unlock();
        }
    }
}