// this class is a wrapper for all browser and elements of the browser
package base;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.xml.xpath.XPath;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Reporter;

public class BaseClass extends Reporter {
	public RemoteWebDriver driver;
	public WebDriverWait wait;
	public String workbookName;
	public String worksheetName;
	// click action
	public void click(WebElement ele)
	{	
		try {
			wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			reportStep("Button "+ele+" clicked successfully", "pass");
		} catch (Exception e) {
			System.out.println("Element not found");
			reportStep("Button "+ele.getText()+" coould not be clicked", "fail");

		}
	}

	//to open browser, maximize and Pass URL
	public RemoteWebDriver startApp(String browserName, String URL)
	{
		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", "./drivers/InternetExplorerDriver.exe");
				driver = new InternetExplorerDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver_64 bit.exe");
				driver = new InternetExplorerDriver();
			} 
			driver.manage().window().maximize();
			driver.get(URL);
		//	reportStep("Browser launched successfully", "pass");

		} catch (Exception e) {
			System.out.println("Issue in launching browser");
			//reportStep("Could not launch Browser", "fail");

		}
		return driver;
	}

	//to close browser
	public void closeApp()
	{
		driver.close();
	}

	//page title verification
	public void verifyPageTitle(String pageTitle)
	{
		if((driver.getTitle()).equals(pageTitle))
		{
			System.out.println("Page title verified successfully");
			reportStep("Page title verified successfully", "pass");
		}
		else
			System.out.println("Could not verify page title");
		reportStep("Page title could not be verified successfully", "fail");

	}
	// clearing and entering values in text box
	public void clearAndEnter(WebElement ele, String text)
	{
		try {
			wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.clear();
			ele.sendKeys(text);
			reportStep("Text entered successfully", "pass");

		} catch (Exception e) {
			System.out.println("Unknown exception");
			reportStep("Could not enter the text", "fail");
		}
	}
	//selecting month and date using the date mentioned in the data excel
	public void selectDate(String date) throws ElementNotInteractableException
	{
		try {
			//keeping all month names in an array
			String[] month = new String[] { "January", "February", "March", "April", "May", "June", "July",
					"August", "September", "October", "November", "December" };
			//finding the index of the array for the month specified in excel sheet
			String monthFromExcel = date.substring(3, (date.length()));
			int dateFromExcel = Integer.parseInt(date.substring(0, 2));
			int indexOfExcelMonth = Arrays.asList(month).indexOf(monthFromExcel);
			//getting the month name displayed in the UI date picker
			String pickerMonth = driver.findElementByXPath("//div[@class='navigation__title']//span[1]")
					.getText();
			//finding the index of the array for the month displayed in the UI
			int indexOfUIMonth = Arrays.asList(month).indexOf(pickerMonth);
			//if month in the UI is less than the month mentioned in the Excel - click next month button
			if (indexOfUIMonth < indexOfExcelMonth) {
				int difference = indexOfExcelMonth - indexOfUIMonth;
				WebElement ele = driver.findElementByXPath("//button[@class='navigation__button is-next']");
				for (int i = 0; i < difference; i++) {
					wait = new WebDriverWait(driver, 30);
					wait.until(ExpectedConditions.elementToBeClickable(ele));
					click(ele);
				}
				Thread.sleep(1000);
				WebElement eleDate = driver
						.findElementByXPath("//button[text()[normalize-space()='" + dateFromExcel + "']]");
				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.elementToBeClickable(eleDate));
				if(eleDate.isEnabled()==true)
				{
					eleDate.click();
					reportStep("Date selected successfully", "pass");
				}
				else
				{
					System.out.println("Requested date is not available to schedule");
					reportStep("Requested date could not be selected", "fail");
				}
			} 
			//if month in the UI is greater than the month mentioned in the Excel - click previous month button
			else if (indexOfUIMonth > indexOfExcelMonth) {
				int difference = indexOfUIMonth - indexOfExcelMonth;
				WebElement ele = driver.findElementByXPath("//button[@class='navigation__button is-previous']");
				for (int i = 0; i < difference; i++) {
					wait = new WebDriverWait(driver, 30);
					wait.until(ExpectedConditions.elementToBeClickable(ele));
					click(ele);
				}
				Thread.sleep(1000);
				WebElement eleDate = driver
						.findElementByXPath("//button[text()[normalize-space()='" + dateFromExcel + "']]");
				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.elementToBeClickable(eleDate));
				if(eleDate.isEnabled() == true)
				{
					eleDate.click();
					reportStep("Date selected successfully", "pass");
				}
				else
				{
					System.out.println("Requested date is not available to schedule");
					reportStep("Requested date could not be selected", "fail");
				}
			} 
			//if month in the UI and month mentioned in the Excel are equal - click the date directly
			else if (indexOfUIMonth == indexOfExcelMonth) {
				Thread.sleep(1000);
				WebElement eleDate = driver.findElementByXPath("//button[text()[normalize-space()='" + dateFromExcel + "']]");
				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.elementToBeClickable(eleDate));
				if(eleDate.isEnabled() == true)
				{
					eleDate.click();
					reportStep("Date selected successfully", "pass");
				}
				else
				{
					System.out.println("Requested date is not available to schedule");
					reportStep("Requested date could not be selected", "fail");
				}
			} 
		} catch (ElementNotSelectableException e) {
			System.out.println("Given date is not enabled to be selected. Choose a different date");
		}
		catch(NoSuchElementException e)
		{
			System.out.println("Element not present");
		}
		catch (Exception e) {
			System.out.println("Could not select the particular date");
			reportStep("Requested date could not be selected", "fail");
		}
	}
	//UI value verification
	public boolean verifyUIValue(String uiValue, WebElement ele)
	{
		String actualUIValue = ele.getText();
		if(actualUIValue.equalsIgnoreCase(uiValue))
		{
			reportStep("UI value "+uiValue+" verified successfully", "pass");
			return true;
		}
		else
		{
			reportStep("UI value "+uiValue+"could not be verified successfully", "fail");
			return false;
		}
	}
	//UI partial value verification
	public boolean verifyPartialUIValue(String uiValue, WebElement ele)
	{
		String actualUIValue = ele.getText();
		if(actualUIValue.contains(uiValue))
		{
			reportStep("UI value "+uiValue+" verified successfully", "pass");
			return true;
		}
		else
		{
			reportStep("UI value "+uiValue+"could not be verified successfully", "fail");
			return false;
		}
	}
	//to scroll to an element
	public void scrollToElement()
	{
		try {
			Actions action = new Actions(driver);
			action.sendKeys(Keys.PAGE_DOWN).build().perform();
			reportStep("page scrolled successfully", "pass");
		} catch (Exception e) {
			System.out.println("Unknown Exception");
			reportStep("page could not be scrolled", "fail");
		}
	}
	//to check if there are any notifications
	
	//to check if there are any notifications

	 public boolean checkNotification(String locator, String property) 
	 {
		 boolean isNotificationPresent = driver.findElementsByXPath(property).size()>0;
		 return isNotificationPresent;
	 }

	//to find an element
	public WebElement locateElement(String locator, String property)
	{
		try {
			switch (locator.toLowerCase()) {
			case "id":
				wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(property)));
				if(driver.findElementById(property).isDisplayed()==true)
				{
				return driver.findElementById(property);
				}
				else
					return null;
			case "name":
				wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(property)));
				if(driver.findElementByName(property).isDisplayed()==true)
				{
				return driver.findElementByName(property);
				}
				else return null;
			case "class":
				wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(property)));
				if(driver.findElementByClassName(property).isDisplayed()==true)
				{
				return driver.findElementByClassName(property);
				}
				else return null;
			case "link":
				wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(property)));
				if(driver.findElementByLinkText(property).isDisplayed()==true)
				{
				return driver.findElementByLinkText(property);
				}
				else return null;
			case "xpath":
				wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(property)));
				if(driver.findElementByXPath(property).isDisplayed()==true)
				{
				return driver.findElementByXPath(property);
				}
				else return null;
			}
		} catch (NoSuchElementException e) {
			System.out.println("Could not find element with the property "+property);
		}
		return null;
	}
	// to get pagetitle
	public String fetchPageTitle()
	{
		return driver.getTitle();
	}

	@Override
	public long takeSnap() {
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L; 
		try {
			FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE) , new File("./reports/images/"+number+".jpg"));
		} catch (WebDriverException e) {
			System.out.println("The browser has been closed.");
		} catch (IOException e) {
			System.out.println("The snapshot could not be taken");
		}
		return number;		
	}
	
	public String fetchText(WebElement ele)
	{
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		String elementText = ele.getText();
		return elementText;
	}
}



