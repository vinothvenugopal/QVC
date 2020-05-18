// this class is a wrapper for all browser and elements of the browser
package base;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
	//	public RemoteWebDriver driver;
	//	public WebDriverWait wait;

	// click action
	public void click(WebElement ele)
	{	
		try {
			setWait();
			getWait().until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			reportStep("Button "+ele+" clicked successfully", "pass");
		} catch (Exception e) {
			System.out.println("Element not found");
			reportStep("Button "+ele.getText()+" coould not be clicked", "fail");

		}
	}

	//to open browser, maximize and Pass URL
	public void startApp(String browserName, String URL)
	{
		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				System.setProperty("webdriver.chrome.silentOutput", "true");
				setDriver("chrome");
			} else if (browserName.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", "./drivers/InternetExplorerDriver.exe");
				setDriver("internet explorer");
			} else if (browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver_64 bit.exe");
				setDriver("firefox");
			} 
			getDriver().manage().window().maximize();
			getDriver().get(URL);
			//	reportStep("Browser launched successfully", "pass");

		} catch (Exception e) {
			System.out.println("Issue in launching browser");
			reportStep("Could not launch Browser", "fail");

		}
	}

	//to close browser
	public void closeApp()
	{
		getDriver().close();
	}

	//page title verification
	public void verifyPageTitle(String pageTitle)
	{
		if((getDriver().getTitle()).equals(pageTitle))
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
			setWait();
			getWait().until(ExpectedConditions.elementToBeClickable(ele));
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
			WebElement eleDate;
			//keeping all month names in an array
			String[] month = new String[] { "January", "February", "March", "April", "May", "June", "July",
					"August", "September", "October", "November", "December" };
			//finding the index of the array for the month specified in excel sheet
			String monthFromExcel = date.substring(3, (date.length()));
			int dateFromExcel = Integer.parseInt(date.substring(0, 2));
			List<WebElement> calendarDateList = getDriver()
					.findElementsByXPath("//button[text()[normalize-space()='" + dateFromExcel + "']]");
			int indexOfExcelMonth = Arrays.asList(month).indexOf(monthFromExcel);
			//getting the month name displayed in the UI date picker
			String pickerMonth = getDriver().findElementByXPath("//div[@class='navigation__title']//span[1]")
					.getText();
			//finding the index of the array for the month displayed in the UI
			int indexOfUIMonth = Arrays.asList(month).indexOf(pickerMonth);
			//if month in the UI is less than the month mentioned in the Excel - click next month button
			if (indexOfUIMonth < indexOfExcelMonth) {
				int difference = indexOfExcelMonth - indexOfUIMonth;
				WebElement ele = getDriver().findElementByXPath("//button[@class='navigation__button is-next']");
				for (int i = 0; i < difference; i++) {
					setWait();
					getWait().until(ExpectedConditions.elementToBeClickable(ele));
					click(ele);
				}
				Thread.sleep(1000);
				if(calendarDateList.size()>1)
				{
					eleDate = getDriver()
							.findElementByXPath("(//button[text()[normalize-space()='" + dateFromExcel + "']])[2]");
				}
				else
				{
					eleDate = getDriver()
							.findElementByXPath("//button[text()[normalize-space()='" + dateFromExcel + "']]");
				}
				setWait();
				getWait().until(ExpectedConditions.elementToBeClickable(eleDate));
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
				WebElement ele = getDriver().findElementByXPath("//button[@class='navigation__button is-previous']");
				for (int i = 0; i < difference; i++) {
					setWait();
					getWait().until(ExpectedConditions.elementToBeClickable(ele));
					click(ele);
				}
				Thread.sleep(1000);
				if(calendarDateList.size()>1)
				{
					eleDate = getDriver()
							.findElementByXPath("(//button[text()[normalize-space()='" + dateFromExcel + "']])[2]");
				}
				else
				{
					eleDate = getDriver()
							.findElementByXPath("//button[text()[normalize-space()='" + dateFromExcel + "']]");
				}
				setWait();
				getWait().until(ExpectedConditions.elementToBeClickable(eleDate));
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
				if(calendarDateList.size()>1)
				{

					eleDate = getDriver()
							.findElementByXPath("(//button[text()[normalize-space()='" + dateFromExcel + "']])[2]");
				}
				else
				{
					eleDate = getDriver()
							.findElementByXPath("//button[text()[normalize-space()='" + dateFromExcel + "']]");
				}
				setWait();
				getWait().until(ExpectedConditions.elementToBeClickable(eleDate));
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
			Actions action = new Actions(getDriver());
			action.sendKeys(Keys.PAGE_DOWN).build().perform();
			reportStep("page scrolled successfully", "pass");
		} catch (Exception e) {
			System.out.println("Unknown Exception");
			reportStep("page could not be scrolled", "fail");
		}
	}
	//to check if there are any notifications

	//to check if there are any notifications

	public boolean checkNotification(String locator, String property) throws InterruptedException 
	{
		Thread.sleep(2000);
		boolean isNotificationPresent = getDriver().findElementsByXPath(property).size()>0;
		return isNotificationPresent;
	}

	//to find an element
	public WebElement locateElement(String locator, String property)
	{
		try {
			switch (locator.toLowerCase()) {
			case "id":
				setWait();
				getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id(property)));
				if(getDriver().findElementById(property).isDisplayed()==true)
				{
					return getDriver().findElementById(property);
				}
				else
					return null;
			case "name":
				setWait();
				getWait().until(ExpectedConditions.visibilityOfElementLocated(By.name(property)));
				if(getDriver().findElementByName(property).isDisplayed()==true)
				{
					return getDriver().findElementByName(property);
				}
				else return null;
			case "class":
				setWait();
				getWait().until(ExpectedConditions.visibilityOfElementLocated(By.className(property)));
				if(getDriver().findElementByClassName(property).isDisplayed()==true)
				{
					return getDriver().findElementByClassName(property);
				}
				else return null;
			case "link":
				setWait();
				getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText(property)));
				if(getDriver().findElementByLinkText(property).isDisplayed()==true)
				{
					return getDriver().findElementByLinkText(property);
				}
				else return null;
			case "xpath":
				setWait();
				getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(property)));
				if(getDriver().findElementByXPath(property).isDisplayed()==true)
				{
					return getDriver().findElementByXPath(property);
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
		return getDriver().getTitle();
	}

	@Override
	public long takeSnap() {
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L; 
		try {
			FileUtils.copyFile(getDriver().getScreenshotAs(OutputType.FILE) , new File("./reports/images/"+number+".jpg"));
		} catch (WebDriverException e) {
			System.out.println("The browser has been closed.");
		} catch (IOException e) {
			System.out.println("The snapshot could not be taken");
		}
		return number;		
	}

	public String fetchText(WebElement ele)
	{
		setWait();
		getWait().until(ExpectedConditions.elementToBeClickable(ele));
		String elementText = ele.getText();
		return elementText;
	}

	//selecting month and date using the date mentioned in the data excel
	public void pickWaitListDate(String date) throws ElementNotInteractableException
	{
		try {
			//keeping all month names in an array
			String[] month = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
					"Aug", "Sep", "Oct", "Nov", "Dec" };
			//finding the index of the array for the month specified in excel sheet
			WebElement datePicker = locateElement("xpath", "//button[@class='btn cal-btn pull-right']//i");
			click(datePicker);
			String monthFromExcel = date.substring(3, 6);
			int dateFromExcel = Integer.parseInt(date.substring(0, 2));
			int indexOfExcelMonth = Arrays.asList(month).indexOf(monthFromExcel);
			//getting the month name displayed in the UI date picker
			String pickerMonth = getDriver().findElementByXPath("//div[@class='headermonthtxt']").getText();
			//finding the index of the array for the month displayed in the UI
			int indexOfUIMonth = Arrays.asList(month).indexOf(pickerMonth);
			//if month in the UI is less than the month mentioned in the Excel - click next month button
			if (indexOfUIMonth < indexOfExcelMonth) {

				System.out.println("Wait List can be scheduled only for the month "+pickerMonth+" Date requested falls on "+monthFromExcel+" month.");
				throw new Exception("Test case failed");
				/*
				 * int difference = indexOfExcelMonth - indexOfUIMonth; WebElement ele =
				 * getDriver().findElementByXPath("(//div[@class='headerbtncell'])[2]"); for (int i =
				 * 0; i < difference; i++) { 
				 * wait.until(ExpectedConditions.elementToBeClickable(ele)); click(ele); }
				 * Thread.sleep(1000); WebElement eleDate = driver
				 * .findElementByXPath("//td[@class='daycell currmonth tablesingleday']//span[text()='"
				 * + dateFromExcel + "']]"); 
				 * wait.until(ExpectedConditions.elementToBeClickable(eleDate));
				 * if(eleDate.isEnabled()==true) { eleDate.click();
				 * System.out.println("Date Selected successfully");
				 * reportStep("Date selected successfully", "pass"); } else {
				 * System.out.println("Requested date is not available to schedule");
				 * reportStep("Requested date could not be selected", "fail"); }
				 */
			} 
			//if month in the UI is greater than the month mentioned in the Excel - click previous month button
			else if (indexOfUIMonth > indexOfExcelMonth) {

				System.out.println("Wait List can be scheduled only for the month "+pickerMonth+" Date requested falls on "+monthFromExcel+" month.");
				throw new Exception("Test Case Failed");
				/*
				 * int difference = indexOfUIMonth - indexOfExcelMonth; WebElement ele =
				 * getDriver().findElementByXPath("(//div[@class='headerbtncell'])[1]"); for (int i =
				 * 0; i < difference; i++) { 
				 * wait.until(ExpectedConditions.elementToBeClickable(ele)); click(ele); }
				 * Thread.sleep(1000); WebElement eleDate = driver
				 * .findElementByXPath("//td[@class='daycell currmonth tablesingleday']//span[text()='"
				 * + dateFromExcel + "']]"); 
				 * wait.until(ExpectedConditions.elementToBeClickable(eleDate));
				 * if(eleDate.isEnabled() == true) { eleDate.click();
				 * System.out.println("Date Selected successfully");
				 * reportStep("Date selected successfully", "pass"); } else {
				 * System.out.println("Requested date is not available to schedule");
				 * reportStep("Requested date could not be selected", "fail"); }
				 */
			} 
			//if month in the UI and month mentioned in the Excel are equal - click the date directly
			else if (indexOfUIMonth == indexOfExcelMonth) {
				Thread.sleep(1000);
				String xpth = "//table[@class='caltable']//tr//span[text()='" + dateFromExcel + "']";

				//getting the background color of the particular date to check if the date is enabled or disabled
				String dateBGCssValue = getDriver().findElementByXPath("//*[text()='"+dateFromExcel+"']/ancestor::td").getCssValue("background-color");
				WebElement eleDate = getDriver().findElementByXPath("//table[@class='caltable']//tr//span[text()='" + dateFromExcel + "']");
				/*
				 * Actions action = new Actions(driver);
				 * action.moveToElement(eleDate).build().perform();
				 */
				/*
				 * String bgColor = eleDate.getCssValue("background-color");
				 * System.out.println(bgColor);
				 */

				/*System.out.println(bgColor);
				String enabledColor = getDriver().findElementByXPath("//table[@class='caltable']//tr//span[text()='14']").getCssValue("color");
				System.out.println(enabledColor);*/

				setWait();
				getWait().until(ExpectedConditions.elementToBeClickable(eleDate)); 

				if(dateBGCssValue.equals("rgba(0, 0, 0, 0)"))
				{
					eleDate.click();
					System.out.println("Date Selected successfully");
					reportStep("Date selected successfully", "pass");
				}
				else
				{
					System.out.println("Requested date is not available to schedule");
					reportStep("Requested date could not be selected", "fail");
					throw new Exception();
				}
			} 
		} catch (ElementNotSelectableException e) {
			System.out.println("Given date is not enabled to be selected. Choose a different date");
		}

		catch(NoSuchElementException e) { 
			System.out.println("Element not present");
		}

		catch (Exception e) {
			System.out.println("Could not select the particular date");
			reportStep("Requested date could not be selected", "fail");
		}

	}
}



