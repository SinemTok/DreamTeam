package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(

		features = "src/test/resources/features",

		glue = "steps",

		dryRun = true,

		monochrome = true,

		tags = "",

		plugin = { "pretty", "json:target/Cucumber.json", "html:target/Cucumber-default-report.html" }

)

public class BashirRunner {

}
