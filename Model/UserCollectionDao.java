package com.Quinnox.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.Quinnox.Controller.UserLoginClass;

public class UserCollectionDao 
{
	Connection conn = MySqlConnect.getConnection();
	static Statement stmt = null;

	public HashMap<Integer, UserLoginClass> userCollection(String roleSelected) 
	{ 
		HashMap<Integer, UserLoginClass> hashMap = new HashMap<Integer,UserLoginClass>();
		try
		{
			stmt = conn.createStatement();
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int userId=0;
		String username="";
		String password="";
		String role="";
		try 
		{
			String query="Select * from loginCredentials where isActive=1 AND role='"+roleSelected+"'";

			ResultSet rs = stmt.executeQuery(query);
			int i=0;
			while (rs.next()) 
			{

				userId = Integer.parseInt(rs.getString("userid"));
				username = rs.getString("username");
				password = "";
				role = rs.getString("role");
				UserLoginClass record = new UserLoginClass(userId,username,password, role);
				hashMap.put(i,record);
				i++;
			}

		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return hashMap ;
	}

	public HashMap<Integer, UserLoginClass> managerCollection(int managerSelected) 
	{ 
		HashMap<Integer, UserLoginClass> hashMap = new HashMap<Integer,UserLoginClass>();
		try
		{
			stmt = conn.createStatement();
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int userId=0;
		String username="";
		String password="";
		String role="";
		try 
		{
			String query="Select * from loginCredentials where isActive=1 AND role='manager' AND userId!="+managerSelected;

			ResultSet rs = stmt.executeQuery(query);
			int i=0;
			while (rs.next()) 
			{

				userId = Integer.parseInt(rs.getString("userid"));
				username = rs.getString("username");
				password = "";
				role = "manager";
				UserLoginClass record = new UserLoginClass(userId,username,password, role);
				hashMap.put(i,record);
				i++;
			}
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashMap ;
	}
}
