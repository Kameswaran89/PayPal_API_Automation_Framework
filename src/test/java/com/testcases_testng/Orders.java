package com.testcases_testng;

import org.testng.annotations.Test;

import com.constants.ApiEndpoints;
import com.payloads.Order_Amount_POJO;
import com.payloads.UpdateOrder_RequestBody_POJO;
import com.payloads.ConfirmOrder_BillingAddress_POJO;
import com.payloads.ConfirmOrder_Card_POJO;
import com.payloads.ConfirmOrder_PaymentSource_POJO;
import com.payloads.ConfirmOrder_RequestBody_POJO;
import com.payloads.CreateOrder_PurchaseUnits_POJO;
import com.payloads.CreateOrder_RequestBody_POJO;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class Orders extends BaseClass{
	
	@Test
	public void Order() {
		
		String orderID;
		
		// Create Order
		
		Order_Amount_POJO amount=new Order_Amount_POJO();
		amount.setCurrency_code("USD");
		amount.setValue("100");
		
		CreateOrder_PurchaseUnits_POJO purchase=new CreateOrder_PurchaseUnits_POJO();
		purchase.setAmount(amount);
		
		List<CreateOrder_PurchaseUnits_POJO> listdata=new ArrayList<>();
		listdata.add(purchase);
		
		CreateOrder_RequestBody_POJO body=new CreateOrder_RequestBody_POJO();
		body.setIntent("CAPTURE");
		body.setPurchase_units(listdata);
		
		Response res=given()
			.spec(requestSpec)
			.body(body)
		.when()
			.post(ApiEndpoints.createOrder)
		.then()
			.spec(responseSpec)
			.statusCode(201)
			.extract()
			.response();
		
		orderID=res.jsonPath().getString("id");
					
		// Show Order Details
		
		given()
			.spec(requestSpec)
		.when()
			.get(ApiEndpoints.getOrder+orderID)
		.then()
			.spec(responseSpec)
			.statusCode(200);
		
		// Update Order
		
		Order_Amount_POJO value=new Order_Amount_POJO();
		value.setCurrency_code("USD");
		value.setValue("150");
		
		UpdateOrder_RequestBody_POJO data=new UpdateOrder_RequestBody_POJO();
		data.setOp("add");
		data.setPath("/purchase_units/@reference_id=='default'/amount");
		data.setValue(value);
		
		List<UpdateOrder_RequestBody_POJO> requestBody=new ArrayList<>();
		requestBody.add(data);
		
		given()
			.spec(requestSpec)
			.body(requestBody)
		.when()
			.patch(ApiEndpoints.updateOrder+orderID)
		.then()
			.statusCode(204);
		
		// Confirm Order
		
		ConfirmOrder_BillingAddress_POJO billAdd=new ConfirmOrder_BillingAddress_POJO();
		billAdd.setAddress_line_1("123 main street a");
		billAdd.setAddress_line_2("Apt 75");
		billAdd.setAdmin_area_1("CA");
		billAdd.setAdmin_area_2("San Jose");
		billAdd.setPostal_code("95131");
		billAdd.setCountry_code("US");
		
		ConfirmOrder_Card_POJO card=new ConfirmOrder_Card_POJO();
		card.setName("tester");
		card.setNumber("4111111111111111");
		card.setExpiry("2028-04");
		card.setSecurity_code("123");
		card.setBilling_address(billAdd);
		
		ConfirmOrder_PaymentSource_POJO paySource=new ConfirmOrder_PaymentSource_POJO();
		paySource.setCard(card);
		
		ConfirmOrder_RequestBody_POJO requestBody2=new ConfirmOrder_RequestBody_POJO();
		requestBody2.setPayment_source(paySource);
		
		given()
			.spec(requestSpec)
			.body(requestBody2)
			.pathParam("id",orderID)
		.when()
			.post(ApiEndpoints.confirmOrder)
		.then()
			.spec(responseSpec)
			.statusCode(200);
		
		// Authorize Payment For Order
		
//		given()
//			.spec(requestSpec)
//			.pathParam("id",orderID)
//		.when()
//			.post(ApiEndpoints.authorizePayment)
//		.then()
//			.spec(responseSpec)
//			.statusCode(201);
		
		// Capture Payment For Order
		
//		given()
//			.spec(requestSpec)
//			.pathParam("id",orderID)
//		.when()
//			.post(ApiEndpoints.capturePayment)
//		.then()
//			.spec(responseSpec)
//			.statusCode(201);
		
		
	}

}
