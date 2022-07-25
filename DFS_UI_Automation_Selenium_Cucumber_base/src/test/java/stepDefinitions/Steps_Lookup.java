package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.AWTException;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

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
import utilities.WaitHelper;
public class Steps_Lookup extends BaseClass {
	
	ExtentTest scenario;
	static int Count =0;
	String name;
	String name1;
	String gban;
	float paymentAmt =21;
	float monthlyCharge;
	int noOfInstallment;
	String planID;
	
	
	
	@BeforeAll
	public static void before() throws IOException, ClassNotFoundException
	{
		
		String currenttime=CaptureScreenShot.getCurrentDateandTime();
		htmlReporter = new ExtentHtmlReporter("Reports/TestcaseReport_Screenshot/Reports "+currenttime+"/HtmlReport/Extent1Html.html");
		//htmlReporter.config().setAutoCreateRelativePathMedia(true);
		//htmlReporter.setAppendExisting(true);
		htmlReporter.loadXMLConfig(new File("./src/test/resources/extent-config.xml"));
		
	}
	
	/*@AfterStep
	public static void takeScreenshot(Scenario scenario) throws IOException
	{
		//final byte [] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(capture_ScreenShot.getByteScreenshot(), "image/png", "screenshot");
	}*/
	
	@After
	public void close_browser() throws IOException {
	//Desktop.getDesktop().browse(new File("Reports/HtmlReport/ExtentHtml.html").toURI());
	driver.close();
	driver.quit();
	}
	
//	@Afterall
//	public void teardown() throws IOException {
//	//Desktop.getDesktop().browse(new File("Reports/HtmlReport/ExtentHtml.html").toURI());
//	driver.quit();
//	}
	
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
		
		
	}
	
	

	@Given("User navigated to URL  {string}")
	public void opens_url(String url) throws IOException, ClassNotFoundException {
		//lp=new LookupPage(driver); 
		try {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		
		List  <WebElement> all_links = driver.findElements(By.tagName("a"));
		HashSet<String> hs = new HashSet<String>();
		//hs.addAll(all_links);
		
		for(int i=0; i<all_links.size()-1; i++) {
			hs.add(all_links.get(i).getText());
			
		}
		System.out.println(hs.toString());
		
		}
		catch(Exception e)
		{
			//step1.log(Status.FAIL, "Fail");
		}
	}

	@When("Enter BAN")
	public void enter_ban() throws IOException, ClassNotFoundException, SQLException {
		dbconnection=new DataBaseConnection();
		try {
//		String BAN_Query="select distinct(ACC_SAC_NBR) from ofsllprd.accounts where ACC_SAC_NBR in (select distinct(ACC_SAC_NBR) from ofsllprd.accounts where ACC_PRD_PRODUCT='LOAN' and acc_status_cd_tmo in ('Active', 'Active-Shipped')\r\n"
//				+ "and ACC_STATUS_CD = 'ACTIVE' Order by ACC_APP_DT desc fetch next 20 rows only) ORDER BY DBMS_RANDOM.RANDOM Fetch First 1 Row Only";	
//		String ban=dbconnection.getDBresult(BAN_Query);
//		//String ban1 = "618250536";
//		
//		lp.enterBan(ban);
		
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
		//abstract query to fetch ban
		String query="Select ins.ACCOUNT_NUMBER, dbl.ACCOUNT_TYPE, dbl.ACCOUNT_SUB_TYPE, dbl.BAN_STATUS, dbl.COL_DELINQ_STATUS, dbl.BILL_CYCLE, ins.ECL, ins.ECLA, ins.created_at from Installment_Account_ECL ins INNER JOIN billing_account@samson_dblink dbl ON ins.ACCOUNT_NUMBER=dbl.BAN WHERE ins.ACCOUNT_NUMBER=dbl.BAN and ins.DOUBLED_INDICATOR = 'N' and ins.ECL>'1500' and ins.ECLA > '720' and CREATED_AT > (sysdate-90) and dbl.ACCOUNT_TYPE='I' and dbl.ACCOUNT_SUB_TYPE='R' and dbl.CREDIT_CLASS='A' and dbl.BAN_STATUS='O' and dbl.COL_DELINQ_STATUS='N' and rownum<50 ORDER BY DBMS_RANDOM.RANDOM fetch first 1 row only";
		String ban =dbconnection.getabstractDBresult(query);
		gban = ban;
		lp.enterBan(ban);
		
		}
		catch(Exception e)
		{
			//step1.log(Status.FAIL, "Fail");
		}
		
		
		
	}

	@When("Enter userid")
	public void enter_userid() throws IOException, ClassNotFoundException {
		try {
		lp.enterUserID("sdet");
		}
		catch(Exception e)
		{
			//step1.log(Status.FAIL, "Fail");
		}
	}

	@When("Enter channel")
	public void enter_channel() throws IOException, ClassNotFoundException {
		try {
		lp.enterChannel("CSM"); 
		}
		catch(Exception e)
		{
			//step1.log(Status.FAIL, "Fail");
		}
	}

	@When("Enter level")
	public void enter_level() throws IOException, ClassNotFoundException {
		try {
		lp.enterLevel("06");  
		}
		catch(Exception e)
		{
			//step1.log(Status.FAIL, "Fail");
		}
	}

	@When("click on lookup button")
	public void click_on_lookup_button() throws IOException, ClassNotFoundException {
		try {
		lp.clickLookupPlanButton();
		}
		catch(Exception e)
		{
			//step1.log(Status.FAIL, "Fail");
		}
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String exp_title) throws AWTException, IOException, ClassNotFoundException {
		 try {
		String title=driver.getTitle();
	   assertEquals(exp_title, title);
	  
		 }
		 catch(Exception | org.opentest4j.AssertionFailedError e)
		 {
			//step1.log(Status.FAIL, "Fail");
		}
	}
	
	@Then("I click lookup userid required error message should be displayed")
	public void i_click_lookup_userid_required_error_message_should_be_displayed() throws ClassNotFoundException, IOException 
	{
		try {
		lp.clickLookupPlanButton();
		assertEquals(lp.returnUserIDError(), "User ID is required");
	
		}
		catch(Exception | org.opentest4j.AssertionFailedError e)
		{
			//step1.log(Status.FAIL, "Fail");
		}
	   
	}
	@Then("Channel required error message should be displayed")
	public void channel_required_error_message_should_be_displayed() throws ClassNotFoundException, IOException 
	{
		try {
	  assertEquals(lp.returnchannelError(), "Channel is required");
	
	//step1.addScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot());
		}
		catch(Exception | org.opentest4j.AssertionFailedError e)
		{
			//step1.log(Status.FAIL, "Fail");
		}
	}
	
	// MAKE PAYMENT
	
	@When("click on planid")
	public void click_on_planid() {
		//ips=new InstallmentPlanSummary(driver); 
	    ips.clickOnPlanID();
	   planID = ips.getPlanID();
	    
	}	
	
	@When("click on Makepayment")
	public void click_on_makepayment() {
	    ips.clickOnMakePayment();

	}
	@When("enter partial amount")
	public void enter_partial_amount() {
	     
	   //  this.paymentAmt=21;
		ips.enterPartialAmount(paymentAmt);
	    
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
	    ips.clickonSubmitButton();
	}
	@Then("Page should display {string}")
	public void page_should_display(String string) {
		try {
			  assertEquals(ips.getSuccessMessage(), string);
			
			//step1.addScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot());
				}
				catch(Exception | org.opentest4j.AssertionFailedError e)
				{
					//step1.log(Status.FAIL, "Fail");
				}
	}
	@Then("Status should be Active-Additional-Payment-Applied")
	public void status_should_be_active_additional_payment_applied() {
		try {
			  assertEquals(ips.getBANstatus(), "Active-Additional-Payment-Applied");
			
			//step1.addScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot());
				}
				catch(Exception | org.opentest4j.AssertionFailedError e)
				{
					//step1.log(Status.FAIL, "Fail");
				}
	}
	@Then("Current Remaining Balance should be updated")
	public void current_remaining_balance_should_be_updated() {
		String crbb = ips.getcurrentRemainingBalancebeforePayment();
		System.out.println(crbb);
		crbb = crbb.replace("$", "");
		System.out.println(crbb);
		Float crbbp=Float.parseFloat(crbb);
		System.out.println(crbbp);
		try {
			  assertEquals(Float.parseFloat(ips.getnewRemainingBalance()), crbbp);
			
			//step1.addScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot());
				}
				catch(Exception | org.opentest4j.AssertionFailedError e)
				{
					//step1.log(Status.FAIL, "Fail");
				}
	    
	}
	@Then("New Remaining Balance should be updated")
	public void new_remaining_balance_should_be_updated() {
		String crbb = ips.getcurrentRemainingBalancebeforePayment();
		crbb = crbb.replace("$", "");
		float crbbp=Float.parseFloat(crbb);
		try {
			  assertEquals(Float.parseFloat(ips.getcurrentRemainingBalance()), crbbp);
			
			//step1.addScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot());
				}
				catch(Exception | org.opentest4j.AssertionFailedError e)
				{
					//step1.log(Status.FAIL, "Fail");
				}
	    }
	@Then("Page Details should be match with DB tables.")
	public void page_details_should_be_match_with_db_tables() {
		dbconnection=new DataBaseConnection();
		try {
			
			

			//String query="select acc.acc_NBR as LOAN_ID,acc.ACC_STATUS_CD as LOAN_STATUS,acc.ACC_SAC_NBR as BAN,pay.pmt_jpay_id as JPAYID,pay.PMT_ID as paymentTransactionId,txn.TXN_DT as TransactionDate,TO_CHAR(pay.pmt_amt,'9999999.99') as AmountPaid from ofsllprd.payments pay,ofsllprd.txns txn,ofsllprd.accounts acc where txn.txn_pmt_id=pay.pmt_id and acc.acc_aad_id=txn.txn_aad_id and acc.ACC_SAC_NBR='"+ gban +"'";
//			String query="select TO_CHAR(pay.pmt_amt,'9999999.99') as AmountPaid from ofsllprd.payments pay,ofsllprd.txns txn,ofsllprd.accounts acc where txn.txn_pmt_id=pay.pmt_id and acc.acc_aad_id=txn.txn_aad_id and acc.ACC_SAC_NBR='"+ gban +"'";
//			
//			//System.out.println(query);
//			
//			String payment_amount =dbconnection.getofsllDBresult(query);
//			System.out.println(payment_amount);
			
			
			}
			catch(Exception e)
			{
				//step1.log(Status.FAIL, "Fail");
			}
	}
	
	@When("enter remaining amount")
	public void enter_remaining_amount() {

		String crbb = ips.getcurrentRemainingBalancebeforePayment();
		System.out.println(crbb);
		crbb = crbb.replace("$", "");
		System.out.println(crbb);
		Float crbbp=Float.parseFloat(crbb);
		System.out.println(crbbp);
		ips.enterPartialAmount(crbbp);
		
	}
	
	@When("fetch the monthly charge amount")
	public void fetch_the_monthly_charge_amount() {
	   //int planid = Integer.parseInt(ips.getPlanID());
		monthlyCharge = stringToFloat(ips.getmonthlyCharge());
		
	}
	@When("enter monthly charge amount")
	public void enter_monthly_charge_amount() {
		
		System.out.println(monthlyCharge +"monthlycharge");
		ips.enterPartialAmount(monthlyCharge);
		
		noOfInstallment = ips.getnoOfInstallments(planID); 
		System.out.println(planID+"planid");
		
		
		System.out.println(noOfInstallment+"noOfInstallment");
		
	}
	@Then("Current Remaining Number of Installments should be reduced by one")
	public void current_remaining_number_of_installments_should_be_reduced_by_one() {
	    
		System.out.println(planID);
		int noOfInstallmentAfterPayment = ips.getnoOfInstallments(planID);
		try {
			  assertEquals(noOfInstallment-1, noOfInstallmentAfterPayment);
			
			//step1.addScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot());
				}
				catch(Exception | org.opentest4j.AssertionFailedError e)
				{
					//step1.log(Status.FAIL, "Fail");
				}
	    
	    
	}
	@Then("agreement status should be Active-Additional-Payment-Applied")
	public void agremment_status_in_agree_shouldbe_active_additional_payment_applied() {
	    driver.navigate().back();
	    try {
			  assertEquals(ips.getequipmentStatus(), "Active-Additional-Payment-Applied");
			
			//step1.addScreenCaptureFromBase64String(capture_ScreenShot.getBase64Screenshot());
				}
				catch(Exception | org.opentest4j.AssertionFailedError e)
				{
					//step1.log(Status.FAIL, "Fail");
				}
	    
	    
	}
	
	@When("Enter BAN with multiline loan")
	public void enter_ban_with_multiline_loan() {
		dbconnection=new DataBaseConnection();
		try {

		// query to fetch ban with multiline loan
		String query="select ACC_SAC_NBR from ofsllprd.accounts where ACC_PRD_PRODUCT = 'LOAN' and ACC_STATUS_CD_TMO in ('Active', 'Active-Shipped') " ;
		query=query	+ " group by ACC_AGREEMENT_NBR_TMO,ACC_SAC_NBR,ACC_STATUS_CD_TMO,ACC_PRD_PRODUCT,ACC_APP_DT having count(distinct(acc_NBR))=2 Order by DBMS_RANDOM.RANDOM fetch next 100 rows only ";
		
		//String qquery = "select ACC_SAC_NBR from ofsllprd.accounts where ACC_PRD_PRODUCT = 'LOAN' and ACC_STATUS_CD_TMO in ('Active', 'Active-Shipped') and rownum<50 group by ACC_AGREEMENT_NBR_TMO,ACC_SAC_NBR,ACC_STATUS_CD_TMO,ACC_PRD_PRODUCT,ACC_APP_DT having count(distinct(acc_NBR))=2 Order by ACC_APP_DT desc fetch next 20 rows only ";
		
		
		String ban =dbconnection.getDBresult(query);
		gban = ban;
		
		lp.enterBan(ban);
		
		
		}
		catch(Exception e)
		{
			//step1.log(Status.FAIL, "Fail");
		}
		
		
	}
	
	//Equipment return
	
	@When("click on Return checkbox")
	public void click_on_return_checkbox() {
		ips.clickOnReturnCheckbox();

		
	}
	@When("click on Return Selected Items")
	public void click_on_return_selected_items() {
	    ips.clickonReturnSelectItemButton();
		
	}
	
	@When("check status of equipment")
	public void check_status_of_equipment() {
	    System.out.println(planID);
	    dbconnection=new DataBaseConnection();
		try {

		String query = "select agr.AGR_STATUS_CODE as AgreementStatus from OFSLLPRD.AGREEMENTS_TMO agr where agr.AGR_AGREEMENT_NBR in ('"+planID+"')";
			
			
		System.out.println(query);
		
		String eqp_status =dbconnection.getofsllDBresult(query);
		
		System.out.println(eqp_status+"...eqpstatus");
		
		}
		catch(Exception e)
		{
			//step1.log(Status.FAIL, "Fail");
		}
	}
	@When("enter Email and verify email")
	public void enter_email_and_verify_email() {
	    String mailID = "Nikhil.Manjunath1@T-Mobile.com";
	    ips.sendemailID(mailID);
	    ips.verifyemailID(mailID);
		
	}
	
	@When("click on Submit in pop up")
	public void click_on_submit_in_pop_up() throws InterruptedException {
	    Thread.sleep(3000);
		ips.clickAcceptButton();
		Thread.sleep(3000);
	}
	
	
	@When("Accept the alert")
	public void accept_the_alert() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());
	    driver.switchTo().alert().accept();
	}
	@Then("agreement status in agreements_tmo table should be {string}")
	public void agreement_status_in_agreements_tmo_table_should_be(String string) {
		dbconnection=new DataBaseConnection();
		try {

		String query = "Select agr.AGR_STATUS_CODE as AgreementStatus from OFSLLPRD.AGREEMENTS_TMO agr"
				+ " where agr.AGR_AGREEMENT_NBR in ('"+planID+"')";
			
			
		String eqp_status =dbconnection.getofsllDBresult(query);
		
		System.out.println(eqp_status+"eqpstatus");
		
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(eqp_status, string);
		
		}
		catch(Exception e)
		{
			//step1.log(Status.FAIL, "Fail");
		}
	}
	
	
	@Then("FAT should have {string} activity with -ve outstanding balance in balance_activity column")
	public void fat_should_have_activity_with_ve_outstanding_balance_in_balance_activity_column(String string) {
		dbconnection=new DataBaseConnection();
		try {

		String query = "select FAT_BAL_ACTVY_AMT_DEVICE from OFSLLPRD.financial_activity_tmo where  FAT_ACTIVITY_DESCRIPTION='Return Complete' "
				+ "and FAT_PLAN_ID='"+planID+"' order by FAT_EIP_EQUIPMENT_ID";
			
			
		System.out.println(query);
		
		String FAT_BAL_ACTVY_AMT_DEVICE =dbconnection.getofsllDBresult(query);
		
		System.out.println(FAT_BAL_ACTVY_AMT_DEVICE+"...FAT_BAL_ACTVY_AMT_DEVICE");
		
		}
		catch(Exception e)
		{
			System.out.println("db connection error");
		}
	}
	
	@When("navigate back to summary screen")
	public void navigate_back_to_summary_screen() throws InterruptedException {
	    driver.navigate().back();
	    Thread.sleep(2000);
	    driver.navigate().back();
	}
	
	
	
	
	
	public float stringToFloat(String s) {
		s=s.replace("$", "");
		return Float.parseFloat(s);
	}
	/*@AfterAll
	public static void teardown()
	{
		extent.flush();
	}
	*/
	

}
