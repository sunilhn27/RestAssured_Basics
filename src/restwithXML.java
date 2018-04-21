import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class restwithXML {

	@Test
	public void satrt() throws IOException {

		String b = resoursetostring("D:\\html\\resourse.xml");
		RestAssured.baseURI = "https://maps.googleapis.com";

		Response res = given().queryParam("key", "AIzaSyBRaA-z-2cc7hBbf6VIkkXcEynyjCB5Fno").body(b).when()
				.post("/maps/api/place/add/xml").then().assertThat().statusCode(200).and().contentType(ContentType.XML)
				.statusCode(200).extract().response();

		String r = res.asString();
		System.out.println(r);

	}

	// https://maps.googleapis.com/maps/api/place/add/xml?key=AIzaSyBRaA-z-2cc7hBbf6VIkkXcEynyjCB5Fno

	public static String resoursetostring(String path) throws IOException {

		return new String(Files.readAllBytes(Paths.get(path)));

	}
}