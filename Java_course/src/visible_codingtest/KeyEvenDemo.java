package visible_codingtest;
import javax.swing.*;

public class KeyEvenDemo extends JFrame{
	private KeyboardPanel keyboardPanel = new KeyboardPanel();
	
	public KeyEvenDemo(){
		add(keyboardPanel);
		keyboardPanel.setFocusable(true);
		
		
	}
	
	public static void main(String[] args){
		KeyEvenDemo frame = new KeyEvenDemo();
		frame.setTitle("keyeventdemo");
		frame.setLocationRelativeTo(null);//set the location of window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setResizable(false); //forbid maxest-window

	}




	static class  KeyboardPanel extends JPanel{
		private int x = 100;
		private int y = 100;
		
		
		
	}
}


