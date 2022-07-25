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

	static int Count =0;
	String name;
	String name1;
	
//	@BeforeAll
//	public static void before() throws IOException, ClassNotFoundException
//	{
//		
//		String currenttime=CaptureScreenShot.getCurrentDateandTime();
//		extent=new ExtentReports();
//		htmlReporter = new ExtentHtmlReporter("Reports/TestcaseReport_Screenshot/Reports "+currenttime+"/HtmlReport/Extent1Html.html");
//		//htmlReporter.config().setAutoCreateRelativePathMedia(true);
//		//htmlReporter.setAppendExisting(true);
//		htmlReporter.loadXMLConfig(new File("./src/test/resources/extent-config.xml"));
//		extent.attachReporter(htmlReporter);
//		spark=new ExtentSparkReporter("Reports/TestcaseReport_Screenshot/Reports "+currenttime+"/HtmlReport/automation1-report.html");
//		spark.loadXMLConfig(new File("./src/test/resources/extent-config.xml"));
//		extent.attachReporter(spark);
//	}
//	
	/*@AfterStep
	public static void takeScreenshot(Scenario scenario) throws IOException
	{
		//final byte [] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(capture_ScreenShot.getByteScreenshot(), "image/png", "screenshot");
	}*/
	
//	@After
//	public void close_browser() throws IOException {
//	extent.flush();	
//	//Desktop.getDesktop().browse(new File("Reports/HtmlReport/ExtentHtml.html").toURI());
//	driver.close();
//	}
//	
//	@Before
//	public void setUp(Scenario scenario) throws IOException, ClassNotFoundException
//	{
//		configProp=new Properties();
//		FileInputStream confiProfile=new FileInputStream("./src/test/resources/config.properties");
//		configProp.load(confiProfile);
//		
//		String br=configProp.getProperty("browser");
//		
//		if(br.equals("chrome"))
//		{
//		System.setProperty("webdriver.chrome.driver",configProp.getProperty("chromepath"));
//		driver=new ChromeDriver();
//		}
//		else if(br.equals("firefox"))
//		{
//			System.setProperty("webdriver.gecko.driver",configProp.getProperty("firefoxpath"));
//			driver=new FirefoxDriver();
//		}
//		else
//		{
//			System.setProperty("webdriver.ie.driver",configProp.getProperty("iepath"));
//			driver=new InternetExplorerDriver();
//		}
//		capture_ScreenShot = new CaptureScreenShot(driver);
//		//this.scenario = scenario;
//		//this.feature= feature;
//		name=featureName;
//		featureName = FilenameUtils.getBaseName(scenario.getUri().toString());
//		
//		if(Count<1)
//		{
//		test = extent.createTest(new GherkinKeyword("Feature"),featureName);
//		Count++;
//		}
//		else if(!(featureName.equals(name)))
//		{
//		//String fullFeatureName = FilenameUtils.getName(scenario.getUri().toString());
//		//this.scenario = scenario;
//		 test = extent.createTest(new GherkinKeyword("Feature"),featureName);
//		}
//		//node1 = feature.createNode(new GherkinKeyword("Scenario"), scenario1.getName());
//		node1 = test.createNode(new GherkinKeyword("Scenario"), scenario.getName());
//		Collection<String> tags = scenario.getSourceTagNames();
//		for(String category:tags)
//		{	
//		node1.assignCategory(category);
//		}
//	}
//	
	
//	@Given("User navigated to URL {string}")
//	public void user_navigated_to_url(String url) throws ClassNotFoundException, IOException 
//	{
//		//ExtentTest step1 = node1.createNode(new GherkinKeyword("Given"), "User navigated to URL"+url+"");
//		ips=new InstallmentPlanSummary(driver); 
//		try {
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.get(url);
//		//step1.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());	
//		}
//		catch(Exception e)
//		{
//			//step1.log(Status.FAIL, "Fail");
//			//step1.fail(e);
//			//step1.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());	
//		}
//		throw new io.cucumber.java.PendingException();
//	}
	
//	@Given("User navigated to URL {string}")
//	public void user_navigated_to_url(String string) {
////		lp=new LookupPage(driver); 
////		ips=new InstallmentPlanSummary(driver); 
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.get(string);
//		
//	}
//	
	@When("click on planid")
	public void click_on_planid() {
		ips=new InstallmentPlanSummary(driver); 
	    ips.clickOnPlanID();
	    
	}	
	
	
	
	
//	@When("click on plainid")
//	public void click_on_planid() {
//	    
//		ips.clickOnPlanID();
//	   
//	}
	@When("click on Makepayment")
	public void click_on_makepayment() {
	    ips.clickOnMakePayment();

	}
	@When("enter partial amount")
	public void enter_partial_amount() {
	    ips.enterPartialAmount(20);
	    
	}
	@When("enter Email for Notifications")
	public void enter_email_for_notifications() {
	    ips.enterEmail("abc@xyz.com");
	}
	@When("enter verify Email Adress")
	public void enter_verify_email_adress() {
	    ips.verifyEmail("abc@xyz.com");
	}
	@When("click on Submit")
	public void click_on_submit() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("Page should display {string}")
	public void page_should_display(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("Status should be Active-Additional-Payment-Applied")
	public void status_should_be_active_additional_payment_applied() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("Current Remaining Balance should be updated")
	public void current_remaining_balance_should_be_updated() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("New Remaining Balance should be updated")
	public void new_remaining_balance_should_be_updated() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("Page Details should be match with DB tables.")
	public void page_details_should_be_match_with_db_tables() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
