package com.Quinnox.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import com.Quinnox.Controller.ApproveRejectClass;

public class OrderDetailsDao
{
	public HashMap<Integer,ApproveRejectClass> orderDetailMethod(String orderId)
	{
		HashMap<Integer,ApproveRejectClass> hashMap = new HashMap<Integer,ApproveRejectClass>();
	
	try 
	{
		Connection conn = MySqlConnect.getConnection();
		Statement stmt =  conn.createStatement();
		ResultSet rs;

		rs= stmt.executeQuery("select * from orderItem where orderId="+ orderId +" order by productId");

		if(rs != null)
		{

			int i=0;
			while (rs.next())
			{
				int orderid=0;
				int productid=0;
				int userid=0;
				int quantity=0;
				int priceunit=0;
				int totalprice=0;
				String status="";


				productid = rs.getInt("productId");
				quantity = rs.getInt("quantity");
				priceunit = rs.getInt("price");
				totalprice = rs.getInt("totalPrice");


				ApproveRejectClass record = new ApproveRejectClass(orderid,productid,userid,
						quantity,priceunit,totalprice,status);

				hashMap.put(i,record);
				i++;
			}

			conn.close(); 
		}
	}
	catch (Exception e) 
    {
      e.printStackTrace();
    }
	return hashMap;
}
}