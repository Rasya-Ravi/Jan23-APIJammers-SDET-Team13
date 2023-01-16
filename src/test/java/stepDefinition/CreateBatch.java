package stepDefinition;

import io.cucumber.java.bs.I.Is;
import static org.hamcrest.Matchers.*;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;


import static io.restassured.RestAssured.given;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hamcrest.core.IsNot;
import org.json.simple.JSONObject;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

public class CreateBatch {
	Response response;
	ValidatableResponse validatableResponse;	
	String batchID;
	String pgmNAME;
	
	@Given("A service with Batch BaseUrl")
	public void a_service_with_with() {
		RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms";
	}
	
	@When("POST request is made with batch {string},{string},{string},{double},{double}")
	public void post_request_is_made_with(String bName, String bDesc, String bstatus, Double bNoOfClasses,Double pgmId) {
        JSONObject request = new JSONObject();
		
		request.put("batchName",bName);
		request.put("batchDescription",bDesc);
		request.put("batchStatus",bstatus);
		
		request.put("batchNoOfClasses",bNoOfClasses);
		request.put("programId",pgmId);
		response = given().header("Content-type", "application/json").and().body(request).when().post("/batches");
		validatableResponse =response.then();
		validatableResponse.statusCode(201);
		validatableResponse.log().all();
	}
	@Then("Validate  batchId and ProgramName created")
	public void validate_random_batch_id_created() {
		validatableResponse.body("programId",notNullValue());	
		batchID = validatableResponse.contentType(ContentType.JSON).extract().path("batchId").toString();
		pgmNAME= validatableResponse.contentType(ContentType.JSON).extract().path("programName").toString();
	}

	@Then("Validate Batch status code")
	public void validate_prog_status_code() {
		validatableResponse.statusCode(201);
	}

	@Then("Validate Content - Type")
	public void validate_content_type() {
		System.out.println(response.getHeader("content-type"));
	}

	@Then("Validate batch Response time")
	public void validate_response_time() {
		response.getTime();
		System.out.println(response.getTime());
	}

}
