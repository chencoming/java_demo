/*
 * 这是一个服务器，让它在9999端口监听
 * 可以接收从客户端传过来的信息
 * */

package netcoding;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class MyServer2 {
	
	public static void main(String [] args){
		new MyServer2();
	}

	public MyServer2(){
		try{
			//在9999号端口监听
			ServerSocket serversocket = new ServerSocket(9999);
			
			System.out.println("服务器启动，正在接收请求...");
			//等待某个客户端来连接，该函数会返回一个socket连接
			Socket socket = serversocket.accept();
			System.out.println("服务器收到请求，正在处理...");
			//接收socket输入流
			InputStreamReader isr = new InputStreamReader(socket.getInputStream()); 
			BufferedReader br = new BufferedReader(isr);
			
			//接收控制台输入的信息
			InputStreamReader isrSys = new InputStreamReader(System.in); 
			BufferedReader brSys = new BufferedReader(isrSys);
			
			PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
			StringBuffer info ;
			
			while(true){
				info= new StringBuffer(br.readLine());
				
				System.out.println("客户端说：" + info);
				
				if(info.toString().equals("bye")){
					System.out.println("会话结束！");
					socket.close();
					break;
				}
				
				System.out.print("请输入你想对客户端说的话：");
				info = new StringBuffer(brSys.readLine());
				
				pw.println(info);
			}
			
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
