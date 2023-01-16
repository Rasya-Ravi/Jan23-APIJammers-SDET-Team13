package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import java.time.LocalDateTime;

import org.json.simple.JSONObject;
import static org.hamcrest.Matchers.*;
import io.cucumber.java.en.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import utilities.CommonUtilities;

public class CreateProgram {
	
	Response response;
	ValidatableResponse validatableResponse;	
	String ProgramID;
	
	@Given("A service with Program BaseUrl")
	public void a_service_with_with() {
		RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms";
	}

	@When("When POST request is made with Program {string},{string}")
	public void post_request_is_made_with_and(String pgmDesc, String pgmStatus) {
		JSONObject request = new JSONObject();
		LocalDateTime myDateObj = LocalDateTime.now();

		request.put("programName","Updated1Brand_1_V0.7" +CommonUtilities.getRandomString().substring(0,5));
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

	@Then("Validate Content-Type")
	public void validate_content_type() {
		System.out.println(response.getHeader("content-type"));
	}
	@When("Delete Program by Id")
	public void delete_program_by_id() {
	   String deleteEndpoint = "/deletebyprogid/"+ProgramID;
	   validatableResponse = given().header("Content-type", "application/json").when().delete(deleteEndpoint).then();	  
	}

	@Then("Validate corresponding ProgramId deleted")
	public void validate_corresponding_program_id_deleted() {
	    validatableResponse.statusCode(200);
	}


}
