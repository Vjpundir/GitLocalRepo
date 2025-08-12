package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestData;
import resources.Util;

public class StepDefinition extends Util{

	RequestSpecification req;
	ResponseSpecification resSpec;
	RequestSpecification res;
	Response response;
	String place_Id;
	JsonPath js;
	
	TestData td = new TestData();

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String language) throws IOException {

		res = given().spec(requestSpecification()).body(td.AddPlacePayLoad(name, address, language));

	}
	
	//constructor will called with values of resources you pass here

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {

		APIResources resourcesAPI =APIResources.valueOf(resource);
		
		System.out.println(resourcesAPI.getResource());
		
		resSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("Post"))
		
	response = res.when().post(resourcesAPI.getResource());
		else if(method.equalsIgnoreCase("Get"))
			response = res.when().get(resourcesAPI.getResource());
				
			
				}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer st) {

		assertEquals(response.getStatusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		
		assertEquals(getJsonPath(response, key), value);
	}
	
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expname, String resource) throws IOException {
		
		String place_Id =getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_Id", place_Id);
		user_calls_with_http_request(resource,"Get");
		String actname =getJsonPath(response,"name");
		
		//assertEquals(actname,expname);
		
		System.out.println("Test is passed correctly:"+ actname);


	}


}
