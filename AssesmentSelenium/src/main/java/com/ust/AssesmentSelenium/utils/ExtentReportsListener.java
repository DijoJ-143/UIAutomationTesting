package com.ust.AssesmentSelenium.utils;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.ust.AssesmentSelenium.base.ReusableFunction;


// Listener class to generate Extent Reports
public class ExtentReportsListener implements ITestListener {
    static WebDriver driver; // WebDriver instance

    // ExtentReports and ExtentTest objects initialization
    public ExtentReports extent;
    public ExtentTest test;

    // Method executed before the start of the test suite
    public void onStart(ITestContext context) {
        try {
            extent = ExtentManager.createInstance(); // Creating instance of ExtentReports
        } catch (IOException e) {
            e.printStackTrace(); // Printing stack trace if an IOException occurs
        }
    }

    // Method executed before the start of each test method
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName()); // Creating ExtentTest for the current test
    }

    // Method executed when a test method passes
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test case passed"); // Logging test pass status
        test.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN)); // Logging test name with green color
        String folderName = result.getInstanceName(); // Getting test instance name
        String testName = result.getName(); // Getting test name
        String filepath = System.getProperty("user.dir") + "//TestOutput//Screenshots//Passed" + folderName + "//" + testName + "//" + testName + "_passed.png";
        // Path to save screenshot
        try {
            ReusableFunction.takeScreenShot(filepath); // Taking screenshot
            test.log(Status.PASS, (Markup) test.addScreenCaptureFromPath(filepath)); // Attaching screenshot to the report
            // Attaching screenshot to the report
        } catch (Exception e) {
            e.printStackTrace(); // Printing stack trace if an exception occurs
        }
    }

    // Method executed when a test method fails
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test case Failed"); // Logging test fail status
        test.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED)); // Logging test name with red color
        String folderName = result.getInstanceName(); // Getting test instance name
        String testName = result.getName(); // Getting test name
        String filepath = System.getProperty("user.dir") + "//TestOutput//Screenshots//Failed" + folderName + "//" + testName + "//" + testName + "_failed.png";
        // Path to save screenshot
        try {
            ReusableFunction.takeScreenShot(filepath); // Taking screenshot
            test.log(Status.PASS, (Markup) test.addScreenCaptureFromPath(filepath)); // Attaching screenshot to the report
        } catch (Exception e) {
            e.printStackTrace(); // Printing stack trace if an exception occurs
        }
    }

    // Method executed when a test method is skipped
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test case SKIPPED"); // Logging test skip status
        test.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.AMBER)); // Logging test name with amber color
    }

    // Method executed after all the test methods are finished
    public void onFinish(ITestContext context) {
        extent.flush(); // Flushing the ExtentReports instance
    }
}
