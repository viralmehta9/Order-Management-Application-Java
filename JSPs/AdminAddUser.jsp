<%@page import="java.util.ArrayList"%>
<%@page import="com.Quinnox.Controller.ManagerPopulate"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add User</title>
<script>
function validateForm()
{
	var username_regex = /^[\w.-]+$/;

	var uname=document.forms["myForm"]["uname"].value;
	if (uname==null || uname=="")
	  {
	  	alert("Please Enter Username");
	  	return false;
	  }
	if ( !(username_regex.test(uname)) ) 
		{
			return false;
		}
	var pwd=document.forms["myForm"]["password"].value;
	  
	  if (pwd == null || pwd=="")
		{
	    	alert("Please Enter Password.");
	    	return false;
	    }
	  if(pwd.length<8)
	  {
		  alert("Minimum 8 characters in password is required");
		  return false;
  		}
		var cont=document.forms["myForm"]["cont"].value;
	  
	  if (cont == null|| cont=="")
		{
	    	alert("Please Enter Contact Number.");
	    	return false;
	    }
	  if(cont.length<11)
	  {
		  alert("Phone Number is invalid");
		  return false;
	  }
	  return true;
}


function  populateManager(selectobj)
{
	alert(selectobj.selectedIndex);
	if(selectobj.selectedIndex==1)
	{
		mgr.style.visibility = 'visible';
		
	}
	else
	{
		mgr.style.visibility = 'hidden';
	}
}

</script>
<style type=text/css>
#mgr {
	visibility: hidden;
}
</style>

</head>
<body bgcolor="#E0E0E0">
<%
	Boolean logged = null;
	
	try 
	{
		logged = (Boolean) session.getAttribute("logged");
	}
	catch (Exception e) 
	{
		System.out.println(e);
	}
	if (logged == null || !logged)
	{
		RequestDispatcher rd = request.getRequestDispatcher("LoginPage.jsp");
		rd.forward(request, response);
	} 
	else 
	{
		ArrayList<Integer> managerList =new ArrayList<Integer>();
		managerList=(ArrayList<Integer>)request.getAttribute("ManagerList");
	   

		
		
%>


<form name="myForm" action="AdminAddUser" method="post"
	onsubmit="return validateForm()">
<table>
	<tr>
		<td>Username:</td>
		<td><input type="text" name="uname"></td>

	</tr>
	<tr>
		<td>Password:</td>
		<td><input type="password" name="password"></td>
	</tr>
	<tr>
		<td>Specify Role:</td>
		<td><select name="role" onchange="populateManager(this)">
			<option value="manager">PurchaseManager</option>
			<option value="employee">PurchaseEmployee</option>

			<option value="supplier">Supplier</option>
			<option value="qce">QualityControlEngineer</option>
		</select></td>

		<td>
		<div id='mgr'><select name="managerList">


			<option value="SelectManager">SelectManager</option>

			<% 
	            if (managerList != null)
	            {
		        for(int i=0; i<managerList.size(); i++)  
		        { 
		        out.println("<option value="+managerList.get(i)+">" + managerList.get(i) + " </option>");

		        } }
		        %>

		</select></div>
		</td>


	</tr>
	<tr>
		<td>Address:</td>
		<td><input type="text" name="addr"></td>
	</tr>
	<tr>
		<td>Contact:</td>
		<td><input type="text" name="cont"></td>
	</tr>
	<tr>
		<td><input type="radio" name="sex" value="Male">Male</td>
		<td><input type="radio" name="sex" value="Female">Female</td>
	</tr>
	<tr>
		<td><input type="Submit" name="submit" value="Add User"></td>
	</tr>
</table>



</form>
<a href="AdminHome.jsp">Click here</a>
to go to Home Page

<%
	String userAdded = (String) request.getAttribute("UserAdded");
		if (userAdded != null) 
		{
			out.println(userAdded + "<br/>");
		}	}
%>

</body>
</html>