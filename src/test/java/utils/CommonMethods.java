package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.PageInitiliazer;

public class CommonMethods extends PageInitiliazer {

	/**
	 * This method clears a text box and send the text parameter to it.
	 * 
	 * @param element
	 * @param text
	 */
	public static void sendText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}

	/**
	 * This method pauses the execution for a certain amount of seconds.
	 * 
	 * @param seconds
	 */
	public static void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method returns a timestamp as a String.
	 * 
	 * @return
	 */
	public static String getDateStamp() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		return sdf.format(date);
	}

	/**
	 * This method will take a screenshot
	 * 
	 * @param fileName
	 * @return
	 */
	public static String takeScreenShot(String fileName) {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File screenshot = ts.getScreenshotAs(OutputType.FILE);
		String destination = Constants.SCREENSHOT_FILEATH + fileName + getDateStamp() + ".png";

		try {
			FileUtils.copyFileToDirectory(screenshot, new File(destination));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to save the screenshot!");
		}

		return destination;

	}

	/**
	 * This method will take a screenshot and save it with the given fileName.
	 * 
	 * @param fileName
	 * @return
	 */
	public static byte[] takeScreenshot(String fileName) {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File screenshot = ts.getScreenshotAs(OutputType.FILE);
		String destination = Constants.SCREENSHOT_FILEATH + fileName + getDateStamp() + ".png";

		try {
			FileUtils.copyFileToDirectory(screenshot, new File(destination));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to save the screenshot!");
		}

		byte[] toReturn = ts.getScreenshotAs(OutputType.BYTES);
		return toReturn;

	}

	/**
	 * This method switches to a frame using its index.
	 * 
	 * @param index
	 */
	public static void switchToFrame(int index) {

		try {
			driver.switchTo().frame(index);
		} catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method switches to a frame using its name or id.
	 * 
	 * @param nameOrID
	 */
	public static void switchToFrame(String nameOrID) {

		try {
			driver.switchTo().frame(nameOrID);
		} catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method switches to a frame using a WebElement.
	 * 
	 * @param element
	 */
	public static void switchToFrame(WebElement element) {

		try {
			driver.switchTo().frame(element);
		} catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method switches the focus of the driver to the child window.
	 */
	public static void switchToChildWindow() {
		String mainWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {
			if (!mainWindow.equals(handle)) {
				driver.switchTo().window(handle);
			}
		}
	}

	/**
	 * This method switches the focus of the driver to an Alert and accepts it if
	 * found.
	 * 
	 * If not found, the NoAlertPresentException is handled.
	 */
	public static void acceptAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (NoAlertPresentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method switches the focus of the driver to an Alert and dismisses it if
	 * found.
	 * 
	 * If not found, the NoAlertPresentException is handled.
	 */
	public static void dismissAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		} catch (NoAlertPresentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will switch the focus of the driver to an alert and return its
	 * text.
	 * 
	 * If alert is not found, the NoAlertPresentException is handled.
	 * 
	 * @return
	 */
	public static String getAlertText() {
		String alertText = null;

		try {
			Alert alert = driver.switchTo().alert();
			alertText = alert.getText();
		} catch (NoAlertPresentException e) {
			e.printStackTrace();
		}
		return alertText;
	}

	/**
	 * This method will switch the focus of the driver to an alert and send text to
	 * it.
	 * 
	 * If alert is not found, the NoAlertPresentException is handled.
	 * 
	 * @param text
	 */
	public static void sendAlertText(String text) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(text);

		} catch (NoAlertPresentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method checks if the radio/checkbox is enabled, and then clicks on the
	 * 
	 * @param elementList
	 * @param selectValue
	 */
	public static void clickRadioOrCheckBox(List<WebElement> elementList, String selectValue) {

		for (WebElement el : elementList) {
			String elementValue = el.getAttribute("value").trim();

			if (elementValue.equals(selectValue) && el.isEnabled()) {
				el.click();
				break;
			}
		}
	}

	/**
	 * This method selects a drop down element based on visible text.
	 * 
	 * @param element
	 * @param visibleText
	 */
	public static void selectDropdown(WebElement element, String visibleText) {
		try {
			Select sl = new Select(element);
			sl.selectByVisibleText(visibleText);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method selects a drop down element based on index.
	 * 
	 * @param element
	 * @param index
	 */
	public static void selectDropdown(WebElement element, int index) {
		try {
			Select sl = new Select(element);
			sl.selectByIndex(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will select a date on a calendar given the List<WebElement> and
	 * the date to select.
	 * 
	 * @param elements
	 * @param data
	 */
	public static void selectCalendarData(List<WebElement> elements, String data) {

		for (WebElement day : elements) {
			if (day.getText().equals(data)) {
				if (day.isEnabled()) {
					click(day);
				} else {
					System.out.println("This day is not enabled!");
					break;
				}
			}
		}

	}

	/**
	 * This method is another substitute for selectDropdown().
	 * 
	 * It gets a list of WebElements and a string value to click on the particular
	 * element containing the value.
	 * 
	 * @param list
	 * @param value
	 */
	public void clickOnElement(List<WebElement> list, String value) {
		for (WebElement option : list) {
			if (option.getText().equals(value)) {
				click(option);
				break;
			}
		}
	}

	/**
	 * This method will check if the WebElement(field) is displayed and it will
	 * print the field name if it is displayed or not
	 * 
	 * @param field
	 * @param fieldName
	 */
	public void verifyFieldVisibility(WebElement field, String fieldName) {
		if (field.isDisplayed()) {
			System.out.println(fieldName + " is visible!");
			Assert.assertTrue(fieldName + " is visible!", true);
		} else {
			System.out.println(fieldName + " is NOT visible!");
			Assert.fail(fieldName + " is NOT visible!");
		}
	}

	/**
	 * This method will check if the WebElement(field) from the List of WebElements
	 * is displayed and it will print the field name if it is displayed or not
	 * 
	 * @param field
	 * @param fieldName
	 */
	public void verifyFieldVisibility(List<WebElement> fields, String fieldName) {
		for (WebElement field : fields) {
			if (field.isDisplayed()) {
				System.out.println(fieldName + " is visible!");
				Assert.assertTrue(fieldName + " is visible!", true);
			} else {
				System.out.println(fieldName + " is NOT visible!");
				Assert.fail(fieldName + " is NOT visible!");
			}
		}
	}

	/**
	 * This method will clear all the text boxes and print the field name if it does
	 * cleared or not
	 * 
	 * @param element
	 * @param fieldName
	 */
	public void clearField(WebElement element, String fieldName) {
		try {
			element.clear();
			System.out.println(fieldName + " field has been cleared.");
		} catch (Exception e) {
			System.out.println("Unable to clear field: " + fieldName);
		}
	}

	public static WebDriverWait getWaitObject() {

		return new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT_TIME));
	}

	public static WebElement waitForClickability(WebElement element) {

		return getWaitObject().until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method will wait for the locator to be clickable.
	 * 
	 * @param locator
	 * @return
	 */
	public static WebElement waitForClickability(By locator) {
		return getWaitObject().until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * This method will wait for the element to be visible.
	 * 
	 * @param element
	 * @return
	 */
	public static WebElement waitForVisible(WebElement element) {
		return getWaitObject().until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This method will wait for the locator to be visible.
	 * 
	 * @param locator
	 * @return
	 */
	public static WebElement waitForVisible(By locator) {
		return getWaitObject().until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * This method will wait for clickability and then click on the passed element.
	 * 
	 * @param element
	 */
	public static void click(WebElement element) {
		waitForClickability(element).click();
	}

	/**
	 * This method will cast the driver to a JavascriptExecutor object and return
	 * it.
	 * 
	 * @return
	 */
	public static JavascriptExecutor getJSObject() {
		return (JavascriptExecutor) driver;
	}

	/**
	 * This method will click on an element using JavascriptExecutor.
	 * 
	 * @param element
	 */
	public static void jsClick(WebElement element) {
		getJSObject().executeScript("arguments[0].click()", element);
	}

	/**
	 * This method will scroll the page until a specific element is in view.
	 * 
	 * @param element
	 */
	public static void scrollToElement(WebElement element) {
		getJSObject().executeScript("arguments[0].scrollIntoView(true)", element);
	}

	/**
	 * This method scrolls the page down based on the pixel parameter.
	 * 
	 * @param pixels
	 */
	public static void scrollDown(int pixels) {
		getJSObject().executeScript("window.scrollBy(0, " + pixels + ")");
	}

	/**
	 * This method scrolls the page up based on the pixel parameter.
	 * 
	 * @param pixels
	 */
	public static void scrollUp(int pixels) {
		getJSObject().executeScript("window.scrollBy(0, -" + pixels + ")");
	}

}
