package RestAssuredAutomation.RestAssured;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.xml.xsom.impl.Ref.ContentType;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.ExcelData;

public class UpdateStateStatus {
	
	ExcelData ed=new ExcelData();
	
	  @DataProvider
		public Object[][] getData() throws InterruptedException{
			Object data[][] = ExcelData.getTestData("Sheet1");
			return data;
		}
	  
	  @Test(dataProvider="getData")
		public void  updateRunState(String version,String state,String id) throws InterruptedException{
		 ResponseSpecification respSpec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(io.restassured.http.ContentType.JSON).build();
		 RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("http://localhost:8080/nifi-api").addHeader("Content-Type","application/json").build();
			/*RestAssured.baseURI="http://localhost:8080/nifi-api";
		    String resp=given().header("Content-Type","application/json").*/
		    String resp=RestAssured.given(reqSpec).body(files.payload.updateStatus(version,state)).
		    when()
		    .put("/processors/"+id+"/run-status").
		    
		    then().spec(respSpec).
		    extract().response().asString();
		    System.out.println(resp);
		    JsonPath js=new JsonPath(resp);
		  
		    String stateValue=js.get("component.state");
		    System.out.println(stateValue);
		    Assert.assertEquals(stateValue, state);
		    
		
		}
  
	  
}
