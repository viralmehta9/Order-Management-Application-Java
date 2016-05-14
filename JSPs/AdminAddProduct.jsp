<%@page import="com.Quinnox.Controller.SupplierClass"%>
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
<title>Insert ti                   tle here</title>
<script type="text/javascript">
function validateForm()
{
var numericExpression = /^[0-9]+$/;
//var username_regex = /^[\w.-]+$/;
var productName=document.forms["myForm"]["PName"].value;
var description=document.forms["myForm"]["description"].value;
var price=document.forms["myForm"]["price"].value;


//var chks = document.myForm.getElementsByName("checkBox");
//var hasChecked = false;
if (productName==null || productName=="")
  {
  alert("Please Enter Product Name");
  return false;
  }
/*
if ( username_regex.test(productName) ) {
	return true;	
}
else
{alert("Username should be String");
return false;
}*/
 
  if (description == null|| description=="") {

    alert("Please Enter Description.");
    return false;
      }
  
  
  if (price==null || price=="")
    {
    alert("Please Enter price");
    return false;
    }
  if ( price.match(numericExpression) ) {

return true;
	}
  else
  {alert("Price Should be integer value");
  return false;
  }
	/*
  if( document.myForm.checkBox.checked==false)
  {
	 */ alert("Select Checkbox");
 // } */
  var chks = document.forms["myForm"]["checkBox"];
  var hasChecked = false;
  for (var i = 0; i < chks.length; i++)
  {
	  alert(chks[i].checked);
  	if (chks[i].checked)
  	{
  	hasChecked = true;
  	break;
  	}
  }

  if (hasChecked == false)
  	{
  	alert("Please select Supplier");
  	return false;
  	}

  
    return true;
  }
 
</script>
</head>
<body bgcolor="#E0E0E0">
<%
	Boolean logged = null;
	try {
		logged = (Boolean) session.getAttribute("logged");

	} catch (Exception e) 
	{
		System.out.println(e);
	}
	if (logged == null || !logged)
	{
		RequestDispatcher rd = request
				.getRequestDispatcher("LoginPage.jsp");
		rd.forward(request, response);
	} 
	else {
%>
<form name="myForm" action="AdminAddProduct" method="post" onsubmit="return validateForm()">
Enter Product Name:
 <input type= "text" name= "PName"><br/>
 
Enter Description:
<textarea rows="4" cols="50" name="description"></textarea><br/>
Enter Price: <input type= "text" name= "price"><br/>

Select Supplier :<br/>
<%
HashMap hashMap = (HashMap)request.getAttribute("SupplierList");
if(hashMap.size()>0)
{
%>

<table border="2">
<tr>
	<th>SupplierId</th>
	<th>SupplierName</th>
	
</tr>
	
<%

 
	Set Keys = hashMap.keySet();
	Object key = null;
	Iterator it =Keys.iterator();	

	while(it.hasNext())
	{
		key=it.next();
		SupplierClass record = (SupplierClass)hashMap.get(key);
		//out.println("orderId="+record.getOrderid());
		
		int suppId =record.getUserId();
		String supplierName = record.getSupplierName();
		
%>
	<tr>
	<td><%=suppId%></td>
	<td><%=supplierName%></td>
	
	<td><input type="checkbox" name="checkBox" value=<%=suppId%>></td>
	</tr>
<%} %>
</table>
<br/><br/><br/>

<INPUT type="submit" value="Add Product">
<INPUT type="reset" value="Reset"><br/><br/>
</form>
<%
}
else
{
%>
NO Suppliers Available
<%} %>

<a href="AdminHome.jsp">Click here</a> to go to Home Page<br/><br/>
<%
		String ProductAdded=(String)request.getAttribute("ProductAdded");
		if(ProductAdded!=null)
		{
			out.println(ProductAdded+"<br/>");
		
		}}%>

</body>
</html>