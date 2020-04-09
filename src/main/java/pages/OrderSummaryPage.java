package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;

public class OrderSummaryPage extends BaseClass {

	public OrderSummaryPage(RemoteWebDriver driver,ExtentTest node, ExtentTest test) {
		this.driver = driver;
		this.node = node;
		this.test = test;
	}
	public OrderSummaryPage verifyVisaCenter(String center) throws InterruptedException
	{
		//Thread.sleep(1000);
		WebElement ele = locateElement("xpath", "(//table[@class='table m-0-auto']//tr)[1]//td[2]");
		//WebElement ele = driver.findElementByXPath("(//table[@class='table m-0-auto']//tr)[1]//td[2]");
		boolean trueFalse = verifyUIValue(center, ele);
		if(trueFalse == true)
		{
			System.out.println("Visa Center value verified successfully");
		}
		else
		{
			System.out.println("Visa center value in the UI is different than it is supposed to be");
		}
		return this;
	}
	public OrderSummaryPage verifyDate(String date) throws InterruptedException
	{
		//Thread.sleep(1000);
		WebElement ele = locateElement("xpath", "(//table[@class='table m-0-auto']//tr)[2]//td[2]");
		//WebElement ele = driver.findElementByXPath("(//table[@class='table m-0-auto']//tr)[2]//td[2]");
		boolean trueFalse = verifyPartialUIValue(date, ele);
		if(trueFalse == true)
		{
			System.out.println("Date value verified successfully");
		}
		else
		{
			System.out.println("Date value in the UI is different than it is supposed to be");
		}
		return this;
	}
	public OrderSummaryPage verifyTime(String time) throws InterruptedException
	{
		//Thread.sleep(1000);
		WebElement ele = locateElement("xpath", "(//table[@class='table m-0-auto']//tr)[3]//td[2]");
		//WebElement ele = driver.findElementByXPath("(//table[@class='table m-0-auto']//tr)[3]//td[2]");
		boolean trueFalse = verifyUIValue(time, ele);
		if(trueFalse == true)
		{
			System.out.println("Appointment time verified successfully");
		}
		else
		{
			System.out.println("Appointment Time in the UI is different than it is supposed to be");
		}
		return this;
	}
	public AppointmentDetailsPage clickConfirmButton() throws InterruptedException
	{
		//Thread.sleep(1000);
		WebElement ele = locateElement("xpath", "//button[text()='Confirm']");
		//WebElement ele = driver.findElementByXPath("//button[text()='Confirm']");
		click(ele);
		return new AppointmentDetailsPage(driver,node,test);
	}
}
