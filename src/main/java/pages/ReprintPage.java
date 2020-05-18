package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;

public class ReprintPage extends BaseClass {

	public ReprintPage(ExtentTest node, ExtentTest test) {
		this.node = node;
		this.test = test;
	}
	public ReprintPage enterPassPortNumber(String passPortNumber)
	{
		WebElement ele = locateElement("name", "passportNumber");
		clearAndEnter(ele, passPortNumber);
		return this;
	}
	public ReprintPage enterVisaNumber(String visaNumber)
	{
		WebElement ele = locateElement("name", "visaNumber");
		clearAndEnter(ele, visaNumber);
		return this;
	}
	public ReprintPage enterCaptcha() throws InterruptedException
	{
		WebElement ele = locateElement("name", "captcha");
		click(ele);
		Thread.sleep(5000);
		return this;
	}
	public AppointmentDetailsPage clickSubmitButton()
	{
		WebElement ele = locateElement("xpath", "//button[text()[normalize-space()='Submit']]");
		click(ele);
		return new AppointmentDetailsPage(node,test);
	}
}
