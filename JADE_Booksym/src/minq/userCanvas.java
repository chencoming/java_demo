package minq;


import java.awt.Button;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")							
/*@SuppressWarnings��������һЩ��ͨ����
�뵫�Ǵ����п��������쳣�Ĵ���ᷢ�����棬
��ȷ����������ʱ������־�����ʾ������£�																					
 ����ʹ�����ע�͡�
 */
class userCanvas extends JPanel{
	
	
	userAgent ug = null;
	
	Button checkButton = new Button("��ѯ");	
	Button buyButton = new Button("����");	
	
	public static JTextField booknameinput = new JTextField("input name of book");
	//JTextArea booknameinput2 = new JTextArea("bookname2");

	public static Panel panel =new Panel();
	public static Panel pane2 =new Panel();
	//public static Panel pane3 =new Panel();
	

	public userCanvas(final userAgent ug){
		
		this.ug = ug;
		pane2.add(booknameinput);
		//pane3.add(booknameinput2);
		panel.add(checkButton);
		panel.add(buyButton);

		checkButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				ug.sendQueryMsg(booknameinput.getText().toString());
			}
		});
		
		buyButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				ug.sendInformMsg(booknameinput.getText().toString()); 		
			}
		});
		
		
		
	}
    
    
}