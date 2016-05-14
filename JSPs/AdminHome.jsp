<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Home Page</title>

</head>
<body bgcolor="#ccccff">
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

%>
Hello,
<%=uname%>
<br/>
<br/>
<a href="ManagerPopulate" target="content"> ADD USER</a>
<br/>
<br/>
<a href="RoleSelection.jsp" target="content"> DELETE USER</a>
<br/>
<br/>
<a href="SupplierCollection" target="content">ADD PRODUCT</a>
<br/>
<br/>
<a href="LoginStart.jsp" target="_top"> Logout</a>


<%
	String productAdded = (String) request.getAttribute("ProductAdded");
		if (productAdded != null) 
		{
			out.println(productAdded + "<br/>");
		}
		
		String sessionId = session.getId();
		int userId = (Integer)session.getAttribute("userId");
		
		
%>
<%} %>
</body>
</html>