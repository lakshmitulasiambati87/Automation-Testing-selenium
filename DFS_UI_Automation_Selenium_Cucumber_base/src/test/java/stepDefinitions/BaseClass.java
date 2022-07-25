package stepDefinitions;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.Scenario;
import pageObjects.InstallmentPlanSummary;
import pageObjects.LookupPage;
import utilities.CaptureScreenShot;
import utilities.DataBaseConnection;

public class BaseClass {
	public  WebDriver driver;
	public LookupPage lp;
	public InstallmentPlanSummary ips;
	public Properties configProp;
	public static CaptureScreenShot capture_ScreenShot;
	public Media mediaModel; 
	//ExtentReports extent;
	static ExtentReports extent;
	ExtentTest logger;
	static ExtentHtmlReporter htmlReporter;
	static ExtentSparkReporter spark;
	static ExtentTest test;
	//ExtentTest scenario;
	static ExtentTest node1;
	static Scenario scenario; 
	static String featureName;	
	String ban;
	public DataBaseConnection dbconnection;
}
