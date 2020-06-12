package CloudAutomation.AgentManager;

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

public class HeartBeat {
	Response response;

	ReUsableMethods rum;
		

		
		@DataProvider
		public Object[][] getData() throws InterruptedException{
			Object data[][] = ExcelData.getTestData("heartBeat");
			return data;
		}
		
		
		@Test(dataProvider="getData")
		public void  getJobStatus(String TestCaseName,String agentId) throws InterruptedException{
			rum=new ReUsableMethods();
			RestAssured.baseURI="http://localhost:8030/api/v1/manager";
			
		    Reporter.log(TestCaseName);
		     response=given().header("Content-Type","application/json").when()
		    		 .get("/heartbeat/"+agentId)
		    .then().log().all().
		     extract().response();
		     
		     if(TestCaseName.equals("Checking with valid agentId")){
		    	 String status=rum.getJsonPath(response, "status");
		     	 Assert.assertEquals(status, "200");
		     	 String message=rum.getJsonPath(response,"message");
		  	     System.out.println(message);
		  	     Assert.assertEquals(message, "Successfull");
		  	     String resagentId=rum.getJsonPath(response, "result.agentId");
		  	     System.out.println(resagentId);
		  	     
		  	   }
		     
		     else if(TestCaseName.equals("Checking with Invalid agentId")){
		    	 String status=rum.getJsonPath(response, "status");
		     	 Assert.assertEquals(status, "300");
		     	 String message=rum.getJsonPath(response,"message");
		  	     System.out.println(message);
		  	     Assert.assertEquals(message, "Invalid Agent Id");
		    	
		     }
		     
		     if(TestCaseName.equals("Checking without providing the  agentId")){
		    	 try{
		    	 String status=rum.getJsonPath(response, "status");
		     	 Assert.assertEquals(status, "404");
		     	String error=rum.getJsonPath(response,"error");
		  	     System.out.println(error);
		  	     Assert.assertEquals(error, "Not Found");
		     	
		    	 }
		    	 catch(JsonPathException je){
		    		 je.getMessage();
		    	 }
		     }
		}
}
