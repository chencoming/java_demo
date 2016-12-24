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
class bookCanvas extends JPanel{
	
	
	bookAgent ug = null;
	
	Button addButton = new Button("添加书本");	
	
	public static JTextField booknameinput = new JTextField("input name of book");
	public static JTextField numinput = new JTextField("input num of books");
	public static JTextField priceinput = new JTextField("input price of book");
	

	public static Panel panel =new Panel();
	public static Panel pane2 =new Panel();
	public static Panel pane3 =new Panel();
	
	

	public bookCanvas(final bookAgent ug){
		
		this.ug = ug;
		pane2.add(booknameinput);
		pane2.add(numinput);
		pane3.add(priceinput);
		
		
		panel.add(addButton);

		addButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				ug.addbook(booknameinput.getText(),numinput.getText(),priceinput.getText());
			}
		});
		
		
		
	}
    
    
}