package com.utilities;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import com.constants.ApiEndpoints;

public class GenerateToken {
	
	public static String generateToken() {
		
		// Class to Generate New Token For Authorization
		
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
			
		String token=res.jsonPath().getString("access_token");
		
		return token;
		
		
	}

}
