<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%session.invalidate(); %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LoginStart</title>
</head>
<frameset rows="30%,*">
<frame name="head" src="CompanyName.jsp"  scrolling="no" noresize="noresize">
<frameset cols="20%,*">
<frame name="menu" src="Menu.jsp" target="_top">
<frame name="content" src="Blank.jsp">
</frameset>
</frameset>
</html>
