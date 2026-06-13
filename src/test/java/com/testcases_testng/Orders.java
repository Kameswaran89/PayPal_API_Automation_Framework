package com.testcases_testng;

import org.testng.annotations.Test;

import com.constants.ApiEndpoints;
import com.dataproviders.ExcelDataProvider;
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
	
	// Test Case to Validate Orders API
	
	@Test(dataProvider="orderTestData",dataProviderClass=ExcelDataProvider.class)
	public void Order(String crtCurrCode,String crtVal,String intent,String updCurrCode,String updVal,String updOp,String updPath,
					String confAddLn1,String confAddLn2,String confAdmAr1,String confAdmAr2,String confPostCod,String confCountCod,
					String confName,String confNum,String confExp,String confSecCode) {
		
		String orderID;
		
		logger.info("Starting Orders API Test Case");
		
		// Create Order
		
		logger.info("Create Order Started");
		
		Order_Amount_POJO amount=new Order_Amount_POJO();
		amount.setCurrency_code(crtCurrCode);
		amount.setValue(crtVal);
		
		CreateOrder_PurchaseUnits_POJO purchase=new CreateOrder_PurchaseUnits_POJO();
		purchase.setAmount(amount);
		
		List<CreateOrder_PurchaseUnits_POJO> listdata=new ArrayList<>();
		listdata.add(purchase);
		
		CreateOrder_RequestBody_POJO body=new CreateOrder_RequestBody_POJO();
		body.setIntent(intent);
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
		
		logger.info("Create Order Completed");
					
		// Show Order Details
		
		logger.info("Show Order Details Started");
		
		given()
			.spec(requestSpec)
		.when()
			.get(ApiEndpoints.getOrder+orderID)
		.then()
			.spec(responseSpec)
			.statusCode(200);
		
		logger.info("Show Order Details Completed");
		
		// Update Order
		
		logger.info("Order Update Started");
		
		Order_Amount_POJO value=new Order_Amount_POJO();
		value.setCurrency_code(updCurrCode);
		value.setValue(updVal);
		
		UpdateOrder_RequestBody_POJO data=new UpdateOrder_RequestBody_POJO();
		data.setOp(updOp);
		data.setPath(updPath);
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
		
		logger.info("Order Update Completed");
		
		// Confirm Order
		
		logger.info("Order Confirmation Started");
		
		ConfirmOrder_BillingAddress_POJO billAdd=new ConfirmOrder_BillingAddress_POJO();
		billAdd.setAddress_line_1(confAddLn1);
		billAdd.setAddress_line_2(confAddLn2);
		billAdd.setAdmin_area_1(confAdmAr1);
		billAdd.setAdmin_area_2(confAdmAr2);
		billAdd.setPostal_code(confPostCod);
		billAdd.setCountry_code(confCountCod);
		
		ConfirmOrder_Card_POJO card=new ConfirmOrder_Card_POJO();
		card.setName(confName);
		card.setNumber(confNum);
		card.setExpiry(confExp);
		card.setSecurity_code(confSecCode);
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
		
		logger.info("Order Confirmation Completed");
		
		// Authorize Payment For Order
		
//		logger.info("Order Authorization Started");
		
//		given()
//			.spec(requestSpec)
//			.pathParam("id",orderID)
//		.when()
//			.post(ApiEndpoints.authorizePayment)
//		.then()
//			.spec(responseSpec)
//			.statusCode(201);
		
//		logger.info("Order Authorization Completed");
		
		// Capture Payment For Order
		
//		logger.info("Payment Capture for Order Started");
		
//		given()
//			.spec(requestSpec)
//			.pathParam("id",orderID)
//		.when()
//			.post(ApiEndpoints.capturePayment)
//		.then()
//			.spec(responseSpec)
//			.statusCode(201);
		
//		logger.info("Payment Capture for Order Completed");
		
		logger.info("End of Orders API Test Case");
		
		
	}

}
