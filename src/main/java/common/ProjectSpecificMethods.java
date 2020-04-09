package common;

import java.io.IOException;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import base.BaseClass;
import utils.ReadExcel;

public class ProjectSpecificMethods extends BaseClass{
	
	@Parameters({"url"})
	@BeforeMethod
	public RemoteWebDriver initiateBrowser(String url) 
	{
		driver = startApp("chrome", url);
		node = test.createNode(testCaseName);
		return driver;
	}

	@AfterMethod
	public void closeBrowser()
	{
		closeApp();
	}
	
	@DataProvider(name = "getVisaDetails")
	public String[][] getDetails() throws IOException
	{
		String[][] data = ReadExcel.readData(workbookName, worksheetName);
		return data;
	}
}
