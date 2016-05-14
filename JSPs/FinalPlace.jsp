<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.Quinnox.Controller.Details" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#E0E0E0">

<form  action="FinalPlaceOrder"  method="post" name="form" target="_top">

<%
     
HashMap<Integer,Details> hm=new HashMap<Integer,Details>();
hm=(HashMap)request.getAttribute("detailOrderId");

if(hm !=null)
{
Set keys=hm.keySet();
Collection values= hm.values();
Iterator it= keys.iterator();

        %>
<table id="dataTable" width="350px" border="1">

        <tr>
               
                <td width="119"><b>OrderId</b></td>
                <td width="168"><b>Product Name</b></td>
                <td width="168"><b>Supplier Name</b></td>
                <td width="168"><b>Price</b></td>
        </tr>
      
<%
while(it.hasNext())
{
Object key= it.next();
Details details= (Details)hm.get(key);
int oId=details.getoId();
String pname=details.getProductName();
String sname=details.getSupplierName();
int price=details.getPrice();

%>

<TR> <TD><label><%=oId%> </label></TD>
     <TD><label>  <%=pname%> </label></TD>
     <TD><label>&nbsp;   <%=sname%> </label></TD>
     <TD><label> &nbsp;&nbsp;  <%=price%></label></TD>
       
 </TR>
<%   }  } %>

       
        
</table>
<input type="submit" value="Submit Order" name="Submit" target="_top" />

</form>
</body>
</html>