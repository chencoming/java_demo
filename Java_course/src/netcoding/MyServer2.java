/*
 * ����һ����������������9999�˿ڼ���
 * ���Խ��մӿͻ��˴���������Ϣ
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
			//��9999�Ŷ˿ڼ���
			ServerSocket serversocket = new ServerSocket(9999);
			
			System.out.println("���������������ڽ�������...");
			//�ȴ�ĳ���ͻ��������ӣ��ú����᷵��һ��socket����
			Socket socket = serversocket.accept();
			System.out.println("�������յ��������ڴ���...");
			//����socket������
			InputStreamReader isr = new InputStreamReader(socket.getInputStream()); 
			BufferedReader br = new BufferedReader(isr);
			
			//���տ���̨�������Ϣ
			InputStreamReader isrSys = new InputStreamReader(System.in); 
			BufferedReader brSys = new BufferedReader(isrSys);
			
			PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
			StringBuffer info ;
			
			while(true){
				info= new StringBuffer(br.readLine());
				
				System.out.println("�ͻ���˵��" + info);
				
				if(info.toString().equals("bye")){
					System.out.println("�Ự������");
					socket.close();
					break;
				}
				
				System.out.print("����������Կͻ���˵�Ļ���");
				info = new StringBuffer(brSys.readLine());
				
				pw.println(info);
			}
			
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
