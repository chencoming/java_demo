package netcoding;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class QClient extends JFrame implements ActionListener{
	
	
	JTextArea jtextarea = null; 
	JTextField jtextfield = null;
	JButton btnsent = null;
	JButton btnclose = null;
	
	JPanel jpanel = null;
	JScrollPane jscrollpanel = null;
	
	Socket socket = null;
	// ����Ϣ�����������Ķ���
	PrintWriter pw = null;
	
	public static void main(String[]args){
		new QClient();
	}
	
	
	public QClient(){
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
		this.setTitle("�ͻ���");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		try{
			//��9999�Ŷ˿ڼ���
			socket = new Socket("127.0.0.1",9999);
			
			
			//����socket������
			InputStreamReader isr = new InputStreamReader(socket.getInputStream()); 
			BufferedReader br = new BufferedReader(isr);
			
			
			pw = new PrintWriter(socket.getOutputStream(),true);
			StringBuffer info = new StringBuffer("");
			
			while(true){
				info.append("�ͻ���˵��" +br.readLine() +"\n");
				
				jtextarea.setText( info.toString());
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
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
