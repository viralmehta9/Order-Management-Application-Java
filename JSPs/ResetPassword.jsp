<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#E0E0E0">
<% 


		String user=(String)session.getAttribute("user");
		
		%>
<form action= "ResetPassword" method= "post">
<%=user %>
Enter new Password: <input type= password name= "pwd"> <br/>
Confirm new Password <input type=password name="confirmPwd"><br/>
<input type =Submit value= "Reset Password">
</form>

</body>
</html>