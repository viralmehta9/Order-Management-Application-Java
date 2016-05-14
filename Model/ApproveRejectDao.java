package com.Quinnox.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import com.Quinnox.Controller.ApproveRejectClass;

public class ApproveRejectDao 
{
	public HashMap<Integer,ApproveRejectClass> approveRejectMethod(int managerId)
	{
		HashMap<Integer,ApproveRejectClass> hashMap = new HashMap<Integer,ApproveRejectClass>();
		try 
		{
			Connection conn = MySqlConnect.getConnection();
			Statement stmt =  conn.createStatement();
			ResultSet rs;
			ResultSet rs1;
			
						
			rs1=stmt.executeQuery("select userId from loginCredentials where managerId="+managerId);
			
			if(rs1 != null)
			{
				String query="select orderId,userId, costOfOrder from orderDetails where status='placed' AND ";
				while(rs1.next())
				{
					int empUserId = rs1.getInt("userId");
					query+="userId="+empUserId+" OR ";
				}
				query+=" userId="+managerId;
				query+=" order by orderId,userId";
			
				rs= stmt.executeQuery(query);
				
				
				if(rs != null)
				{
					int i=0;
					while (rs.next())
					{
						int orderid=0;
						
						int userid=0;
						
						int totalprice=0;
						
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
			
		}
		catch (Exception e) 
        {
          e.printStackTrace();
        }
		return hashMap;
	}
}