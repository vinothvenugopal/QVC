package testcases;

import org.testng.annotations.Test;

import common.ProjectSpecificMethods;
import pages.NotificationPage;

public class TC_001_Notification extends ProjectSpecificMethods {
	@Test
	public void clickNotification()
	{
		new NotificationPage(driver)
		.closeNotification();
	}

}
