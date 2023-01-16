package testRunner;



	import org.junit.runner.RunWith;

	import io.cucumber.junit.Cucumber;
	import io.cucumber.junit.CucumberOptions;
	//import io.cucumber.testng.CucumberOptions;


	@RunWith(Cucumber.class)
	@CucumberOptions
	 (
		
		features="src\\test\\resources\\Features",
	   	    
	   
	glue = "stepDefenitions" ,
			dryRun = false,
			monochrome = true,
			plugin = {"pretty","html:target/HtmlReports.html","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"})
		
	public class TestRun{
		
		}


	

