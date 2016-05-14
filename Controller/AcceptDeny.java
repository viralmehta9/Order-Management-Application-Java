package com.Quinnox.Controller;

import java.io.IOException;

import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Quinnox.Model.AcceptDenyDao;

/**
 * Servlet implementation class AcceptDeny
 */
public class AcceptDeny extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AcceptDeny() {
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
			if(session.getAttribute("userId")!=null)
			{
				int userId = (Integer)session.getAttribute("userId");

				AcceptDenyDao acceptDenyDao = new AcceptDenyDao();
				HashMap<Integer,ApproveRejectClass> hashMap = acceptDenyDao.acceptDenyMethod(userId);
				request.setAttribute("hashMap", hashMap);



				rd = request.getRequestDispatcher("AcceptDenyForm.jsp");
				rd.forward(request, response);


			}
			
			rd = request.getRequestDispatcher("SupplierHome.jsp");
			rd.forward(request, response);
		}
		
	}

}
