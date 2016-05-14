package com.Quinnox.Controller;

public class SupplierClass 
{

	private int userId;
	private String supplierName;
	public SupplierClass(int userId, String supplierName)
	{
		super();
		this.userId = userId;
		this.supplierName = supplierName;
	}
	public int getUserId()
	{
		return userId;
	}
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	public String getSupplierName()
	{
		return supplierName;
	}
	public void setSupplierName(String supplierName)
	{
		this.supplierName = supplierName;
	}
}

