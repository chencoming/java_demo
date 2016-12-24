package thesecondpart;
import java.util.*;
public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date = new Date();
		System.out.println("the elapsed time since Jan 1,1970 is " + 
				date.getTime() + "milliseconds");
		System.out.println(date.toString());
		
		
		Random ran1 = new Random(3);
		for(int i = 0;i < 10;i++){
			System.out.print(ran1.nextInt(1000) + " ");
		}
		System.out.println();
		
		Random ran2 = new Random(3);
		for(int i = 0;i < 10;i++){
			System.out.print(ran2.nextInt(1000) + " ");
		}
		
		
		
	}

}
