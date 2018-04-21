import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class rest {

	@Test
	public void satrt() {

		RestAssured.baseURI = "https://maps.googleapis.com";

		given().param("query", "restaurants+in+Sydney").param("key", "AIzaSyBRaA-z-2cc7hBbf6VIkkXcEynyjCB5Fno");
		when().

				get("/maps/api/place/textsearch/json").then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and().
				// body("results[1].name.",equalTo("The Gantry Restaurant & Bar"));
				header("Server", equalTo("scaffolding on HTTPServer2"));

		// https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+in+Sydney&key=AIzaSyBRaA-z-2cc7hBbf6VIkkXcEynyjCB5Fno
	}
}