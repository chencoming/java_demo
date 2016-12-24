package ming.concurrence.syn;

/**
 * ͬһʱ��ֻ����һ���߳�ִ��synchronized����飬��ͬ��������Ȼ���Ա�ִ��
 * @author coming  2016-4-16
 */
public class ThreadRun4NotSyn {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final ThreadRun4NotSyn mythread = new ThreadRun4NotSyn();		/*
		 *  JVM��ÿ�����̶����ж����,ÿ��static����,��������,�ֲ�����,��Ȼ�ⶼ��ָ��������.
		 *  ���������ǲ�����Ϊ����,����ʵ����һ���洢��ַ.�����������ڹ���ʱ�ȴӸ���ʼ���������õĶ��󲢱������,
		 *  ��˵ݹ鵽��ĩ��,���и���������,û�б���ǵ��Ķ���˵��û�б�����,��ô���ǿ��Ա����յĶ���
		 *  (��Щ������finalized����,��Ȼû������,��JVM����һ��ר�ŵĶ�����������ֱ��finalized������ִ�к�
		 *  �ŴӸö������Ƴ���Ϊ����û�����õĶ���,���Ի���,����뱾�������۵��޹�,�������Ļ��ֵ��Ժ���˵��).
		 *  �⿴�����ܺõ������ڲ���Ļص�������,s�Ȳ������Ǿ�̬����,Ҳ���Ƿ����е���ʱ����,Ҳ���Ƿ�������,
		 *  ����������Ϊ��,���ڲ�����Ҳû�б���������,���ĸ����ڲ����ⲿ���Ǹ�������,�����ʱ�������s��ָ����������,
		 *  ��ص������е��������s��ʧȥ������,���ܱ�����,�������ڲ���ص�����������������߳���ִ��,
		 *  ���ܻ�Ҫ�ڻ��պ󻹻����������.�⽫��ʲô���?��ʹ��final���η������ᱣ�ֶ�������ò���ı�,
		 *  ���ұ������������ά����������ڻص������е���������.���������final������final�����ĸ�������.
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
	 * ͬ������
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
	 * ��ͬ������
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
