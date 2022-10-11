package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/LoginFeature.feature"},
		glue = {"salseforce.steps"}
		)
public class TestRunner extends AbstractTestNGCucumberTests{
}