package com.Quinnox.Model;
import java.sql.DriverManager; 
import java.sql.SQLException; 
import java.sql.Connection; 

public class MySqlConnect 
{
		public static  Connection getConnection() 
		{ 
			Connection conn = null; 

			try
			{ 
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance(); 
				conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=OrderManagement;integratedSecurity=true"); 
			} 
			catch (InstantiationException e) { 

				// TODO Auto-generated catch block 
				e.printStackTrace();
			}
			catch (IllegalAccessException e) 
			{
				// TODO Auto-generated catch block 
				e.printStackTrace();

			} 
			catch (ClassNotFoundException e) 
			{
				// TODO Auto-generated catch block 
				e.printStackTrace();
			} 
			catch (SQLException e) 
			{ 
				// TODO Auto-generated catch block 
				e.printStackTrace();
			}
			return conn; 
		}

		public static void main(String[] args) 
		{
			System.out.println("Microsoft SQL Server 2008 r2 Connect Example."); 
			try 
			{
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance(); 
				Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=OrderManagement;integratedSecurity=true"); 
				System.out.println("Connected to the database"); 
				conn.close();

				System.out.println("Disconnected from database"); 
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}

