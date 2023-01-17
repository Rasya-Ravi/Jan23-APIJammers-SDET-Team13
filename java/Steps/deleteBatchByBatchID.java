package Steps;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.Assert;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class deleteBatchByBatchID {

	
	RequestSpecification request;
	Response response= null;
	JSONObject requestParamsJSON;
	String BatchId;
	int Statuscode;
	
	
	@Given("A service with BaseUrl with endpoint")
	public void a_service_with_base_url_with_endpoint() {
		RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms";
		 request = RestAssured.given();
	    
	}

	@When("DELETE request is made {int}")
	public void delete_request_is_made(Integer int1) {
		String path = "/batches/" + int1.toString();
		 response =  RestAssured.given()
				  .delete(path)
				  .then()
				  .statusCode(200)
				  .extract().response();
		 	
		 Statuscode = response.getStatusCode();
			
			Assert.assertEquals(Statuscode, 200);
			BatchId = int1.toString();
	}

	@Then("Validate Status Code")
	public void validate_status_code() {
		
		Assert.assertEquals(Statuscode, 200);
	}

	@Then("Validate Response time")
	public void validate_response_time() {
		ValidatableResponse v = response.then();//response time validation
		v.time(Matchers.lessThan(1000L));
	}

	@Then("Valiadte Status is ok")
	public void valiadte_status_is_ok() {
String statusLine = response.getStatusLine();
		
		System.out.println("StatusLine is " +statusLine);
	    Assert.assertEquals("HTTP/1.1 200 ", statusLine );
	}

	
}
