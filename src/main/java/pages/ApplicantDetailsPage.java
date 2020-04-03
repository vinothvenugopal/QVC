package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.BaseClass;

public class ApplicantDetailsPage extends BaseClass {
	public ApplicantDetailsPage(RemoteWebDriver driver) {
		this.driver = driver;
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
		//Thread.sleep(500);
		return new PrimaryContactPage(driver);
	}
}
