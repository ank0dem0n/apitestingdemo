package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	public static RequestSpecification rqsb;  // so that single instance is created across all request
	 ResponseSpecification rpsb;
	
	 
	public RequestSpecification requestspecification() throws IOException {
		
		if(rqsb==null) {
			PrintStream log= new PrintStream(new FileOutputStream("logging.txt"));
			 rqsb= new RequestSpecBuilder().setBaseUri(getGlobalValue("baseuri"))
					.addQueryParam("key", "qaclick123")
					.setAccept(ContentType.JSON)
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
			 return rqsb;
		}
		return rqsb;
		
	}
	public ResponseSpecification responsespecification() {
		rpsb= new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(200)
				.build();
		return rpsb;
	}
	
	public static String getGlobalValue(String key) throws IOException {
		Properties prop= new Properties();
		FileInputStream fis=new FileInputStream("src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}

}
