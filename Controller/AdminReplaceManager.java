package com.Quinnox.Controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Quinnox.Model.UserCollectionDao;

/**
 * Servlet implementation class AdminReplaceManager
 */
public class AdminReplaceManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminReplaceManager() {
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
				int managerSelected = Integer.parseInt(request.getParameter("managerId"));

				session.setAttribute("oldManager", managerSelected);

				UserCollectionDao managerList = new UserCollectionDao();
				HashMap<Integer, UserLoginClass> hashmap = managerList.managerCollection(managerSelected);

				request.setAttribute("hashMap", hashmap);
				rd = request.getRequestDispatcher("AdminReplaceManager.jsp");
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}		
		rd.forward(request, response);

	}
}
