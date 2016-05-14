package com.Quinnox.Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.Quinnox.Controller.TimeStampClass;

public class ManagerApprovalHandlerDao 
{
	Connection conn = MySqlConnect.getConnection();
	Statement stmt;
	
	String query="";
	String query1="";
	boolean queryResult; 
	
public void approveMethod(int orderId,int userId)
{
	try 
	{
		stmt = conn.createStatement();
		query = "update orderDetails set status='approved' where orderId="+orderId;
		query1 = "update auditTrail set managerId="+userId+",managerStatus='approved',managerTimestamp='"+TimeStampClass.getTimeStamp()+"' where orderId="+orderId;
		queryResult = stmt.execute(query);
		queryResult = stmt.execute(query1);
	}
	catch (SQLException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void rejectMethod(int orderId,int userId,String comments )
{
	try {
		stmt = conn.createStatement();
		query = "update orderDetails set status='rejected', comments='"+comments+"' where orderId="+orderId;
		query1 = "update auditTrail set managerId="+userId+",managerStatus='rejected',managerTimestamp='"+TimeStampClass.getTimeStamp()+"' where orderId="+orderId;
		queryResult = stmt.execute(query);
		 queryResult = stmt.execute(query1);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void acceptMethod(int orderId,int userId)
{
	try {
		stmt = conn.createStatement();
		query = "update orderDetails set status='accepted' where orderId="+orderId;
		query1= "update auditTrail set supplierId="+userId+",supplierStatus='accepted',supplierTimestamp='"+TimeStampClass.getTimeStamp()+"' where orderId="+orderId;
		queryResult = stmt.execute(query);
		 queryResult = stmt.execute(query1);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void denyMethod(int orderId,int userId,String comments)
{
	try {
		stmt = conn.createStatement();
		query = "update orderDetails set status='denied', comments='"+comments+"' where orderId="+orderId;
		query1= "update auditTrail set supplierId="+userId+",supplierStatus='denied',supplierTimestamp='"+TimeStampClass.getTimeStamp()+"' where orderId="+orderId;
		queryResult = stmt.execute(query);
		 queryResult = stmt.execute(query1);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void passMethod(int orderId,int userId)
{
	try {
		stmt = conn.createStatement();
		query = "update orderDetails set status='passed' where orderId="+orderId;
		query1 = "update auditTrail set qceId="+userId+",qceStatus='passed',qceTimestamp='"+TimeStampClass.getTimeStamp()+"' where orderId="+orderId;
		queryResult = stmt.execute(query);
		 queryResult = stmt.execute(query1);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void failMethod(int orderId,int userId, String comments)
{
	try {
		stmt = conn.createStatement();
		query = "update orderDetails set status='failed', comments='"+comments+"' where orderId="+orderId;
		query1 = "update auditTrail set qceId="+userId+",qceStatus='failed',qceTimestamp='"+TimeStampClass.getTimeStamp()+"' where orderId="+orderId;
		queryResult = stmt.execute(query);
		 queryResult = stmt.execute(query1);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
