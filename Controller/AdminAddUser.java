
package com.Quinnox.Controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Quinnox.Model.AddUserDao;

/**
 * Servlet implementation class AdminAddUser
 */
public class AdminAddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAddUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
			RequestDispatcher rd=request.getRequestDispatcher("LoginPage.jsp");
			rd.forward(request, response);
		}
		else
		{
			String username = "";
			String password = "";
			String role = "";
			String managerList="";
			int managerId=-1;
			username = request.getParameter("uname");
			password = request.getParameter("password");
			role = request.getParameter("role");
			managerList=request.getParameter("managerList");
			if(!managerList.equals("SelectManager"))
			{
				managerId=Integer.parseInt(request.getParameter("managerList"));
			}
			RequestDispatcher rd;

			AddUserDao addUserDao = new AddUserDao();
			int isActive=0;
			addUserDao.insertUser(username, password, role,managerId,isActive);

			request.setAttribute("UserAdded", "User "+username+" Added");
			rd = request.getRequestDispatcher("ManagerPopulate");
			rd.forward(request, response);
		}
	}
}
