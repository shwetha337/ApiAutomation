package Practice;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReUsableMethods;
import files.payload;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
// validate if Add Place API is workimg as expected 
		//Add place-> Update Place with New Address -> Get Place to validate if New address is present in response
		
		//given - all input details 
		//when - Submit the API -resource,http method
		//Then - validate the response
		/*RestAssured.baseURI= "http://localhost:8080/nifi-api";
		String response=given().log().all().header("Content-Type","application/json")
		.body(payload.updateStatus()).when().put("/processors/6efc3522-7e43-1ea0-d2cc-dfc445f832bf/run-status")
		.then().assertThat().log().all().statusCode(200).body("id", equalTo("6efc3522-7e43-1ea0-d2cc-dfc445f832bf")).extract().response().asString();
		
		//System.out.println(response);
		JsonPath js=new JsonPath(response); //for parsing Json
		String state=js.getString("component.state");
		System.out.println(state);
		Assert.assertEquals(state, "STOPPED");*/
		
		
		
		
		
		
		
		
		
		/*.then().assertThat().statusCode(200).body("id", equalTo("6efc3522-7e43-1ea0-d2cc-dfc445f832bf")).extract().response().asString();
		
		System.out.println(response);
		JsonPath js=new JsonPath(response); //for parsing Json
		String state=js.getString("state");
	
		System.out.println(state);*/
		
		/*//Update Place
		String newAddress = "Summer Walk, Africa";*/
		
	/*	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").
		when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));*/
		
/*		//Get Place
		
	String getPlaceResponse=	given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id",placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
	JsonPath js1=ReUsableMethods.rawToJson(getPlaceResponse);
	String actualAddress =js1.getString("address");
	System.out.println(actualAddress);
	Assert.assertEquals(actualAddress, "Pacific ocean");*/
	//Cucumber Junit, Testng
	
	
	
	
	
	
	
	
	

		
		
		
		
		
		
		
		
		
		
		
	}

}
