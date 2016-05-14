package com.Quinnox.Controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Quinnox.Model.ApproveRejectDao;

/**
 * Servlet implementation class ApproveReject
 */
public class ApproveReject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApproveReject() {
		super();

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
			int userId = (Integer)session.getAttribute("userId");

			ApproveRejectDao approveRejectDao = new ApproveRejectDao();		
			HashMap<Integer,ApproveRejectClass>hashMap=approveRejectDao.approveRejectMethod(userId);
			request.setAttribute("hashMap", hashMap);

			rd = request.getRequestDispatcher("ApproveRejectForm.jsp");
			rd.forward(request, response);

		}
	}

}
