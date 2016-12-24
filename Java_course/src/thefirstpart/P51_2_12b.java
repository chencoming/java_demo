package thefirstpart;
import java.util.Scanner;
public class P51_2_12b {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
	    System.out.print("Enter employee's name: ");
		String name = scanner.next();			
		
		float hourlypay,federaltax,statetax;
		
		System.out.print("Enter the number\n" +
				"of hours worked in a week:");
		int worktime = scanner.nextInt();
		
		System.out.print("Enter hourly pay rate:");
		hourlypay = scanner.nextFloat();
		
		System.out.print("Enter federal tax "
				+ "withholding rate:");
		federaltax = scanner.nextFloat();
		
		System.out.print("Enter state tax "
				+ "withholding rate:");
		statetax = scanner.nextFloat();
		
		System.out.print("Employee Name   " + name +
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
