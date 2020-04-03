package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.BaseClass;

public class ManageAppointmentPage extends BaseClass {
	public ManageAppointmentPage(RemoteWebDriver driver) {
		this.driver = driver;
	}
	
	public ManageAppointmentPage clickModifyButton()
	{
		WebElement ele = locateElement("xpath", "//button[text()='Modify']");
		click(ele);
		return this;
	}
	
	public ManageAppointmentPage clickRescheduleButton()
	{
		WebElement ele = locateElement("xpath", "//button[text()='Reschedule']");
		click(ele);
		return this;
	}
	public ManageAppointmentPage clickWaitListButton()
	{
		WebElement ele = locateElement("xpath", "//button[text()='Waitlist']");
		click(ele);
		return this;
	}
	public ManageAppointmentPage clickReprintButton()
	{
		WebElement ele = locateElement("xpath", "//button[text()='Reprint']");
		click(ele);
		return this;
	}
	public ManageAppointmentPage enterPassPortNumber(String passPortNumber)
	{
		WebElement ele = locateElement("name", "passportNumber");
		clearAndEnter(ele, passPortNumber);
		return this;
	}
	public ManageAppointmentPage enterVisaNumber(String visaNumber)
	{
		WebElement ele = locateElement("name", "visaNumber");
		clearAndEnter(ele, visaNumber);
		return this;
	}
	public ManageAppointmentPage enterCaptcha() throws InterruptedException
	{
		WebElement ele = locateElement("name", "captcha");
		click(ele);
		Thread.sleep(5000);
		return this;
	}
	public ModifyPrimaryContactPage clickSubmitButton()
	{
		WebElement ele = locateElement("xpath", "//button[text()[normalize-space()='Submit']]");
		click(ele);
		return new ModifyPrimaryContactPage(driver);
	}
	
}
