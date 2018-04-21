import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Files.Resourses;
import Files.payload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GrabResopnse2 {

	Properties p = new Properties();

	@BeforeMethod
	public void setupproperties() throws Exception {
		FileInputStream fs = new FileInputStream("F:\\SpringFramework\\RESTAssured\\src\\Files\\App.properties");
		p.load(fs);
		System.out.println("Succesfglly loaded");
	}

	@Test()
	public void grab() {

		RestAssured.baseURI = p.getProperty("Baseurl");
		// https://maps.googleapis.com/maps/api/place/add/json?key=AIzaSyBRaA-z-2cc7hBbf6VIkkXcEynyjCB5Fno

		Response res = given().queryParam("key", "AIzaSyBRaA-z-2cc7hBbf6VIkkXcEynyjCB5Fno")
				.body(payload.body()).when().post(Resourses.getpostutl()).then().statusCode(200)
				.body("status", equalTo("OK")).and().contentType(ContentType.JSON).extract().response();
		String resv = res.asString();
		System.out.println(resv);
		JsonPath js = new JsonPath(resv);
		String placeid = js.get("place_id");
		System.out.println(" json output :" + placeid);

		// deleting created place
		given().queryParam("key", "AIzaSyBRaA-z-2cc7hBbf6VIkkXcEynyjCB5Fno")
				.body("{\r\n" + "  \"place_id\": \"" + placeid + "\"\r\n" + "}\r\n" + "     ")
				.post("/maps/api/place/delete/json").then().assertThat().statusCode(200).contentType(ContentType.JSON);
	}
}