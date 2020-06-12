package CloudAutomation.AWSConnector;

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

public class EnrollCustomerAWS {
	
	String resp;
	Response response;
	
	ReUsableMethods rum;
	@DataProvider
	public Object[][] getData() throws InterruptedException{
		Object data[][] = ExcelData.getTestData("Sheet2");
		return data;
	}
	
	
	@Test(dataProvider="getData")
	public void  enrollCustomer(String TestCaseName,String cuId,String accountId,String accessKeyId,String accessKey,String region,String orgName,String aisaacTenantID,String caller) throws InterruptedException{
		rum=new ReUsableMethods();
		RestAssured.baseURI="http://localhost:8020/api/v1/aws";
		
	    Reporter.log(TestCaseName);
	     response=given().header("Content-Type","application/json").
	    body(payload.enrollCustomerAWSConnector(cuId, accountId, accessKeyId, accessKey, region, orgName, aisaacTenantID, caller)).
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
	  	     String cuId1=rum.getJsonPath(response, "result.cuId");
	  	     Assert.assertEquals(cuId1,cuId);
	  	     System.out.println(cuId1);
	  	    
	     }
	     
	}
}
