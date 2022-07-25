package stepDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Collection;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.InstallmentPlanSummary;
import pageObjects.LookupPage;
import utilities.CaptureScreenShot;

public class Steps_MakePartialPayment extends BaseClass {

	

	
	
//	@When("click on planid")
//	public void click_on_planid() {
//		//ips=new InstallmentPlanSummary(driver); 
//	    ips.clickOnPlanID();
//	   
//	    
//	}	
	
	
	
	
//
//	@When("click on Makepayment")
//	public void click_on_makepayment() {
//	    ips.clickOnMakePayment();
//
//	}
//	@When("enter partial amount")
//	public void enter_partial_amount() {
//	    ips.enterPartialAmount(20);
//	    
//	}
//	@When("enter Email for Notifications")
//	public void enter_email_for_notifications() {
//	    ips.enterEmail("abc@xyz.com");
//	}
//	@When("enter verify Email Adress")
//	public void enter_verify_email_adress() {
//	    ips.verifyEmail("abc@xyz.com");
//	}
//	@When("click on Submit")
//	public void click_on_submit() {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//	@Then("Page should display {string}")
//	public void page_should_display(String string) {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//	@Then("Status should be Active-Additional-Payment-Applied")
//	public void status_should_be_active_additional_payment_applied() {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//	@Then("Current Remaining Balance should be updated")
//	public void current_remaining_balance_should_be_updated() {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//	@Then("New Remaining Balance should be updated")
//	public void new_remaining_balance_should_be_updated() {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//	@Then("Page Details should be match with DB tables.")
//	public void page_details_should_be_match_with_db_tables() {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
}
