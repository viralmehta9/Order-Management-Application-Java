package com.Quinnox.Controller;

public class ApproveRejectClass 
{
	int orderid;
	int productid; 
	int userid;
	int quantity; 
	int priceunit;
	int totalPrice;
	String status;

	public ApproveRejectClass(int orderid, int productid, int userid,
			int quantity, int priceunit,int totalPrice,String status) 
	{
		super();
		this.orderid = orderid;
		this.productid = productid;
		this.userid = userid;
		this.quantity = quantity;
		this.priceunit = priceunit;
		this.totalPrice=totalPrice;
		this.status = status;
	}

	public ApproveRejectClass(int orderid, int userid, int totalPrice) {
		super();
		this.orderid = orderid;
		this.userid = userid;
		this.totalPrice = totalPrice;
	}

	public ApproveRejectClass() 
	{
		super();
	}

	public int getOrderid()
	{
		return orderid;
	}

	public void setOrderid(int orderid)
	{
		this.orderid = orderid;
	}

	public int getProductid() 
	{
		return productid;
	}

	public void setProductid(int productid)
	{
		this.productid = productid;
	}

	public int getUserid() 
	{
		return userid;
	}

	public void setUserid(int userid)
	{
		this.userid = userid;
	}

	public int getQuantity() 
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public int getPriceunit()
	{
		return priceunit;
	}

	public void setPriceunit(int priceunit) 
	{
		this.priceunit = priceunit;
	}

	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	} 

}
