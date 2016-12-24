package thefirstpart;
import javax.swing.JOptionPane;
public class P72_3_18 {
	public static void main(String[] args) {
		String integer = JOptionPane.showInputDialog(null,"Enter a integer:");
		int a = Integer.parseInt(integer),x = 0; 
		switch(a){
		case 1: x += 5; break;
		case 2: x += 10; break;
		case 3: x += 16; break;
		case 4: x += 34; break;
		default: JOptionPane.showMessageDialog(null, "Error input!");
					System.exit(0);
		}
		JOptionPane.showMessageDialog(null, "x = " + x);
	}

}
