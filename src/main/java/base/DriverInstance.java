package base;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverInstance {

	private static final ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
	private static final ThreadLocal<WebDriverWait> wait = new ThreadLocal<WebDriverWait>();
	
	public void setWait()
	{
		wait.set(new WebDriverWait(getDriver(), 30));
	}
	
	public WebDriverWait getWait()
	{
		return wait.get(); 
	}
	
	public void setDriver(String browser)
	{
		switch (browser) {
		case "chrome":
			driver.set(new ChromeDriver());
			break;
		case "internet explorer":
			driver.set(new InternetExplorerDriver());
			break;
		case "firefox":
			driver.set(new FirefoxDriver());
			break;
		default:
			break;
		}
	}
	
	public RemoteWebDriver getDriver()
	{
		return driver.get();
	}
}
