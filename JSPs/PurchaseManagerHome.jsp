<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
<br />

<a href="PlaceOrder" target="content">PLACE ORDERS</a>
<br />

<a href="ApproveReject" target="content">CHECK PENDING ORDER APPROVALS</a>
<br />

<a href="OrderIdFetcher"  target="content">VIEW AUDIT RECORDS</a>
<br/>

<a href="LoginStart.jsp" target="_top"> Logout</a>
<%}
	String sessionId = session.getId();
	int userId = (Integer)session.getAttribute("userId");
	
	%>
</body>
</html>