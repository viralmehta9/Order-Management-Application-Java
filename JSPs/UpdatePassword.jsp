<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#E0E0E0"><h1>hello</h1>
<% 
ArrayList<String> list = new ArrayList<String>();
        
        
           list = (ArrayList<String>)request.getAttribute("uname");

           %>

<form action="UsernameInSession" method="post" >
<select name="usernameSelect">
<%

for(int i=0;i<list.size(); i++)
{

	String username=(String)list.get(i);
	%>
	<option><%=username%></option>
	<%} %>
	</select><br/> <br/>
	<input type="Submit" value="Submit">
	</form>
	
</body>
</html>