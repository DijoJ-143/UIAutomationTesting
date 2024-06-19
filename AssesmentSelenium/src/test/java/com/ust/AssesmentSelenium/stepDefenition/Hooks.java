package com.ust.AssesmentSelenium.stepDefenition;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ust.AssesmentSelenium.base.ReusableFunction;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	public static WebDriver driver;
	private static ExtentReports extent;
	private ExtentTest test;
	static {
		// Initialize ExtentReports only once
		String repname = "CucumberReport" + getTimeStamp() + ".html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "//reports//Cucumber_Reports//" + repname);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}

	@Before
	public void setup(Scenario scenario) {
		if (driver == null) {
			driver = ReusableFunction.invokeBrowser();
		}
		ReusableFunction function = new ReusableFunction(driver);
		function.openWebsite("site");
		// Create a new test for each scenario
		test = extent.createTest(scenario.getName());
	}

	public static String getTimeStamp() {
		return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	}

	@After
	public void closeBrowser(Scenario scenario) {
		// Directory to save screenshots
		String screenshotDir = System.getProperty("user.dir") + "//CucumberScreenshots//";
		String screenshotSubDir = scenario.isFailed() ? "Failed" : "Passed";
		String screenshotPath = screenshotDir + screenshotSubDir;
		// Ensure directories exist
		new File(screenshotPath).mkdirs();
		// Take the screenshot
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotFileName = screenshotPath + "//" + scenario.getName() + "_" + getTimeStamp() + ".png";
		try {
			Files.copy(screenshotFile.toPath(), Paths.get(screenshotFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(screenshotFileName);
		if (scenario.isFailed()) {
			test.fail("Test failed");
		} else {
			test.pass("Test passed");
		}
	}

	@AfterAll
	public static void tearDown() {
		if (extent != null) {
			extent.flush();
		}
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
