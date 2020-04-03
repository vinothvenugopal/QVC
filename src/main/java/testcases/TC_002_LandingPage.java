package testcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.ProjectSpecificMethods;
import pages.NotificationPage;

public class TC_002_LandingPage extends ProjectSpecificMethods{


	@Test
	public void landingPageMethod() throws InterruptedException
	{
		new NotificationPage(driver)
		.closeNotification()
		.clickLanguageDropDown()
		.clickLanguage()
		.clickCountryDropDown()
		.clickCountry()
		.verifyHomePageTitle();
	}
}
