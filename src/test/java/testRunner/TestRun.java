package testRunner;



	import org.junit.runner.RunWith;

	import io.cucumber.junit.Cucumber;
	import io.cucumber.junit.CucumberOptions;
	//import io.cucumber.testng.CucumberOptions;


	@RunWith(Cucumber.class)
	@CucumberOptions
	 (
		
		features="C:\\Users\\Rasya Ravi\\eclipse-workspace\\Cucumber_Selenium_Java\\src\\test\\resources\\features",
	   
	    glue={"StepDefenition"},
	     tags="@tag1")
	    // dryRun=true,
	   // plugin={"pretty",
	    		//"html:test-output"};
		
		
		
	public class TestRun{
		
		}


	

