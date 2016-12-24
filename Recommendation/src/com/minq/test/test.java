package com.minq.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.minq.main.Sqlhelper;

//import java.util.Random;

public class test {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Sqlhelper sqlhelper = new Sqlhelper();
		Connection connection = sqlhelper.getConnection();
		
		 PreparedStatement preparedstatement  = null;
		
		 for(int i=0 ;i<9990;i++){
			// insert into pathrecord values (11,54,'广东九博电子科技有限公司',
			 //'惠州市技师学院办公电器设备采购公开招标公告.招标编号：惠采招[2011]107号',
			 //'http://huizhou.gdgpo.com/gdgpmsPortal/jsp/article_content.jsp?articleId=402870833224a50101322989910c0f7a',
			 //'2013-03-02 16:52:50');
			 String sql = "insert into pathrecord values (" +(1010+i) +",1474,'广东亿迅科技有限公司','东莞市谢岗医院医疗设备采购项目公开招标公告','http://dongguan.gdgpo.com/gdgpmsPortal/jsp/article_content.jsp?articleId=52ccc11f2d26ef56012d2b1a36c814c1','2013-03-12 16:52:50' ) ";
			 try {
				preparedstatement =connection.prepareStatement(sql);
				preparedstatement.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		 }
		
		
		
		
		
		/*String urlString = "http://www.yxst.gov.cn/kjtz/kjtz/1345085779939233333.html";
		
		System.out.println(HashAlgorithms.FNVHash1(urlString1));
		System.out.println(HashAlgorithms.FNVHash1(urlString2));*/

		/*int prime =17;
		
		String urlString = "http://www.yxst.gov.cn/kjtz/kjtz/1345085779939233333.html";
		String urlString2 = "http://www.yxst.gov.cn/kjtz/kjtz/13450857703920033.html3";
		
		
		System.out.println(HashAlgorithms.additiveHash(urlString, prime));
		System.out.println(HashAlgorithms.additiveHash(urlString2, prime));
		
		System.out.println(HashAlgorithms.rotatingHash(urlString, prime));
		
		System.out.println(HashAlgorithms.FNVHash1(urlString));
		
		System.out.println(HashAlgorithms.RSHash(urlString));*/
		
		
		
		/*int [] id = {8911,19921,22742,23117,25557,29236,31109,
						39861,40391,41809,41842 ,42041,45866,46004 ,48515};
		for(int i =0;i<id.length;i++){
			System.out.println(id[i] + "\t" + id[i]%16);
		}
		Random random = new Random();
		int num = random.nextInt(16);
		int num2 = random.nextInt(16);
		System.out.print(num +  " "  + num2);*/
	}

}
