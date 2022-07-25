package testRunner;

import cucumber.api.*;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


//import cucumber.api.CucumberOptions;
//import cucumber.api.testng.CucumberFeatureWrapper;
//import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(tags= ("@functiona"),
features = ".//Features", 
glue = "stepDefinitions",

plugin= {"pretty",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		"html:target/cucumber-reports.html",
		"json:target/Cucumber1.json"},
dryRun=false,
monochrome=true)
	 
public class TestNGRun extends AbstractTestNGCucumberTests {
	 
}

