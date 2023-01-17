package Runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(
        features = { "./src/test/resources/features/ist.feature", "src/test/resources/features/deleteBatchByID.feature"},
        glue = {"Steps"},
        plugin = { "pretty",
        		"html:target/report.html","json:target/report.json","rerun:target/failed_scenarios.txt" },
        monochrome = true
)
public class runner {

}
