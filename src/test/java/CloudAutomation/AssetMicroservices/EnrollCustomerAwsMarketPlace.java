package CloudAutomation.AssetMicroservices;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import resources.ExcelData;
import static io.restassured.RestAssured.given;

public class EnrollCustomerAwsMarketPlace {
	
		
		String resp;
		Response response;
		
		ReUsableMethods rum;
		@DataProvider
		public Object[][] getData() throws InterruptedException{
			Object data[][] = ExcelData.getTestData("aws");
			return data;
		}
		
		
		@Test(dataProvider="getData")
		public void  enrollCustomer(String TestCaseName,String caller,String orgName,String aisaacTenantID,String accountId,String accessKeyId,String accessKey,String region) throws InterruptedException{
			rum=new ReUsableMethods();
			RestAssured.baseURI="http://localhost:8050/api/v1/asset";
			
		    Reporter.log(TestCaseName);
		     response=given().header("Content-Type","application/json").
		    body(payload.enrollCustomerAws(caller, orgName, aisaacTenantID,  accountId, accessKeyId, accessKey, region)).
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
		    
		    else if(TestCaseName.equals("Checking For Required field of accountId")){
		        checkingForTheRequiredFields();
			    String result=rum.getJsonPath(response,"result");
			    System.out.println(result);
			    Assert.assertEquals(result, "accountId is required");
			    
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
		    
		    else if(TestCaseName.equals("Checking For Required field of region")){
		        checkingForTheRequiredFields();
			    String result=rum.getJsonPath(response,"result");
			    System.out.println(result);
			    Assert.assertEquals(result,"region is required");
			    
		    }
		   
		   
		    
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
		    
		    else if(TestCaseName.equals("Checking For invalid accessKeyId") ||
		    		TestCaseName.equals("Checking For invalid accessKey" )){
			       
		    	  
		    	    String status=rum.getJsonPath(response,"status");
				    Assert.assertEquals(status, "200");
				    String result=rum.getJsonPath(response,"result");
				   System.out.println(result);
				    Assert.assertEquals(result, "null");
			    
		    }
		    
		    else if(TestCaseName.equals("Checking For invalid  AccountId")){
			       
		    	   
		    	   String status=rum.getJsonPath(response,"status");
				   Assert.assertEquals(status, "200");
				   String result=rum.getJsonPath(response,"result");
				   System.out.println(result);
				   Assert.assertEquals(result, "null");
			    
		    }
		    
		    else if(TestCaseName.equals("Checking For invalid region")){
			       
		    	   
		    	   checkingForTheRequiredFields();
				   String result=rum.getJsonPath(response,"result");
				   System.out.println(result);
				   Assert.assertEquals(result, "Invalid region");
			    
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



