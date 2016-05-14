<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.Quinnox.Controller.TimeStampClass"%>
    <%@page import="java.util.HashMap"%>
    <%@page import="java.util.Set"%>
	<%@page import="java.util.Iterator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Audit Table</title>
</head>
<body>

<%
HashMap hashMap = (HashMap)request.getAttribute("hashMap");
if(hashMap.size()>0)
{
%>
<form>


<%
	Set Keys = hashMap.keySet();
	Object key = null;
	Iterator it =Keys.iterator();	
	
	while(it.hasNext())
	{
		key=it.next();
		TimeStampClass record = (TimeStampClass)hashMap.get(key);
		
		int orderId = record.getOrderId();
		int userId = record.getUserId();
		String placedTimestamp = record.getPlacedTimestamp();
		
		int managerId = record.getManagerId();
		String managerStatus = record.getManagerStatus();
		String managerTimestamp = record.getManagerTimestamp();
		
		int supplierId = record.getSupplierId();
		String supplierStatus = record.getSupplierStatus();
		String supplierTimestamp = record.getSupplierTimestamp();
		
		int QCEId = record.getQceId();
		String QCEStatus = record.getQceStatus();
		String QCETimestamp = record.getQceTimestamp();
		
%>
OrderId = <%=orderId %>
		<table border=2>
		<tr>
		<td bgcolor="#ffff00">UserId</td>
		<td><%=userId%>&nbsp</td>
		</tr>
		<tr>
		<td bgcolor="#ffff00">Placed Timestamp </td>	
		<td><%=placedTimestamp%>&nbsp</td>
		</tr>
		
		<%if(managerId!=0) 
		{
		%>
		<tr>
		<td bgcolor="#ffff00">Manager ID</td>
		<td><%=managerId%>&nbsp</td>
		</tr>
		<tr>
		<td bgcolor="#ffff00">Manager Status</td>
		<td><%=managerStatus%>&nbsp</td>
		</tr>
		<tr>
		<td bgcolor="#ffff00">Manager Timestamp</td>
		<td><%=managerTimestamp%>&nbsp</td>
		</tr>
		<%}%>
		
		<%if(supplierId!=0) 
		{
		%>
		<tr>
		<td bgcolor="#ffff00">Supplier ID</td>>
		<td><%=supplierId%>&nbsp</td>
		</tr><tr>
		<td bgcolor="#ffff00">Supplier Status</td>
		<td><%=supplierStatus%>&nbsp</td>
		</tr><tr>
		<td bgcolor="#ffff00">Supplier Timestamp</td>
		<td><%=supplierTimestamp%>&nbsp</td>
		</tr>
		<%}%>
		
		<%if(QCEId!=0) 
		{
		%>
		<tr>
		<td bgcolor="#ffff00">QCE ID</td>
		<td><%=QCEId%>&nbsp</td>
		</tr><tr>
		<td bgcolor="#ffff00">QCE Status</td>
		<td><%=QCEStatus%>&nbsp</td>
		</tr><tr>
		<td bgcolor="#ffff00">QCE Timestamp</td> 
		<td><%=QCETimestamp%>&nbsp</td>
		</tr>
		<%}%>
<%}%>
	</table></form>
<%
}
else
{
	%>
No entries Available
<%} %>
</body>
</html>