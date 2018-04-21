import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GrabResopnse {

	@Test
	public void grab() {
		RestAssured.baseURI = "https://maps.googleapis.com";

		Response res = given().queryParam("key", "AIzaSyBRaA-z-2cc7hBbf6VIkkXcEynyjCB5Fno")
				.body("{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -33.8669710,\r\n"
						+ "    \"lng\": 151.1958750\r\n" + "  },\r\n" + "  \"accuracy\": 50,\r\n"
						+ "  \"name\": \"Google Shoes!\",\r\n" + "  \"phone_number\": \"(02) 9374 4000\",\r\n"
						+ "  \"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\",\r\n"
						+ "  \"types\": [\"shoe_store\"],\r\n" + "  \"website\": \"http://www.google.com.au/\",\r\n"
						+ "  \"language\": \"en-AU\"\r\n" + "}\r\n" + " ")
				.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
				.contentType(ContentType.JSON).and().body("status", equalTo("OK")).extract().response();

		String responseconverted = res.asString();
		System.out.println(responseconverted);

		JsonPath js = new JsonPath(responseconverted);
		String jsoutput = js.get("place_id");
		System.out.println("The place id  is :" + jsoutput);

	}
}