package resources;

public enum ApiResources {
	
	add_placeApi("/maps/api/place/add/json"),
	delete_placeApi("/maps/api/place/delete/json"),
	get_placeApi("/maps/api/place/get/json");
	
	private String resources;
	
	ApiResources(String resorces) {
		this.resources=resorces;
	}
	
	public String getResources() {
		return resources;
		
	}

}
