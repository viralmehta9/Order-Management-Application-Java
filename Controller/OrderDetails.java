package com.Quinnox.Controller;

import java.io.IOException;

import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Quinnox.Model.OrderDetailsDao;

/**
 * Servlet implementation class OrderDetails
 */
public class OrderDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			RequestDispatcher rd;


			String orderId=request.getParameter("orderId");
			String role=request.getParameter("role");

			OrderDetailsDao orderDetailsDao= new OrderDetailsDao();
			HashMap<Integer,ApproveRejectClass> hashMap = orderDetailsDao.orderDetailMethod(orderId);
			request.setAttribute("hashMap", hashMap);
			request.setAttribute("orderId", orderId);
			request.setAttribute("role", role);

			rd = request.getRequestDispatcher("SingleOrderDetailsManager.jsp");
			rd.forward(request, response);
		}
	}
}
