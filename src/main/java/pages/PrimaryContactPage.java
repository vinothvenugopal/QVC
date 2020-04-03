package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.BaseClass;

public class PrimaryContactPage extends BaseClass {

	public PrimaryContactPage(RemoteWebDriver driver) {
		this.driver = driver;
	}
	
	public PrimaryContactPage enterSponsorMobileNumber(String mobileNumber) throws InterruptedException
	{
		//Thread.sleep(1000);
		WebElement ele = locateElement("id", "phone");
		//WebElement ele = driver.findElementById("phone");
		clearAndEnter(ele, mobileNumber);
		return this;
	}
	public PrimaryContactPage enterSponsorEmailId(String emailID)
	{
		WebElement ele = locateElement("id", "email");
		//WebElement ele = driver.findElementById("email");
		clearAndEnter(ele, emailID);
		return this;
	}
	public PrimaryContactPage enterApplicantEmailId(String secondEmailID)
	{
		WebElement ele = locateElement("id", "emailId");
		//WebElement ele = driver.findElementById("emailId");
		clearAndEnter(ele, secondEmailID);
		return this;
	}
	public PrimaryContactPage enterApplicantPhoneNumber(String mobileNumber)
	{
		WebElement ele = locateElement("id", "contactNumber");
		clearAndEnter(ele, mobileNumber);
		return this;
	}
	public SchedulePage clickConfirmationButton()
	{
		WebElement ele = locateElement("xpath", "//button[text()='I confirm that the details above are accurate and I am the primary applicant']");
		//WebElement ele = driver.findElementByXPath("//button[text()='I confirm that the details above are accurate and I am the primary applicant']");
		click(ele);
		return new SchedulePage(driver);
	}
}
