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

public class GetAssets {
	
    Response response;
    ReUsableMethods rum;
	@DataProvider
	public Object[][] getData() throws InterruptedException{
		Object data[][] = ExcelData.getTestData("getAssets");
		return data;
	}
	
	
	@Test(dataProvider="getData")
	public void  getAsessts(String TestCaseName,String cuId,String discoveryId,String type) throws InterruptedException{
		rum=new ReUsableMethods();
		RestAssured.baseURI="http://localhost:8050/api/v1/asset";
		
	    Reporter.log(TestCaseName);
	     response=given().header("Content-Type","application/json").
	    body(payload.getAssets(cuId, discoveryId, type)).
	    when()
	    .post("/getAssets")
	    .then().log().all().
	     extract().response();
	     
	     if(TestCaseName.equals("Checking with valid discovery Id for aws with type all")||
	    		 TestCaseName.equals("Checking with valid cuId for aws with type all")){
	    	 String status=rum.getJsonPath(response, "status");
	     	 Assert.assertEquals(status, "200");
	     	 String message=rum.getJsonPath(response,"message");
	  	     System.out.println(message);
	  	     Assert.assertEquals(message, "Successfull");
	  	   String hostingProvider=rum.getJsonPath(response,"result.Result[0].hostingProvider");
	  	    System.out.println(hostingProvider);
	  	    Assert.assertEquals(hostingProvider, "AWS");
	     }
	     else if(TestCaseName.equals("Checking with valid discovery Id for aws with type delta")||
	    		 (TestCaseName.equals("Checking with valid cuId for aws with type delta"))){
	    	 String status=rum.getJsonPath(response, "status");
	     	 Assert.assertEquals(status, "200");
	     	 String message=rum.getJsonPath(response,"message");
	  	     System.out.println(message);
	  	     Assert.assertEquals(message, "Successfull");
	     }
	     
	     else if(TestCaseName.equals("Checking with valid discovery Id for azure with type all")||
	    		 TestCaseName.equals("Checking with valid cuId for azure with type all")||
	    		 TestCaseName.equals("Checking with valid cuId for azure with type delta")) {
	    	 String status=rum.getJsonPath(response, "status");
	     	 Assert.assertEquals(status, "200");
	     	 String message=rum.getJsonPath(response,"message");
	  	     System.out.println(message);
	  	     Assert.assertEquals(message, "Successfull");
	  	     String hostingProvider=rum.getJsonPath(response,"result.Result[0].hostingProvider");
	  	    System.out.println(hostingProvider);
	  	    Assert.assertEquals(hostingProvider, "Azure");
	     }
	     
	     else if(TestCaseName.equals("Checking with valid discovery Id for azure with type delta")){
	    	 String status=rum.getJsonPath(response, "status");
	     	 Assert.assertEquals(status, "400");
	     	 String message=rum.getJsonPath(response,"message");
	  	     System.out.println(message);
	  	     Assert.assertEquals(message, "Invalid Discovery Id/Type( Delta not supported. Please try with Type all. )");
	     }
	     
	     else if(TestCaseName.equals("Checking with Invalid discovery Id")){
	    	 String status=rum.getJsonPath(response, "status");
	     	 Assert.assertEquals(status, "400");
	     	 String message=rum.getJsonPath(response,"message");
	  	     System.out.println(message);
	  	     Assert.assertEquals(message, "Invalid Discovery Id");
	     }
	     
	     else if(TestCaseName.equals("Checking with Invalid cuId")){
	    	 String status=rum.getJsonPath(response, "status");
	     	 Assert.assertEquals(status, "400");
	     	String message=rum.getJsonPath(response,"message");
	  	     System.out.println(message);
	  	     Assert.assertEquals(message, "Unauthorized Input");
	     	
	     }
	     
	     else if(TestCaseName.equals("Checking without providing the  discovery Id")||
	    		 TestCaseName.equals("Checking without providing the cuId")){
	    	 String status=rum.getJsonPath(response, "status");
	     	 Assert.assertEquals(status, "400");
	     	 String message=rum.getJsonPath(response,"message");
	  	     System.out.println(message);
	  	     Assert.assertEquals(message, "cuId/discoveryId must not be null");
	     }
	     
	    else if(TestCaseName.equals("Checking For Required field of type")||
	    		TestCaseName.equals("Checking For Invalid type")){
	    	String status=rum.getJsonPath(response, "status");
	     	 Assert.assertEquals(status, "400");
	     	 String message=rum.getJsonPath(response,"message");
	  	     System.out.println(message);
	  	     Assert.assertEquals(message, "[type is Invalid]");
	    }
	}
}
