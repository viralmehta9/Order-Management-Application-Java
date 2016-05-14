
package com.Quinnox.Controller;
import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Quinnox.Model.AddProductDao;

/**
 * Servlet implementation class AdminAddProduct
 */
public class AdminAddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAddProduct() {
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
		RequestDispatcher rd=null;

		if(logged==null || !logged)
		{
			rd=request.getRequestDispatcher("LoginPage.jsp");
			rd.forward(request, response);
		}
		else
		{
			String pName= request.getParameter("PName");
			String description= request.getParameter("description");
			int price=Integer.parseInt(request.getParameter("price"));
			String[] checkBox = request.getParameterValues("checkBox");

			AddProductDao addProductDao= new AddProductDao();
			addProductDao.insertProduct(pName,description,price,checkBox);

			request.setAttribute("ProductAdded", "Product "+pName+" is added");
			rd = request.getRequestDispatcher("SupplierCollection");
			rd.forward(request, response);
		}
	}
}
