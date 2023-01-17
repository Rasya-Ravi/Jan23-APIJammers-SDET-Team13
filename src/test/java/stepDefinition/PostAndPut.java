package stepDefinition;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.json.simple.JSONObject;
import static io.restassured.RestAssured.given;

import java.util.Random;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
//import junit.framework.Assert;
import org.junit.Assert;
import org.testng.asserts.SoftAssert;


public class PostAndPut {
	public static Response response;
	public static JsonPath jPath;
	public static JSONObject request;
	public static String URL="";
	public static int progId=0;
	public static String reqForPutMthd;
	public static DateTime dt;
	public static DateTimeFormatter fmt;
	public static String str;
	public static String strProgDesc;
	public static String strProgStatus;
	public static String strProgName;
	public static int rndmProgID;
	JsonPath jsonPathEvaluator;
	
	@Given("A service with {string}")
	public void a_service_with(String bUrl) {
	    
	URL=bUrl;
	System.out.println("The base url is:"+ URL);
	
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@When("POST request is made with  {string},{string},{string} and {string}")
	public void post_request_is_made_with_and(String pname, String pdescription, String pstatus, String endpoint) {
	    
	   dt = new DateTime();
	   fmt = ISODateTimeFormat.dateTime();
	   str = fmt.print(dt);
	   request= new JSONObject();
	   rndmProgID = genRandomNumber();
	   strProgName = pname+"_"+rndmProgID;
	   System.out.println("The new programName is :"+strProgName);
	   request.put("programName", strProgName);
	   strProgDesc=pdescription;
	   request.put("programDescription", strProgDesc);
	   strProgStatus=pstatus;
	   request.put("programStatus", strProgStatus);
	   request.put("creationTime",str);
	   request.put("lastModTime",str);
	   response = given().header("Content-type", "application/json").and().body(request).when().post(URL+ endpoint).then().extract().response();
	   System.out.println("The response body is :"+ response.getBody().asPrettyString());
	 
	}
	
	@Then("fetch the programId from the POST response")
	public void fetch_the_program_id_from_the_post_response() {
	   
		jsonPathEvaluator = response.jsonPath();
		Integer apiProgramId = jsonPathEvaluator.get("programId");
		Assert.assertNotNull(apiProgramId);
		progId = apiProgramId.intValue();
		System.out.println("The progId is :"+ progId);
		
		
	}

		@Then("validate the POST response")
		public void validate_prog_status_code() {
						
			SoftAssert sa = new SoftAssert();
			
			Assert.assertEquals(201, response.getStatusCode());
			System.out.println("The actual response code "+response.getStatusCode() +" is validated");
			
			//validating the ProgramName
			jsonPathEvaluator = response.jsonPath();
	
			sa.assertEquals(jsonPathEvaluator.get("programName"), strProgName, "ProgramName value is incorrect in the POST response");
			sa.assertAll();
			//Assert.assertEquals("ProgramName value is incorrect in the POST response",strProgName,jsonPathEvaluator.get("programName")); 
			System.out.println("The ProgramName " +strProgName  + " is validated in POST response");
			
			//validating the ProgramDescription
			Assert.assertEquals("ProgramDesc value is incorrect in the POST response",strProgDesc,jsonPathEvaluator.get("programDescription")); 
			System.out.println("The ProgramDesc " +strProgDesc  + " is validated in POST response");
			
	}

	
	@Then("Validate Response time")
	public void validate_response_time() {
		   System.out.println("The Response time in ms is: "+response.getTime());
		   Assert.assertTrue("The Response time is exceeding 1500ms",response.getTime()<=1300 );
 	}
	
	@SuppressWarnings("unchecked")
	@Then("update the program by programId using PUT method")
	public void update_the_program_by_program_id_with() {
	    // Write code here that turns the phrase above into concrete actions
		request= new JSONObject();
		request.put("programId",progId );
		request.put("programName", "Updated"+strProgName);
		request.put("programDescription", strProgDesc);
		request.put("programStatus", strProgStatus);
		request.put("creationTime",str);
		request.put("lastModTime",str);
		   	   
		response= given().header("Content-type", "application/json").and().body(request).
				   when().put(URL+"/putprogram/"+progId).then().extract().response();
		   
		System.out.println("Response Body of put method after update is: " + response.getBody().asPrettyString());
	}
	
	public int genRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(10000);
	}
}
