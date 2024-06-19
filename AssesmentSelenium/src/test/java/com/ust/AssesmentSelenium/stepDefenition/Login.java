package com.ust.AssesmentSelenium.stepDefenition;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;

import com.ust.AssesmentSelenium.pom.LoginPom;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login {

	WebDriver driver = Hooks.driver;
	LoginPom lp = new LoginPom(driver);

	@Given("User already on home page")
	public void user_already_on_home_page() {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(lp.isInHomepage(), driver.getCurrentUrl());
		lp.clickOnLoginIcon();
	}

	@When("User input {string} as phonenumber")
	public void user_input_as_phonenumber(String string) {
		// Write code here that turns the phrase above into concrete actions
	lp.provideMobileNumber(string);
	}

	@Then("User get {string} as error message")
	public void user_get_as_error_message(String string) {
		// Write code here that turns the phrase above into concrete actions
	assertEquals(lp.getErrorMsg(), string);
	
	}

}
