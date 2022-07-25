package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;


public class LookupPage {
	public WebDriver ldriver;
	public WaitHelper waithelper;
	
	public LookupPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(ldriver, this);
		waithelper=new WaitHelper(ldriver);
	}
	
	@FindBy(id="ban")
	@CacheLookup
	WebElement ban_textbox;
	
	@FindBy(id="userID")
	@CacheLookup
	WebElement userID_textbox;
	
	@FindBy(id="level")
	@CacheLookup
	WebElement level_textbox;
	
	@FindBy(id="channel")
	@CacheLookup
	WebElement channel_textbox;
	
	@FindBy(id="LookupPlanButton")
	@CacheLookup
	WebElement lookupPlanButton;
	
	@FindBy(xpath="//span[.='User ID is required']")
	@CacheLookup
	WebElement userIDError;
	
	@FindBy(xpath="//span[.='Channel is required']")
	@CacheLookup
	WebElement channelError;
	
	@FindBy(xpath="//td[contains(text(),'\"+PlanId+\"')]")
	WebElement planIDheading;
	
	
	public void enterBan(String BAN)
	{
		waithelper.waitForElement(ban_textbox, 30);
		ban_textbox.clear();
		ban_textbox.sendKeys(BAN);
	}
	
	public void enterUserID(String userid)
	{
		waithelper.waitForElement(userID_textbox, 30);
		userID_textbox.clear();
		userID_textbox.sendKeys(userid);
	}
	
	public void enterLevel(String level)
	{
		waithelper.waitForElement(level_textbox, 30);
		level_textbox.clear();
		level_textbox.sendKeys(level);
	}
	
	public void enterChannel(String channel)
	{
		waithelper.waitForElement(channel_textbox, 30);
		channel_textbox.clear();
		channel_textbox.sendKeys(channel);
	}
	
	public void clickLookupPlanButton()
	{
		waithelper.waitForElement(lookupPlanButton, 30);
		lookupPlanButton.click();
	}
	
	
	public String returnUserIDError()
	{
		waithelper.waitForElement(userIDError, 30);
		return userIDError.getText();
	}
	
	public String returnchannelError()
	{
		waithelper.waitForElement(channelError, 30);
		return channelError.getText();
	}
	
//	@FindBy(xpath="(//table[@id='InstallmentPlansTable']/tbody/tr/td[text()='Active-Shipped' or text()='Active' ]//preceding-sibling::td)[1]")
//	@CacheLookup
//	WebElement PlanID;
//	
//	public void clickOnPlanID() {
//		waithelper.waitForElement(PlanID, 60);
//		PlanID.click();
//	}
}
