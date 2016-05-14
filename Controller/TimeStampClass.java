package com.Quinnox.Controller;
import java.util.Date;

public class TimeStampClass 
{

	int orderId;
	int userId;
	String placedTimestamp;
	int managerId;
	String managerStatus;
	String managerTimestamp;
	int supplierId;
	String supplierStatus;
	String supplierTimestamp;
	int qceId;
	String qceStatus;
	String qceTimestamp;


	public TimeStampClass(int orderId, int userId, String placedTimestamp,
			int managerId, String managerStatus, String managerTimestamp,
			int supplierId, String supplierStatus, String supplierTimestamp,
			int qceId, String qceStatus, String qceTimestamp) 
	{
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.placedTimestamp = placedTimestamp;
		this.managerId = managerId;
		this.managerStatus = managerStatus;
		this.managerTimestamp = managerTimestamp;
		this.supplierId = supplierId;
		this.supplierStatus = supplierStatus;
		this.supplierTimestamp = supplierTimestamp;
		this.qceId = qceId;
		this.qceStatus = qceStatus;
		this.qceTimestamp = qceTimestamp;
	}


	public TimeStampClass() 
	{
		super();
	}

	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getPlacedTimestamp() {
		return placedTimestamp;
	}


	public void setPlacedTimestamp(String placedTimestamp) {
		this.placedTimestamp = placedTimestamp;
	}


	public int getManagerId() {
		return managerId;
	}


	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}


	public String getManagerStatus() {
		return managerStatus;
	}


	public void setManagerStatus(String managerStatus) {
		this.managerStatus = managerStatus;
	}


	public String getManagerTimestamp() {
		return managerTimestamp;
	}


	public void setManagerTimestamp(String managerTimestamp) {
		this.managerTimestamp = managerTimestamp;
	}


	public int getSupplierId() {
		return supplierId;
	}


	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}


	public String getSupplierStatus() {
		return supplierStatus;
	}


	public void setSupplierStatus(String supplierStatus) {
		this.supplierStatus = supplierStatus;
	}


	public String getSupplierTimestamp() {
		return supplierTimestamp;
	}


	public void setSupplierTimestamp(String supplierTimestamp) {
		this.supplierTimestamp = supplierTimestamp;
	}


	public int getQceId() {
		return qceId;
	}


	public void setQceId(int qceId) {
		this.qceId = qceId;
	}


	public String getQceStatus() {
		return qceStatus;
	}


	public void setQceStatus(String qceStatus) {
		this.qceStatus = qceStatus;
	}


	public String getQceTimestamp() {
		return qceTimestamp;
	}


	public void setQceTimestamp(String qceTimestamp) {
		this.qceTimestamp = qceTimestamp;
	}


	public static Date getTimeStamp()
	{
		Date date = new Date();
		return (date);
	}
}

