package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(

		features = "/src/test/resources/features",

		glue = "steps",

		dryRun = true,

		monochrome = true,

		tags = "@",

		plugin = { "pretty", "json:target/cucumber.json", "html:target/cucumber-html-reports.html" }

)

public class TestRunner {

}
