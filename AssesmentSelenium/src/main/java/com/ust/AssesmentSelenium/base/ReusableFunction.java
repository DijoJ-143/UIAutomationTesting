package com.ust.AssesmentSelenium.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;

import com.ust.AssesmentSelenium.utils.FileIO;

// Class for reusable functions in Selenium tests
public class ReusableFunction {
	private static WebDriver driver;
	private WebDriverWait wait;
	public static Properties properties;
	public static String browser_choice;

	// Constructor
	public ReusableFunction(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		properties = FileIO.getProperties();
	}

	// Method to report test failure
	public static void reportFail(String message) {
		Assert.fail("Testcase Failed: " + message);
	}

	// Method to invoke browser based on properties
	public static WebDriver invokeBrowser() {
		if (properties == null) {
			properties = FileIO.getProperties();
		}
		browser_choice = properties.getProperty("browser");
		try {
			if (browser_choice.equalsIgnoreCase("chrome")) {
				driver = DriverSetup.invokeChromeBrowser();
			} else if (browser_choice.equalsIgnoreCase("edge")) {
				driver = DriverSetup.invokeEdgeBrowser();
			} else {
				throw new Exception("Invalid browser name provided in property file");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ReusableFunction(driver);
		return driver;
	}

	// Method to open a website
	public void openWebsite(String url) {
		if (properties == null) {
			properties = FileIO.getProperties();
		}
		try {
			driver.get(properties.getProperty(url));
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	// Method to wait for an element to be displayed
	public void waitForElementToDisplay(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Method to set text to any input field
	public void setTextToInputField(WebElement element, String text) {
		try {
			waitForElementToDisplay(element);
			element.clear(); // Clearing any existing text
			element.sendKeys(text); // Entering new text
		} catch (NoSuchElementException e) {
			System.err.println("Element not found: " + e.getMessage());
		}
	}

	// Method to click on any element
	public void clickOnElement(WebElement element) {
		waitForElementToDisplay(element);
		element.click();
	}

	// Method to get text of an element
	public String getText(WebElement element) {
		waitForElementToDisplay(element);
		return element.getText();
	}

	// Method to get current URL
	public String getUrl() {
		return driver.getCurrentUrl();
	}

	// Method to switch to a new tab
	public static void switchToNewTab() {
		try {
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(tabs.size() - 1));
		} catch (Exception e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}
	}

	// Method to switch to the previous tab
	public static void switchToPrevTab() {
		try {
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.close();
			driver.switchTo().window(tabs.get(tabs.size() - 2));
		} catch (Exception e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}
	}

	// Method to check if an element is present
	public static boolean isElementPresent(By locator, Duration timeout) {
		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Method to send text to an element
	public static void sendText(WebElement locator, String text) {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(locator));
			locator.sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}
	}

	// Method to get text of an element
	public static String getText(By locator) {
		String text = null;
		try {
			new WebDriverWait(driver, Duration.ofSeconds(30))
					.until(ExpectedConditions.presenceOfElementLocated(locator));
			text = driver.findElement(locator).getText();
		} catch (Exception e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}
		return text;
	}

	// Method to click on an element with JavaScript
	public static void clickJS(By locator, Duration timeout) {
		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(locator));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
			jse.executeScript("arguments[0].click", driver.findElement(locator));
		} catch (Exception e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}
	}

	// Method to switch to another frame
	public void switchToFrame(WebElement element) {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(30))
					.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		} catch (Exception e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}
	}

	// Method to take a screenshot
	public static void takeScreenShot(String filepath) {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File srcFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(filepath);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Method to read data from an Excel file
	public static String[][] readExcelFile(String path, String sheetname) {
		try {
			FileInputStream input = new FileInputStream(System.getProperty("user.dir") + properties.getProperty(path));
			Workbook wb = new XSSFWorkbook(input);
			Sheet sheet = wb.getSheet(sheetname);
			int rowCount = sheet.getPhysicalNumberOfRows();
			int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();
			DataFormatter dFormatter = new DataFormatter();
			String[][] data = new String[rowCount][columnCount];
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < columnCount; j++) {
					data[i][j] = dFormatter.formatCellValue(sheet.getRow(i).getCell(j));
				}
			}
			return data;
		} catch (IOException e) {
			System.err.println("An error occurred while reading the Excel file: " + e.getMessage());
			return null;
		}
	}

	// Method to take a delay in seconds
	public void delaySeconds(int sec) {
		try {
			Thread.sleep(1000 * sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Method to take a delay
	public void delay() {
		wait.until(ExpectedConditions.alertIsPresent());
	}

	// Method to scroll the page to bring the element into view
	public void scrollToElement(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			System.err.println("An error occurred: " + e.getMessage());
		}
	}

	// Method to create a FluentWait
	public void fluentWait() {
		FluentWait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
	}

	// Method to switch to a child window
	public void switchToChildWindow() {
		String parent = driver.getWindowHandle();
		Set<String> set = driver.getWindowHandles();
		for (String handle : set) {
			if (!handle.equals(parent)) {
				driver.switchTo().window(handle);
				System.out.println("Child window title: " + driver.getTitle());
			}
		}
	}

	// Method to switch to the parent window
	public void switchToParentHandle() {
		String child = driver.getWindowHandle();
		Set<String> set = driver.getWindowHandles();
		for (String handle : set) {
			if (!handle.equals(child)) {
				driver.switchTo().window(handle);
				System.out.println("Parent window title: " + driver.getTitle());
			}
		}
	}

	/**
	 * Sets text to an input field.
	 * 
	 * @param element The WebElement representing the input field.
	 * @param text    The text to set.
	 */
	public void setTextToInputField1(WebElement element, String text) {
		try {
			waitForElementToDisplay(element);
			element.clear(); // Clearing any existing text
			element.sendKeys(text, Keys.ENTER);
			// Entering new text
		} catch (NoSuchElementException e) {
			System.err.println("Element not found: " + e.getMessage());
		}
	}
}
