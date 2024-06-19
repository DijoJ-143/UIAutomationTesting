package com.ust.AssesmentSelenium.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.ust.AssesmentSelenium.base.ReusableFunction;
import com.ust.AssesmentSelenium.pom.CartPom;
import com.ust.AssesmentSelenium.pom.HomePom;
import com.ust.AssesmentSelenium.utils.ExtentReportsListener;
@Listeners(ExtentReportsListener.class)
public class BaseClass {
	
	public static WebDriver driver;
	public static ReusableFunction function;
	public static HomePom home;
	public static CartPom cart;
	
	@BeforeTest
	public void setUp() {
		driver = ReusableFunction.invokeBrowser();
		function = new ReusableFunction(driver);
		function.openWebsite("site");
		home= new HomePom(driver);
       
    }
	
	@AfterTest
	// Driver and Browser closing function
	public void teardown() {
		driver.quit();
	}

	
	
	

}
