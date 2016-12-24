package thefirstpart;
import java.util.Scanner;
public class P74_3_9 {
	public static void main(String[] args) {
		System.out.println("Enter three sides of Triangular:");
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		int c = scanner.nextInt();
		if(a + b > c && a + c > b && b + c > a)
		{
			System.out.print("The girth of Triangular is " + (a + b + c));
		}
		else{
			System.out.print("Invalid sides!");
			System.exit(0);
		}
	}
}
