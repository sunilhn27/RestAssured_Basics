package Practice;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetRequest {

	// https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+in+Sydney&key=AIzaSyBRaA-z-2cc7hBbf6VIkkXcEynyjCB5Fno

	public static void main(String[] args) {
		RestAssured.baseURI = "https://maps.googleapis.com";
		Response res = given().param("key", "AIzaSyBRaA-z-2cc7hBbf6VIkkXcEynyjCB5Fno")
				.param("query", "restaurants+in+Sydney").when().get("/maps/api/place/textsearch/json").then()
				.assertThat().statusCode(200).contentType(ContentType.JSON).extract().response();

		String out = res.asString();
		System.out.println(out);

	}

}
