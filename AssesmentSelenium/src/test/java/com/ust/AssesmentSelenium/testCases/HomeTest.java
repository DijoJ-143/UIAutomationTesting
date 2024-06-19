package com.ust.AssesmentSelenium.testCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class HomeTest extends BaseClass {

	// testing whether in desired page or not
	@Test(priority = 0)
	public void testIsinHomePage() {

		assertEquals(driver.getCurrentUrl(), function.properties.getProperty("site"));
	}

	// testing search functionality
	@Test(priority = 1)
	public void testSearch() {
		home.clickonSearchbutton().inputSearch().clickonPrintedShirt();

		assertTrue(driver.getCurrentUrl().contains("Printed"));
	}

	// testing footer link functionality
	@Test(priority = 2)
	public void testFooterLink() {

		assertTrue(driver.getCurrentUrl().contains(home.clickonFooterLink()));
	}
}
