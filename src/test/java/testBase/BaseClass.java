package testBase;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import utils.ConfigsReader;
import utils.Constants;

public class BaseClass {

	public static WebDriver driver;

	public static void setUp() {

		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEATH);

		switch (ConfigsReader.getProperty("browser").toLowerCase()) {

		case "chrome":

			driver = new ChromeDriver();
			break;

		case "firefox":

			driver = new FirefoxDriver();
			break;
			
		case "explorer":
			driver = new InternetExplorerDriver();
			break;
			
		default:
			throw new RuntimeException("Browser could not found!");

		}
		
		driver.manage().window().maximize();
		driver.get(ConfigsReader.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT_TIME));
		
		
		PageInitiliazer.initialize();

	}
	
	public static void tearDown() {
		
		if(driver != null) {
			driver.close();
		}
	}

}
