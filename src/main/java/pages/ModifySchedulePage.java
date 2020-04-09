package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;

public class ModifySchedulePage extends BaseClass {

	public ModifySchedulePage(RemoteWebDriver driver, ExtentTest node, ExtentTest test) {
		this.driver = driver;
		this.node = node;
		this.test = test;
	}
	
	public ModifySchedulePage enterPassPortNumber(String passPortNumber)
	{
		WebElement ele = locateElement("name", "passportNumber");
		clearAndEnter(ele, passPortNumber);
		return this;
	}
	public ModifySchedulePage enterVisaNumber(String visaNumber)
	{
		WebElement ele = locateElement("name", "visaNumber");
		clearAndEnter(ele, visaNumber);
		return this;
	}
	public ModifySchedulePage enterCaptcha() throws InterruptedException
	{
		WebElement ele = locateElement("name", "captcha");
		click(ele);
		Thread.sleep(5000);
		return this;
	}
	public ModifySchedulePage clickSubmitButton()
	{
		WebElement ele = locateElement("xpath", "//button[text()[normalize-space()='Submit']]");
		click(ele);
		boolean isNotificationPresent = checkNotification("xpath", "//div[@class='pull-left appt']//button");
		if(isNotificationPresent == true)
		{
			WebElement eleNotificationText = locateElement("xpath", "(//div[@class='col-md-12']//modal-content)[1]");
			String notificationText = fetchText(eleNotificationText);
			System.out.println("Message "+notificationText+" is displayed. /n Cannot proceed further with Re-Scheduling with this data");
			reportStep(notificationText, "fail");
			WebElement ele1 = locateElement("xpath", "//div[@class='pull-left appt']//button");
			click(ele1);
			throw new RuntimeException();
		}
		else
			reportStep("Submit button clicked successfully", "pass");
		//return new PrimaryContactPage(driver, node, test);
		return this;
	}
	
	public ModifySchedulePage selectQVCCenter(String city) throws InterruptedException
	{
		WebElement ele = locateElement("xpath", "//span[@class='fa fa-chevron-down']");
		click(ele);
		WebElement ele1 = locateElement("xpath", "//ul[@class='dropdown-menu']//a[text()='"+city+"']");
		click(ele1);
		return this;
	}
	public ModifySchedulePage selectCategory(String category)
	{
		if (category.equalsIgnoreCase("normal")) {
			WebElement ele = locateElement("xpath", "//label[@class='conti c-text-f mr-20']");
			click(ele);
		}
		else if(category.equalsIgnoreCase("lounge"))
		{
			WebElement ele = locateElement("xpath", "//label[@class='conti c-text-f mr-20 pull-right']");
			click(ele);
		}
		return this;
	}
	public ModifySchedulePage pickDate(String date) throws InterruptedException
	{
		selectDate(date);
		return this;
	}
	public ModifySchedulePage pickTime(String time) throws InterruptedException
	{
		//Thread.sleep(1000);
		WebElement ele = locateElement("xpath", "//button[text()[normalize-space()='"+time+"']]");
		//WebElement ele = driver.findElementByXPath("//button[text()[normalize-space()='"+time+"']]");
		scrollToElement();
		click(ele);
		return this;
	}
	public OrderSummaryPage clickNextButton()
	{
		WebElement ele = locateElement("xpath", "//button[text()='Next']");
		click(ele);
		return new OrderSummaryPage(driver,node,test);
	}
	
}
