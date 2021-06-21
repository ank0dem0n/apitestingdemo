package stepDefination;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.ApiResources;
import resources.Utils;
import resources.testdatabuild;


public class place_validation extends Utils{
	
	RequestSpecification res;
	ResponseSpecification rpsb;
	Response response;

	testdatabuild data= new testdatabuild();
	
	@Given("^add_place payload \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void addplace_payload(String name, String language, String address) throws Throwable {
		
    	res=given().spec(requestspecification()).body(data.addplacepayload(name,language,address));
   	
    }

	@When("^user call \"([^\"]*)\" with \"([^\"]*)\" http request$")
    public void user_call_something_with_something_http_request(String resource, String r_method)throws Throwable {
		 
		 ApiResources resource_api = ApiResources.valueOf(resource);
		 String resss=resource_api.getResources();
		 
		if(r_method.equalsIgnoreCase("post"))
		{
			response=res.when().post(resss)
	    			.then().spec(responsespecification()).extract().response();	
		}
		else if(r_method.equalsIgnoreCase("get")) 
		{
			response=res.when().get(resss)
	    			.then().spec(responsespecification()).extract().response();
		}
		
    }

    @Then("^then api call got success with status code \\\"([^\\\"]*)\\\"$")
    public void then_api_call_got_success_with_status(String stcode) throws Throwable {
    	int statuscode=Integer.parseInt(stcode);
        assertEquals(response.getStatusCode(),statuscode);
    }

    @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
    public void something_in_response_body_is_something(String strArg1, String strArg2) throws Throwable {
        
    JsonPath j=new JsonPath(response.asString());
    assertEquals(j.get(strArg1),strArg2);
    
       
    }
    @And("^verify place id created maps to \"([^\"]*)\" and \"([^\"]*)\"$")
    public void verify_place_id_created_maps_to(String expectedname, String res_method) throws Throwable {
    	JsonPath j= new JsonPath(response.asString());
    	String placeid=j.getString("place_id");
    	res=given().spec(requestspecification()).queryParam("place_id", placeid);
    	user_call_something_with_something_http_request("get_placeApi","get");
    	JsonPath j2=new JsonPath(response.asString());
    	String actualname=j2.getString("name");
    	assertEquals(actualname, expectedname);
    }

	

}


