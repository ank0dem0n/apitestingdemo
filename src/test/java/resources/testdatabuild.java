package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class testdatabuild {
	
	
	public AddPlace addplacepayload(String name, String language, String address) {
		AddPlace p =new AddPlace();
    	p.setAccuracy(50);
    	p.setName(name);
    	p.setPhoneNumber("(+91) 983 893 1111");
    	p.setAddress(address);
    	List<String> l=new ArrayList<String>();
    	l.add("shoe park");
    	l.add("vegetable shop");
    	p.setTypes(l);
    	p.setWebsite("http://localhost:8080/");
    	p.setLanguage(language);
    	Location loc =new Location();
    	loc.setLat(-38.383494);
    	loc.setLng(38.383494);
    	p.setLocation(loc);
    	return p;
	}

}
