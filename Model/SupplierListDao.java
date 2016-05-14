package com.Quinnox.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.Quinnox.Controller.SupplierClass;

public class SupplierListDao
{
	public HashMap<Integer, SupplierClass> supplier()
	{
		Connection conn = MySqlConnect.getConnection();
		Statement stmt = null;
		HashMap<Integer, SupplierClass> hashMap = new HashMap<Integer,SupplierClass>();

		try {
			stmt=conn.createStatement();

			String s="Select userId, username from loginCredentials where role='Supplier'";
			ResultSet rs=stmt.executeQuery(s);

			int i=0;
			while (rs.next())
			{
				int userid = Integer.parseInt(rs.getString("userId"));
				String suppliername = rs.getString("username");
				SupplierClass record = new SupplierClass(userid,suppliername);
				hashMap.put(i,record);
				i++;
			}

		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashMap;
	}

}
