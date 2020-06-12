package files;

import java.util.List;

import io.restassured.path.json.JsonPath;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.Response;

public class ReUsableMethods {

	
	/*public static JsonPath rawToJson(String response)
	{    
		JsonPath js1 =new JsonPath(response);
		try{
		
		     return js1;
		}
		catch (Exception e) {
		      throw new JsonPathException("Failed to parse the JSON document", e);
		    }
	}*/
	
	
	public String getJsonPath(Response response,String key)
	{
		  String resp=response.asString();
		JsonPath   js = new JsonPath(resp);
		return js.get(key).toString();
	}
	
	
	
}
