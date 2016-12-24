package database;

import java.io.File;
import java.io.PrintWriter;

public class FileOperation {
	public static void main(String[] args) throws Exception{
		File file = new File("C:/Documents and Settings/Administrator/×ÀÃæ/scores.txt");
		if(file.exists()){
			System.out.println("File already exists");
		}
		
		PrintWriter writer = new PrintWriter(file);
		
		writer.print("chen jin ming ");
		writer.println(90);
		writer.print("chen xiao qing ");
		writer.println(80);
		
		writer.close();
		
	}
}
