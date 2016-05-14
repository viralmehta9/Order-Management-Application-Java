<%@page import="com.Quinnox.Controller.TimeStampClass"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
ArrayList orderListArray=(ArrayList)request.getAttribute("orderId"); %>
<form action="AuditTable" method="post">
<select name="orderIdSelect">
<% 
for(int i=0; i<orderListArray.size(); i++)
{
TimeStampClass record = (TimeStampClass)orderListArray.get(i);
int orderId = (Integer)record.getOrderId();
%>
<option value="<%=orderId%>"><%=orderId%></option>
<%} %>
</select>
<input type="submit" value="FindDetails"> 
</form>
</body>
</html>