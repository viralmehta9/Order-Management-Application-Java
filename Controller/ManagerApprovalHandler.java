package com.Quinnox.Controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Quinnox.Model.ManagerApprovalHandlerDao;

/**
 * Servlet implementation class ManagerApprovalHandler
 */
public class ManagerApprovalHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManagerApprovalHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

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
			RequestDispatcher rd=null;

			String buttonClicked = request.getParameter("submit");
			int orderId= (Integer.parseInt(request.getParameter("orderId")));

			int userId = (Integer)session.getAttribute("userId");	

			ManagerApprovalHandlerDao managerApprovalHandlerDao= new ManagerApprovalHandlerDao();

			if(buttonClicked.equals("Approve"))
			{

				managerApprovalHandlerDao.approveMethod(orderId,userId);
				rd = request.getRequestDispatcher("ApproveReject");
			}
			else if(buttonClicked.equals("Reject"))
			{
				String comments = (String)request.getParameter("comments");
				managerApprovalHandlerDao.rejectMethod(orderId,userId,comments);

				rd = request.getRequestDispatcher("ApproveReject");
			}
			else if(buttonClicked.equals("Accept"))
			{	managerApprovalHandlerDao.acceptMethod(orderId,userId);
			rd = request.getRequestDispatcher("AcceptDeny");
			}
			else if(buttonClicked.equals("Deny"))
			{
				String comments = (String)request.getParameter("comments");
				managerApprovalHandlerDao.denyMethod(orderId,userId,comments);
				rd = request.getRequestDispatcher("AcceptDeny");
			}
			else if(buttonClicked.equals("Pass"))
			{	managerApprovalHandlerDao.passMethod(orderId,userId);
			rd = request.getRequestDispatcher("PassFail");
			}
			else if(buttonClicked.equals("Fail"))
			{
				String comments = (String)request.getParameter("comments");
				managerApprovalHandlerDao.failMethod(orderId,userId,comments);
				rd = request.getRequestDispatcher("PassFail");
			}


			rd.forward(request, response);
		}
	} 
}


