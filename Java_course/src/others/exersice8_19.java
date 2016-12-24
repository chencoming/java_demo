package others;
import java.io.*;
import java.util.*;

public class exersice8_19 {
	public static void main(String[]args)throws Exception{
		if (args.length != 2) {
			System.out.println("The input is false");
			System.exit(0);
		}
		// Check if source file exists
		File sourceFile = new File(args[0]);
		if (!sourceFile.exists()) {
			System.out.println("Source file " + args[0] + " does not exist");
			System.exit(0);
		}

		// Check if target file exists
		File targetFile = new File(args[1]);
		if (targetFile.exists()) {
			System.out.println("Target file " + args[1] + " already exists");
			System.exit(0);
		}

		// Create input and output files
		Scanner input = new Scanner(sourceFile);
		PrintWriter output = new PrintWriter(targetFile);
	

		while (input.hasNext()) {
			int i=0;
	        if(i<=1){
			String s1 = input.nextLine();
			String s2 = s1+input.nextLine();
			output.println(s2);
			i++;
			}
			String s1=input.nextLine();
			String s2=s1;
			output.println(s2);
			
		    input.close();
		    output.close();
		}
		}
	}
