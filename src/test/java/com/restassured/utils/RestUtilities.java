package com.restassured.utils;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;

public class RestUtilities {

	public static String ENDPOINT = "";
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
		REQ_SPEC = new RequestSpecBuilder().setBaseUri("http://api.zippopotam.us").build();
		return REQ_SPEC;
	}

	public static ResponseSpecification getResponseSpec() {
		RESP_SPEC = new ResponseSpecBuilder().expectStatusCode(200).expectResponseTime(lessThan(3L), TimeUnit.SECONDS)
				.build();
		return RESP_SPEC;
	}

	public static RequestSpecification queryParam(RequestSpecification reqSpec, String param, String value) {
		return reqSpec.queryParam(param, value);
	}

	public static RequestSpecification queryParams(RequestSpecification reqSpec, Map<String, String> queryMap) {
		return reqSpec.queryParams(queryMap);
	}

	public static RequestSpecification pathParam(RequestSpecification reqSpec, String param, String value) {
		return reqSpec.queryParam(param, value);
	}

	public static RequestSpecification pathParams(RequestSpecification reqSpec, Map<String, String> queryMap) {
		return reqSpec.queryParams(queryMap);
	}

	public static Response getResponse() {
		return given().get(ENDPOINT);
	}

	public static Response getResponse(RequestSpecification reqSpec, String reqType) {
		REQ_SPEC.spec(reqSpec);
		Response response= null;
		if (reqType.equalsIgnoreCase("get")) {
			response = given().spec(REQ_SPEC).get(ENDPOINT);
		} else if (reqType.equalsIgnoreCase("put")) {
			response = given().spec(REQ_SPEC).put(ENDPOINT);
		} else if (reqType.equalsIgnoreCase("post")) {
			response = given().spec(REQ_SPEC).post(ENDPOINT);
		} else if (reqType.equalsIgnoreCase("delete")) {
			response = given().spec(REQ_SPEC).delete(ENDPOINT);
		} else {
			System.out.println("Type not supported");
		}
		response.then().log().all();
		response.then().spec(RESP_SPEC);
		return response;
	}
	
	public static JsonPath getJsonPath(Response response) {
		String path = response.asString();
		return new JsonPath(path);
	}

	public static XmlPath getXmlPath(Response response) {
		String path = response.asString();
		return new XmlPath(path);
	}
	
	public static RequestSpecification setContentType(ContentType type) {
		REQ_SPEC.contentType(type);
		return REQ_SPEC;
	}
}
