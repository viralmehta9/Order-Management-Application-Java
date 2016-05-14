<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stationary</title>

</head>
<body bgcolor="#87CEFA">
<form action="ValidateLogin" method="post" target="_top">
<table width="397" height="200" align="center" bgcolor="#FFB6C1">
<tr>
<td width="182" height="42" class="name">Enter UserName :</td>
<td width="182"><input name="username" type="text" value=""></td>
</tr>
<tr>
<td width="182" height="42" class="pass">Enter Password :</td>
<td width="182" height="42"> <input type="password" name="password" /></td>
</tr>
<tr><td width="182" height="42" class="role">
Select Role : </td>
<td width="182" height="42">
<select name="role">
<option id="admin">admin</option>
 <option id="employee">employee</option>
 <option id="manager">manager</option>
 <option id="supplier">supplier</option>
 <option id="qce">qce</option>
 </select>
 </td></tr>
 <tr><td width="182" height="42">
 <input type="submit" value="Login" height="50" width="100"/> </td></tr>
 </table>
 </form>
 <%
		String failed=(String)request.getAttribute("loginfailed");
		if(failed!=null)
		{
			out.println(failed+"<br/>");
		}
	%>
</body>
</html>