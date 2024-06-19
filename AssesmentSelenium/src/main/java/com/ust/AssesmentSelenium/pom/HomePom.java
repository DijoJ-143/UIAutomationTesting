package com.ust.AssesmentSelenium.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ust.AssesmentSelenium.base.ReusableFunction;

public class HomePom {
	// Initializing
	ReusableFunction function;
	WebDriver driver;

	// Parametrized constructor
	public HomePom(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
		function = new ReusableFunction(driver);
	}

	// Locators
	@FindBy(id = "search")
	WebElement searchbox;

	@FindBy(xpath = "(//p[@class='cat-para'])[5]")
	WebElement printedshirt;
	
	@FindBy(css = "div[class='desktop-pSearchlinks']>a")
	public  List<WebElement> footerlink;
	// Methods
	

	// click on search button
	public HomePom clickonSearchbutton() {
		function.clickOnElement(searchbox);
		function.delaySeconds(4);
		return this;
	}

	// searching a valid data
	public HomePom inputSearch() {
		function.setTextToInputField(searchbox, "shirts");
		function.delaySeconds(6);
		return this;
	}

	// selecting a valid option
	public HomePom clickonPrintedShirt() {
		function.clickOnElement(printedshirt);
		function.delaySeconds(4);
		return this;
	}

	/*
	 * click on individual sites and check whether it reaches there or not
	 */
	public String clickonFooterLink() {
		// TODO Auto-generated method stub
		for (int i = 0; i < footerlink.size(); i++) {
			function.scrollToElement(footerlink.get(i));
            function.clickOnElement(footerlink.get(i));
            
        }
		
		return driver.getCurrentUrl();
		
		

	}
	
	//passing driver to next POM
	public CartPom passDrivertonextPage() {
		return PageFactory.initElements(driver, CartPom.class);
	}

}
