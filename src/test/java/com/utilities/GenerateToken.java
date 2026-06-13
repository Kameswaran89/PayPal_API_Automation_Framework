package com.utilities;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.constants.ApiEndpoints;

public class GenerateToken {
	
	private static final Logger logger=LogManager.getLogger(GenerateToken.class);
	
	public static String generateToken() {
		
		// Class to Generate New Token For Authorization
		
		logger.info("Token Generation Started");
		
		Response res= given()
							.header("Content-Type","application/x-www-form-urlencoded")
							.auth().preemptive().basic(ConfigReader.getUsername(),ConfigReader.getPassword())
							.formParam("grant_type", "client_credentials")
							.log().all()
					  .when()
					  		.post(ConfigReader.getBaseURI()+ApiEndpoints.token)
					  .then()
					  		.statusCode(200)
					  		.extract()
					  		.response();
		
		logger.info("Token Generated Successfully");
			
		String token=res.jsonPath().getString("access_token");
		
		return token;
		
		
	}

}
