package com.Quinnox.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddProductDao 
{	String query="";
	Connection conn = MySqlConnect.getConnection();
	Statement stmt = null;
	ResultSet resultSet;
	int pid=0;
	String supplier="";
	ResultSet supplierName;
	public void insertProduct(String pName, String description, int price,String[] checkBox)
	{
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("insert into product (productName, description,priceUnit) values('"+pName+"', '"+description+"',"+price+")");
			resultSet=stmt.executeQuery("select productId from product where productName='"+pName+"'");
			while(resultSet.next())
			{
				pid= resultSet.getInt("productId");
			}
			for (int i = 0; i < checkBox.length; i++) 
			{	supplierName=stmt.executeQuery("select username from loginCredentials where userId='"+checkBox[i]+"'");
			while(supplierName.next())
			{ 
				 supplier= supplierName.getString("userName");
				
			}
			stmt.executeUpdate("insert into supplyProduct values("+pid+",'"+checkBox[i]+"','"+supplier+"')");

			}
}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
