
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Postrest {

	@Test
	public void post() {

		// https://maps.googleapis.com/maps/api/place/add/json?key=AIzaSyBRaA-z-2cc7hBbf6VIkkXcEynyjCB5Fno

		RestAssured.baseURI = "https://maps.googleapis.com";
		
		given().queryParam("key", "AIzaSyBRaA-z-2cc7hBbf6VIkkXcEynyjCB5Fno")
		.body("{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -33.8669710,\r\n"
						+ "    \"lng\": 151.1958750\r\n" + "  },\r\n" + "  \"accuracy\": 50,\r\n"
						+ "  \"name\": \"Google Shoes!\",\r\n" + "  \"phone_number\": \"(02) 9374 4000\",\r\n"
						+ "  \"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\",\r\n"
						+ "  \"types\": [\"shoe_store\"],\r\n" + "  \"website\": \"http://www.google.com.au/\",\r\n"
						+ "  \"language\": \"en-AU\"\r\n" + "}\r\n" + " ")
				.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
				.contentType(ContentType.JSON).and().body("status", equalTo("OK"));
	}
}
