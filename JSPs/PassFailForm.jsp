<%@page import="com.Quinnox.Controller.ApproveRejectClass"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PassFail</title>
<script type="text/javascript">

function orderDetails(orderId,role)
{
	document.location.href="OrderDetails?orderId="+orderId+"&&role="+role;
}

</script>
</head>
<body bgcolor="#E0E0E0">
<form action="" method="post">
<%
String sessionId = session.getId();
String role = (String)session.getAttribute("role");


HashMap hashMap = (HashMap)request.getAttribute("hashMap");
if(hashMap.size()>0)
{
%>

<table border="2">
<tr>
    <th bgcolor="#ffff00">OrderId</th>
	<th bgcolor="#ffff00">UserId</th>
	<th bgcolor="#ffff00">Total Price</th>
	<th bgcolor="#ffff00">View Details</th>
</tr>
	
<%
	Set Keys = hashMap.keySet();
	Object key = null;
	Iterator it =Keys.iterator();	

	while(it.hasNext())
	{
		key=it.next();
		ApproveRejectClass record = (ApproveRejectClass)hashMap.get(key);
		
		int orderId=0;
		int userId=0;
		int totalPrice=0;
		
		orderId = record.getOrderid();
		userId = record.getUserid();
		totalPrice = record.getTotalPrice();
		
%>
	<tr>
	<td><%=orderId%></td>
	<td><%=userId%></td>
	<td><%=totalPrice%></td>
	<td><button type="button" onclick="orderDetails(<%=orderId%>,'<%=role%>')">Check</button></td>
	</tr>
<%} %>
</table>

<%
}
else
{
%>
NO MORE ORDERS TO CHECK!
<%} %>
</form>
</body>
</html>