package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class InstallmentPlanSummary {
	public WebDriver ldriver;
	public WaitHelper waithelper;
	
	public InstallmentPlanSummary(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(ldriver, this);
		waithelper=new WaitHelper(ldriver);
	}
	
	@FindBy(xpath="(//span[.='BAN']/../../td)[1]")
	@CacheLookup
	WebElement BAN;
	
	@FindBy(xpath="(//span[.='Ban Status']/../../td)[1]")
	@CacheLookup
	WebElement BANStatus;
	
	
	public String returnBAN()
	{
		waithelper.waitForElement(BAN, 30);
		return BAN.getText();
	}
	
	public String returnBANStatus()
	{
		waithelper.waitForElement(BANStatus, 30);
		return BANStatus.getText();
	}

}
