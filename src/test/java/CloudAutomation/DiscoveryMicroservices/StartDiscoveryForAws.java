package CloudAutomation.DiscoveryMicroservices;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import resources.ExcelData;

public class StartDiscoveryForAws {
	String resp;
	Response response;
	
	ReUsableMethods rum;
	
	@DataProvider
	public Object[][] getData() throws InterruptedException{
		Object data[][] = ExcelData.getTestData("startDiscoveryForAws");
		return data;
	}
	
	
	@Test(dataProvider="getData")
	public void  startDiscoveryForAws(String TestCaseName,String cuId,String type,String accountId) throws InterruptedException{
		rum=new ReUsableMethods();
		RestAssured.baseURI="http://localhost:8070/api/v1/discovery";
		
	    Reporter.log(TestCaseName);
	     response=given().header("Content-Type","application/json").
	    body(payload.startDiscoveryForAws(cuId, type, accountId)).
	    when()
	    .post("/startDiscovery")
	    .then().log().all().
	     extract().response();
	     
	     if(TestCaseName.equals("Positive Sceanrio with valid data")){
	    	
	    	 String status=rum.getJsonPath(response, "status");
	     	 Assert.assertEquals(status, "200");
	     	 String message=rum.getJsonPath(response,"message");
	  	     System.out.println(message);
	  	     Assert.assertEquals(message, "Successfull");
	  	     String discoveryId=rum.getJsonPath(response, "result.discoveryId");
	  	     System.out.println(discoveryId);
	  	     String rescuId=rum.getJsonPath(response, "result.cuId");
	  	     Assert.assertEquals(cuId, rescuId);
	    	 
	    	 
	    	 }
	     
	     
	     else if(TestCaseName.equals("Checking For Required field of cuId")){
	    	 
	    	 String status=rum.getJsonPath(response, "status");
	     	 Assert.assertEquals(status, "400");
	     	 String message=rum.getJsonPath(response,"message");
	  	     System.out.println(message);
	  	     Assert.assertEquals(message, "[cuId must not be null]");
	  	     String result=rum.getJsonPath(response, "result");
	  	     Assert.assertEquals(result, "Error");
	     
	     }
	
	     else if(TestCaseName.equals("Checking For Invalid cuId")){
	    	 String status=rum.getJsonPath(response, "status");
	     	 Assert.assertEquals(status, "400");
	    	 
	     }
	     
	     else if(TestCaseName.equals("Checking For Required field of type")
	    		 ||TestCaseName.equals("Checking For Invalid type")
	    		 ||TestCaseName.equals("Checking the type with the value ConsoleLogs")||
	    		 TestCaseName.equals("Checking the type with the value vm")||
	    		 TestCaseName.equals("Checking the type with the value ELB")||
	    		 TestCaseName.equals("Checking the type with the value VPC")){
	    	 
	    	 String status=rum.getJsonPath(response, "status");
	     	 Assert.assertEquals(status, "400");
	     	 String message=rum.getJsonPath(response,"message");
	  	     System.out.println(message);
	  	     Assert.assertEquals(message, "[aws.type is Invalid]");
	  	     String result=rum.getJsonPath(response, "result");
	  	     Assert.assertEquals(result, "Error");
	     }
	     
	     else if(TestCaseName.equals("Checking For Required field of accountId")
	    		 ||TestCaseName.equals("Checking For Invalid  accountId")){
	    	 
	    	 String status=rum.getJsonPath(response, "status");
	     	 Assert.assertEquals(status, "400");
	     	 String message=rum.getJsonPath(response,"message");
	  	     System.out.println(message);
	  	     Assert.assertEquals(message, "[awsDetailsModel[0].accountId is Invalid]");
	  	     String result=rum.getJsonPath(response, "result");
	  	     Assert.assertEquals(result, "Error");
	     }
	    
	   
	 }
	     
	
}
