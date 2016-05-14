package com.Quinnox.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManagerPopulateDao 
{
	 Connection conn = MySqlConnect.getConnection();
     static Statement stmt = null;
     public ArrayList<Integer> managerPopulate()
     {ArrayList<Integer> managerList= new ArrayList<Integer>();
             try
             {
                     stmt = conn.createStatement();
             } catch (SQLException e1)
             {
                     // TODO Auto-generated catch block
                     e1.printStackTrace();
             }
             int userId=0;
             try
             {

                     String query="Select userId from loginCredentials where role='manager'";

                     ResultSet rs = stmt.executeQuery(query);

                     while (rs.next())
                     {
                    	 userId = rs.getInt("userId");
                             managerList.add(userId);

                     }
             }
             catch (SQLException e)
             {
                     // TODO Auto-generated catch block
                     e.printStackTrace();

             }
             return managerList ;
     }
     /*public ArrayList<String> productPopulate()
     {ArrayList<String> productList= new ArrayList<String>();
             try
             {
                     stmt = conn.createStatement();
             } catch (SQLException e1)
             {
                     // TODO Auto-generated catch block
                     e1.printStackTrace();
             }
             String productName="";
             try
             {

                     String query="Select productName from product";

                     ResultSet rs = stmt.executeQuery(query);

                     while (rs.next())
                     {
                             productName = rs.getString("productName");
                             productList.add(productName);

                     }
             }
             catch (SQLException e)
             {
                     // TODO Auto-generated catch block
                     e.printStackTrace();

             }
             return productList ;
     }*/

}
