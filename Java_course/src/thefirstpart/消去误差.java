package thefirstpart;
//import javax.swing.JOptionPane;
public class ÏûÈ¥Îó²î {
	public static void main(String[] args) {
		double sum = 0;
		int n = 50000;
		for(int i = 1;i <= n;i++)
			sum += 1.00 / i;
		//JOptionPane.showMessageDialog(null, "The result counted from\n" + "left to right is:" + sum);
		System.out.println("The result counted from left to right is:" + sum);
		sum = 0;
		for(int j = n;j >= 1;j--)
			sum += 1.00 / j;
		//JOptionPane.showMessageDialog(null, "The result counted from\n" + "right to left is:" + sum);
		System.out.println("The result counted from right to left is:" + sum);
	}

}
