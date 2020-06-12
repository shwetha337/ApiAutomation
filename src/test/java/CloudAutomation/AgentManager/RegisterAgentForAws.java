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

public class RegisterAgentForAws {
	String resp;
	Response response;
	
	ReUsableMethods rum;
	@DataProvider
	public Object[][] getData() throws InterruptedException{
		Object data[][] = ExcelData.getTestData("registerAgentAws");
		return data;
	}
	
	
	@Test(dataProvider="getData")
	public void  registerAgentAws(String TestCaseName,String aisaacTenantID,String accessKey,String accessKeyId,String accountId,String	marketPlace,String region,String caller,String orgName) throws InterruptedException{
		rum=new ReUsableMethods();
		RestAssured.baseURI="http://localhost:8030/api/v1/manager";
		
	    Reporter.log(TestCaseName);
	     response=given().header("Content-Type","application/json").
	    body(payload.registerAgentAws(aisaacTenantID, accessKey, accessKeyId, accountId, marketPlace, region, caller, orgName)).
	    when()
	    .post("/registerAgent")
	    .then().log().all().
	     extract().response();
	     
	    
      if(TestCaseName.equals("Positive Sceanrio with valid data")||
    		  TestCaseName.equals("Checking for Invalid aisaacTenantID")||
    		  TestCaseName.equals("Checking For invalid accountId")) {
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
      
      else if(TestCaseName.equals("Checking For Required field of accessKeyId")){
	        checkingForTheRequiredFields();
		    String result=rum.getJsonPath(response,"result");
		    System.out.println(result);
		    Assert.assertEquals(result,"awsAccessKeyId is required");
		    
	    }
      else if(TestCaseName.equals("Checking For Required field of accessKey")){
	        checkingForTheRequiredFields();
		    String result=rum.getJsonPath(response,"result");
		    System.out.println(result);
		    Assert.assertEquals(result,"awsAccessKey is required");
		    
	    }
      else if(TestCaseName.equals("Checking For Required field of accountId")){
	        checkingForTheRequiredFields();
		    String result=rum.getJsonPath(response,"result");
		    System.out.println(result);
		    Assert.assertEquals(result, "accountId is required");
		    
	    }
      else if(TestCaseName.equals("Checking For Required field of region")){
	        checkingForTheRequiredFields();
		    String result=rum.getJsonPath(response,"result");
		    System.out.println(result);
		    Assert.assertEquals(result,"region is required");
		    
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
      
	   else if(TestCaseName.equals("Checking For  invalid  accessKeyId")||
			   TestCaseName.equals("Checking For  invalid  accessKey")||
			   TestCaseName.equals("Checking For invalid caller")){
		     String status=rum.getJsonPath(response, "status");
	     	 Assert.assertEquals(status, "300");
	     	 String message=rum.getJsonPath(response,"message");
	  	     System.out.println(message);
	  	     Assert.assertEquals(message, "Failed to register agent");
		   
	   }
      
	   else if(TestCaseName.equals("Checking For invalid region")){
		   checkingForTheRequiredFields();
		   String result=rum.getJsonPath(response,"result");
		    System.out.println(result);
		    Assert.assertEquals(result, "Invalid region");
		   
	   }
      
	   else if(TestCaseName.equals("Checking For invalid orgName")){
		   checkingForTheRequiredFields();
		   String cuId=rum.getJsonPath(response,"result.cuId");
	  	     System.out.println(cuId);
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
