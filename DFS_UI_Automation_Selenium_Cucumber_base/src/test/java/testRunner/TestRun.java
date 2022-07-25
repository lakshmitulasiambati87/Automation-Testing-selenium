package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions
(
features=".//Features",
glue="stepDefinitions",
dryRun=false,
monochrome=true,
plugin= {"pretty",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" },
//plugin= {"pretty","html:target/test-output.html",
//		"json:target/test-output.json",
//		"junit:target/test-output.xml"
//		},
tags= ("@functiona")
)
public class TestRun {

	public static void main(String[] args) {
		
	}

}
