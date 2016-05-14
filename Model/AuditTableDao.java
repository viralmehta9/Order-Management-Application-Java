package com.Quinnox.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import com.Quinnox.Controller.TimeStampClass;

public class AuditTableDao 
{
	public HashMap<Integer, TimeStampClass> audit(String role, int orderId)
	{
	ResultSet rs;
	//int orderId =0;
	int userId = 0 ;
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
	 HashMap<Integer, TimeStampClass> hashMap = new HashMap<Integer, TimeStampClass>();
	try 
    {
    	Connection conn = MySqlConnect.getConnection();
		Statement stmt =  conn.createStatement();
        /*if(role.equals("admin"))
        {
        	rs=stmt.executeQuery("Select * from auditTrail order by userId,orderId");
        }
        else if(role.equals("supplier"))
        {
        	rs=stmt.executeQuery("Select * from auditTrail where supplierId="+userId+" order by userId,orderId");
        }
        else if(role.equals("qce"))
        {
        	rs=stmt.executeQuery("Select * from auditTrail where qceId="+userId+" order by userId,orderId");
        }
        else if(role.equals("manager"))
        {
        	String query="Select * from auditTrail where ";
        	ResultSet rs1 = stmt.executeQuery("Select userId from loginCredentials where managerId="+userId);
        	if(rs1!=null)
        	{
        		int empUserId;
        		while(rs1.next())
        		{
        			empUserId =  rs1.getInt("userId");
        			query += "userId="+empUserId+" or ";
        		}
        		query+=" userId="+userId+" AND orderId="+orderId+" order by userId,orderId";
        		System.out.println("My qry="+query);
        		rs=stmt.executeQuery(query);
        	}
        	else
        	{
        		rs=stmt.executeQuery("Select * from auditTrail where userId="+userId+" AND orderId="+orderId+" order by userId,orderId");
        	}
        }
        else
        {
        	rs = stmt.executeQuery("Select * from auditTrail where userId="+userId+" AND orderId="+orderId+" order by userId,orderId");
        }
		*/
		
		rs = stmt.executeQuery("Select * from auditTrail where orderId="+orderId);
		if(rs != null)
		{
			
			 int i=0;
			 while (rs.next())
			 {
				 orderId = rs.getInt("orderId");
				 userId =  rs.getInt("userId");
				 placedTimestamp =  rs.getString("placedTimestamp");
				 managerId =  rs.getInt("managerId");
				 managerStatus =  rs.getString("managerStatus");
				 managerTimestamp =  rs.getString("managerTimestamp");
				 supplierId =  rs.getInt("supplierId");
				 supplierStatus =  rs.getString("supplierStatus");
				 supplierTimestamp =  rs.getString("supplierTimestamp");
				 qceId =  rs.getInt("qceId") ;
				 qceStatus =  rs.getString("QCEStatus");
				 qceTimestamp =  rs.getString("QCETimestamp");
				
				 TimeStampClass record = new  TimeStampClass(orderId,userId,placedTimestamp,
							managerId, managerStatus,managerTimestamp,
							supplierId, supplierStatus,supplierTimestamp,
							qceId,qceStatus, qceTimestamp);
		         
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