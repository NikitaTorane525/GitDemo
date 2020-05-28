package resources;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String langauge, String address) throws JsonProcessingException {

		// Serialization
		AddPlace addplaceobj = new AddPlace();
		Location locationobj = new Location();

		addplaceobj.setAccuracy(50);

		addplaceobj.setAddress(address);

		addplaceobj.setLanguage(langauge);

		addplaceobj.setName(name);

		addplaceobj.setPhone_number("(+91) 983 893 3937");

		addplaceobj.setWebsite("http://sdsdds.com");

		List<String> typeslistobj = new ArrayList<String>();

		typeslistobj.add("shoe dsds");

		typeslistobj.add("dsds");

		addplaceobj.setTypes(typeslistobj);

		locationobj.setLat(-38.383494);

		locationobj.setLng(33.427362);

		addplaceobj.setLocation(locationobj);

		return addplaceobj;
	}

	public String deletePlacePayload(String placeId) {
		return "{\r\n    \"place_id\":\"" + placeId + "\"\r\n}";
	}

}
