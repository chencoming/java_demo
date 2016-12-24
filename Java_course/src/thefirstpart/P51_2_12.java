package thefirstpart;
import javax.swing.JOptionPane;
public class P51_2_12 {
	public static void main(String[] args) {
		
		String name = JOptionPane.showInputDialog(null,"Enter employee's name: ");
		
		float hourlypay,federaltax,statetax;
		
		String inputworktime = JOptionPane.showInputDialog(null,"Enter the number\n" +
				"of hours worked in a week:");
		int worktime = Integer.parseInt(inputworktime);
		
		String inputhourlypay = JOptionPane.showInputDialog(null,"Enter hourly pay rate:");
		hourlypay = Float.parseFloat(inputhourlypay);
		
		String inputfederaltax = JOptionPane.showInputDialog(null,"Enter federal tax "
				+ "withholding rate:");
		federaltax = Float.parseFloat(inputfederaltax);
		
		String inputstatetax = JOptionPane.showInputDialog(null,"Enter state tax "
				+ "withholding rate:");
		statetax = Float.parseFloat(inputstatetax);
		
		JOptionPane.showMessageDialog(null, "Employee Name   " + name +
				"\nHours worked   " + worktime + 
				"\nPay Rate   $" + hourlypay + 
				"\nGross Pay   $" + hourlypay * worktime +
				"\nDeductions" + 
				"\n  Federal Withholding(" + federaltax * 100 + "%)   $" + 
										hourlypay * worktime * federaltax +
				"\n  State Withholding(" + statetax * 100 + "%)   $" +
									((double)(int)( hourlypay * worktime * statetax * 100) ) / 100 +
				"\n  Total Deduction   $" + ((double)(int)( hourlypay * worktime * (federaltax + statetax) * 100) ) / 100 +
				"\nNet Pay   $" + ((double)(int)((hourlypay * worktime - hourlypay * worktime * (federaltax + statetax))* 100)) / 100
				);
	}

}
