package com.Quinnox.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Quinnox.Model.LoginDao;

/**
 * Servlet implementation class ValidateLogin
 */
public class ValidateLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateLogin() {
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

		// TODO Auto-generated method stub
		String userName=request.getParameter("username");
		String pwd=request.getParameter("password");
		String role=request.getParameter("role");
		Boolean loginValidation=false;
		RequestDispatcher rd =null;

		String password="";
		String roleName="";
		int userId = 0;
		int isActive=0;

		if(userName.equals("admin") && pwd.equals("admin123") && role.equals("admin"))
		{
			rd = request.getRequestDispatcher("AdminView.jsp");
			loginValidation=true;

		} 		
		else
		{
			LoginDao loginDao = new LoginDao();
			String uname = loginDao.validateLogin(userName);
			String[] split = uname.split(",");
			String uId = split[0];
			 password = split[1];
			 roleName = split[2];
			String isActiveString = split[3];
			userId = Integer.parseInt(uId);
			isActive=Integer.parseInt(isActiveString);
			
			
			
			//while(rs.next())
			//{
			//	password = rs.getString("password");
			//	roleName = rs.getString("role");
			//	isActive=rs.getInt("isActive");
			//	userId = rs.getInt("userId");
			//}
			if(roleName.equals(role) && password.equals(pwd)&& (isActive==1))
			{
				 if(roleName.equals("employee"))
                 {
                         rd = request.getRequestDispatcher("EmployeeView.jsp");
                         loginValidation= true;
                 }
                 else if(roleName.equals("manager"))
                 {
                         rd = request.getRequestDispatcher("ManagerView.jsp");
                         loginValidation= true;
                 }
                 else if(roleName.equals("supplier"))
                 {
                         rd = request.getRequestDispatcher("SupplierView.jsp");
                         loginValidation= true;
                 }
                 else if(roleName.equals("qce"))
                 {
                         rd = request.getRequestDispatcher("qceView.jsp");
                         loginValidation= true;
                 }


				
			}
		}
		if(loginValidation==true)
		{
			HttpSession session=request.getSession(true);
			session.setAttribute("logged", true);
			session.setAttribute("uname",userName);
			session.setAttribute("userId", userId);
			session.setAttribute("role", role);
		}
		else
		{
			request.setAttribute("loginfailed", "Login failed.");
			rd=request.getRequestDispatcher("LoginPage.jsp");	
		}

		rd.forward(request, response);

	}

}
