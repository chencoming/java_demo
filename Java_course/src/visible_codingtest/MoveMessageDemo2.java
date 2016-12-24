package visible_codingtest;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//@SuppressWarnings("serial")
public class MoveMessageDemo2 extends JFrame{	
	public MoveMessageDemo2(){
		MovableMessagePanel p =  new MovableMessagePanel("welcome to java");
		
		getContentPane().setLayout(new BorderLayout());
//		getContentPane().add(p);
		
		
	}
	
	public static void main(String[] args){
		MoveMessageDemo2 frame = new MoveMessageDemo2();
		frame.setTitle("MoveMessageDemo");
		frame.setLocationRelativeTo(null);//set the location of window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setVisible(true);

	}




	static class  MovableMessagePanel extends JPanel{
		private String message = "welcome to java";
		private int x = 20;
		private int y = 30;
		
		public MovableMessagePanel(String s){
			message  = s;
			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e){
					x = e.getX();
					y = e.getY();
					repaint();
				}
			});
		}
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawString(message, x, y);
		}
		
		
	}
		
}


