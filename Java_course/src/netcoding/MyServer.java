/*
 * 这是一个服务器，让它在9999端口监听
 * 可以接收从客户端传过来的信息
 * */

package netcoding;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class MyServer {
	
	public static void main(String [] args){
		new MyServer();
	}

	public MyServer(){
		try{
			//在9999号端口监听
			ServerSocket serversocket = new ServerSocket(9999);
			
			System.out.println("服务器启动");
			//等待某个客户端来连接，该函数会返回一个socket连接
			Socket socket = serversocket.accept();
			
			InputStreamReader isr = new InputStreamReader(socket.getInputStream()); 
			BufferedReader br = new BufferedReader(isr);
			
			String info = br.readLine();
			
			System.out.println("服务器端：" + info);
			
			PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
			pw.println("你好吗，恭喜你连接到服务器！");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
