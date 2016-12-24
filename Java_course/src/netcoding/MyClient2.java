/*
 * ����һ���ͻ��ˣ��������ӷ�����
 * */

package netcoding;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class MyClient2 {

	
	public static void main(String []args){
		new MyClient2();
	}
	
	
	public MyClient2(){
		try{
			//Socket()����ȥ����ĳ��������������Ϊ��������ip��ַ�Ͷ˿ں�
			Socket socket = new Socket("127.0.0.1",9999);
			
			//ͨ��pw��sд���ݣ�true��ʾ��ʱ����
			PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
			//pw.println("��������ǿͻ���");
			
			InputStreamReader isr = new InputStreamReader(socket.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			
			InputStreamReader isrSys = new InputStreamReader(System.in);
			BufferedReader brSys = new BufferedReader(isrSys);
			
			StringBuffer info;
			
			while(true){
				System.out.print("����������Է�����˵�Ļ���");
				
				info = new StringBuffer(brSys.readLine());
				
				pw.println(info);
				
				if(info.toString().equals("bye")){
					System.out.println("�Ự������");
					socket.close();
					break;
				}
				
				info = new StringBuffer(br.readLine());
				
				System.out.println("������˵��" + info);
				
				
			}
			
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
