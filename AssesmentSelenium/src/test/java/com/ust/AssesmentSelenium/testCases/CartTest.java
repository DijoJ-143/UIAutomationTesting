package com.ust.AssesmentSelenium.testCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ust.AssesmentSelenium.dataProvider.DataProviding;

public class CartTest extends BaseClass {

	// testing whether in desired page or not
	@Test(priority = 0)
	public void testAddingAshirtToCartFunctionality() {
		cart = home.passDrivertonextPage();

		String title = cart.clickonSearchbutton().inputSearch().clickonPrintedShirt().clickonaShirt().getCartTitle();
		function.delaySeconds(4);

		assertTrue(driver.getCurrentUrl().contains((title.split(" "))[0].trim().toLowerCase()));

	}
	// using dataprovider for size calculation

	@Test(priority = 1, dataProvider = "Sizedata", dataProviderClass = DataProviding.class)
	public void testIsItemInCart(String size) {
		int sizerange = Integer.parseInt(size);
		cart.clickonSize(sizerange);
	}

	// adding on to cart

	@Test(priority = 2)
	public void testAddingToCart() {
		cart.clickonAddToCart();
		assertTrue(cart.clickonWhishlist().contains("cart"));
	}

	//removing item
	@Test(priority = 3)
	public void testRemoveFunctionality() {
		cart.clickonRemoveFromCart();
		assertTrue(cart.isRemoved().contains("successfully"));
	}

}
