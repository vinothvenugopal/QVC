package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;

public class HomePage extends BaseClass{
	public HomePage(RemoteWebDriver driver, ExtentTest node, ExtentTest test) {
		this.driver = driver;
		this.node = node;
		this.test = test;
	}
	public HomePage verifyHomePageTitle()
	{
		String title = fetchPageTitle();
		verifyPageTitle(title);
		return this;
	}
	public ApplicantDetailsPage clickBookAppointment() throws InterruptedException
	{
		//Thread.sleep(2000);
		WebElement ele = locateElement("xpath", "(//a[@href='/schedule'])[2]");
		//WebElement ele = driver.findElementByXPath("(//a[@href='/schedule'])[2]");
		click(ele);
		return new ApplicantDetailsPage(driver,node,test);
	}
	public ManageAppointmentPage clickManageAppointmentLink()
	{
		WebElement ele = locateElement("xpath", "//li[@class='menu']//a[@href='/manage']");
		click(ele);
		return new ManageAppointmentPage(driver,node,test);
	}

}
