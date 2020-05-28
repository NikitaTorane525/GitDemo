package stepdefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileNotFoundException;
import java.io.IOException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification request;
	ResponseSpecification resspect;
	Response response;
	TestDataBuild testdata = new TestDataBuild();
	APIResources apiResource;
	JsonPath js;
	static String place_id;

	@Given("Add Place Payload {string} {string} {string}")
	public void add_Place_Payload(String name, String language, String address) throws IOException {
		RestAssured.useRelaxedHTTPSValidation();
		request = given().spec(requestSpecification()).body(testdata.addPlacePayload(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_something_with_post_http_request(String resource, String method) {
		APIResources apiResource = APIResources.valueOf(resource);
		
		System.out.println(apiResource.getResource());

		resspect = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("POST")) {
			response = request.when().post(apiResource.getResource());
		} else if (method.equalsIgnoreCase("GET")) {
			response = request.when().get(apiResource.getResource());
		}
	}

	@Then("the Api call got success with status code {int}")
	public void the_Api_call_got_success_with_status_code(Integer int1) {
		// Junit assertion
		assertEquals(response.getStatusCode(), 200);
	}

	@And("{string} in response body is {string}")
	public void something_in_response_body_is_something(String keyValue, String Expectedvalue) {
		assertEquals(getJsonPath(response, keyValue), Expectedvalue);
	}

	@And("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
		// requestSpec
		
		place_id = getJsonPath(response, "place_id");
		request = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_something_with_post_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
	}

	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
		given().spec(requestSpecification()).body(testdata.deletePlacePayload(place_id));
	}

}
