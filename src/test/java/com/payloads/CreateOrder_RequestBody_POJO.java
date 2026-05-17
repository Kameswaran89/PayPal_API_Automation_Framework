package com.payloads;

import java.util.List;

public class CreateOrder_RequestBody_POJO {
	
	private String intent;
	private List<CreateOrder_PurchaseUnits_POJO> purchase_units;
	
	public String getIntent() {
		return intent;
	}
	
	public void setIntent(String intent) {
		this.intent=intent;
	}
	
	public List<CreateOrder_PurchaseUnits_POJO> getPurchase_units() {
		return purchase_units;
	}
	
	public void setPurchase_units(List<CreateOrder_PurchaseUnits_POJO> purchase_units) {
		this.purchase_units=purchase_units;
	}

}
