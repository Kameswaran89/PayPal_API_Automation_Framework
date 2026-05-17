package com.payloads;

public class ConfirmOrder_Card_POJO {
	
	private String name;
	private String number;
	private String expiry;
	private String security_code;
	private ConfirmOrder_BillingAddress_POJO billing_address;
	
	public String getName() {
		return name;
	}
	
	public String getNumber() {
		return number;
	}
	
	public String getExpiry() {
		return expiry;
	}
	
	public String getSecurity_code() {
		return security_code;
	}
	
	public ConfirmOrder_BillingAddress_POJO getBilling_address() {
		return billing_address;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setNumber(String number) {
		this.number=number;
	}
	
	public void setExpiry(String expiry) {
		this.expiry=expiry;
	}
	
	public void setSecurity_code(String security_code) {
		this.security_code=security_code;
	}
	
	public void setBilling_address(ConfirmOrder_BillingAddress_POJO billing_address) {
		this.billing_address=billing_address;
	}

}
