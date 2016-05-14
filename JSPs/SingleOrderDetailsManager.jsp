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
<title>ApproveReject</title>
<script type="text/javascript">

function validateComments()
{
	var comments = document.getElementById("comments").value;
	if(comments == null || comments == "")
	{
		document.getElementById("commentsError").innerHTML = "Plz Enter Comments Before Rejecting";
		return false;
	}
	else
	{
		document.getElementById("commentsError").innerHTML = "";
		return true;
	}
}

</script>
</head>
<body bgcolor="#E0E0E0">
<form action="ManagerApprovalHandler" method="post">
<%
String orderId = (String)request.getAttribute("orderId");
String role = (String)request.getAttribute("role");
role=role.trim();

HashMap hashMap = (HashMap)request.getAttribute("hashMap");
if(hashMap.size()>0)
{
%>
Details of Order No = <%=orderId%><%=role%>
<input type="hidden" name="orderId" id="orderId" value="<%=orderId%>">
<br/><br/>
<table border="2">
<tr>
	
	<th bgcolor="#ffff00">ProductId</th>
	<th bgcolor="#ffff00">PriceUnit</th>
	<th bgcolor="#ffff00">Quantity</th>
	<th bgcolor="#ffff00">Total Price</th>
</tr>
	
<%
	Set Keys = hashMap.keySet();
	Object key = null;
	Iterator it =Keys.iterator();	

	String commentError = (String)request.getAttribute("commentError");
	if(commentError == null || commentError == "null")
	{
		commentError = "";
	}
	
	while(it.hasNext())
	{
		key=it.next();
		ApproveRejectClass record = (ApproveRejectClass)hashMap.get(key);
		//out.println("orderId="+record.getOrderid());
		
		int productId = record.getProductid();
		int userId = record.getUserid();
		int quantity = record.getQuantity();
		int priceUnit = record.getPriceunit();
		int totalPrice = record.getTotalPrice();
		
%>
	<td><%=productId%></td>
	<td><%=priceUnit%></td>
	<td><%=quantity%></td>
	<td><%=totalPrice%></td>
	</tr>
<%} %>
</table>
<br/><br/>
Comments : &nbsp;<input type="text" name="comments" id="comments"> 
<br/><div id="commentsError"></div>
<%
if(role.equals("manager"))
{
%>
<INPUT type="reset" value="Reset"><br/><br/>
<INPUT type="submit" name="submit" value="Approve">
<INPUT type="submit" name="submit" value="Reject" onclick="return validateComments()">
<%
}

else if(role.equals("supplier"))
{
%>
<INPUT type="reset" value="Reset"><br/><br/>
<INPUT type="submit" name="submit" value="Accept">
<INPUT type="submit" name="submit" value="Deny" onclick="return validateComments()">
<%}
else if(role.equals("qce")) {
%>
<INPUT type="reset" value="Reset"><br/><br/>
<INPUT type="submit" name="submit" value="Pass">
<INPUT type="submit" name="submit" value="Fail" onclick="return validateComments()">
<%} } 
 else{ %>
NO PENDING APPROVALS!
<%} %>
</form>
</body>
</html>