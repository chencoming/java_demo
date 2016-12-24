package netcoding;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class QServer extends JFrame implements ActionListener {

	JTextArea jtextarea = null; 
	JTextField jtextfield = null;
	JButton btnsent = null;
	JButton btnclose = null;
	
	JPanel jpanel = null;
	JScrollPane jscrollpanel = null;
	
	
	ServerSocket serversocket = null;
	
	// ����Ϣ�����ͻ��˵Ķ���
	PrintWriter pw = null;
	
	
	
	
	
	
	public static void main(String[]args){
		new QServer();
	}
	
	
	public QServer(){
		jtextarea = new JTextArea(); 
		jtextfield = new JTextField(10);
		btnsent = new JButton("����");
		btnclose = new JButton("�ر�");
		
		btnsent.addActionListener(this);
		btnclose.addActionListener(this);
		
		jscrollpanel = new JScrollPane(jtextarea);
		
		jpanel = new JPanel();
		jpanel.add(jtextfield);
		jpanel.add(btnsent);
		jpanel.add(btnclose);
		
		
		this.add(jscrollpanel,"Center");
		this.add(jpanel,"South");
		
		this.setSize(300,200);
		this.setVisible(true);
		this.setLocation(400, 300);
		this.setTitle("�����");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try{
			//��9999�Ŷ˿ڼ���
			serversocket = new ServerSocket(9999);
			
			System.out.println("����������");
			//�ȴ�ĳ���ͻ��������ӣ��ú����᷵��һ��socket����
			Socket socket = serversocket.accept();
			
			//����socket������
			InputStreamReader isr = new InputStreamReader(socket.getInputStream()); 
			BufferedReader br = new BufferedReader(isr);
			
			/*//���տ���̨�������Ϣ
			InputStreamReader isrSys = new InputStreamReader(System.in); 
			BufferedReader brSys = new BufferedReader(isrSys);*/
			
			pw = new PrintWriter(socket.getOutputStream(),true);
			
			StringBuffer info = new StringBuffer();
			
			while(true){
				info.append("������˵��" +br.readLine() +"\n");
				
				jtextarea.setText( info.toString());
				
				/*if(info.toString().equals("bye")){
					System.out.println("�Ự������");
					socket.close();
					break;
				}
				
				System.out.print("����������Կͻ���˵�Ļ���");
				info = new StringBuffer(brSys.readLine());
				
				pw.println(info);*/
			}
				
			
		}catch(Exception e){
			e.printStackTrace();
		}	
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//�û����·��Ͱ�ť
		if(arg0.getSource() == btnsent){
			String info = jtextfield.getText();
			pw.println(info);
			jtextfield.setText("");
			
		}
		if(arg0.getSource()== btnclose){
			try {
				serversocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
