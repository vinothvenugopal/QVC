package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.BaseClass;

public class AppointmentDetailsPage extends BaseClass{
	public AppointmentDetailsPage(RemoteWebDriver driver) {
		this.driver = driver;
		
	}
	public AppointmentDetailsPage verifyHeader() throws InterruptedException
	{
		//Thread.sleep(5000);
		WebElement ele = locateElement("xpath", "(//p[text()='Appointment Details'])[1]");
		//WebElement ele = driver.findElementByXPath("(//p[text()='Appointment Details'])[1]");
		String text = ele.getText();
		boolean trueFalse = verifyUIValue(text, ele);
		if(trueFalse == true)
		{
			System.out.println("Appointment Details header verified successfully");
		}
		else
		{
			System.out.println("Appointment Details header in the UI is different than it is supposed to be");
		}
		return this;
	}
}
