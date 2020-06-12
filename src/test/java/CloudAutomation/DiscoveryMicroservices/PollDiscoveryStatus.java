package CloudAutomation.DiscoveryMicroservices;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.Response;
import resources.ExcelData;

public class PollDiscoveryStatus {
	

Response response;

ReUsableMethods rum;
	

	
	@DataProvider
	public Object[][] getData() throws InterruptedException{
		Object data[][] = ExcelData.getTestData("pollDiscoveryStatus");
		return data;
	}
	
	
	@Test(dataProvider="getData")
	public void  startDiscoveryForAzure(String TestCaseName,String discoveryId) throws InterruptedException{
		rum=new ReUsableMethods();
		RestAssured.baseURI="http://localhost:8070/api/v1/discovery";
		
	    Reporter.log(TestCaseName);
	     response=given().header("Content-Type","application/json").when()
	    .get("/pollDiscoveryStatus/"+discoveryId)
	    .then().log().all().
	     extract().response();
	     
	     if(TestCaseName.equals("Checking with valid discovery Id")){
	    	 String status=rum.getJsonPath(response, "status");
	     	 Assert.assertEquals(status, "200");
	     	 String message=rum.getJsonPath(response,"message");
	  	     System.out.println(message);
	  	     Assert.assertEquals(message, "Successfull");
	  	     String resdiscoveryId=rum.getJsonPath(response, "result.discoveryid");
	  	     Assert.assertEquals(discoveryId,resdiscoveryId);
	  	     String resdiscoveryStatus=rum.getJsonPath(response, "result.discoveryStatus");
	  	     System.out.println(resdiscoveryStatus);
	  	   
	  	   
	  	 
	     }
	     
	     else if(TestCaseName.equals("Checking with Invalid discovery Id")){
	    	 String status=rum.getJsonPath(response, "status");
	     	 Assert.assertEquals(status, "400");
	     	 String message=rum.getJsonPath(response,"message");
	  	     System.out.println(message);
	  	     Assert.assertEquals(message, "Invalid Discovery ID");
	    	
	     }
	     
	     if(TestCaseName.equals("Checking without providing the  discovery Id")){
	    	 try{
	    	 String status=rum.getJsonPath(response, "status");
	     	 Assert.assertEquals(status, "404");
	    	 }
	    	 catch(JsonPathException je){
	    		 je.getMessage();
	    	 }
	     }
	     
	} 
	

}
