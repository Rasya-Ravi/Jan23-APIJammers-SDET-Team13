package stepDefenitions;

import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class Batch {

	Response response;
	RequestSpecification request;
	String jsonString;
	String BatchId;
	String pi;
	int Statuscode;
	long resTime;
	
	@Given("A service with BaseUrl with endpoint")
	public void a_service_with_base_url_with_endpoint() {
			RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms";
			request = RestAssured.given();
	}

	@When("GET Request is made")
	public void get_request_is_made() {
		response  = request.get("/batches");
		jsonString = response.asString();
		List<Map<String, String>> prList = JsonPath.from(jsonString).get();
		System.out.println(prList);
		resTime = response.time();

		BatchId=  response.then().contentType(ContentType.JSON).extract().path("programId").toString();
		String jsonString = response.asPrettyString();
		System.out.println(jsonString);
		
	}

	@Then("Validate Status Code")
	public void validate_status_code() {
		
		Assert.assertEquals(200, response.getStatusCode());
		Statuscode = response.getStatusCode();//status code validation
	   

	}
	
	@Then("Valiadte Status is ok")
	public void valiadte_status_is_ok() {
		String statusLine = response.getStatusLine();

		System.out.println("StatusLine is " +statusLine);
	    Assert.assertEquals("HTTP/1.1 200 ", statusLine );
	}
	
	@Then("Validate Response time")
	public void validate_response_time() {
		
		ValidatableResponse v = response.then();//response time validation
		v.time(Matchers.lessThan(1500L));
		v.contentType(Matchers.anything(BatchId));//contains field programID validation
	}

    @Then("Validate Content - Type")
    public void validate_content_type() {
	ValidatableResponse vc= response.then();
	
	vc.contentType(Matchers.anything(BatchId));
    }
   
    @When("GET Request is made with {string}")
    
    public void get_request_is_made_with(String BID) {
    	String path = "/batches/batchId/" + BID.toString();
		 response =  RestAssured.given()
				  .get(path)
				  .then()
				  .statusCode(200)
				  .extract().response();
		 	Statuscode = response.getStatusCode();
			System.out.println(response.asPrettyString());
			Assert.assertEquals(Statuscode, 200);
			BatchId = BID.toString();
			
    }

   @When("GET Requests is made with {string}")
   public void get_requests_is_made_with(String BName) {
	   String path = "/batches/batchName/" + BName.toString();
		 response =  RestAssured.given()
				  .get(path)
				  .then()
				  .statusCode(200)
				  .extract().response();
		 	Statuscode = response.getStatusCode();
			System.out.println(response.asPrettyString());
			Assert.assertEquals(Statuscode, 200);
			BatchId = BName.toString();
}

  
}







