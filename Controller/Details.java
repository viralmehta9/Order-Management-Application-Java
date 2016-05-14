package com.Quinnox.Controller;

public class Details 
{	
	private int oId;
	private String productName;
	private String supplierName;
	private int price;

	public Details(int oId,String productName,String supplierName,int price)
	{
		this.oId=oId;
		this.productName=productName;
		this.supplierName=supplierName;
		this.price=price;
	}
	public int getoId()
	{
		return oId;
	}
	public void setoId(int oId)
	{
		this.oId = oId;
	}
	@Override
	public String toString()
	{
		return "Details [oId=" + oId + ", productName=" + productName
		+ ", supplierName=" + supplierName + ", price=" + price + "]";
	}
	public String getProductName()
	{
		return productName;
	}
	public void setProductName(String productName) 
	{
		this.productName = productName;
	}
	public String getSupplierName()
	{
		return supplierName;
	}
	public void setSupplierName(String supplierName) 
	{
		this.supplierName = supplierName;
	}
	public int getPrice() 
	{
		return price;
	}
	public void setPrice(int price)
	{
		this.price = price;
	}
}


