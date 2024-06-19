package com.ust.AssesmentSelenium.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

// Manager class for Extent Reports
public class ExtentManager {
    // ExtentReports and ExtentSparkReporter objects initialization
    public static ExtentReports extent;
    public static ExtentSparkReporter htmlReporter;
    
    // Method to create and return an instance of ExtentReports
    public static ExtentReports createInstance() throws IOException {
        // Generating report name with timestamp
        String repname = "TestReport-" + getTimeStamp() + ".html";
        // Setting up HTML reporter with the report name
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//reports//testng_report//" + repname);
        // Loading configuration from extent-config.xml
        htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "\\src\\test\\resources\\extent-config.xml");
        // Initializing ExtentReports
        extent = new ExtentReports();
        // Attaching HTML reporter to ExtentReports instance
        extent.attachReporter(htmlReporter);
        // Setting system information for the report
        extent.setSystemInfo("OS", "Windows");
        extent.setSystemInfo("Host Name", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User1", "DIJO J");
        return extent;
    }
    
    // Method to get current timestamp
    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    }
}
