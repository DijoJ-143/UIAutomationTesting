package com.ust.AssesmentSelenium.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverSetup {

	private static WebDriver driver;

	// invoke chrome browser
	public static WebDriver invokeChromeBrowser() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		return driver;
	}

	// invoke edge browser
	public static WebDriver invokeEdgeBrowser() {
		EdgeOptions options = new EdgeOptions();
		options.addArguments("guest");
		options.addArguments("--start-maximized");
		driver = new EdgeDriver(options);
		return driver;
	}

}
