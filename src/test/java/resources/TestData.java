package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestData {
	
	public AddPlace AddPlacePayLoad(String name , String address , String language)
	
	{
		AddPlace p = new AddPlace();

		p.setAccuracy(50);
		p.setName(name);
		p.setAddress(address);
		p.setPhone_number("(+91) 9030303070");
		p.setWebsite("http://google.com");
		p.setLanguage(language);

		List<String> list = new ArrayList<>();
		list.add("shoe park");
		list.add("shop");

		p.setTypes(list);
		Location l = new Location();

		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		return p;
	}
	
	
	public String deletePayLoad(String placeId)
	{
		return "{\r\n"
				+ "    \"place_id\":\""+placeId+"\"\r\n"
				+ "}";
	}

}
