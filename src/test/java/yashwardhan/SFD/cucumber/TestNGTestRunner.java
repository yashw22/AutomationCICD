package yashwardhan.SFD.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src\\test\\java\\yashwardhan\\SFD\\cucumber", glue = "yashwardhan\\SFD\\stepDefinitions", monochrome = true, plugin = {
		"html:target\\cucumber.html" }, tags = "@Regression")
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
