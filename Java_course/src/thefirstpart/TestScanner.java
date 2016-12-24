package thefirstpart;
import java.util.Scanner;
public class TestScanner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.print("enter an integer: ");
		int intvalue = scanner.nextInt();
		System.out.println("you enter an integer " + intvalue);
		int a = 1;
		a %= 3 / a + 3;
		System.out.println(a);
		double d = 1.0;
		//d = 4 + d * d + 4;
		d = d - 1.5 * 3 + a++; 
		System.out.println(d);		
	}
}
