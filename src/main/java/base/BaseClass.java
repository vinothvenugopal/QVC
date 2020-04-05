// this class is a wrapper for all browser and elements of the browser
package base;

import java.util.Arrays;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
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
		} catch (Exception e) {
			System.out.println("Element not found");
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

		} catch (Exception e) {
			System.out.println("Issue in launching browser");
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
		}
		else
			System.out.println("Could not verify page title");
	}
	// clearing and entering values in text box
	public void clearAndEnter(WebElement ele, String text)
	{
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		ele.clear();
		ele.sendKeys(text);
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
				}
				else
				{
					System.out.println("Requested date is not available to schedule");
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
				}
				else
				{
					System.out.println("Requested date is not available to schedule");
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
				}
				else
				{
					System.out.println("Requested date is not available to schedule");
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
		}
	}
	//UI value verification
	public boolean verifyUIValue(String uiValue, WebElement ele)
	{
		String actualUIValue = ele.getText();
		if(actualUIValue.equalsIgnoreCase(uiValue))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//UI partial value verification
	public boolean verifyPartialUIValue(String uiValue, WebElement ele)
	{
		String actualUIValue = ele.getText();
		if(actualUIValue.contains(uiValue))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//to scroll to an element
	public void scrollToElement()
	{
		try {
			Actions action = new Actions(driver);
			action.sendKeys(Keys.PAGE_DOWN).build().perform();
		} catch (Exception e) {
			System.out.println("Unknown Exception");
		}
	}
	//to check if there are any notifications
	public void checkNotification(WebElement ele)
	{
		ele.click();
	}

	//to find an element
	public WebElement locateElement(String locator, String property)
	{
		try {
			switch (locator.toLowerCase()) {
			case "id":
				wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(property)));
				return driver.findElementById(property);
			case "name":
				wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(property)));
				return driver.findElementByName(property);
			case "class":
				wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(property)));
				return driver.findElementByClassName(property);
			case "link":
				wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(property)));
				return driver.findElementByLinkText(property);
			case "xpath":
				wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(property)));
				return driver.findElementByXPath(property);
			}
		} catch (NoSuchElementException e) {
			//System.out.println("Could not find element with the property "+property);
		}
		return null;
	}
	// to get pagetitle
	public String fetchPageTitle()
	{
		return driver.getTitle();
	}
}


