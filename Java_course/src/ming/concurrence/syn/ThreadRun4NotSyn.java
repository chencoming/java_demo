package ming.concurrence.syn;

/**
 * 同一时刻只允许一个线程执行synchronized代码块，非同步代码仍然可以被执行
 * @author coming  2016-4-16
 */
public class ThreadRun4NotSyn {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final ThreadRun4NotSyn mythread = new ThreadRun4NotSyn();		/*
		 *  JVM中每个进程都会有多个根,每个static变量,方法参数,局部变量,当然这都是指引用类型.
		 *  基础类型是不能作为根的,根其实就是一个存储地址.垃圾回收器在工作时先从根开始遍历它引用的对象并标记它们,
		 *  如此递归到最末梢,所有根都遍历后,没有被标记到的对象说明没有被引用,那么就是可以被回收的对象
		 *  (有些对象有finalized方法,虽然没有引用,但JVM中有一个专门的队列引用它们直到finalized方法被执行后
		 *  才从该队列中移除成为真正没有引用的对象,可以回收,这个与本主题讨论的无关,包括代的划分等以后再说明).
		 *  这看起来很好但是在内部类的回调方法中,s既不可能是静态变量,也不是方法中的临时变量,也不是方法参数,
		 *  它不可能作为根,在内部类中也没有变量引用它,它的根在内部类外部的那个方法中,如果这时外面变量s重指向其它对象,
		 *  则回调方法中的这个对象s就失去了引用,可能被回收,而由于内部类回调方法大多数在其它线程中执行,
		 *  可能还要在回收后还会继续访问它.这将是什么结果?而使用final修饰符不仅会保持对象的引用不会改变,
		 *  而且编译器还会持续维护这个对象在回调方法中的生命周期.所以这才是final变量和final参数的根本意义.
		 */
		
		Thread th1 = new Thread(new Runnable() {
			@Override
			public void run() {
				mythread.synMethod();
			}
		}, "th1");
		
		Thread th2 = new Thread(new Runnable() {
			@Override
			public void run() {
				mythread.notSynMethod();
			}
		}, "th2");
		
		th1.start();
		th2.start();
	}
	
	/**
	 * 同步方法
	 */
	public void synMethod(){
		synchronized(this){
			int i=5;
			while(i-->0){
				System.out.println(Thread.currentThread().getName()+" : "+i);
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 非同步方法
	 * 
	 * @return void
	 */
	public void notSynMethod(){
		int i=5;
		while(i-->0){
			System.out.println(Thread.currentThread().getName()+" : "+i);
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
