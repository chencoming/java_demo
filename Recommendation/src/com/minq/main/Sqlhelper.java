package com.minq.main;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class Sqlhelper {
	
	 	private Connection connection= null;
	    private PreparedStatement preparedstatement = null;	    //大多数情况下用preparedStatement替代statement
	    private ResultSet resultset = null;
		
		/**
		 * 说明：得到数据库连接
		 * @return Connection
		 */
		public Connection getConnection(){
		    //连接数据库的参数
		    String dburl = "";
		    String dbusername = "";
		    String dbdriver = "";
		    String dbpassword = "";

			Properties sqlProperties = null;
			InputStream in = null;
			try {
				sqlProperties = new Properties();
				in =this. getClass().getResourceAsStream("mysql.properties");
				sqlProperties.load(in);
				
				dburl = sqlProperties.getProperty("dbUrl");
				dbusername = sqlProperties.getProperty("dbUserName");
				dbdriver = sqlProperties.getProperty("dbDriver");
				dbpassword = sqlProperties.getProperty("dbPassWord");
				
				Class.forName(dbdriver);
				connection = DriverManager.getConnection(dburl,dbusername,dbpassword);
				return connection;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				try {
					in.close();
					in = null;//垃圾回收站
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}	
			}
			return null;
		}
	
		/**
		 * 说明：执行数据库查询操作select
		 * @param String Sql语句
		 * @return ArrayList<Object>
		 * */
		public ArrayList<Object> executeQuery(String sql){
			//System.out.println(sql);
			ArrayList<Object> objList = new ArrayList<Object>();
			try {
				this.connection = getConnection();
				this.preparedstatement = this.connection.prepareStatement(sql);
				this.resultset = this.preparedstatement.executeQuery();
				
				ResultSetMetaData rsmd = resultset.getMetaData();
				int column = rsmd.getColumnCount();
				while (resultset.next()) {
					Object [] objects = new Object[column];
					for(int i =1; i<=column;i++){
						objects[i-1] = resultset.getObject(i);
					}
					objList.add(objects);
				}
				this.close();			
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return objList;
			
		}
		
		
		/**
		 * 说明：为Sql语句添加select参数
		 * @param parameters 需要select的参数
		 * @return String
		 * */
		public String fillSelectParams(String[] parameters){
			String sql = "select ";
			if(null != parameters ){
				for(int i = 0; i < parameters.length;i ++){
					sql += parameters[i] +",";
				}
				sql = sql.substring(0, sql.length()-1);
			}else{
				sql += "* ";
			}
			return sql;
		}
		
		/**
		 * 为Sql语句添加b表名参数
		 * 	@param sql sql语句
		 * @param tableName 表名
		 * @param havingAlias 是否有别名
		 * @param aliaString 别名
		 * @return String
		 * */
		public String fillTableParam (String sql, String tableName ,boolean havingAlias, String aliaString){
			
			if(havingAlias && null != aliaString){
				sql += " from ("  +tableName+ " ) "+ aliaString;
			}else{
				sql += " from " + tableName;
			}
			return sql;
		}
		
		/**
		 * 说明：为Sql语句添加where参数
		 * @param sql sql语句
		 * @param parameters 字符串数组
		 * @return String
		 * */
		public String fillWhereParams(String sql, String[] parameters){
			sql +=" where ";
			if(null != parameters ){
				for(int i = 0; i < parameters.length;i ++){
					sql += parameters[i] +" and ";
				}
				sql = sql.substring(0, sql.length()-4);
			}
			return sql;
		}
		
		/**
		 * 说明：为Sql语句添加group by、order by、结果数量限制等参数
		 * @param sql Sql语句
		 * @param paramGroupby group by参数
		 * @param paramOrderby order by参数
		 * @param maxLimit 限制返回结果最大数量
		 * @return String
		 * */
		public String fillOtherParams(String sql, String paramGroupby,String paramOrderby,int maxLimit){
			if(null != paramGroupby){
				sql += " group by " + paramGroupby;
			}
			if(null !=paramOrderby){
				sql +=" order by " +  paramOrderby;
			}
			if(maxLimit>0){
				sql +=" limit 0 , " + maxLimit;
			}
			
			return sql;
		}
		
		
		/**
		 * 说明：关闭资源
		 */
		public  void close(){
			if(null != resultset){
				try {
					resultset.close();	
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				resultset = null;
			}
			if(null != preparedstatement){
				try {
					preparedstatement.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				preparedstatement = null;
			}
			if(null != connection){
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				connection = null;
			}
		}
	
}
