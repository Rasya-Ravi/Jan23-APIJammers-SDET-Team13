package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import java.time.LocalDateTime;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;


import static org.hamcrest.Matchers.*;
import io.cucumber.java.en.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import utilities.CommonUtilities;

public class DeleteProgram {
	
	Response response;
	ValidatableResponse validatableResponse;	
	String ProgramID;
	String ProgName;
	JSONObject request = new JSONObject();
	@Given("A service with Program BaseUrl")
	public void a_service_with_with() {
		RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms";
	}

	@When("POST request is made with Program {string},{string}")
	public void post_request_is_made_with_program(String pgmDesc, String pgmStatus) {
		
		LocalDateTime myDateObj = LocalDateTime.now();
		request.put("programName","Jan23-API" +CommonUtilities.getRandomString().substring(0,5));
		request.put("programDescription", pgmDesc);
		request.put("programStatus", pgmStatus);
		
		request.put("creationTime", myDateObj.toString());
		request.put("lastModTime", myDateObj.toString());
		response = given().header("Content-type", "application/json").and().body(request).when().post("/saveprogram");
		validatableResponse =response.then();
		validatableResponse.statusCode(201);
		validatableResponse.log().all();
	}

	@Then("Validate random programId created")
	public void validate_the_created_program_by() {
	  validatableResponse.body("programId",notNullValue());	
	  ProgramID = validatableResponse.contentType(ContentType.JSON).extract().path("programId").toString();
	}

	@Then("Validate prog status code")
	public void validate_prog_status_code() {
		validatableResponse.statusCode(201);
	}

	@Then("Validate prog Response time")
	public void validate_response_time() {
		response.getTime();
		System.out.println(response.getTime());
	}

	@Then("Validate program Content-Type")
	public void validate_content_type() {
		System.out.println(response.getHeader("content-type"));
	}
	@When("Delete Program by Id")
	public void delete_program_by_id() {
	   String deleteEndpointId = "/deletebyprogid/"+ProgramID;
	   validatableResponse = given().header("Content-type", "application/json").when().delete(deleteEndpointId).then();	  
	}
	@Then("Validate corresponding ProgramId programs deleted")
	public void validate_corresponding_program_id_programs_deleted() {
		validatableResponse.statusCode(200);
	}
	
	@When("Delete Program by program Name")
	public void delete_program_by_program_name() {
		ProgName= request.put("programName","Jan23-API" +CommonUtilities.getRandomString().substring(0,5)).toString();
		String deleteEndpointName = "//deletebyprogname/"+ProgName;
		validatableResponse = given().header("Content-type", "application/json").when().delete(deleteEndpointName).then();	
		System.out.println("Program Name:" +ProgName);
	}
	@Then("Validate corresponding programs should be deleted")
	public void validate_corresponding_programs_should_be_deleted() {
		Assertions.assertEquals(ProgName, response.then().contentType(ContentType.JSON).extract().path("programName").toString());
		System.out.println(response.getStatusCode());
	}


}
