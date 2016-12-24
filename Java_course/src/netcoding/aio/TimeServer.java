package netcoding.aio;


/**
 *
 * @author coming  2016-7-22
 */
public class TimeServer {

	/**
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
		int port = 8080;
		
		AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
		
		new Thread(timeServer,"AIO-AsyncTimeServerHandle-001").start();
	}

}
