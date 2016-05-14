package com.Quinnox.Controller;

public class Cart
{

	private String pname;
	private String sname;
	private int quantity;
	private int price;

	public Cart(String pname, String sname, int quantity, int price) {
		super();
		this.pname = pname;
		this.sname = sname;
		this.quantity = quantity;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Cart [pname=" + pname + ", sname=" + sname + ", quantity="
		+ quantity + ", price=" + price + "]";
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
