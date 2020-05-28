package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req;

	public RequestSpecification requestSpecification() throws IOException {
		if (req == null) {
			PrintStream stream = new PrintStream(new FileOutputStream("src/test/java/logging/Logging.txt"));

			req = new RequestSpecBuilder().setBaseUri(getGlobalProperties("baseUrl")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(stream))
					.addFilter(ResponseLoggingFilter.logResponseTo(stream)).setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	}

	public static String getGlobalProperties(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(
				"C:\\OCP\\JAVA\\java-developer\\Cucumber_Framework\\src\\test\\java\\resources\\global.properties");
		prop.load(file);
		return prop.getProperty(key);

	}

	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		System.out.println(resp);
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}
}
