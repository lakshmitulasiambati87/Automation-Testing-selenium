package pageObjects;

import org.openqa.selenium.By;
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
	
	@FindBy(xpath="(//table[@id='InstallmentPlansTable']/tbody/tr/td[text()='Active-Shipped' or text()='Active' ]//preceding-sibling::td)[1]")
	@CacheLookup
	WebElement PlanID;
	
	public void clickOnPlanID() {
		waithelper.waitForElement(PlanID, 60);
		PlanID.click();
	}
	
	public String getPlanID() {
		waithelper.waitForElement(PlanID, 60);
		return PlanID.getText();
		
	}
	
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
	
	public void enterPartialAmount(Float a) {
		waithelper.waitForElement(PaymentAmountTxtBox, 30);
		PaymentAmountTxtBox.sendKeys(String.valueOf(a));
	}
	
	public void clickOnMakePayment()
	{
		waithelper.waitForElement(MakePaymentButton, 30);
		MakePaymentButton.click();
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
	
	@FindBy(id="submitButton")
	@CacheLookup
	WebElement submitButton;
	
	public void clickonSubmitButton()
	{
		submitButton.click();
	}
	
	@FindBy(name = "success")
	@CacheLookup
	WebElement successMessage;
	
	public String getSuccessMessage()
	{
		waithelper.waitForElement(successMessage, 30);
		return successMessage.getText();
	}
	
	@FindBy(xpath="//table[@id=\"paymentTable\"]/tbody/tr/td[contains(text(),\"Active-Shipped-Additional-Payment-Applied\")]")
	@CacheLookup
	WebElement banStatus;
	
	public String getBANstatus()
	{
		waithelper.waitForElement(banStatus, 60);
		return banStatus.getText();
	}
	
	@FindBy(xpath="//table[@id=\"paymentTable\"]/tbody/tr/td[contains(text(),\"Active-Additional-Payment-Applied\")]//preceding-sibling::td[2][1]")
	@CacheLookup
	WebElement currentRemainingBalance;
	
	public String getcurrentRemainingBalance() {
		waithelper.waitForElement(currentRemainingBalance, 60);
		return currentRemainingBalance.getText();
	}
	
	@FindBy(xpath="//table[@id=\"paymentTable\"]/tbody/tr/td[contains(text(),\"Active-Additional-Payment-Applied\")]//following-sibling::td[2][1]")
	@CacheLookup
	WebElement newRemainingBalance;
	
	public String getnewRemainingBalance() {
		waithelper.waitForElement(newRemainingBalance, 30);
		return newRemainingBalance.getText();
	}
	
	@FindBy(xpath="//table[@id=\"paymentTable\"]/tbody/tr/td[contains(text(),\"Active\")]//preceding-sibling::td[2][1]")
	@CacheLookup
	WebElement currentRemainingBalancebeforePayment;
	
	public String getcurrentRemainingBalancebeforePayment() {
		waithelper.waitForElement(currentRemainingBalancebeforePayment, 30);
		return currentRemainingBalancebeforePayment.getText();
		
	}
	
	@FindBy(xpath="//table[@id='InstallmentPlansTable']/tbody/tr/td[text()='Active-Shipped' or text()='Active' ]//following-sibling::td[8]")
	@CacheLookup
	WebElement monthlyCharge;
	
	public String getmonthlyCharge() {
		waithelper.waitForElement(monthlyCharge, 30);
		return monthlyCharge.getText();
		
	}
	
//	@FindBy(xpath="//table[@id='InstallmentPlansTable']/tbody/tr/td[text()='Active-Shipped' or text()='Active' ]//following-sibling::td[8]")
//	@CacheLookup
//	WebElement noOfInstallments;
	
	public int getnoOfInstallments(String PlanID) {
		WebElement noOfInstallments =ldriver.findElement(By.xpath("//td[contains(text(),'"+PlanID+"')]/following::tr/td[5]"));
		waithelper.waitForElement(noOfInstallments, 30);
		 int ni = Integer.parseInt(noOfInstallments.getText());
		 return ni;
		
	}
	
	@FindBy(xpath="//table[@id=\"EquipmentDetailsTable\"]/tbody/tr/td")
	@CacheLookup
	WebElement equipmentStatus;
	
	public String getequipmentStatus() {
		waithelper.waitForElement(equipmentStatus, 30);
		return equipmentStatus.getText();
		
	}
	
	
	@FindBy(name="itemsToReturn")
	@CacheLookup
	WebElement returnCheckbox;
	
	public void clickOnReturnCheckbox() {
		waithelper.waitForElement(returnCheckbox, 30);
		 returnCheckbox.click();
		
	}
	
	@FindBy(xpath="//input[@value=\"Return Selected Items\"]")
	@CacheLookup
	WebElement returnSelectedItemsbutton;
	
	public void clickonReturnSelectItemButton() {
		waithelper.waitForElement(returnSelectedItemsbutton, 30);
		returnSelectedItemsbutton.click();
		
	}
	
	@FindBy(id="emailId")
	@CacheLookup
	WebElement emailID;
	
	public void sendemailID(String mailID) {
		waithelper.waitForElement(emailID, 30);
		emailID.sendKeys(mailID);
	}
	
	@FindBy(id="verifyEmail")
	@CacheLookup
	WebElement vemailID;
	
	public void verifyemailID(String mailID) {
		waithelper.waitForElement(vemailID, 30);
		vemailID.sendKeys(mailID);
	}
	
	@FindBy(xpath="//button/span[contains(text(),'Accept')]")
	@CacheLookup
	WebElement accept_button;
	
	public void clickAcceptButton() {
		waithelper.waitForElement(accept_button, 30);
		accept_button.click();
	}
	
	
	

}
