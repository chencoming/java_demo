/*
 * ����һ���ͻ��ˣ��������ӷ�����
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
			//Socket()����ȥ����ĳ��������������Ϊ��������ip��ַ�Ͷ˿ں�
			Socket socket = new Socket("127.0.0.1",9999);
			
			//ͨ��pw��sд���ݣ�true��ʾ��ʱ����
			PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
			pw.println("��������ǿͻ���");
			
			InputStreamReader isr = new InputStreamReader(socket.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			
			String info = br.readLine();
			System.out.println("�ӷ��������ص���Ϣ��" + info);
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
