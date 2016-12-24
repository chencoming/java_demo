package database;

import java.io.File;
import java.util.Scanner;

public class FileOperation2 {
	public static void main(String[] args) throws Exception{
		File file = new File("C:/Documents and Settings/Administrator/桌面/test.txt");
		
		Scanner readScanner = new Scanner(file);
		
		String message;
		String user="",product = "",scores = "";

		int token = 0; //用于对付每行数据中存在空格的情况
		while(readScanner.hasNext()){

			message = readScanner.next();
			int length =  message.length();
			for(int i =0;i <length;i++){
				if(message.charAt(i) !=':'){
					if(token == 0){
						user += message.charAt(i);
					}
					else if(token == 1){
						product += message.charAt(i);
					}
					else if(token == 4){
						scores += message.charAt(i);
						
					}
				}
				if(message.charAt(i) ==':'&&message.charAt(i + 1) ==':'){
					token ++;
				}
			}
			//System.out.println(user +" "+ product +" "+ scores);
			
			if(token == 4){//执行下一行，此时如果token为4，表示一条信息已经录入完整
				//System.out.println(user +" "+ product +" "+ scores);
				TransType(user,product,scores);
				user = "";
				product = "";
				scores = "";
				token = 0;
				System.out.println("O---V----E---R");
				//此处可执行转换为整型和对数据库的操作
			}

		}
		
		readScanner.close();
	}

	private static  void TransType(String user,String product,String scores) {
		// TODO Auto-generated method stub
		int userid,productid;
		float Scores;
		userid = Integer.valueOf(user);
		productid = Integer.valueOf(product);
		Scores = Float.valueOf(scores);
		System.out.println(userid +" "+ productid +" "+ Scores);

	}

}
