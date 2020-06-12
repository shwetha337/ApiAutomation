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

public class GetCustomerSubscriptions {
	
	String resp;
	Response response;
	
	ReUsableMethods rum;
	
	@DataProvider
	public Object[][] getData() throws InterruptedException{
		Object data[][] = ExcelData.getTestData("getCustSubscriptions");
		return data;
	}
	
	
	@Test(dataProvider="getData")
	public void  enrollCustomerAzure(String TestCaseName,String orgName,String email,String aisaacTenantID) throws InterruptedException{
		rum=new ReUsableMethods();
		RestAssured.baseURI="http://localhost:8050/api/v1/asset";
		
	    Reporter.log(TestCaseName);
	     response=given().header("Content-Type","application/json").
	    body(payload.getCustomerSubscriptions(orgName, email, aisaacTenantID)).
	    when()
	    .post("/GetCustomerSubscriptions")
	    .then().log().all().
	     extract().response();
	     
	     if(TestCaseName.equals("Get Customer Subscriptions For Aws With Valid Organzation Name")
	    		 ||TestCaseName.equals("Get Subscription For AWS With InValid aisaacTenantID")){
	    	 verifyingtheresponse();
	    	 String resOrgName=rum.getJsonPath(response, "result.orgName");
	  	     System.out.println(orgName);
	  	     Assert.assertEquals(orgName, resOrgName);
	  	     String resEmail=rum.getJsonPath(response, "result.email");
	  	     System.out.println(email + " "+ resEmail);
	  	     Assert.assertEquals(email, resEmail);
	     }
	     
	     else if(TestCaseName.equals("Get Subscription For Azure With Valid Organisation Name")
	    		 ||TestCaseName.equals("Get Subscription For Azure  With InValid aisaacTenantID")){
	    	 verifyingtheresponse();
	    	 String resOrgName=rum.getJsonPath(response, "result.orgName");
	  	     System.out.println(orgName);
	  	     Assert.assertEquals(orgName, resOrgName);
	  	     String resEmail=rum.getJsonPath(response, "result.email");
	  	     System.out.println(email + " "+ resEmail);
	  	     Assert.assertEquals(email, resEmail);
	  	    String services=rum.getJsonPath(response, "result.azure[0].subscribedServices[0]");
	  	    Assert.assertEquals(services, "Azure");
	    	 
	    	 
	     }
	     else if(TestCaseName.equals("Get Subscription For Aws  With InValid email")||
	    		 TestCaseName.equals("Get Subscription For Azure  With InValid email")||
	    		 TestCaseName.equals("Get Customer Subscriptions For Aws or Azure for Required field of OrgName")
	    		 ||TestCaseName.equals("Get Customer Subscriptions For Aws or Azure for Required field of OrgName")){
	    	 String status=rum.getJsonPath(response, "status");
	    	 Assert.assertEquals(status, "400");
	    	 String message=rum.getJsonPath(response,"message");
	 	     System.out.println(message);
	 	     Assert.assertEquals(message, "Invalid Organisation Name / Email");
	 	     
	    	 
	     }
	     

}
	public void verifyingtheresponse(){
 		String status=rum.getJsonPath(response, "status");
     	 Assert.assertEquals(status, "200");
     	 String message=rum.getJsonPath(response,"message");
  	     System.out.println(message);
  	     Assert.assertEquals(message, "Successfull");
  	     
 	}

}