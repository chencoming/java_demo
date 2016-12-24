package database;

import java.sql.*;

public class Simplejdbc {
	public static void main(String[] args) 
	throws SQLException,ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/datamining","root","ming");
		System.out.println("Database connection");
		
		/*Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("select title   from course ");
		
		while(resultSet.next()){
			System.out.println(resultSet.getString(1));			
		}*/
		
		connection.close();
	}
}
