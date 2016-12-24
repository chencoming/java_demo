package minq;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import jade.core.Agent;


@SuppressWarnings("serial")
public class bookAgent extends Agent{
	
	public PreparedStatement statement;
	public  Connection connection;
	public ResultSet resultset;
	
	protected void setup(){
		this.addBehaviour(new bookbehaviour(this));
		Bookframe bf = new Bookframe();
		bf.setframe(bf, this);
	}
	
	
	public void addbook(String bookname, String numofbook,String price){
		//向目录中添加书本
		int booknum = Integer.parseInt(numofbook);
		boolean b =false;
		float bookprice = Float.parseFloat(price);
		try {
			connection = this.condb();
			statement = connection.prepareStatement("insert into books (bookname ,booknum,bookprice)  values (?,?,?)");
			
			statement.setNString(1,bookname);
			statement.setInt(2,booknum);
			statement.setFloat(3,bookprice);
			
			 b = statement.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		if(b){
			JOptionPane.showMessageDialog(null, "添加成功！");
		}
	}
	
	
	public int querybook(String bookname){
		//向目录中查询书本
		int nums = 0 ;
		try {
			connection = this.condb();
			statement = connection.prepareStatement("select booknum from books where bookname='" + bookname +"'");
			resultset = statement.executeQuery();	
			
			while(resultset.next()){
				nums= resultset.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		
		return nums;
	}
	
	public float querybookprice(String bookname){
		//向目录中查询书本
		float price = 0 ;
		try {
			connection = this.condb();
			statement = connection.prepareStatement("select bookprice from books where bookname='" + bookname +"'");
			resultset = statement.executeQuery();	
			
			while(resultset.next()){
				price= resultset.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		
		return price;
	}
	
	public boolean buybook(String bookname){
		//购买书本
		boolean b = false;
		try {
			int nums = querybook(bookname);
			if(nums!=0){
				nums--;
				statement = connection.prepareStatement("update books set booknum = " + nums +" where bookname='" + bookname +"'");
				b = statement.execute();	
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		return b;
			
	}
	
	private  Connection condb()
		throws Exception{
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("Driver loaded");			
			connection = DriverManager.getConnection("jdbc:mysql://localhost/bookstore","root","ming");			
			return connection;
	}
	
	private void close(){
		
		try {
			this.resultset.close();
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
