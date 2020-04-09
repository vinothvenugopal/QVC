package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;

public class ApplicantDetailsPage extends BaseClass {
	public ApplicantDetailsPage(RemoteWebDriver driver, ExtentTest node, ExtentTest test) {
		this.driver = driver;
		this.node = node;
		this.test = test;
	}

	public ApplicantDetailsPage clickHide()
	{
		WebElement ele = locateElement("xpath", "//i[@class='fa fa-chevron-circle-up head-fix-icn pg fa-chevron-circle-down ag']");
		//WebElement ele = driver.findElementByXPath("//i[@class='fa fa-chevron-circle-up head-fix-icn pg fa-chevron-circle-down ag']");
		click(ele);
		return this;
	}

	public ApplicantDetailsPage clickIndividualAppointment() throws InterruptedException
	{
		//Thread.sleep(2000);
		WebElement ele = locateElement("xpath", "//button[text()='Individual']");
		//WebElement ele = driver.findElementByXPath("//button[text()='Individual']");
		click(ele);
		System.out.println("Individual clicked");
		return this;
	}
	public ApplicantDetailsPage enterPassportNumber(String text) throws InterruptedException
	{
		//Thread.sleep(500);
		WebElement ele = locateElement("xpath", "(//label[@for='usr']/following-sibling::input)[1]");
		//WebElement ele = driver.findElementByXPath("(//label[@for='usr']/following-sibling::input)[1]");
		clearAndEnter(ele, text);
		return this;
	}
	public ApplicantDetailsPage enterVisaNumber(String text) throws InterruptedException
	{
		WebElement ele = locateElement("Xpath", "(//label[@for='usr']/following-sibling::input)[2]");
		//WebElement ele = driver.findElementByXPath("(//label[@for='usr']/following-sibling::input)[2]");
		clearAndEnter(ele, text);
		return this;
	}
	public ApplicantDetailsPage enterCAPTCHA() throws InterruptedException
	{
		WebElement ele = locateElement("Xpath", "//div[@class='ad-input']//input");
		//WebElement ele = driver.findElementByXPath("//div[@class='ad-input']//input");
		click(ele);
		Thread.sleep(6000);
		return this;
	}
	public PrimaryContactPage clickSubmit() throws InterruptedException
	{
		WebElement ele = locateElement("xpath", "//button[text()='Submit']");
		//WebElement ele = driver.findElementByXPath("//button[text()='Submit']");
		click(ele);
		boolean isNotificationPresent = checkNotification("xpath", "//div[@class='pull-left appt']//button");
		if(isNotificationPresent == true)
		{
			WebElement eleNotificationText = locateElement("xpath", "(//div[@class='col-md-12']//modal-content)[1]");
			String notificationText = fetchText(eleNotificationText);
			System.out.println("Message "+notificationText+" is displayed. /n Cannot proceed further with appointment with this data");
			reportStep(notificationText, "fail");
			WebElement ele1 = locateElement("xpath", "//div[@class='pull-left appt']//button");
			click(ele1);
			throw new RuntimeException();
		}
		else
			reportStep("Submit button clicked successfully", "pass");
		return new PrimaryContactPage(driver, node, test);
	}
	
}
