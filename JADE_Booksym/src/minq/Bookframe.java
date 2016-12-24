package minq;

import java.awt.BorderLayout;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Bookframe extends JFrame{
	

	protected void setframe(Bookframe f,bookAgent ug){		
		
		
		
		add(new bookCanvas(ug));
		add(bookCanvas.panel,BorderLayout.SOUTH);
		add(bookCanvas.pane2,BorderLayout.NORTH);
		add(bookCanvas.pane3,BorderLayout.CENTER);
		
		//System.out.println("Ö´ÐÐ setFrameº¯Êý£¡");

		f.setTitle("bookAgent");
		f.setSize(300, 200);//set the size of window:542, 652
		f.setLocationRelativeTo(null);//set the location of window
		//frame.setLocation(100,100);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false); //forbid maxest-window
		
		
	}
}
