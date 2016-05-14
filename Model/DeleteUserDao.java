package com.Quinnox.Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteUserDao 
{
	Connection conn = MySqlConnect.getConnection();
	static Statement stmt = null;

	public void deleteUser(String userId1)
	{
		try 
		{
			stmt = conn.createStatement();
		} 
		catch (SQLException e1) 
		{
			// 	TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try 
		{
			int userId=Integer.parseInt(userId1);
			stmt.execute("update loginCredentials set isActive = 0 where userId="+userId);
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void replaceManager(int oldManager, int newManager)
	{
		try 
		{
			stmt = conn.createStatement();

			String query="update loginCredentials set isActive=0 where userId="+oldManager;
			String query1="update loginCredentials set managerId="+newManager+" where managerId="+oldManager;

			stmt.execute(query);
			stmt.execute(query1);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
