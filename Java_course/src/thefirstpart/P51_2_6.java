package thefirstpart;
import javax.swing.JOptionPane;

public class P51_2_6 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int number,a,b,c;
		String tipsint = JOptionPane.showInputDialog("Enter a integer "
				+ "between 0 and 1000");
		number = Integer.parseInt(tipsint);
		a = number / 100;
		b = number / 10 % 10;
		c = number % 10;
		
		JOptionPane.showMessageDialog(null,"The summary of the numbers of "
				+ number + " is " + (a + b + c));		
	}

}
