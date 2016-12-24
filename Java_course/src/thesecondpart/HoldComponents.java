package thesecondpart;

import javax.swing.*;
import java.awt.*;


public class HoldComponents {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();

		//FlowLayout layout = new FlowLayout();
		//frame.setLayout(layout);
		frame.setLayout(new FlowLayout());
		
		JButton jbtok = new JButton("OK");
		frame.add(jbtok);
		
		frame.setTitle("Window 1");
		frame.setSize(400,300);
		frame.setLocation(400,350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
