package com.Quinnox.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Quinnox.Model.DeleteUserDao;

/**
 * Servlet implementation class ReplaceManager
 */
public class ReplaceManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReplaceManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd=null;
		HttpSession session=request.getSession(false);
		Boolean logged=null;

		try 
		{
			logged=(Boolean)session.getAttribute("logged");

		} catch (Exception e) 
		{
			// TODO: handle exception
		}


		if(logged==null || !logged)
		{
			rd=request.getRequestDispatcher("LoginPage.jsp");
		}
		else
		{
			try 
			{
				int oldManager = (Integer)session.getAttribute("oldManager");
				int newManager = Integer.parseInt(request.getParameter("newManager"));

				DeleteUserDao obj = new DeleteUserDao();
				obj.replaceManager(oldManager, newManager);
				rd = request.getRequestDispatcher("AdminHome.jsp");
				request.setAttribute("roleSelected", "manager");
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}		
		rd.forward(request, response);
	}
}
