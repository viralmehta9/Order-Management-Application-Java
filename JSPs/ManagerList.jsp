<%@page import="com.Quinnox.Controller.*"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete User</title>
</head>
<body bgcolor="#E0E0E0">
<%
	Boolean logged = null;
	try {
		logged = (Boolean) session.getAttribute("logged");

	} catch (Exception e) {
		// TODO: handle exception
	}
	if (logged == null || !logged)
	{
		RequestDispatcher rd = request.getRequestDispatcher("LoginPage.jsp");
		rd.forward(request, response);
	} 
	else {
%>
<form action="AdminReplaceManager" method="post">
<%
if(request.getAttribute("hashMap")!=null)
{
HashMap<Integer,UserLoginClass> hashMap = (HashMap<Integer,UserLoginClass>)request.getAttribute("hashMap");
if(hashMap.size()>0)
{
%>

<table border="2">
<tr>
	<th>UserId</th>
	<th>Username</th>
	<th>Role</th>
	
</tr>
	
<%

 
	Set Keys = hashMap.keySet();
	Object key = null;
	Iterator it =Keys.iterator();	

	while(it.hasNext())
	{
		key=it.next();
		UserLoginClass record = (UserLoginClass)hashMap.get(key);
		
		int userId = record.getUserid();
		String username = record.getUsername();
		String password= record.getPassword();
		String role = record.getRole();
		
%>
	<tr>
	<td><%=userId%></td>
	<td><%=username%></td>
	<td><%=role%></td>
	
	<td><input type="radio" name="managerId" value=<%=userId%>></td>
	</tr>
<%} %>
</table>
<br/><br/><br/>
<br/><br/>
<INPUT type="submit" value="Delete"><br/><br/>
<%
}}
else
{
%>
NO user available
<%} %>
</form>
<a href="AdminHome.jsp">Click here</a> to go to Home Page<br/><br/>
<%
		String error=(String)request.getAttribute("Error");
		if(error!=null)
		{
			out.println(error+"<br/>");
		}
	%>
<%
		String delete=(String)request.getAttribute("UserDelete");
		if(delete!=null)
		{
			out.println(delete+"<br/>");
		}
	}%>
</body>
</html>