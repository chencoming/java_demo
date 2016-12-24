package minq;

import java.awt.BorderLayout;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Myframe extends JFrame{
	

	protected void setframe(Myframe f,userAgent ug){		
		
		
		
		add(new userCanvas(ug));
		add(userCanvas.panel,BorderLayout.SOUTH);
		add(userCanvas.pane2,BorderLayout.NORTH);
		//add(ImageCanvas.pane3,BorderLayout.CENTER);
		
		//System.out.println("Ö´ÐÐ setFrameº¯Êý£¡");

		f.setTitle("userAgent");
		f.setSize(300, 200);//set the size of window:542, 652
		f.setLocationRelativeTo(null);//set the location of window
		//frame.setLocation(100,100);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false); //forbid maxest-window
		
		
	}
}
