import static io.restassured.RestAssured.given;

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

public class AddandDeletePlace {

	Properties p = new Properties();

	@BeforeMethod
	public void setupproperties() throws Exception {
		FileInputStream fs = new FileInputStream("F:\\SpringFramework\\RESTAssured\\src\\Files\\App.properties");
		p.load(fs);
		System.out.println("Succesfglly loaded");
	}

	@Test
	public void addanddelete() {

		RestAssured.baseURI = p.getProperty("Baseurl");

		Response re = given().queryParam("key", "AIzaSyBRaA-z-2cc7hBbf6VIkkXcEynyjCB5Fno").body(payload.body()).when()
				.post(Resourses.getpostutl()).then().assertThat().statusCode(200).contentType(ContentType.JSON)
				.extract().response();

		System.out.println(re.asString());

		JsonPath js = new JsonPath(re.asString());
		String place_id = js.get("place_id");
		System.out.println("js output " + place_id);

		// deleteing created palce

		given().body("{\r\n" + "  \"place_id\": \"" + place_id + "\"\r\n" + "}\r\n" + " ").post(Resourses.getpostutl())
				.then().assertThat().statusCode(200).contentType(ContentType.JSON);
		System.out.println("*******************************PASS*****************************");
	}
}
