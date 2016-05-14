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
 * Servlet implementation class UserCollection
 */
public class UserCollection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserCollection() {
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
			try 
			{

				String roleSelected = request.getParameter("roleSelected");

				UserCollectionDao userCollection = new UserCollectionDao();
				HashMap<Integer, UserLoginClass> userList=userCollection.userCollection(roleSelected);

				request.setAttribute("hashMap", userList);

				if(roleSelected.equals("manager"))
				{
					rd = request.getRequestDispatcher("ManagerList.jsp");
				}
				else
				{
					rd = request.getRequestDispatcher("AdminDeleteUser.jsp");
				}

			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}

			String userDelete=(String) request.getAttribute("UserDelete");
			request.setAttribute("UserDelete",userDelete);	
		}
		rd.forward(request, response);
	}
}