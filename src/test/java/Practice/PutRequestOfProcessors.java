package Practice;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PutRequestOfProcessors {
 
    @Test
    public void UpdateState() {
 
        RestAssured.baseURI = "http://localhost:8080/nifi-api";
 
        String requestBody = "{             \r\n" + 
        		"                \"revision\": {\r\n" + 
        		"                             \"version\": 6\r\n" + 
        		"                            },\r\n" + 
        		"               \"state\":\" STOPPED\",\r\n" + 
        		"               \"disconnectedNodeAcknowledged\":\"true\"\r\n" + 
        		"                }";
 
 
        Response response = null;
 
        try {
            response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put("/processors/6efc3522-7e43-1ea0-d2cc-dfc445f832bf/run-status");
        } catch (Exception e) {
            e.printStackTrace();
        }
       String resp=response.toString();
       System.out.println(resp);
        
     
		  
       // System.out.println("Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());
        
        //System.out.println("Does Reponse contains 'tammy'? :" + response.asString().contains("tammy"));
 
 
        Assert.assertEquals(200, response.getStatusCode());
    }
}