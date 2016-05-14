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
 * Servlet implementation class AdminDeleteUser
 */
public class AdminDeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminDeleteUser() {
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
			String[] checkBox = request.getParameterValues("checkBox");
			DeleteUserDao deleteUserDao= new DeleteUserDao();


			if (checkBox != null) 
			{
				for (int i = 0; i < checkBox.length; i++) 
				{					
					deleteUserDao.deleteUser(checkBox[i]);
				}

				request.setAttribute("UserDelete","User Deleted");
				rd = request.getRequestDispatcher("AdminHome.jsp");
			}
			else
			{
				request.setAttribute("Error", "Select User To Be Deleted");
				rd = request.getRequestDispatcher("AdminDeleteUser.jsp");
			}
		}
		rd.forward(request, response);
	}
}

