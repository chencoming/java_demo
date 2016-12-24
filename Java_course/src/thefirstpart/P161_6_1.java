package thefirstpart;
import javax.swing.JOptionPane;
public class P161_6_1 {
	public static void main(String[] args) {

		int [] num = new int [10];
		int sum = 0,average = 0;
		for(int i = 0;i < 10;i++){
			String input = JOptionPane.showInputDialog("Enter the No." + (i + 1) + " number:");
			num[i] = Integer.parseInt(input);
			sum += num[i];
		}
		average = sum / 10;
		
		int large = 0;
		for(int j = 0;j < 10;j++){
			if(num[j] > average)
				large++;
		}
		
		JOptionPane.showMessageDialog(null,"The average of ten numbers is: " + average +
				"\nThere are " + large + " numbers large than average");	
		
		
		
	}

}
