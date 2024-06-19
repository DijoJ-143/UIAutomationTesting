package com.ust.AssesmentSelenium.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ust.AssesmentSelenium.base.ReusableFunction;

public class LoginPom {

	// Initializing
	WebDriver driver;
	ReusableFunction function;

	// Parametrized constructor
	public LoginPom(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
		function = new ReusableFunction(driver);
	}
	
	//Locators
	@FindBy(className = "login_option")
	WebElement loginicon;
	
	@FindBy(name="mobile")
	WebElement mobilenumber;
	
	@FindBy(css = "p[class='size_error']")
	WebElement errormessage;
	
	//Methods
	
	//validating whether in the homepage
	public String isInHomepage() {
		function.delaySeconds(3);
		return function.getUrl();
        

		
	}
	
	//click on the login icon
	public void clickOnLoginIcon() {
        function.clickOnElement(loginicon);
        function.delaySeconds(3);
    }
	
	//providing random data as mobile number
	public void provideMobileNumber(String data) {
		function.delaySeconds(3);
        function.setTextToInputField(mobilenumber, data);
        
    }
	
	//fetching the error messages
	public String getErrorMsg() {
        return function.getText(errormessage);
    }
	

}
