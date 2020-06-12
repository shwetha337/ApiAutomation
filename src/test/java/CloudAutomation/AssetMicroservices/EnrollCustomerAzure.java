package CloudAutomation.AssetMicroservices;

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

public class EnrollCustomerAzure {
	
	String resp;
	Response response;
	
	ReUsableMethods rum;
	@DataProvider
	public Object[][] getData() throws InterruptedException{
		Object data[][] = ExcelData.getTestData("azure");
		return data;
	}
	
	
	@Test(dataProvider="getData")
	public void  enrollCustomerAzure(String TestCaseName,String caller,String orgName,String azureTenantId,String azureClientId,String azureClientKey,String storageAccountRegion,String marketPlace,String aisaacTenantID) throws InterruptedException{
		rum=new ReUsableMethods();
		RestAssured.baseURI="http://localhost:8050/api/v1/asset";
		
	    Reporter.log(TestCaseName);
	     response=given().header("Content-Type","application/json").
	    body(payload.enrollCustomerAzure(caller, orgName, azureTenantId, azureClientId, azureClientKey, storageAccountRegion, marketPlace, aisaacTenantID)).
	    when()
	    .post("/enrollCustomer")
	    .then().log().all().
	     extract().response();
	     
	    
      if(TestCaseName.equals("Positive Sceanrio with valid data")) {
    	 String status=rum.getJsonPath(response, "status");
     	 Assert.assertEquals(status, "201");
     	 String message=rum.getJsonPath(response,"message");
  	     System.out.println(message);
  	     Assert.assertEquals(message, "Successfully Created");
  	     String cuId=rum.getJsonPath(response, "result.cuId");
  	     System.out.println(cuId);
		    
		    
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
	     
	   else if(TestCaseName.equals("Checking For Required field of aisaacTenantID")){
	        checkingForTheRequiredFields();
		    String result=rum.getJsonPath(response,"result");
		    System.out.println(result);
		    Assert.assertEquals(result, "aisaacTenantID is required if caller is ADR");
		    
	    }
	    
	  /*  else if(TestCaseName.equals("Checking For Required field of azureTenantId")){
	        checkingForTheRequiredFields();
		    String result=rum.getJsonPath(response,"result");
		    System.out.println(result);
		    Assert.assertEquals(result, "azureTenantId is required");
		    
	    }
	    else if(TestCaseName.equals("Checking For Required field of azureClientId")){
	        checkingForTheRequiredFields();
		    String result=rum.getJsonPath(response,"result");
		    System.out.println(result);
		    Assert.assertEquals(result,"azureClientId is required");
		    
	    }
	    
	    else if(TestCaseName.equals("Checking For Required field of azureClientKey")){
	        checkingForTheRequiredFields();
		    String result=rum.getJsonPath(response,"result");
		    System.out.println(result);
		    Assert.assertEquals(result,"azureClientKey is required");
		    
	    }
	    
	    else if(TestCaseName.equals("Checking For Required field of storageAccountRegion")){
	        checkingForTheRequiredFields();
		    String result=rum.getJsonPath(response,"result");
		    System.out.println(result);
		    Assert.assertEquals(result,"storageAccountRegion is required");
		    
	    }*/
	   
	   
	    
	     if(TestCaseName.equals("Duplicate OrgName")){
			  
	    
		    String status=rum.getJsonPath(response,"status");
		    Assert.assertEquals(status, "200");
		    String result=rum.getJsonPath(response,"result");
		    System.out.println(result);
		    Assert.assertEquals(result, "Already Exists");
		    }
	    
	    else if(TestCaseName.equals("Checking For invalid caller")){
	    	
	    	   
	    	   String status=rum.getJsonPath(response,"status");
			   Assert.assertEquals(status, "500");
			   String error=rum.getJsonPath(response,"error");
			   System.out.println(error);
			   Assert.assertEquals(error, "Internal Server Error");
	    }
	     
	    else  if(TestCaseName=="Checking For  invalid  marketPlace"){
	        checkingForTheRequiredFields();
		    String result=rum.getJsonPath(response,"result");
		    System.out.println(result);
		    Assert.assertEquals(result,"marketPlace value must be false if caller is ADR");
		    
	    }
	    
	    else if(TestCaseName.equals("Checking For invalid azureTenantId") || 
	    		TestCaseName.equals("Checking For invalid  azureClientKey " ) || 
	    		TestCaseName.equals("Checking For invalid azureClientId")|| 
	    		TestCaseName.equals("Checking For Required field of azureTenantId")||
	    		TestCaseName.equals("Checking For Required field of azureClientId")||
	    		TestCaseName.equals("Checking For Required field of azureClientKey")||
	    		TestCaseName.equals("Checking For invalid storageAccountRegion")
	    		){
		       invalidCredentials();
		    
	         }
	     
	    else if(TestCaseName.equals("Checking For Required field of storageAccountRegion")){
	    	
	    	String status=rum.getJsonPath(response, "status");
	    	 Assert.assertEquals(status, "300");
	    	 String message=rum.getJsonPath(response,"message");
	 	     //System.out.println(message+"Checking For invalid storageAccountRegion");
	 	     Assert.assertEquals(message, "Failure");
	 	    String result=rum.getJsonPath(response,"result");
			   System.out.println(result);
			   Assert.assertEquals(result, "storageAccountRegion is required");
	    	
	      }
	    else if(TestCaseName.equals("Checking For invalid storageAccountRegion")){
	    	
	    	String status=rum.getJsonPath(response, "status");
	    	 Assert.assertEquals(status, "300");
	    	 String message=rum.getJsonPath(response,"message");
	 	     System.out.println(message);
	 	     Assert.assertEquals(message, "Failure");
	 	    String result=rum.getJsonPath(response,"result");
			   System.out.println(result);
			   Assert.assertEquals(result, "Invalid Credentials");
	    }
	} 
	    
	    
	
	 public void checkingForTheRequiredFields(){
		 
		 String status=rum.getJsonPath(response, "status");
    	 Assert.assertEquals(status, "300");
    	 String message=rum.getJsonPath(response,"message");
 	     System.out.println(message);
 	     Assert.assertEquals(message, "Failure");
 	    
		
          }
	
	public void invalidCredentials(){
		 String status=rum.getJsonPath(response, "status");
    	 Assert.assertEquals(status, "300");
    	 String message=rum.getJsonPath(response,"message");
 	     System.out.println(message);
 	     Assert.assertEquals(message, "Failure");
 	    String result=rum.getJsonPath(response,"result");
		   System.out.println(result);
		   Assert.assertEquals(result, "Invalid Credentials");
		
	    
	}
}
