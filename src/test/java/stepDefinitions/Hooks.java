package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlaceAPI")
	
	public void beforeScenario() throws IOException
	{
		
		if(StepDefinition.place_Id==null)
		{
		StepDefinition step = new StepDefinition();
		step.add_place_payload_with("Raghu","English","Chelsonton");
		step.user_calls_with_http_request("AddPlaceAPI","POST");
		step.verify_place_id_created_maps_to_using("Raghu","GetPlaceAPI");
		}
		
	}

}
