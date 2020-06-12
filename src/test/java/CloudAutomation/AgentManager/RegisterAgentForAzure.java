package CloudAutomation.AgentManager;

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

public class RegisterAgentForAzure {
	String resp;
	Response response;
	
	ReUsableMethods rum;
	@DataProvider
	public Object[][] getData() throws InterruptedException{
		Object data[][] = ExcelData.getTestData("registerAgentAzure");
		return data;
	}
	
	
	@Test(dataProvider="getData")
	public void  registerAgentAzure(String TestCaseName,String aisaacTenantID,String azureClientId,String azureClientKey,String azureTenantId,String marketPlace,String storageAccountRegion,String caller,String orgName) throws InterruptedException{
		rum=new ReUsableMethods();
		RestAssured.baseURI="http://localhost:8030/api/v1/manager";
		
	    Reporter.log(TestCaseName);
	     response=given().header("Content-Type","application/json").
	    body(payload.registerAgentAzure(aisaacTenantID, azureClientId, azureClientKey, azureTenantId, marketPlace, storageAccountRegion, caller, orgName)).
	    when()
	    .post("/registerAgent")
	    .then().log().all().
	     extract().response();
	     
	    
      if(TestCaseName.equals("Positive Sceanrio with valid data")||
    		  TestCaseName.equals("Checking for Invalid aisaacTenantID")||
    		  TestCaseName.equals("Checking For invalid storageAccountRegion")||
    		  TestCaseName.equals("Checking For invalid orgName")){
    	 String status=rum.getJsonPath(response, "status");
     	 Assert.assertEquals(status, "200");
     	 String message=rum.getJsonPath(response,"message");
  	     System.out.println(message);
  	     Assert.assertEquals(message, "Successfull");
  	     String agentId=rum.getJsonPath(response, "result.agentId");
  	     System.out.println(agentId);
		    
		    
	     }
      
      else if(TestCaseName.equals("Checking for Required field of aisaacTenantID")){
    	  checkingForTheRequiredFields();
    	  String result=rum.getJsonPath(response,"result");
   	     System.out.println(result);
   	     Assert.assertEquals(result, "aisaacTenantID is required if caller is ADR");
      }
      
      else if(TestCaseName.equals("Checking For Required field of azureClientId")
    		  ||TestCaseName.equals("Checking For Required field of azureClientKey")||
    		  TestCaseName.equals("Checking For Required field of azureTenantId")||
    		  TestCaseName.equals("Checking For  invalid  azureClientId")||
    		  TestCaseName.equals("Checking For  invalid azureClientKey")||
    		  TestCaseName.equals("Checking For invalid azureTenantId")){
    	  checkingForTheRequiredFields();
    	  String result=rum.getJsonPath(response,"result");
   	     System.out.println(result);
   	     Assert.assertEquals(result, "Invalid Credentials");
      }
     
      else if(TestCaseName.equals("Checking For Required field of azureClientId")){
    	  checkingForTheRequiredFields();
    	  String result=rum.getJsonPath(response,"result");
   	     System.out.println(result);
   	     Assert.assertEquals(result, "Invalid Credentials");
      }
      
      else if(TestCaseName.equals("Checking For Required field of storageAccountRegion")){
    	  checkingForTheRequiredFields();
    	  String result=rum.getJsonPath(response,"result");
   	     System.out.println(result);
   	     Assert.assertEquals(result, "storageAccountRegion is required");
      }
      
      else if(TestCaseName.equals("Checking For Required field of caller")){
    	  checkingForTheRequiredFields();
    	  String result=rum.getJsonPath(response,"result");
   	     System.out.println(result);
   	     Assert.assertEquals(result, "caller is required");  
      }
      
      else if(TestCaseName.equals("Checking For Required field of orgName")){
    	  checkingForTheRequiredFields();
    	  String result=rum.getJsonPath(response,"result");
   	     System.out.println(result);
   	     Assert.assertEquals(result, "orgName is required");  
      }
      
      else if(TestCaseName.equals("Checking For invalid caller")){
    	  checkingForTheRequiredFields();
    	  String result=rum.getJsonPath(response,"result");
   	     System.out.println(result);
   	     Assert.assertEquals(result, "marketPlace value must be true if caller is marketPlace");  
      }
      
     
	}
	
	public void checkingForTheRequiredFields(){
		 
		 String status=rum.getJsonPath(response, "status");
    	 Assert.assertEquals(status, "300");
    	 String message=rum.getJsonPath(response,"message");
 	     System.out.println(message);
 	     Assert.assertEquals(message, "Failure");
 	    
		 }
}
