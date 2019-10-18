package com.restassured.utils;

import java.util.concurrent.TimeUnit;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestUtilities {
	
	public static String ENDPOINT="";
	// Request
    public static RequestSpecification REQ_SPEC;
    public static RequestSpecBuilder REQ_BLDR;
    // Response
    public static ResponseSpecification RESP_SPEC;
    public static ResponseSpecBuilder RESP_BLDR;

    public static void setEndPoint(String endpoint) {
    	ENDPOINT = endpoint;
    }
    
    public static RequestSpecification getRequestSpec() {
    	REQ_SPEC = new RequestSpecBuilder().
                 setBaseUri("http://api.zippopotam.us").build();
    	return REQ_SPEC;
    }
    
    public static ResponseSpecification getResponseSpec() {
    	RESP_SPEC = new ResponseSpecBuilder().expectStatusCode(200).
    			expectResponseTime(lessThan(3L), TimeUnit.SECONDS).build();
   	return RESP_SPEC;
    }
}
