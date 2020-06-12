package CloudAutomation.LogEnabler;

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

public class EnableLogs {
	
	String resp;
	Response response;
	
	ReUsableMethods rum;
	
	@DataProvider
	public Object[][] getData() throws InterruptedException{
		Object data[][] = ExcelData.getTestData("enableLogs");
		return data;
	}
	
	
	@Test(dataProvider="getData")
	public void  logEnabler(String TestCaseName,String cuId,String assetList) throws InterruptedException{
		rum=new ReUsableMethods();
		RestAssured.baseURI="http://localhost:8090/api/v1/logenabler";
		
	    Reporter.log(TestCaseName);
	     response=given().header("Content-Type","application/json").
	    body(payload.logEnablerEnableLogs(cuId, assetList)).
	    when()
	    .put("/enableLogs")
	    .then().log().all().
	     extract().response();
	      resp=response.toString();
	     
	     
	     if(TestCaseName.equals("Positive flow with valid data to enable logs")){
	    	 
	    	 String status=rum.getJsonPath(response, "status");
	     	 Assert.assertEquals(status, "200");
	     	 String message=rum.getJsonPath(response,"message");
	  	     System.out.println(message);
	  	     Assert.assertEquals(message, "Successfull");
	  	     String jobId=rum.getJsonPath(response, "result.jobId");
	  	     System.out.println(jobId);
	    	 
	    		
	     }
	    	 
	    	 
	     
	     else if(TestCaseName.equals("Checking For Required field of cuId")){
	    	 
	    	 String status=rum.getJsonPath(response, "status");
	     	 Assert.assertEquals(status, "400");
	     	 String message=rum.getJsonPath(response,"message");
	  	     System.out.println(message);
	  	     Assert.assertEquals(message, "[cuId must not be null]");
	    	 
	    }
	     
	     else if(TestCaseName.equals("Checking For Invalid cuId")) {
	    	
	    	 String status=rum.getJsonPath(response, "status");
	     	 Assert.assertEquals(status, "400");
	     	 String message=rum.getJsonPath(response,"message");
	  	     System.out.println(message);
	  	     Assert.assertEquals(message, "Invalid Customer ID/AssetID");
	     
	     }
	     
	     else if(TestCaseName.equals("Checking For Required field of assetList")) {
	    	 
	    	 String status=rum.getJsonPath(response, "status");
	     	 Assert.assertEquals(status, "400");
	     	 String message=rum.getJsonPath(response,"message");
	  	     System.out.println(message);
	  	     Assert.assertEquals(message, "[assetList[0] must not be null]");
	     
	     }
	     
	     else if(TestCaseName.equals("Checking For Invalid assetList")) {
	    	
	    	 String status=rum.getJsonPath(response, "status");
	     	 Assert.assertEquals(status, "400");
	     	 String message=rum.getJsonPath(response,"message");
	  	     System.out.println(message);
	  	     Assert.assertEquals(message, "Invalid Asset ID");
	    	 
	     }
	}

}
