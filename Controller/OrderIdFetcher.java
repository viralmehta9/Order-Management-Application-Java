package com.Quinnox.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Quinnox.Model.MySqlConnect;
import com.Quinnox.Model.OrderIdFetcherDao;

/**
 * Servlet implementation class OrderIdFetcher
 */
public class OrderIdFetcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderIdFetcher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(false);
		Boolean logged=null;
		try 
		{
			 logged=(Boolean)session.getAttribute("logged");
			 
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		
		
		if(logged==null || !logged)
		{
			RequestDispatcher rd=request.getRequestDispatcher("LoginPage.jsp");
			rd.forward(request, response);
		}
		else
		{
			int userId = (Integer)session.getAttribute("userId");
			String role = (String)session.getAttribute("role");
			
			OrderIdFetcherDao obj = new OrderIdFetcherDao();
			ArrayList orderListArray = obj.getOrderId(role, userId);
			
			request.setAttribute("orderId", orderListArray);
			RequestDispatcher rd=request.getRequestDispatcher("OrderIdFetcher.jsp");
			rd.forward(request, response);
		}

}
}