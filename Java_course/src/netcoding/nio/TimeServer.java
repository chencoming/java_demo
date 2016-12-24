package netcoding.nio;

/**
 *
 * @author coming  2016-7-20
 */
public class TimeServer {

	/**
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
		int port = 8080;
		
		MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
		
		new Thread(timeServer,"NIO-MultiplexerTimeServer-001").start();
		
		new Thread(){
			public void run(){
				while(true){
					System.out.println("The server is listening...");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

}
