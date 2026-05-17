package com.payloads;

public class UpdateProduct_POJO {
	
	private String op;
	private String path;
	private String value;
	
	public String getOp() {
		return op;
	}
	
	public String getPath() {
		return path;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setOp(String op) {
		this.op=op;
	}
	
	public void setPath(String path) {
		this.path=path;
	}
	
	public void setValue(String value) {
		this.value=value;
	}

}
