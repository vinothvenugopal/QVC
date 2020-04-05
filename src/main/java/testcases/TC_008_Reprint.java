package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common.ProjectSpecificMethods;
import pages.LandingPage;

public class TC_008_Reprint extends ProjectSpecificMethods{
	
	@BeforeTest
	public void setExcelValue()
	{
		workbookName = "ReprintData.xlsx";
		worksheetName = "India";
	}
	@Test(dataProvider = "getVisaDetails")
	public void reprintMethod(String passPortNumber, String visaNumber) throws InterruptedException
	{
		new LandingPage(driver)
		.clickLanguageDropDown()
		.clickLanguage()
		.clickCountryDropDown()
		.clickCountry()
		.clickManageAppointmentLink()
		.clickReprintButton()
		.enterPassPortNumber(passPortNumber)
		.enterVisaNumber(visaNumber)
		.enterCaptcha()
		.clickSubmitButton()
		.verifyHeader();
	}

}
