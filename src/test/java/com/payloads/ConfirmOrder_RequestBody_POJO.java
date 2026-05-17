package com.payloads;

public class ConfirmOrder_RequestBody_POJO {
	
	private ConfirmOrder_PaymentSource_POJO payment_source;
	
	public ConfirmOrder_PaymentSource_POJO getPayment_source() {
		return payment_source;
	}
	
	public void setPayment_source(ConfirmOrder_PaymentSource_POJO payment_source) {
		this.payment_source=payment_source;
	}

}
