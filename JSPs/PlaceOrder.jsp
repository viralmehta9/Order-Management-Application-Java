<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.*" %>
<%@ page import=" com.Quinnox.Controller.Item" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" language="javascript">
function isValidOrder()
{
		
	var productName = document.getElementById("productName").value;
	var supplierName = document.getElementById("supplierName").value;
	var quantity = document.getElementById("quantity").value;
	var price = document.getElementById("price").value;
	
		
	var blankField="";
	
	if(quantity=="" || quantity==null)
	{
		var temp = "Quantity";
		blankField=blankField.concat(temp, '\n');
	}
	if(price=="" || price==null)
	{
		var temp = "Price";
		blankField=blankField.concat(temp, '\n');
	}
	
	if(blankField!="")
	{
		alert('Plz fill up following fields:-\n\n'+blankField);
		return false;
	}
	else
	{	
		//nothing blank so find errors
		//validation for errors in fields
		var errorField="";
		
		var a=quantity;
		if(isNaN(a) || a.length>=3 || a.indexOf(".")!=-1)
		{
			var temp = "Quantity";
			errorField=errorField.concat(temp, '\n');
		}
		
		var a=price;
		if(isNaN(a) || a.length>=5)
		{
			var temp = "Price";
			errorField=errorField.concat(temp, '\n');
		}
		
		if(errorField!="")
		{
			alert('Erros in following fields:-\n\n'+errorField);
			return false;
		}
	}
	
	return true;
}

function PopulateSupplier(productName)
{

	document.location.href = "PlaceOrder?productName="+productName;
	
}

function deleteData()
{
	    document.location.href="PlaceOrder";

}

function placeOrder()
{
	 document.location.href="FinalPlaceOrder";
}


</script>
</head>
<body bgcolor="#E0E0E0">
<form  action="PlaceOrder"  method="post" name="form">
<%
     
    ArrayList<String> list2=new ArrayList<String>();
    list2=(ArrayList)application.getAttribute("pname");
    
    ArrayList<String> list3=new ArrayList<String>();
    list3= (ArrayList)request.getAttribute("supplierName");
 
 %>    
<br>
<br>

DETAILS
<table border="1">
<tr><th bgcolor="#ffff00">Product Name</th><th bgcolor="#ffff00">Supplier Name </th><th bgcolor="#ffff00">Quantity</th><th bgcolor="#ffff00">Price</th><th bgcolor="#ffff00">Add More Product</th></tr>
<tr>
<td> 

<%

String name =(String)session.getAttribute("spname");
String select=(String)session.getAttribute("Select");

%>
 <select name="productName" id="productName" onchange="PopulateSupplier(this.value)" >
       <%
       
      if(select == null)
      {
    	  out.println("<option value=Select>Select</option>");
    	  
      }
      if(name!=null)
        {
        	out.println("<option value="+name+">"+name+"</option>");	
        }
       
        if(list2 != null )
        {
	        for(int i=0; i<list2.size(); i++)  
	        { 
	        	if(!(list2.get(i).equals(name)))
	        	{
	        		 out.println("<option value="+list2.get(i)+">" + list2.get(i) + " </option>");	
	        	}	
	        }
      
        }
        %>

 </select>
</td>
 <td>
 <select name="supplierName" id="supplierName">
 
        <%
        
      if (list3 != null)
        
        for(int i=0; i<list3.size(); i++)  
        { 
        	out.println("<option value="+list3.get(i)+">" + list3.get(i) + " </option>");
        	
        } 
        %>
</td>
<td>  <input type="text" name="quantity" id="quantity" > </td><td>  <input type="text" name="price" id="price">  </td>
<td> <input type="submit" name="Add" value="Add More" onclick="return isValidOrder()"></td>
</table>

<% 
HashMap<Integer, Item> hm=new HashMap<Integer,Item>();
//hm=(HashMap)session.getAttribute("Cart");
hm=(HashMap)request.getAttribute("data");

 if(hm !=null)
 {
	 System.out.println("hello.......");
Set keys=hm.keySet();
Collection values= hm.values();
Iterator it= keys.iterator();
%>


<TABLE id="dataTable" width="350px" border="1">
<tr><th><label>Product Name</label> </th>   <th> <label>Supplier Name</label> </th><th>   <label>Quantity</label> </th><th>  <label>Price Per Unit</label> </th><th> <label>Total Price</label></th></tr>

<%
while(it.hasNext())
{
Object key= it.next();
Item items= (Item)hm.get(key);
int pid=items.getPid();
String sname=items.getSupplierName();
String pname=items.getProductName();
int quantity=items.getQuantity();
int price=items.getUnitPrice();
%>

<TR> <TD><label><%=sname%> </label></TD>
     <TD><label>  <%=pname%> </label></TD>
     <TD><label>&nbsp;   <%=quantity%> </label></TD>
     <TD><label> &nbsp;&nbsp;  <%=price%></label></TD>
       <td><label>&nbsp; <%= (quantity *price)%> </label></td>
        <TD><INPUT type="checkbox" name="checkBox" value=<%=pid %> ></TD>
 </TR>
<%   }  } %>

</TABLE>
<INPUT type="submit" name="Delete" value="Delete" onclick="deleteData()"/>
<br/>

<br>

</form>
<form action="FinalPlaceOrder" method="post">
<INPUT type="submit" name="FinalOrder" value="PlaceOrder" onclick="placeOrder()"/>
</form>
</body>
</html>