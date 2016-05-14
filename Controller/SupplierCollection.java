package com.Quinnox.Controller;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Quinnox.Model.SupplierListDao;

/**
 * Servlet implementation class SupplierCollection
 */
public class SupplierCollection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SupplierCollection() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);

		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
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
			RequestDispatcher rd;
			try 
			{
				SupplierListDao supplierListDao= new SupplierListDao();

				HashMap<Integer, SupplierClass> supplierList=supplierListDao.supplier();
				String ProductAdded=(String) request.getAttribute("ProductAdded");

				request.setAttribute("ProductAdded",ProductAdded);
				request.setAttribute("SupplierList", supplierList);

				rd = request.getRequestDispatcher("AdminAddProduct.jsp");
				rd.forward(request, response);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
}