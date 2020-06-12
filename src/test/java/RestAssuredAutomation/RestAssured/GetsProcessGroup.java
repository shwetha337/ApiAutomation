package RestAssuredAutomation.RestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetsProcessGroup {
	@Test
	public void getProcessGroup(){
		//Specify base URI
		  RestAssured.baseURI="http://localhost:8080/nifi-api";
		  
		  //Request object
		  RequestSpecification httpRequest=RestAssured.given();
		  
		  //Response object
		  Response response=httpRequest.request(Method.GET,"/process-groups/9062b4f1-0171-1000-5160-a4c665dbad31");
		  
		  //print response in console window
		  
		  String responseBody=response.getBody().asString();
		  System.out.println("Response Body is:" +responseBody);
		  
		//status code validation
		  int statusCode=response.getStatusCode();
		  System.out.println("Status code is: "+statusCode);
		  Assert.assertEquals(statusCode, 200);
		  
		  //success id validation
		  String id=response.jsonPath().get("id");
		  System.out.println("Id is: "+ id);
		  Assert.assertEquals(id, "9062b4f1-0171-1000-5160-a4c665dbad31");
		  
		//name validation
		  String name=response.jsonPath().get("status.name");
		  System.out.println("Name is: "+ name);
		  Assert.assertEquals(name, "NiFi Flow");
		  
		  String statusLine=response.getStatusLine();
		  System.out.println("Status line is:"+statusLine);
		  Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
	

}
