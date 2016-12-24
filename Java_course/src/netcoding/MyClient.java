/*
 * 这是一个客户端，可以连接服务器
 * */

package netcoding;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class MyClient {

	
	public static void main(String []args){
		new MyClient();
	}
	
	
	public MyClient(){
		try{
			//Socket()就是去连接某个服务器，参数为服务器的ip地址和端口号
			Socket socket = new Socket("127.0.0.1",9999);
			
			//通过pw向s写数据，true表示即时更新
			PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
			pw.println("你好吗，我是客户端");
			
			InputStreamReader isr = new InputStreamReader(socket.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			
			String info = br.readLine();
			System.out.println("从服务器返回的信息：" + info);
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
