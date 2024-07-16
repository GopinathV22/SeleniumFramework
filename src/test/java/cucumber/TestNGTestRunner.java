package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",glue="Framework.Cucumber.StepDefinition",monochrome=true,plugin= {"html:target/cucumber.html"},tags="@ErrorValidations")
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
