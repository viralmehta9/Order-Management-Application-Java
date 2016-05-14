package com.Quinnox.Model;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.Quinnox.Controller.TimeStampClass;

public class OrderIdFetcherDao 
{
	public ArrayList getOrderId(String role, int userId)
	{
		ResultSet rs;
		
		int orderId=0;
		String placedTimestamp = "";
		int managerId = 0 ;
		String managerStatus = "";
		String managerTimestamp = "";
		int supplierId = 0 ;
		String supplierStatus = "";
		String supplierTimestamp = "";
		int qceId = 0 ;
		String qceStatus = "";
		String qceTimestamp ="";
		 //HashMap<Integer, TimeStampClass> hashMap = new HashMap<Integer, TimeStampClass>();
		
		ArrayList arrayList = new ArrayList();
		
	try 
    {
    	Connection conn = MySqlConnect.getConnection();
		Statement stmt =  conn.createStatement();
//        if(role.equals("admin"))
//        {
//        	rs=stmt.executeQuery("Select * from auditTrail order by userId,orderId");
//        }
        if(role.equals("supplier"))
        {
        	rs=stmt.executeQuery("Select * from auditTrail where supplierId="+userId+" order by userId,orderId");
        }
        else if(role.equals("qce"))
        {
        	rs=stmt.executeQuery("Select * from auditTrail where qceId="+userId+" order by userId,orderId");
        }
        else if(role.equals("manager"))
        {
        	String query="Select orderId from auditTrail where ";
        	ResultSet rs1 = stmt.executeQuery("Select userId from loginCredentials where managerId="+userId);
        	if(rs1!=null)
        	{
        		int empUserId;
        		while(rs1.next())
        		{
        			empUserId =  rs1.getInt("userId");
        			query += "userId="+empUserId+" or ";
        		}
        		query+=" userId="+userId+" order by orderId,userId";
        		System.out.println("My qry="+query);
        		rs=stmt.executeQuery(query);
        	}
        	else
        	{
        		rs=stmt.executeQuery("Select * from auditTrail where userId="+userId+" order by orderId,userId");
        	}
        }
        else
        {
        	rs = stmt.executeQuery("Select * from auditTrail where userId="+userId+" order by orderId,userId");
        }
		
        
        if(rs != null)
		{
			
			 int i=0;
			 while (rs.next())
			 {
				 orderId = rs.getInt("orderId");
				 userId = 0;
				 placedTimestamp ="";
				 managerId =0;
				 managerStatus ="";
				 managerTimestamp ="";
				 supplierId =0;
				 supplierStatus ="";
				 supplierTimestamp ="";
				 qceId =0;
				 qceStatus ="";
				 qceTimestamp ="";
				
				 TimeStampClass record = new  TimeStampClass(orderId,userId,placedTimestamp,
							managerId, managerStatus,managerTimestamp,
							supplierId, supplierStatus,supplierTimestamp,
							qceId,qceStatus, qceTimestamp);
		         
		         //hashMap.put(i,record);
				 arrayList.add(record);
		         i++;
			  }
			 
			conn.close(); 

		}
	
    }
	catch (Exception e) 
    {
		e.printStackTrace();
    }
	return arrayList;
	
}
}