/*
 * ����һ����������������9999�˿ڼ���
 * ���Խ��մӿͻ��˴���������Ϣ
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
			//��9999�Ŷ˿ڼ���
			ServerSocket serversocket = new ServerSocket(9999);
			
			System.out.println("����������");
			//�ȴ�ĳ���ͻ��������ӣ��ú����᷵��һ��socket����
			Socket socket = serversocket.accept();
			
			InputStreamReader isr = new InputStreamReader(socket.getInputStream()); 
			BufferedReader br = new BufferedReader(isr);
			
			String info = br.readLine();
			
			System.out.println("�������ˣ�" + info);
			
			PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
			pw.println("����𣬹�ϲ�����ӵ���������");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
