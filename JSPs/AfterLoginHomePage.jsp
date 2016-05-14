<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	Boolean logged = null;
	try {
		logged = (Boolean) session.getAttribute("logged");

	} catch (Exception e) {
		// TODO: handle exception
	}
	if (logged == null || !logged)
	{
		RequestDispatcher rd = request
				.getRequestDispatcher("LoginPage.jsp");
		rd.forward(request, response);
	} 
	else {
%>
<%
	String role = (String) session.getAttribute("role");
		String uname = (String) session.getAttribute("uname");

		if (role.equals("admin")) {
%>
Hello,
<%=uname%>
<br />
<br />
<a href="ManagerPopulate"> ADD USER</a>
<br />
<a href="UserCollection"> DELETE USER</a>
<br />
<a href="SupplierCollection">ADD PRODUCT</a>
<br />
<a href="UsernameView">UPDATE USER PASSWORD</a>
<br />
<a href="AuditTable "> VIEW AUDIT TRAIL</a>
<br />
<%
	} else if (role.equals("manager")) {
%>
Hello,
<%=uname%>
<br />
<br />
<a href="">PLACE ORDERS</a>
<br />
<br />
<a href="ApproveReject">CHECK PENDING ORDER APPROVALS</a>
<br />
<br />
<a href="">VIEW AUDIT RECORDS</a>
<%
	} else if (role.equals("employee")) {
%>
Hello,
<%=uname%>
<br />
<br />
<a href="">PLACE ORDERS</a>
<br />
<br />
<a href="">VIEW AUDIT RECORDS</a>
<%
	} else if (role.equals("supplier")) {
%>
Hello,
<%=uname%>
<br />
<br />
<a href="">VIEW ORDERS</a>
<br />
<br />
<a href="">VIEW AUDIT RECORDS</a>
<%
	} else if (role.equals("qce")) {
%>
Hello,
<%=uname%>
<br />
<br />
<a href="">CHECK SUPPLIED ORDERS</a>
<br />
<br />
<a href="">VIEW AUDIT RECORDS</a>
<%
	}
%>

<%
	String productAdded = (String) request.getAttribute("ProductAdded");
		if (productAdded != null) 
		{
			out.println(productAdded + "<br/>");
		}
	}
%>
</body>
</html>