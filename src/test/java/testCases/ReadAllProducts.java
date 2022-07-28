package testCases;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*; 


public class ReadAllProducts {

	
   /*
	* given: all input details(baseURI,Headers,Playload/body,QueryParameters,Authorization)
    *when: submit api request (HTTP method, Endpoint/Resource)
    *Then: validate response (status code,Header,responseTime,Payload/Body)
    *
	* 1.ReadAllProducts
	* HTTP method: GET
	* EndpointURL: https://techfios.com/api-prod/api/product/read.php
	* Authorization:
	* Basic Auth/Bearer Token
	* Header: 
	* "Content-Type" : "application/json; charset=UTF-8"
	* Status code: 200
    */
	
	@Test
	public void readAllProducts() {
		
		Response response =
				
				given()
//				       log all here prints all the things in given
//				       .log().all()
				       .baseUri("https://techfios.com/api-prod/api/product")
				       .header("Content-Type", "application/json; charset=UTF-8")
				       .auth().preemptive().basic("demo@techfios.com","abc123")
				       .relaxedHTTPSValidation().
				when()
				      .get("/read.php").
				then()
//				       log all here prints all things stored in response 
//				      .log().all()
				      .extract().response();
	
		        
		 int actualStatusCode = response.getStatusCode();
	     Assert.assertEquals(actualStatusCode,200);
	     
	     
	     String actualHeader = response.getHeader("Content-Type");
		 Assert.assertEquals(actualHeader,"application/json; charset=UTF-8");
	     
	    long actualresponseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
	    System.out.println("actual response time:"+ actualresponseTime);
	     
	    
	    if (actualresponseTime<=2000) {
	    	System.out.println("Response time is out of range!!!");
	    	
	    }else {
	    	
	    	  System.out.println("Actual Response Time is out of range!!");
	    }
	    
	    
        String actualResponseBody = response.getBody().asString();
		System.out.println("Actual Response Body : " + actualResponseBody);
//		response.prettyPrint();
		
		
		
		JsonPath jp = new JsonPath(actualResponseBody);
		String firstProductId = jp.get("records[0].id");
		System.out.println("First Product Id: "+ firstProductId );
		if(firstProductId != null) {
			System.out.println("First Product Id is not null.");
		} else {
			System.out.println("First Product Id is null!!");
		}
		
		
		
		
		
		
		
		
	}	
}
