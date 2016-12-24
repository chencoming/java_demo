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
	
	// 把信息发给客户端的对象
	PrintWriter pw = null;
	
	
	
	
	
	
	public static void main(String[]args){
		new QServer();
	}
	
	
	public QServer(){
		jtextarea = new JTextArea(); 
		jtextfield = new JTextField(10);
		btnsent = new JButton("发送");
		btnclose = new JButton("关闭");
		
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
		this.setTitle("服务端");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try{
			//在9999号端口监听
			serversocket = new ServerSocket(9999);
			
			System.out.println("服务器启动");
			//等待某个客户端来连接，该函数会返回一个socket连接
			Socket socket = serversocket.accept();
			
			//接收socket输入流
			InputStreamReader isr = new InputStreamReader(socket.getInputStream()); 
			BufferedReader br = new BufferedReader(isr);
			
			/*//接收控制台输入的信息
			InputStreamReader isrSys = new InputStreamReader(System.in); 
			BufferedReader brSys = new BufferedReader(isrSys);*/
			
			pw = new PrintWriter(socket.getOutputStream(),true);
			
			StringBuffer info = new StringBuffer();
			
			while(true){
				info.append("服务器说：" +br.readLine() +"\n");
				
				jtextarea.setText( info.toString());
				
				/*if(info.toString().equals("bye")){
					System.out.println("会话结束！");
					socket.close();
					break;
				}
				
				System.out.print("请输入你想对客户端说的话：");
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
		//用户按下发送按钮
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
