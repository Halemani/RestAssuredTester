package com.restassured.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

import com.restassured.constants.EndPoints;
import com.restassured.utils.RestUtilities;
import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ValidatePlaceName2 {
	
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;

	@BeforeClass
	public void setUp() {
		requestSpec = RestUtilities.getRequestSpec();
		responseSpec = RestUtilities.getResponseSpec();
	}

	@Test
	public void karnatakaPlaceName2() {
		given()
				.spec(requestSpec)
		.when()
				.get(EndPoints.IN560001)
		.then()
				.log().all()
				.spec(responseSpec)
				.body("places[2].'place name'", equalTo("Legalators Home"));
	}
	
}
