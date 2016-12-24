package ming.concurrence.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test {
    private ReadWriteLock rwl = new ReentrantReadWriteLock();
     
    public static void main(String[] args)  {
        final Test test = new Test();
         
        new Thread(){ // ¶Á²Ù×÷
            public void run() {
                test.get(Thread.currentThread());
            };
        }.start();
        
        new Thread(){  // ¶Á²Ù×÷
            public void run() {
                test.get(Thread.currentThread());
            };
        }.start();
         
        new Thread(){  // Ð´²Ù×÷
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
                System.out.println(thread.getName()+"____¶Á___");
            }
            System.out.println(thread.getName()+"¶Á²Ù×÷Íê±Ï");
        } finally {
            rwl.readLock().unlock();
        }
    }
    
    public void write(Thread thread) {
        rwl.writeLock().lock();
        try {
            long start = System.currentTimeMillis();
             
            while(System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName()+"____Ð´___");
            }
            System.out.println(thread.getName()+"Ð´²Ù×÷Íê±Ï");
        } finally {
            rwl.writeLock().unlock();
        }
    }
}