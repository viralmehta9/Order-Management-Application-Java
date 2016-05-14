package com.Quinnox.Model;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddUserDao
{
	Connection conn = MySqlConnect.getConnection();
	Statement stmt=null;

	public void insertUser(String username,String password, String role,int managerId,int isActive)
	{
		try 
		{
			String query="insert into loginCredentials (username, password, role,managerId,isActive) values('"+username+"', '"+password+"','"+role+"',"+managerId+",1)";

			stmt=conn.createStatement();
			int result=stmt.executeUpdate(query); 
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
}

