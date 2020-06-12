package Practice;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;



public class UpdateRunState {
	
	@Test()
	public void  updateRunState() throws EncryptedDocumentException, IOException{
		
		
		dataDriven d=new dataDriven();
		ArrayList data=d.getData("CheckRunState");
		
		HashMap<String,Object> map=new HashMap();
		HashMap<String,Object> map2=new HashMap();
		System.out.println("data.get(0) : " + data);
		System.out.println("data.get(1) : " + data.get(1));
		map2.put("version",data.get(0));
		map.put("state",data.get(1));
		map.put("revision", map2);
		
		
		
		RestAssured.baseURI="http://localhost:8080/nifi-api";
	    String resp=given().header("Content-Type","application/json").
	    body(map).
	    when()
	    .put("/processors/6efc3522-7e43-1ea0-d2cc-dfc445f832bf/run-status")
	    .then().log().all().assertThat().statusCode(200).
	    extract().response().asString();
	    JsonPath js=new JsonPath(resp);
	    System.out.println(resp);
	    String stateValue=js.get("component.state");
	    System.out.println(stateValue);
	    //Assert.assertEquals(state, "RUNNING");
	
	}
	
	
	
	
	
	
	/*@Test()
	public void  updateRunState() throws EncryptedDocumentException, IOException{
		
		
		dataDriven d=new dataDriven();
		d.getData("CheckRunState");
		HashMap<String,Object> map=new HashMap();
		HashMap<String,Object> map2=new HashMap();
		map2.put("version", "5");
		map.put("revision", map2);
		map.put("state", "STOPPED");
		
		
		RestAssured.baseURI="http://localhost:8080/nifi-api";
	    String resp=given().header("Content-Type","application/json").
	    body(map).
	    when()
	    .put("/processors/6efc3522-7e43-1ea0-d2cc-dfc445f832bf/run-status")
	    .then().log().all().assertThat().statusCode(200).
	    extract().response().asString();
	    JsonPath js=new JsonPath(resp);
	    System.out.println(resp);
	    String stateValue=js.get("component.state");
	    System.out.println(stateValue);
	    //Assert.assertEquals(state, "RUNNING");
	
	}*/
	
	
	/*//files.payload payload =new files.payload();
	@Test(dataProvider="data")
	public void  updateRunState(String version,String state){
		RestAssured.baseURI="http://localhost:8080/nifi-api";
	    String resp=given().header("Content-Type","application/json").
	    body(files.payload.updateStatus(version,state)).
	    when()
	    .put("/processors/6efc3522-7e43-1ea0-d2cc-dfc445f832bf/run-status")
	    .then().log().all().assertThat().statusCode(200).
	    extract().response().asString();
	    JsonPath js=new JsonPath(resp);
	    String stateValue=js.get("component.state");
	    System.out.println(stateValue);
	    //Assert.assertEquals(state, "RUNNING");
	
	}
	
	@DataProvider(name="data")
	public Object[][] getData(){
		
		return new Object[][] {{"3","STOPPED"},{"4","RUNNING"},{"5","DISABLED"}};
			
		}*/
	}


