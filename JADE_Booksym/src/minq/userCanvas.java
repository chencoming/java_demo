package minq;


import java.awt.Button;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")							
/*@SuppressWarnings可以抑制一些能通过编
译但是存在有可能运行异常的代码会发出警告，
你确定代码运行时不会出现警告提示的情况下，																					
 可以使用这个注释。
 */
class userCanvas extends JPanel{
	
	
	userAgent ug = null;
	
	Button checkButton = new Button("查询");	
	Button buyButton = new Button("购买");	
	
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