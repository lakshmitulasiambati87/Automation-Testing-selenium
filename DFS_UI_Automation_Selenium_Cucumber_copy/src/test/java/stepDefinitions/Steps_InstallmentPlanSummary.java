package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

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
import utilities.DataBaseConnection;

public class Steps_InstallmentPlanSummary extends BaseClass{
	
	
	@Then("Verify BAN")
	public void verify_ban() throws ClassNotFoundException, IOException 
	{
		//ips=new InstallmentPlanSummary(driver); 
		ExtentTest step1 = node1.createNode(new GherkinKeyword("Then"), "Verify BAN");
		try {
		String ui_ban=ips.returnBAN();
		String actual_ban = ui_ban.replace(": ", "");
		assertEquals(ban, actual_ban);
		step1.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());	
		}
		catch(Exception e)
		{
			//step1.log(Status.FAIL, "Fail");
			step1.fail(e);
			step1.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());
		}  
	}
	
	@Then("Verify BAN Status")
	public void verify_ban_status() throws ClassNotFoundException, IOException 
	{
		ExtentTest step1 = node1.createNode(new GherkinKeyword("Then"), "Verify BAN Status");
		dbconnection=new DataBaseConnection();
		try {
		String BAN_Query="";	
		String ban_status=dbconnection.getDBresult(BAN_Query);
		String ui_ban_status=ips.returnBANStatus();
		String actual_ban_status = ui_ban_status.replace(": ", "");
		assertEquals(ban_status, actual_ban_status);
		step1.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());	
		}
		catch(Exception e)
		{
			//step1.log(Status.FAIL, "Fail");
			step1.fail(e);
			step1.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());
		}
	}
	
	
	
	
	/*@BeforeAll
	public static void before() throws IOException
	{
		extent=new ExtentReports();
		htmlReporter = new ExtentHtmlReporter("target/Reports/HtmlReport/Extent1Html.html");
		//htmlReporter.config().setAutoCreateRelativePathMedia(true);
		//htmlReporter.setAppendExisting(true);
		htmlReporter.loadXMLConfig(new File("./Report/extent-config.xml"));
		extent.attachReporter(htmlReporter);
		spark=new ExtentSparkReporter("target/Reports/HtmlReport/automation1-report.html");
		spark.loadXMLConfig(new File("./Report/extent-config.xml"));
		extent.attachReporter(spark);
	}
	
	
	
	@After
	public void close_browser() throws IOException {
	extent.flush();	
	//Desktop.getDesktop().browse(new File("Reports/HtmlReport/ExtentHtml.html").toURI());
	driver.close();
	}
	
	@Before
	public void setUp(Scenario scenario) throws IOException, ClassNotFoundException
	{
		configProp=new Properties();
		FileInputStream confiProfile=new FileInputStream("config.properties");
		configProp.load(confiProfile);
		
		String br=configProp.getProperty("browser");
		
		if(br.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver",configProp.getProperty("chromepath"));
		driver=new ChromeDriver();
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",configProp.getProperty("firefoxpath"));
			driver=new FirefoxDriver();
		}
		else
		{
			System.setProperty("webdriver.ie.driver",configProp.getProperty("iepath"));
			driver=new InternetExplorerDriver();
		}
		capture_ScreenShot = new CaptureScreenShot(driver);
		this.scenario = scenario;
		//this.feature= feature;
		
		
		//scenario1 = scenario;
		test = extent.createTest(new GherkinKeyword("Feature"), "Lookup");
		//node1 = feature.createNode(new GherkinKeyword("Scenario"), scenario1.getName());
		node1 = test.createNode(new GherkinKeyword("Scenario"), scenario.getName());
		
	} */
	
	
	
	
	/* @AfterStep
	public void as(Scenario scenario) throws IOException
	{
		capture_ScreenShot = new CaptureScreenShot(driver);
		scenario.attach(capture_ScreenShot.getBase64Screenshot(), "image/png", "Screenshot");
	} */
	
	
/*	@AfterStep
	public void as(Scenario scenario) throws IOException
	{
		
		//logger=extent.createTest("Screenshot");
		//ExtentTest test = extent.createTest("MyFirstTest");
		//logger.pass("details", MediaEntityBuilder.createScreenCaptureFromPath(""+capture_ScreenShot.getBase64Screenshot()+"").build());

		//test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath("1.png").build());
	} */
/*
	@Given("User navigated to URL  {string}")
	public void opens_url(String url) throws IOException, ClassNotFoundException {
		lp=new LookupPage(driver); 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url); */
		/* ExtentTest test= extent.createTest("First Test");
		test.pass("Browser Opened");
		driver.get(url);
		//reporter= new Report(driver);
		//reporter.reportScreenShot();
		//scenario.attach(capture_ScreenShot.getBase64Screenshot(), "image/png", "screenname");
		test.pass("Value Entered", MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());
		test.pass("Finished"); */
		
		//ExtentTest step1 = node1.createNode(new GherkinKeyword("Given"), "User navigated to URL"+url+"");
		//step1.addScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot());
		//step1.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());
		
	//}
/*
	@When("Enter BAN")
	public void enter_ban() throws IOException, ClassNotFoundException {
		lp.enterBan("970655034");
		//reporter.reportScreenShot();
		
		ExtentTest step1 = node1.createNode(new GherkinKeyword("When"), "Enter BAN");
		//step1.addScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot());
		step1.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());
			
	}

	@When("Enter userid")
	public void enter_userid() throws IOException, ClassNotFoundException {
		lp.enterUserID("sdet");
		//reporter.reportScreenShot();
		ExtentTest step1 = node1.createNode(new GherkinKeyword("And"), "Enter userid");
		//step1.addScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot());
		step1.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());
		
	}

	@When("Enter channel")
	public void enter_channel() throws IOException, ClassNotFoundException {
		lp.enterChannel("CSM"); 
		//reporter.reportScreenShot();
		ExtentTest step1 = node1.createNode(new GherkinKeyword("And"), "Enter channel");
		//step1.addScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot());
		step1.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());
		
	}

	@When("Enter level")
	public void enter_level() throws IOException, ClassNotFoundException {
		lp.enterLevel("06");  
		//reporter.reportScreenShot();
		ExtentTest step1 = node1.createNode(new GherkinKeyword("And"), "Enter level");
		//step1.addScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot());
		step1.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());
		
	}

	@When("click on lookup button")
	public void click_on_lookup_button() throws IOException, ClassNotFoundException {
		lp.clickLookupPlanButton();
		//reporter.reportScreenShot();
		ExtentTest step1 = node1.createNode(new GherkinKeyword("And"), "click on lookup button");
		//step1.addScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot());
		step1.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());
		
		
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String exp_title) throws AWTException, IOException, ClassNotFoundException {
	   String title=driver.getTitle();
	   assertEquals(exp_title, title);
		//reporter.reportScreenShot();
		//reporter.extentFlush();
	   ExtentTest step1 = node1.createNode(new GherkinKeyword("Then"), "Page Title should be "+exp_title+"");
		//step1.addScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot());
	   step1.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());
		
	}
	
	@Then("I click lookup userid required error message should be displayed")
	public void i_click_lookup_userid_required_error_message_should_be_displayed() throws ClassNotFoundException, IOException {
	lp.clickLookupPlanButton();
	assertEquals(lp.returnUserIDError(), "User ID is required");
	ExtentTest step1 = node1.createNode(new GherkinKeyword("Then"), "I click lookup userid required error message should be displayed");
	//step1.addScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot());
    step1.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());
	   
	}
	@Then("Channel required error message should be displayed")
	public void channel_required_error_message_should_be_displayed() throws ClassNotFoundException, IOException {
	assertEquals(lp.returnchannelError(), "Channel is required");
	ExtentTest step1 = node1.createNode(new GherkinKeyword("Then"), "Channel required error message should be displayed");
	//step1.addScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot());
    step1.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());
	       
	}
*/

}
