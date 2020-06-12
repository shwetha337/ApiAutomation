package CloudAutomation.LogEnabler;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.Response;
import resources.ExcelData;

public class GetJobStatus {
	Response response;

	ReUsableMethods rum;
		

		
		@DataProvider
		public Object[][] getData() throws InterruptedException{
			Object data[][] = ExcelData.getTestData("getJobStatus");
			return data;
		}
		
		
		@Test(dataProvider="getData")
		public void  getJobStatus(String TestCaseName,String jobId) throws InterruptedException{
			rum=new ReUsableMethods();
			RestAssured.baseURI="http://localhost:8090/api/v1/logenabler";
			
		    Reporter.log(TestCaseName);
		     response=given().header("Content-Type","application/json").when()
		    		 .queryParam("jobId",jobId)
		    .get("/getJobStatus")
		    .then().log().all().
		     extract().response();
		     
		     if(TestCaseName.equals("Checking with valid jobId")){
		    	 String status=rum.getJsonPath(response, "status");
		     	 Assert.assertEquals(status, "200");
		     	 String message=rum.getJsonPath(response,"message");
		  	     System.out.println(message);
		  	     Assert.assertEquals(message, "Successfull");
		  	     String jobStatus=rum.getJsonPath(response, "result.jobStatus");
		  	     System.out.println(jobStatus);
		  	     
		  	   
		  	   
		  	 
		     }
		     
		     else if(TestCaseName.equals("Checking with Invalid jobId")){
		    	 String status=rum.getJsonPath(response, "status");
		     	 Assert.assertEquals(status, "400");
		     	 String message=rum.getJsonPath(response,"message");
		  	     System.out.println(message);
		  	     Assert.assertEquals(message, "Invalid Job ID");
		    	
		     }
		     
		     if(TestCaseName.equals("Checking without providing the  jobId")){
		    	 try{
		    	 String status=rum.getJsonPath(response, "status");
		     	 Assert.assertEquals(status, "400");
		     	String details=rum.getJsonPath(response,"details");
		  	     System.out.println(details);
		  	     Assert.assertEquals(details, "getLogEnableStatus.jobId: must not be null");
		     	
		    	 }
		    	 catch(JsonPathException je){
		    		 je.getMessage();
		    	 }
		     }
		}


}
