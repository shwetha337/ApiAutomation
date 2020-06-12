package Practice;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ExcelData;
import static io.restassured.RestAssured.*;

public class EnrollCustomerForAWS {
	
	String resp;
	Response response;
	ReUsableMethods rum;
	  /*@DataProvider
		public Object[][] getData(){
			Object data[][] = ExcelData.getTestData("aws");
			return data;
		}*/
	  
	 /* @Test(dataProvider="getData")
		public void  enrollCustomer(String orgName,String accountId,String accessKeyId,String accessKey) throws InterruptedException{
		  RestAssured.baseURI="http://localhost:8050/api/v1/asset";
		    String resp=given().header("Content-Type","application/json").
		    body(payload.enrollCustomerAws(orgName, accountId, accessKeyId, accessKey)).
		    when()
		    .post("/enrollCustomer")
		    .then().log().all().assertThat().statusCode(200).
		    extract().response().asString();
		    
		    JsonPath value=ReUsableMethods.rawToJson(resp);
		    String message=value.get("message");
		    System.out.println(message);
		    Assert.assertEquals(message, "Successfully Created");
		    
		    
	  }*/
	  
	 @DataProvider
		public Object[][] getDataOrgName() throws InterruptedException{
			Object data[][] = ExcelData.getTestData("Sheet1");
			return data;
		}
	 
	 
	  @Test(dataProvider="getDataOrgName")
		public void  enrollTheCustomerForAws(String TestCaseName,String caller,String orgName,String aisaacTenantID,String marketPlace,String accountId,String accessKeyId,String accessKey,String region) throws InterruptedException{
		  rum=new ReUsableMethods();
		  
		  RestAssured.baseURI="http://localhost:8050/api/v1/asset";
		    Response response=given().header("Content-Type","application/json").
		    body(payload.checkingForMarket(caller, orgName, aisaacTenantID, marketPlace, accountId, accessKeyId, accessKey, region)).
		    when()
		    .post("/enrollCustomer")
		    .then().log().all().assertThat().extract().response();
		    System.out.println(resp);
		    
		   
		    
		    if(TestCaseName=="Positive Sceanrio with valid data"){
		    	
			    String status=rum.getJsonPath(response,"status");
			    Assert.assertEquals(status, 201);
			    String result=rum.getJsonPath(response,"message");
			    System.out.println(result);
			    Assert.assertEquals(result, "Successfully Created");
			    String cuId=rum.getJsonPath(response,"result.cuId");
			    System.out.println("Customer Id is "+cuId);
			    
			    
		    }
		    
		    /*else  if(TestCaseName=="Checking For  invalid  marketPlace"){
		        checkingForTheRequiredFields();
			    String result=rum.getJsonPath(response,"result");
			    System.out.println(result);
			    Assert.assertEquals(result,"marketPlace value must be false if caller is ADR");
			    
		    }
		    else if(TestCaseName=="Duplicate OrgName"){
				  
			    
			    String status=rum.getJsonPath(response,"status");
			    Assert.assertEquals(status, 200);
			    String result=rum.getJsonPath(response,"result");
			    System.out.println(result);
			    Assert.assertEquals(result, "Already Exists");
			    }*/
		    
		    /*else if(TestCaseName.equalsIgnoreCase("Checking For Required field of caller")){
		    	checkingForTheRequiredFields();
			    String result=response.get("result");
			    System.out.println(result);
			    Assert.assertEquals(result, "caller is required");
			    
		    }
		    
		    else if(TestCaseName.equalsIgnoreCase("Checking For Required field of orgName")){
		    	checkingForTheRequiredFields();
			    String result=response.get("result");
			    System.out.println(result);
			    Assert.assertEquals(result, "orgName is required");
			    
		    }*/
		    
		    /* else if(TestCaseName.equalsIgnoreCase("Checking For Required field of aisaacTenantID")){
		        checkingForTheRequiredFields();
			    String result=response.get("result");
			    System.out.println(result);
			    Assert.assertEquals(result, "aisaacTenantID is required if caller is ADR");
			    
		    }
		    
		    else if(TestCaseName.equalsIgnoreCase("Checking For Required field of accountId ")){
		        checkingForTheRequiredFields();
			    String result=response.get("result");
			    System.out.println(result);
			    Assert.assertEquals(result, "accountId is required");
			    
		    }
		    else if(TestCaseName.equalsIgnoreCase("Checking For Required field of accessKeyId")){
		        checkingForTheRequiredFields();
			    String result=response.get("result");
			    System.out.println(result);
			    Assert.assertEquals(result,"awsAccessKeyId is required");
			    
		    }
		    
		    else if(TestCaseName.equalsIgnoreCase("Checking For Required field of accessKey")){
		        checkingForTheRequiredFields();
			    String result=response.get("result");
			    System.out.println(result);
			    Assert.assertEquals(result,"awsAccessKey is required");
			    
		    }
		    
		    else if(TestCaseName.equalsIgnoreCase("Checking For Required field of region")){
		        checkingForTheRequiredFields();
			    String result=response.get("result");
			    System.out.println(result);
			    Assert.assertEquals(result,"region is required");
			    
		    }
		    
		    else if(TestCaseName.equalsIgnoreCase("Duplicate OrgName")){
		  
		    response=ReUsableMethods.rawToJson(resp);
		    int status=response.get("status");
		    Assert.assertEquals(status, "200");
		    String result=response.get("message");
		    System.out.println(result);
		    Assert.assertEquals(result, "Already Exists");
		    }
		   
		    else if(TestCaseName.equalsIgnoreCase("Checking For invalid caller")){
		    	
		    	   response=ReUsableMethods.rawToJson(resp);
		    	   int status=response.get("status");
				    Assert.assertEquals(status, "500");
				    String error=response.get("error");
				    System.out.println(error);
				    Assert.assertEquals(error, "Internal Server Error");
		    }
		    else if(TestCaseName.equalsIgnoreCase("Checking For  invalid  marketPlace")){
		        checkingForTheRequiredFields();
			    String result=response.get("result");
			    System.out.println(result);
			    Assert.assertEquals(result,"marketPlace value must be false if caller is ADR");
			    
		    }
		    
		    else if(TestCaseName.equalsIgnoreCase("Checking For invalid accessKeyId") || TestCaseName.equalsIgnoreCase("Checking For invalid accessKey" )){
		       
		    	   response=ReUsableMethods.rawToJson(resp);
		    	   int status=response.get("status");
				    Assert.assertEquals(status, "200");
				    String message=response.get("message");
				    System.out.println(message);
				    Assert.assertEquals(message, "11");
				    String result=response.get("result");
				    System.out.println(result);
				    Assert.assertEquals(result, "null");
			    
		    }
		    
		    else if(TestCaseName.equalsIgnoreCase("Checking For Updating the AccountId")){
			       
		    	   response=ReUsableMethods.rawToJson(resp);
		    	   int status=response.get("status");
				    Assert.assertEquals(status, "200");
				    String message=response.get("message");
				    System.out.println(message);
				    Assert.assertEquals(message, "11");
				    String result=response.get("result");
				    System.out.println(result);
				    Assert.assertEquals(result, "Updated Successfully");
			    
		    }*/
		    
	  
	  }
	  public void checkingForTheRequiredFields(){
			 try{
			 String status=rum.getJsonPath(response, "status");
	    	 Assert.assertEquals(status, "300");
	    	 String message=rum.getJsonPath(response,"message");
	 	     System.out.println(message);
	 	     Assert.assertEquals(message, "Failure");
			 }
			 catch(NullPointerException ec){
					
					ec.getMessage();
						
						
					}
			
	          }
	   
}
