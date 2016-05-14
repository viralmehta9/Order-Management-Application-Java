package com.Quinnox.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.Quinnox.Controller.Details;
import com.Quinnox.Controller.TimeStampClass;

public class FinalPlaceOrderDao
{	
	Connection conn = MySqlConnect.getConnection();
	Statement stmt;
	Statement stmt1;
		
	
	
	public ArrayList finalPlaceOrderMethod()
	{	
	
		int lastOrderId=0;
		
		int tPrice=0;

	
		String dProductName;
		String dSupplierName;
		int dPrice;

		HashMap<Integer, Details> hmOfDetails=new HashMap<Integer,Details>();

		ArrayList<String> pname = new ArrayList<String>();
		ArrayList<String> sname = new ArrayList<String>();
		ArrayList qty=new ArrayList();
		ArrayList pr=new ArrayList();
		int totalPrice=0;
			ArrayList returnValue=new ArrayList();
		
		List finalDetails=new ArrayList();

		ResultSet rs11=null;
		ResultSet rs1=null;
		ResultSet ros=null;
		ResultSet rsd=null;

		String s2="select max(orderId) as MaxOrderId from orderDetails";
	
		
		
		String sos= "select productName,supplierName,quantity,price from temp";

		try 
		{

			stmt= conn.createStatement();
			rs1=stmt.executeQuery(s2);
			System.out.println(rs1);

			while(rs1.next())
			{
			//	lastOrderId=Integer.parseInt(rs1.getString("MaxOrderId"));
				String lastOrder=rs1.getString("MaxOrderId");
				
				if(lastOrder!=null)
				{
					lastOrderId=Integer.parseInt(rs1.getString("MaxOrderId"));
				}
				else
				{
					lastOrderId=0;
				}
				System.out.println("the  order id is " + lastOrderId);
				//++lastOrderId;

			}

			System.out.println("the no of rows are" + lastOrderId);

			if(lastOrderId==0)
			{
				lastOrderId=1;
			}
			else
			{
				lastOrderId=lastOrderId+1;
				System.out.println("the latest order id is " + lastOrderId);
			}

		}

		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//	finalDetails.add(lastOrderId);

		try {
			stmt=conn.createStatement();
			
			ros=stmt.executeQuery(sos);

			int i=0;  

			while(ros.next())
			{
				System.out.println("inside temp tabledddddddd...");

				dProductName = ros.getString("productName");

				pname.add(dProductName);

				System.out.println("of temop " +pname);

				dSupplierName= ros.getString("supplierName");
				sname.add(dSupplierName);

				System.out.println("of temop " +sname);

				dPrice=(ros.getInt("quantity")) *(ros.getInt("price"));

				System.out.println("of temop " +dPrice);

				qty.add(ros.getInt("quantity"));

				System.out.println("of temop " +qty);

				pr.add(ros.getInt("price"));

				System.out.println("of temop " +pr);


				totalPrice += (ros.getInt("quantity")) *(ros.getInt("price")); 		

				System.out.println("total amount is "+ totalPrice);


				hmOfDetails.put(i,new Details(lastOrderId,dProductName,dSupplierName,dPrice)); 

				i++;
			}

			System.out.println("total price is"+ totalPrice);

			//				ros.close();
		}
		catch (SQLException e2)
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		tPrice=totalPrice;
		System.out.println(tPrice);
		returnValue.add(hmOfDetails);
		returnValue.add(lastOrderId);
		returnValue.add(tPrice);
		returnValue.add(pname);
		returnValue.add(sname);
		returnValue.add(qty);
		returnValue.add(pr);
		

		return returnValue;
	}


	//		try
	//		{
	//		for (int i = 0; i < productName.size(); i++) 
	//		{
	//			stmt.executeUpdate("insert into orderItem values("+lastOrderId+ ",(select productId from product where productName='"+ productName.get(i)+"'),( select supplierId from supplyProduct where supplierName='"+supplierName.get(i)+"'),"+ qty.get(i)+ "," + price.get(i)+","+priceOfProduct.get(i)+")");
	//		}
	//	stmt.executeUpdate("insert into orderDetails values("+lastOrderId+","+uId+","+totalPrice+",'placed','')");

	//	stmt.executeUpdate("insert into auditTrail (orderId, userId, placedTimestamp) values("+lastOrderId+","+uId+",'"+TimeStampClass.getTimeStamp()+"')");

	//	} 
	//	catch (SQLException e) 
	//	{
	// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}

	//--------------- to delete from temp table	

	//try
	//{
	//	stmt= conn.createStatement();
	//	stmt.executeUpdate(deleteTemp);
	//} 
//	catch (SQLException e1)
	//{
		// TODO Auto-generated catch block
	//	e1.printStackTrace();
	//}
//}

	public void onSubmit(int lastOrderId, int tPrice, int userId,ArrayList pname,ArrayList sname,ArrayList qty,ArrayList pr,String role)
	{
		try {
			
				System.out.println("The role is "+role);
			
		
			for (int i = 0; i < pname.size(); i++) 
				{	
					int qty1=(Integer) qty.get(i);
					int pr1=(Integer)pr.get(i);
				
					int priceOfProduct=qty1*pr1;
			
					conn.setAutoCommit(false);
					 System.out.println("inserting in orderitem " + i+ "orderi is " + lastOrderId);
			//		 int priceOfProduct=Integer.parseInt((String) qty.get(i))*Integer.parseInt((String) pr.get(i));
					stmt.executeUpdate("insert into orderItem values("+lastOrderId+ ",(select productId from product where productName='"+ pname.get(i)+"'),(select userId from loginCredentials where userName='"+sname.get(i)+"'),"+ qty.get(i)+ "," + pr.get(i)+","+priceOfProduct+")");
				}
				
			
			if(role.equals("manager"))
			{
				stmt.executeUpdate("insert into orderDetails values("+lastOrderId+","+userId+","+tPrice+",'Approved','')");
				
		    	stmt.executeUpdate("insert into auditTrail (orderId, userId, placedTimestamp,managerId,managerStatus,managerTimestamp) values("+lastOrderId+","+userId+",'"+TimeStampClass.getTimeStamp()+"',"+userId+",'Approved','"+TimeStampClass.getTimeStamp()+"')");
		    	stmt.executeUpdate("delete from temp");
		    	conn.commit();
				System.out.println("Transaction commit...");
				System.out.println("finally placed");
			}
			else if(role.equals("employee"))
			{	System.out.println("Inside Employee");

				stmt.executeUpdate("insert into orderDetails values("+lastOrderId+","+userId+","+tPrice+",'Placed','')");
				
		    	stmt.executeUpdate("insert into auditTrail (orderId, userId, placedTimestamp) values("+lastOrderId+","+userId+",'"+TimeStampClass.getTimeStamp()+"')");
		    	
		    	stmt.executeUpdate("delete from temp");
		    	conn.commit();
				System.out.println("Transaction commit...");
				System.out.println("finally placed");
			}
			
				
			} 
				
			catch (SQLException e)
				{
							if (conn != null) 
							{
								        try 
								        {
											conn.rollback();
											 System.out.println("Connection rollback...");
											 System.out.println("Nottt placed");
										} 
								        catch (SQLException e1) 
										{
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
				      
							}
			 
				}
	
					try {
						stmt=conn.createStatement();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("deleteng the temp table");
					
					try {
						System.out.println("just deleting it........");
						stmt1=conn.createStatement();
						stmt1.executeUpdate("delete from temp");
						System.out.println("finished......");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("aa");
						e.printStackTrace();
					}
					System.out.println("temp cleared");
					
				/*try {
					
					stmt=conn.createStatement();
					
					System.out.println(" deleting temp ");	
				
				int r =0;
				r=stmt.executeUpdate("delete from temp");
					
					System.out.println(" temp table cleared "+ r);
				  
				} 
			
				 catch (SQLException e1)
				 {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				 }	 */
	}
}