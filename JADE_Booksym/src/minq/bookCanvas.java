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
class bookCanvas extends JPanel{
	
	
	bookAgent ug = null;
	
	Button addButton = new Button("����鱾");	
	
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