package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = ".//Features", glue = "stepDefinitions", dryRun = false, monochrome = true, plugin = {
		"pretty", "html:target/html-results.html", "json:target/json-report.json",
		"junit:target/execution-report.xml" }, publish = false, tags = "@Sanity and @Regression")
public class Runner {

}
