package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(

		features = "/src/test/resources/features",

		glue = "steps",
		
		plugin = {"json:target/cucumber.json", "html:target/cucumber-html-reports.html" , "rerun:target/failed.txt"}
		
		)
public class FailedRunner {

}
