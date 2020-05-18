package pages;

import java.util.Arrays;

import org.apache.poi.util.ArrayUtil;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;

public class SchedulePage extends BaseClass {

	public SchedulePage(ExtentTest node, ExtentTest test) {
		this.node = node;
		this.test = test;
	}
	
	public SchedulePage selectQVCCenter(String city) throws InterruptedException
	{
		//Thread.sleep(1000);
		WebElement ele = locateElement("xpath", "//span[@class='fa fa-chevron-down']");
		//WebElement ele = driver.findElementByXPath("//span[@class='fa fa-chevron-down']");
		click(ele);
		//Thread.sleep(2000);
		WebElement ele1 = locateElement("xpath", "//ul[@class='dropdown-menu']//a[text()='"+city+"']");
		//WebElement ele1 = driver.findElementByXPath("//ul[@class='dropdown-menu']//a[text()='"+city+"']");
		click(ele1);
		return this;
	}
	public SchedulePage selectCategory(String category)
	{
		if (category.equalsIgnoreCase("normal")) {
			WebElement ele = locateElement("xpath", "//label[@class='conti c-text-f mr-20']");
			//WebElement ele = driver.findElementByXPath("//label[@class='conti c-text-f mr-20']");
			click(ele);
		}
		else if(category.equalsIgnoreCase("lounge"))
		{
			WebElement ele = locateElement("xpath", "//label[@class='conti c-text-f mr-20 pull-right']");
			//WebElement ele = driver.findElementByXPath("//label[@class='conti c-text-f mr-20 pull-right']");
			click(ele);
		}
		return this;
	}
	public SchedulePage pickDate(String date) throws InterruptedException
	{
		selectDate(date);
		return this;
	}
	public SchedulePage pickTime(String time) throws InterruptedException
	{
		//Thread.sleep(1000);
		WebElement ele = locateElement("xpath", "//button[text()[normalize-space()='"+time+"']]");
		//WebElement ele = driver.findElementByXPath("//button[text()[normalize-space()='"+time+"']]");
		scrollToElement();
		click(ele);
		return this;
	}
	public OrderSummaryPage clickNext()
	{
		WebElement ele = locateElement("xpath", "//button[text()='Next']");
	//	WebElement ele = driver.findElementByXPath("//button[text()='Next']");
		click(ele);
		return new OrderSummaryPage(node,test);
	}
}
