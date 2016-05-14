package com.Quinnox.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Quinnox.Controller.Item;
import com.Quinnox.Model.PlaceOrderDao;


/**
 * Servlet implementation class PlaceOrder
 */
public class PlaceOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PlaceOrder() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
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
			// get all product ids
			PlaceOrderDao placeOrderDao = new PlaceOrderDao();
			ArrayList<Integer> productId=placeOrderDao.retriveProductId();
			request.setAttribute("pid",productId);

			// get all product names
			ArrayList<String> pName=placeOrderDao.retriveProductName();
			getServletContext().setAttribute("pname",pName);
			String productName;
			
			
			// Adding to cart
			if(request.getParameter("Add")!=null)
			{
				
				// retrive selected values from  placeorder.jsp
				
				String pname= request.getParameter("productName");
				String sname=request.getParameter("supplierName");
				int quantity1 = Integer.parseInt(request.getParameter("quantity"));
				int price=Integer.parseInt(request.getParameter("price"));

				//Calling placeorderdao to add to temp table
				HashMap<Integer, Item> hashMap=	placeOrderDao.insertIntoTempCart(pname,sname,quantity1,price);
				request.setAttribute("data",hashMap);

			}
			
			
			if(request.getParameter("productName")!=null)
			{
				ArrayList<String> supplierNameArray=new ArrayList<String>();
				productName=(String)request.getParameter("productName");

				request.setAttribute("pname", productName);

				String select="Select";

				session.setAttribute("spname", productName);
				session.setAttribute("Select", select);

				ArrayList<Integer> productIdArray=placeOrderDao.retriveProductId();
				request.setAttribute("pid",productIdArray);

			
				request.setAttribute("pname",productName);
				// getting supplier names
				supplierNameArray= placeOrderDao.populateSupplier(productName);

				HashMap hashMapTemp= new HashMap();
				hashMapTemp=placeOrderDao.retrieveTemp();

				request.setAttribute("supplierName",supplierNameArray);
				request.setAttribute("data",hashMapTemp);
			}
			
			//deleting from  temp table cart
			if(request.getParameter("Delete")!=null)
			{
				String[] checkBox = request.getParameterValues("checkBox");

				if (checkBox != null) 
				{
					HashMap<Integer, Item>	hashMap	=placeOrderDao.deleteCart(checkBox);
					request.setAttribute("data",hashMap);
				}
				
			}
			rd=request.getRequestDispatcher("PlaceOrder.jsp");
			rd.forward(request, response);
		}
	}
}