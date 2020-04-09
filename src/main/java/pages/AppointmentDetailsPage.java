package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;

public class AppointmentDetailsPage extends BaseClass{
	public AppointmentDetailsPage(RemoteWebDriver driver, ExtentTest node, ExtentTest test) {
		this.driver = driver;
		this.node = node;
		this.test = test;
		
	}
	public AppointmentDetailsPage verifyHeader() throws InterruptedException
	{
		//Thread.sleep(5000);
		WebElement ele = locateElement("xpath", "(//p[text()='Appointment Details'])[1]");
		//WebElement ele = driver.findElementByXPath("(//p[text()='Appointment Details'])[1]");
		String text = ele.getText();
		boolean trueFalse = verifyUIValue("APPOINTMENT DETAILS", ele);
		if(trueFalse == true)
		{
			System.out.println("Appointment Details header verified successfully");
			reportStep("Appointment details page displayed successfully", "pass");
		}
		else
		{
			System.out.println("Appointment Details header in the UI is different than it is supposed to be");
			reportStep("Appointment details page NOT displayed successfully", "fail");
		}
		return this;
	}
}
