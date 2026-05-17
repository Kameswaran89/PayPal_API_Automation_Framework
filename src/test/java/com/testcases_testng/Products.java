package com.testcases_testng;

import org.testng.annotations.Test;

import com.constants.ApiEndpoints;
import com.payloads.CreateProduct_POJO;
import com.payloads.UpdateProduct_POJO;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class Products extends BaseClass{
	
	@Test
	public void Product() {
		
		// Create Product
		
		CreateProduct_POJO requestBody=new CreateProduct_POJO();
		requestBody.setName("software product");
		requestBody.setType("SERVICE");
		
		Response res=given()
			.spec(requestSpec)
			.body(requestBody)
		.when()
			.post(ApiEndpoints.createProduct)
		.then()
			.spec(responseSpec)
			.statusCode(201)
			.extract()
			.response();
		
		String id=res.jsonPath().getString("id");
		
		// List Products
		
		given()
			.spec(requestSpec)
		.when()
			.get(ApiEndpoints.listProducts)
		.then()
			.spec(responseSpec)
			.statusCode(200);
		
		// Show Product Details
		
		given()
			.spec(requestSpec)
			.pathParam("id", id)
		.when()
			.get(ApiEndpoints.showProductDetails)
		.then()
			.spec(responseSpec)
			.statusCode(200);
		
		// Update Product
		
		UpdateProduct_POJO data=new UpdateProduct_POJO();
		data.setOp("add");
		data.setPath("/description");
		data.setValue("entertainment service");
		
		List<UpdateProduct_POJO> requestBody2=new ArrayList();
		requestBody2.add(data);
		
		given()
			.spec(requestSpec)
			.body(requestBody2)
			.pathParam("id",id)
		.when()
			.patch(ApiEndpoints.updateProductDetails)
		.then()
			.statusCode(204);
		
		
	}

}
