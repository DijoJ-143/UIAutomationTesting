package com.ust.AssesmentSelenium.testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(glue = "classpath:com.ust.AssesmentSelenium.stepDefenition", 
features = "classpath:features/",
plugin = {
		"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		"timeline:test-output-thread",
		"html:prettyreport/html-reports/CucmberReport.html"
		}, monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {

}
