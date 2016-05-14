package com.Quinnox.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.Quinnox.Controller.Item;

public class PlaceOrderDao 
{
	Connection conn=MySqlConnect.getConnection();
	Statement stmt = null;
	ResultSet resultSet = null;

	public ArrayList<Integer> retriveProductId()
	{
		ArrayList<Integer> productId=new ArrayList<Integer>(); 
		String query="select productId from product";
		try 
		{
			stmt=conn.createStatement();
			resultSet = stmt.executeQuery(query);

			while(resultSet.next())
			{
				productId.add(resultSet.getInt("productId"));
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productId;
	}

	public ArrayList<String> retriveProductName()
	{
		ArrayList<String> productName=new ArrayList<String>(); 
		String query="select productName from product";
		try 
		{
			stmt=conn.createStatement();
			resultSet = stmt.executeQuery(query);	
			while(resultSet.next())
			{
				productName.add(resultSet.getString("productName"));						
			}
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productName;
	}

	public HashMap<Integer, Item> insertIntoTempCart(String pname,String sname, int quantity1,int price)
	{
		HashMap<Integer, Item> hashMap=new HashMap<Integer,Item>();
		int productId;
		String productName;
		int  quantity;
		int unitPrice;
		String supplierName;
		String query1="insert into temp (productId,productName,supplierName,quantity,price) values((select productId from product where productName='"+pname+"'),'"+pname+"','"+sname+"',"+quantity1+","+price+")";
		//String query1="insert into temp values('"+pname+"','"+sname+"',"+quantity1+","+price+")";
		try {
			stmt=conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.executeUpdate(query1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		String query2="select * from temp";
		try {
			stmt=conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			resultSet = stmt.executeQuery(query2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int i=0;  

		try {
			while (resultSet.next())

			{  
				productId=resultSet.getInt("productId");

				productName=resultSet.getString("productName");    
				supplierName=resultSet.getString("supplierName");

				quantity=resultSet.getInt("quantity") ;

				unitPrice=resultSet.getInt("price"); 

				hashMap.put(i,new Item(productId,productName,supplierName, quantity,unitPrice)); 

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

	public HashMap<Integer, Item> deleteCart(String[] checkBox)
	{
		HashMap<Integer, Item> hashMap=new HashMap<Integer,Item>();
		int productId;
		String productName;
		int  quantity;
		int unitPrice;
		String supplierName;

		try 
		{
			stmt=conn.createStatement();
			for (int i = 0; i < checkBox.length; i++) 
			{
				boolean queryResult =stmt.execute("delete from temp  where productId='"+checkBox[i]+"'");
			}
			String query="select * from temp";
			stmt=conn.createStatement();
			resultSet = stmt.executeQuery(query);
			int i=0;
			while (resultSet.next())
			{  
				productId=resultSet.getInt("productId");
				productName=resultSet.getString("productName");
				supplierName=resultSet.getString("supplierName");
				quantity=resultSet.getInt("quantity") ;
				unitPrice=resultSet.getInt("price"); 
				hashMap.put(i,new Item(productId,productName,supplierName, quantity,unitPrice)); 
				i++;
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return hashMap;
	}

	public ArrayList<String> populateSupplier(String productName)
	{
		ArrayList<Integer> productId=new ArrayList<Integer>();
		ArrayList<String> supplierName=new ArrayList<String>();
		String query1 = "select productId from product where productName='"+productName+"'";

		try {
			stmt=conn.createStatement();
			resultSet = stmt.executeQuery(query1);

			while(resultSet.next())
			{
				productId.add(resultSet.getInt(1));


			}
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String query2 = "select supplierName from supplyProduct where productId='"+ productId.get(0)+"'";


		try {
			
			stmt=conn.createStatement();
			resultSet = stmt.executeQuery(query2);


			while(resultSet.next())
			{
				supplierName.add(resultSet.getString("supplierName"));

			}

		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return supplierName;
	}
	public HashMap retrieveTemp()
	{
		HashMap<Integer, Item> hashMapTemp=new HashMap<Integer,Item>();
		int pId;
		String pName;
		int  quantity;
		int unitPrice;
		String sName;

		String query="select * from temp";
		try {
			stmt=conn.createStatement();
			resultSet = stmt.executeQuery(query);
			int i=0;
			while (resultSet.next())
			{  
				pId=resultSet.getInt("productId");
				pName=resultSet.getString("productName");
				sName=resultSet.getString("supplierName");
				quantity=resultSet.getInt("quantity") ;
				unitPrice=resultSet.getInt("price"); 
				hashMapTemp.put(i,new Item(pId,pName,sName, quantity,unitPrice)); 
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashMapTemp;
	}
}