package com.payloads;

public class UpdateOrder_RequestBody_POJO {
	
	private String op;
	private String path;
	private Order_Amount_POJO value;
	
	public String getOp() {
		return op;
	}
	
	public String getPath() {
		return path;
	}
	
	public Order_Amount_POJO getValue() {
		return value;
	}
	
	public void setOp(String op) {
		this.op=op;
	}
	
	public void setPath(String path) {
		this.path=path;
	}
	
	public void setValue(Order_Amount_POJO value) {
		this.value=value;
	}

}
