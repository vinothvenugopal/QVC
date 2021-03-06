package testcases;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import common.ProjectSpecificMethods;
import pages.LandingPage;
import pages.NotificationPage;

public class TC_005_NormalAppointmentFlow extends ProjectSpecificMethods {
	
	
	@BeforeTest
	public void setExcelValues()
	{
		testCaseName = "Appointment Booking";
		testDescription = "Normal appointment booking flow";
		nodes = "QVC";
		authors = "Vinoth";
		category = "Smoke";
//		node = test.createNode(testCaseName);
		workbookName = "BookAppointmentData.xlsx";
		worksheetName = "India";
	}

	@Test(dataProvider = "getVisaDetails")
	public void normalAppointmentFlow(String lang, String Ctry, String passportNumber, String visaNumber,String sponsorPhNo, String sponsorEmail, String emailID, String phoneNumber, String category, String center, String date, String timeSlot) throws InterruptedException
	{
		new LandingPage(node,test)
		.checkLandingNotification()
		.clickLanguageDropDown()
		.clickLanguage(lang)
		.clickCountryDropDown()
		.clickCountry(Ctry)
		.clickBookAppointment()
		.enterPassportNumber(passportNumber)
		.enterVisaNumber(visaNumber)
		.enterCAPTCHA()
		.clickSubmit()
		.enterSponsorMobileNumber(sponsorPhNo)
		.enterSponsorEmailId(sponsorEmail)
		.enterApplicantEmailId(emailID)
		.enterApplicantPhoneNumber(phoneNumber)
		.clickConfirmationButton()
		.selectQVCCenter(center)
		.selectCategory(category)
		.pickDate(date)
		.pickTime(timeSlot)
		.clickNext()
		.verifyVisaCenter(center)
		.verifyDate(date)
		.verifyTime(timeSlot)
		.clickConfirmButton()
		.verifyHeader();
	}

}
