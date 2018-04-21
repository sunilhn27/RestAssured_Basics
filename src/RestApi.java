import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import org.testng.annotations.*;

import io.restassured.RestAssured;

public class RestApi {

	@Test
	public void Test() {

		RestAssured.baseURI = "https://maps.googleapis.com";

		given().param("location", "-33.8670522,151.1957362").param("radius", "500").param("key",
				"AIzaSyBRaA-z-2cc7hBbf6VIkkXcEynyjCB5Fno");
		when().get("/maps/api/place/nearbysearch/json").then().assertThat().body("results[0].name",equalTo("Cruise Bar, Restaurant & Events"));
		System.out.println("pass");
	}

	// https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&type=restaurant&keyword=cruise&key=AIzaSyBRaA-z-2cc7hBbf6VIkkXcEynyjCB5Fno
}
