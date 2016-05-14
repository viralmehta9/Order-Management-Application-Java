
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
import com.Quinnox.Model.FinalPlaceOrderDao;
import com.Quinnox.Controller.Details;

/**
 * Servlet implementation class FinalPlaceOrder
 */
public class FinalPlaceOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FinalPlaceOrder() {
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
			RequestDispatcher rd=null;
			HashMap<Integer, Details> hmOfDetails=new HashMap<Integer,Details>();

			FinalPlaceOrderDao finalPlaceOrder= new FinalPlaceOrderDao();
			ArrayList returnArray=finalPlaceOrder.finalPlaceOrderMethod();
			int lastOrderId=0;
			int tPrice=0;
			ArrayList pname= new ArrayList();
			ArrayList sname= new ArrayList();
			ArrayList qty= new ArrayList();
			ArrayList pr= new ArrayList();

			for(int i=0;i<returnArray.size(); i++)
			{
				hmOfDetails=(HashMap<Integer, com.Quinnox.Controller.Details>) returnArray.get(0);
				lastOrderId=(Integer) returnArray.get(1);
				tPrice=(Integer) returnArray.get(2);
				pname=(ArrayList) returnArray.get(3);
				sname=(ArrayList) returnArray.get(4);
				qty= (ArrayList) returnArray.get(5);
				pr= (ArrayList) returnArray.get(6);
			}
			request.setAttribute("detailOrderId",hmOfDetails);

			if(request.getParameter("Submit")!=null)
			{
				int userId=(Integer) session.getAttribute("userId");
				String role= (String) session.getAttribute("role");
				finalPlaceOrder.onSubmit(lastOrderId,tPrice, userId,pname,sname,qty,pr,role);
				if(role.equals("employee"))
				{
					rd=request.getRequestDispatcher("EmployeeView.jsp");
				}
				if(role.equals("manager"))
				{
					rd=request.getRequestDispatcher("ManagerView.jsp");
				}
				rd.forward(request, response);
			}
			else
			{
				rd=request.getRequestDispatcher("FinalPlace.jsp")	;
				System.out.println(rd.toString());

				rd.forward(request, response);
			}
		}
	}
}

