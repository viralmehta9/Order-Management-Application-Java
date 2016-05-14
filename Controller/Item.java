package com.Quinnox.Controller;

public class Item //implements java.io.Serializable

{   
	private int pid;
	private String productName;
	private String supplierName;
	private int quantity ;
	private int unitPrice;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Item()
	{
	}

	public Item( int pid,String productName,String supplierName,
			int quantity, int unitPrice)
	{

		this.pid=pid;
		this.productName=productName;
		this.supplierName=supplierName;
		this.quantity= quantity;
		this.unitPrice=unitPrice;
	}

	@Override
	public String toString() {
		return "Item [pid=" + pid + ", productName=" + productName
		              + ", supplierName=" + supplierName + ", quantity="
		              + quantity + ", unitPrice=" + unitPrice + "]";
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getPid() {
		return pid;
	}

}


