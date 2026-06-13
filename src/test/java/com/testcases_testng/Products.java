package com.testcases_testng;

import org.testng.annotations.Test;

import com.constants.ApiEndpoints;
import com.dataproviders.ExcelDataProvider;
import com.payloads.CreateProduct_POJO;
import com.payloads.UpdateProduct_POJO;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class Products extends BaseClass{
	
	// Test Case to Validate Products API
	
	@Test(dataProvider="productTestData",dataProviderClass=ExcelDataProvider.class)
	public void Product(String name,String type,String op,String path,String value) {
		
		logger.info("Starting Products API Test Case");
		
		// Create Product
		
		logger.info("Create Product Started");
		
		CreateProduct_POJO requestBody=new CreateProduct_POJO();
		requestBody.setName(name);
		requestBody.setType(type);
		
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
		
		logger.info("Create Product Completed");
		
		// List Products
		
		logger.info("List of Products Started");
		
		given()
			.spec(requestSpec)
		.when()
			.get(ApiEndpoints.listProducts)
		.then()
			.spec(responseSpec)
			.statusCode(200);
		
		logger.info("List of Products Completed");
		
		// Show Product Details
		
		logger.info("Show Product Details Started");
		
		given()
			.spec(requestSpec)
			.pathParam("id", id)
		.when()
			.get(ApiEndpoints.showProductDetails)
		.then()
			.spec(responseSpec)
			.statusCode(200);
		
		logger.info("Show Product Details Completed");
		
		// Update Product
		
		logger.info("Update Product Started");
		
		UpdateProduct_POJO data=new UpdateProduct_POJO();
		data.setOp(op);
		data.setPath(path);
		data.setValue(value);
		
		List<UpdateProduct_POJO> requestBody2=new ArrayList<>();
		requestBody2.add(data);
		
		given()
			.spec(requestSpec)
			.body(requestBody2)
			.pathParam("id",id)
		.when()
			.patch(ApiEndpoints.updateProductDetails)
		.then()
			.statusCode(204);
		
		logger.info("Update Product Ended");
		
		
	}

}
