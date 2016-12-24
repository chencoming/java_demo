package thefirstpart;
import java.util.Scanner;
public class arraytest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int lenth;
		System.out.print("Enter the number:");
		Scanner scanner = new Scanner(System.in);
		lenth = scanner.nextInt();
		int [] array = new int [lenth];
		for(int i = 0;i < lenth;i++){
			System.out.print(array[i] + "  ");
		}
		
		System.out.println();
		System.out.print(array);
		
		
	}

}
