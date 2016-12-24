package thefirstpart;
import java.util.Scanner;
public class P74_3_2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter an number:");
		int num = scanner.nextInt();
		switch(num % 2){
		case 0: System.out.print("Is " + num + " an even number? true");break; 
		case 1: System.out.print("Is " + num + " an even number? false");break;
		}
	}
}
