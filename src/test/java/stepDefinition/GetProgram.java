package stepDefinition;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import  io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class GetProgram {
	Response response;
	RequestSpecification request;
	String jsonString;
	String ProgramID;
	String pi;
	int Statuscode;
	long resTime;
	@Given("A service with BaseUrl\\/endpoint")
	public void a_service_with_base_url_endpoint() {
		RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms";
		 request = RestAssured.given();
		
		}
	@When("GET Request is made")
	public void get_request_is_made() 
	{
		response  = request.get("/allPrograms");
		jsonString = response.asString();
		List<Map<String, String>> prList = JsonPath.from(jsonString).get();
		System.out.println(prList);
		resTime = response.time();
		
		ProgramID=  response.then().contentType(ContentType.JSON).extract().path("programId").toString();
		String jsonString = response.asPrettyString();
		System.out.println(jsonString);
		
		
	}

	@Then("Validate status code {int}")
	public void validate_status_code(Integer statusCode) {
		
		//int statusCode = response.getStatusCode();
		Assert.assertEquals(200, response.getStatusCode());
		
		// response =  RestAssured.given().get(path);
				  
		Assert.assertEquals(ProgramID, response.then().contentType(ContentType.JSON).extract().path("programId").toString());
		Statuscode = response.getStatusCode();//status code validation, json validation
		//String res= response.asString();
		
	
		
	     
		ValidatableResponse v = response.then();//response time validation
		//v.time(Matchers.lessThan(1500L));
		v.contentType(Matchers.anything(ProgramID));//contains field programID validation
		v.header("Content-Type", "application/json");//validate header content type
		
	
	
   
		
	}
	@Then("validate schema")
	public void validate_schema() {
		ValidatableResponse v = response.then();//response time validation
		v.assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("C:\\Users\\dgupta3\\eclipse-workspace\\APO\\src\\test\\resources\\Prameterised\\schema.json")));
		//schema validation but getting null Expected String for ProgramDiscription 
			System.out.println("scema validation");
	}

	@Then("Validate resopnse Time")
	public void validate_resopnse_time() {
		ValidatableResponse v = response.then();//response time validation
		v.time(Matchers.lessThan(1500L));
	}

	@Then("validate Status Line")
	public void validate_status_line() {
String statusLine = response.getStatusLine();
		
		System.out.println("StatusLine is " +statusLine);
	    Assert.assertEquals("HTTP/1.1 200 ", statusLine ); 
	    
	}

	
	@Given("A service BaseUrl with endpoint")
	public void a_service_base_url_with_endpoint() {
		RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms";
		 request = RestAssured.given();
	    
	}
	
		
	@When("i made GET Request with {int}")
	public void i_made_get_request_with(Integer int1) {
		
		String path = "/programs/" + int1.toString();
		 response =  RestAssured.given()
				  .get(path)
				  .then()
				  .statusCode(200)
				  .extract().response();
		 	Statuscode = response.getStatusCode();
			System.out.println(response.asPrettyString());
			Assert.assertEquals(Statuscode, 200);
			ProgramID = int1.toString();
	    
	}
	
	@Then("Validate Corresponding response status code {int}")
	public void validate_corresponding_response_status_code(Integer int1) {
		String path = "/programs/" + ProgramID;
		 response =  RestAssured.given()
				  .get(path);
		Statuscode = response.getStatusCode();
		System.out.println(response.asPrettyString());
		Assert.assertEquals(200, Statuscode);
String statusLine = response.getStatusLine();
		
		System.out.println("StatusLine is " +statusLine);
	    Assert.assertEquals("HTTP/1.1 200 ", statusLine );
		Statuscode = response.getStatusCode();
		
		ResponseBody bdy = response.getBody();
		String B = bdy.asString();
		JsonPath j = response.jsonPath();
		
		String pi = j.getString("programId");
		System.out.println("programId is " +pi);
		
		
		String pn = j.getString("programName");
		System.out.println("programName is " +pn);
		
		
		
		
		
		ValidatableResponse v = response.then();
	v.time(Matchers.lessThan(1000L));
	
		v.contentType(Matchers.anything(ProgramID));
		

v.body("programStatus",Matchers.equalTo("Active"));
	v.assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("C:\\Users\\dgupta3\\eclipse-workspace\\APO\\src\\test\\resources\\Prameterised\\IDschema.json")));//schema validation
	v.contentType(ContentType.JSON);
	v.header("Content-Type", "application/json");

	}
	}



