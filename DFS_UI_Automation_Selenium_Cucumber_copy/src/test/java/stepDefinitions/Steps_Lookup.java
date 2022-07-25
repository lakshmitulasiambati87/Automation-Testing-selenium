package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.AWTException;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Collection;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import pageObjects.InstallmentPlanSummary;
import pageObjects.LookupPage;
import utilities.CaptureScreenShot;
import utilities.DataBaseConnection;
public class Steps_Lookup extends BaseClass {
	
	//ExtentTest scenario;
	static int Count =0;
	String name;
	String name1;
	
	
	@BeforeAll
	public static void before() throws IOException, ClassNotFoundException
	{
		
		String currenttime=CaptureScreenShot.getCurrentDateandTime();
		extent=new ExtentReports();
		htmlReporter = new ExtentHtmlReporter("Reports/TestcaseReport_Screenshot/Reports "+currenttime+"/HtmlReport/Extent1Html.html");
		//htmlReporter.config().setAutoCreateRelativePathMedia(true);
		//htmlReporter.setAppendExisting(true);
		htmlReporter.loadXMLConfig(new File("./src/test/resources/extent-config.xml"));
		extent.attachReporter(htmlReporter);
		spark=new ExtentSparkReporter("Reports/TestcaseReport_Screenshot/Reports "+currenttime+"/HtmlReport/automation1-report.html");
		spark.loadXMLConfig(new File("./src/test/resources/extent-config.xml"));
		extent.attachReporter(spark);
		
	}
	
	/*@AfterStep
	public static void takeScreenshot(Scenario scenario) throws IOException
	{
		//final byte [] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(capture_ScreenShot.getByteScreenshot(), "image/png", "screenshot");
	}*/
	
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
		FileInputStream confiProfile=new FileInputStream("./src/test/resources/config.properties");
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
		
		ips=new InstallmentPlanSummary(driver);
		lp=new LookupPage(driver); 
		
		capture_ScreenShot = new CaptureScreenShot(driver);
		//this.scenario = scenario;
		//this.feature= feature;
		name=featureName;
		featureName = FilenameUtils.getBaseName(scenario.getUri().toString());
		
		if(Count<1)
		{
		test = extent.createTest(new GherkinKeyword("Feature"),featureName);
		Count++;
		}
		else if(!(featureName.equals(name)))
		{
		//String fullFeatureName = FilenameUtils.getName(scenario.getUri().toString());
		//this.scenario = scenario;
		 test = extent.createTest(new GherkinKeyword("Feature"),featureName);
		}
		//node1 = feature.createNode(new GherkinKeyword("Scenario"), scenario1.getName());
		node1 = test.createNode(new GherkinKeyword("Scenario"), scenario.getName());
		Collection<String> tags = scenario.getSourceTagNames();
		for(String category:tags)
		{	
		node1.assignCategory(category);
		}
	}
	
	

	@Given("User navigated to URL  {string}")
	public void opens_url(String url) throws IOException, ClassNotFoundException {
		ExtentTest step1 = node1.createNode(new GherkinKeyword("Given"), "User navigated to URL"+url+"");
		//lp=new LookupPage(driver); 
		try {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		step1.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());	
		}
		catch(Exception e)
		{
			//step1.log(Status.FAIL, "Fail");
			step1.fail(e);
			step1.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());	
		}
	}

	@When("Enter BAN")
	public void enter_ban() throws IOException, ClassNotFoundException, SQLException {
		ExtentTest step1 = node1.createNode(new GherkinKeyword("When"), "Enter BAN");
		dbconnection=new DataBaseConnection();
		try {
		String BAN_Query="select distinct(ACC_SAC_NBR) from ofsllprd.accounts where ACC_SAC_NBR in (select distinct(ACC_SAC_NBR) from ofsllprd.accounts where ACC_PRD_PRODUCT='LOAN' and acc_status_cd_tmo in ('Active', 'Active-Shipped')\r\n"
				+ "and ACC_STATUS_CD = 'ACTIVE' Order by ACC_APP_DT desc fetch next 20 rows only) ORDER BY DBMS_RANDOM.RANDOM Fetch First 1 Row Only";	
		String ban=dbconnection.getDBresult(BAN_Query);
		lp.enterBan(ban);
		
		//samson db to fetch ban
//		String query ="select b.ban, s.subscriber_no, s.sub_status, b.sys_creation_date from billing_account b, \r\n"
//				+ "subscriber s where s.customer_id=b.customer_id and b.CREDIT_CLASS='A' \r\n"
//				+ "and b.ACCOUNT_TYPE='I' and b.BAN_STATUS='O' and b.COL_DELINQ_STATUS='N'\r\n"
//				+ "and s.SUB_Status='A'and b.sys_creation_date > (sysdate-180) and subscriber_no in (select subscriber_no from service_agreement \r\n"
//				+ "where Expiration_date is null) and b.customer_id in \r\n"
//				+ "(select customer_id from subscriber group by customer_id having count(subscriber_no)>1) ORDER BY DBMS_RANDOM.RANDOM Fetch First 1 Row Only";
//		String ban =dbconnection.getsamsonDBresult(query);
//		lp.enterBan(ban);
//		
		
		step1.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());	
		}
		catch(Exception e)
		{
			//step1.log(Status.FAIL, "Fail");
			step1.fail(e);
			step1.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());
		}
		
		
		
	}

	@When("Enter userid")
	public void enter_userid() throws IOException, ClassNotFoundException {
		ExtentTest step1 = node1.createNode(new GherkinKeyword("And"), "Enter userid");
		try {
		lp.enterUserID("sdet");
		step1.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());	
		}
		catch(Exception e)
		{
			//step1.log(Status.FAIL, "Fail");
			step1.fail(e);
			step1.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());
		}
	}

	@When("Enter channel")
	public void enter_channel() throws IOException, ClassNotFoundException {
		ExtentTest step1 = node1.createNode(new GherkinKeyword("And"), "Enter channel");
		try {
		lp.enterChannel("CSM"); 
		step1.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());	
		}
		catch(Exception e)
		{
			//step1.log(Status.FAIL, "Fail");
			step1.fail(e);
			step1.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());
		}
	}

	@When("Enter level")
	public void enter_level() throws IOException, ClassNotFoundException {
		ExtentTest step1 = node1.createNode(new GherkinKeyword("And"), "Enter level");
		try {
		lp.enterLevel("06");  
		step1.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());
		}
		catch(Exception e)
		{
			//step1.log(Status.FAIL, "Fail");
			step1.fail(e);
			step1.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());
		}
	}

	@When("click on lookup button")
	public void click_on_lookup_button() throws IOException, ClassNotFoundException {
		ExtentTest step1 = node1.createNode(new GherkinKeyword("And"), "click on lookup button");
		try {
		lp.clickLookupPlanButton();
		step1.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());	
		}
		catch(Exception e)
		{
			//step1.log(Status.FAIL, "Fail");
			step1.fail(e);
			step1.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());
		}
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String exp_title) throws AWTException, IOException, ClassNotFoundException {
		 ExtentTest step1 = node1.createNode(new GherkinKeyword("Then"), "Page Title should be "+exp_title+"");
		 try {
		String title=driver.getTitle();
	   assertEquals(exp_title, title);
	  
	   step1.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());
		 }
		 catch(Exception | org.opentest4j.AssertionFailedError e)
		 {
			//step1.log(Status.FAIL, "Fail");
			step1.fail(e);
			step1.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());
		}
	}
	
	@Then("I click lookup userid required error message should be displayed")
	public void i_click_lookup_userid_required_error_message_should_be_displayed() throws ClassNotFoundException, IOException 
	{
		ExtentTest step1 = node1.createNode(new GherkinKeyword("Then"), "I click lookup userid required error message should be displayed");
		try {
		lp.clickLookupPlanButton();
		assertEquals(lp.returnUserIDError(), "User ID is required");
	
		step1.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());
		}
		catch(Exception | org.opentest4j.AssertionFailedError e)
		{
			//step1.log(Status.FAIL, "Fail");
			step1.fail(e);
			step1.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());
		}
	   
	}
	@Then("Channel required error message should be displayed")
	public void channel_required_error_message_should_be_displayed() throws ClassNotFoundException, IOException 
	{
		ExtentTest step1 = node1.createNode(new GherkinKeyword("Then"), "Channel required error message should be displayed");
		try {
	  assertEquals(lp.returnchannelError(), "Channel is required");
	
	//step1.addScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot());
         step1.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());     
		}
		catch(Exception | org.opentest4j.AssertionFailedError e)
		{
			//step1.log(Status.FAIL, "Fail");
			step1.fail(e);
			step1.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot()).build());
		}
	}
	
	
	/*@AfterAll
	public static void teardown()
	{
		extent.flush();
	}
	*/
	

}
