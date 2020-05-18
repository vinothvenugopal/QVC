package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;

public class WaitListPage extends BaseClass {

	public WaitListPage(ExtentTest node, ExtentTest test) {
		this.node = node;
		this.test = test;
	}
	
	public WaitListPage enterPassportNumber(String passPortNumber)
	{
		WebElement ele = locateElement("name", "passportNumber");
		clearAndEnter(ele, passPortNumber);
		return this;
	}
	
	public WaitListPage enterVisaNumber(String visaNumber)
	{
		WebElement ele = locateElement("name", "visaNumber");
		clearAndEnter(ele, visaNumber);
		return this;
	}
	public WaitListPage enterCaptcha() throws InterruptedException
	{
		WebElement ele = locateElement("name", "captcha");
		click(ele);
		Thread.sleep(6000);
		return this;
	}
	public AddWaitListPage clickSubmitButton() throws InterruptedException
	{
		WebElement ele = locateElement("xpath", "//button[text()[normalize-space()='Submit']]");
		click(ele);
		boolean isNotificationPresent = checkNotification("xpath", "//div[@class='pull-left appt']//button");
		if(isNotificationPresent == true)
		{
			WebElement eleNotificationText = locateElement("xpath", "(//div[@class='col-md-12']//modal-content)[1]");
			String notificationText = fetchText(eleNotificationText);
			if(notificationText.equals("WaitList has been Initiated Successfully"))
			{
				System.out.println("Waitlist Initiated Successfully");
				WebElement ele1 = locateElement("xpath", "//button[text()='OK']");
				click(ele1);
			}
			else
			{
			System.out.println("Message "+notificationText+" is displayed. /n Cannot proceed further with appointment with this data");
			reportStep(notificationText, "fail");
			WebElement ele1 = locateElement("xpath", "//div[@class='pull-left appt']//button");
			click(ele1);
			throw new RuntimeException();
			}
		}
		else
			reportStep("Submit button clicked successfully", "pass");
		return new AddWaitListPage(node, test);
	}
	
	
	
	
}
