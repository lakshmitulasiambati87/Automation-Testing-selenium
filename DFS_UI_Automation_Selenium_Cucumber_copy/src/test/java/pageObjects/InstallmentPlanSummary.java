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
	
	@FindBy(xpath="(//table[@id='InstallmentPlansTable']/tbody/tr/td[text()='Active-Shipped' or text()='Active' ]//preceding-sibling::td)")
	@CacheLookup
	WebElement PlanID;
	
	@FindBy(xpath="//input[@value='Make Payment']")
	@CacheLookup
	WebElement MakePaymentButton;
	
	@FindBy(xpath="//table[@id='paymentTable']/tbody//input[@type='text']")
	@CacheLookup
	WebElement PaymentAmountTxtBox;
	
	@FindBy(id="email")
	@CacheLookup
	WebElement EmailTxtBox;
	
	@FindBy(id="verifyEmail")
	@CacheLookup
	WebElement VerifyEmailTxtBox;
	
	public void verifyEmail(String s) {
		VerifyEmailTxtBox.sendKeys(s);
	}
	
	public void enterEmail(String s) {
		EmailTxtBox.sendKeys(s);
	}
	
	public void enterPartialAmount(int a) {
		waithelper.waitForElement(PaymentAmountTxtBox, 30);
		PaymentAmountTxtBox.sendKeys(String.valueOf(a));
	}
	
	public void clickOnMakePayment()
	{
		waithelper.waitForElement(MakePaymentButton, 30);
		MakePaymentButton.click();
	}
	
	public void clickOnPlanID() {
		waithelper.waitForElement(PlanID, 30);
		PlanID.click();
	}
	
	
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
