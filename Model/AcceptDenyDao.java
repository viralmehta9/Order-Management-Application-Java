package com.Quinnox.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import com.Quinnox.Controller.ApproveRejectClass;

public class AcceptDenyDao
{
	public HashMap<Integer,ApproveRejectClass> acceptDenyMethod(int supplierId)
	{
		HashMap<Integer,ApproveRejectClass> hashMap = new HashMap<Integer,ApproveRejectClass>();
		try 
		{
			Connection conn = MySqlConnect.getConnection();
			Statement stmt =  conn.createStatement();
			ResultSet rs;
			rs= stmt.executeQuery("select orderId,userId, costOfOrder from orderDetails where status='approved' AND orderId in (select orderId from orderItem where suppierId="+supplierId+") order by orderId");

			if(rs != null)
			{
				
				int i=0;
				while (rs.next())
				{
					int orderid=0;
					//int productid=0;
					int userid=0;
				//	int quantity=0;
				//	int priceunit=0;
					int totalprice=0;
					//String status="";


					orderid = rs.getInt("orderid");
					userid = rs.getInt("userid");
					totalprice = rs.getInt("costOfOrder");

					ApproveRejectClass record = new ApproveRejectClass(orderid,userid,totalprice);

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