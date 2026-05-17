package com.testcases_testng;

import org.testng.annotations.BeforeSuite;

import com.utilities.ConfigReader;
import com.utilities.GenerateToken;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseClass {
	
	// Base Class to set Base URI, Content-Type, Authentication and Response 
	
	protected static RequestSpecification requestSpec;
	protected static ResponseSpecification responseSpec;
	
	@BeforeSuite
	public void requestSpecification() {
		
		requestSpec=new RequestSpecBuilder()
				.setBaseUri(ConfigReader.getBaseURI())
				.setContentType(ContentType.JSON)
				.addHeader("Authorization","Bearer "+GenerateToken.generateToken())
				.build();	
		
		responseSpec=new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.build();
	}
	
	

}
