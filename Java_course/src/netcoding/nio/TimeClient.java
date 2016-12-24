package netcoding.nio;

/**
 *
 * @author coming  2016-7-20
 */
public class TimeClient {

	/**
	 * @param args
	 * @return void
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		new Thread(new TimeClientHandle("127.0.0.1", 8080),
				"TimeClient-001").start();
		new Thread(new TimeClientHandle("127.0.0.1", 8080),
				"TimeClient-002").start();
		new Thread(new TimeClientHandle("127.0.0.1", 8080),
				"TimeClient-003").start();
		
	}

}
