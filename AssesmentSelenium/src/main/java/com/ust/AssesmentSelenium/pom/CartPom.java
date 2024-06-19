package com.ust.AssesmentSelenium.pom;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ust.AssesmentSelenium.base.ReusableFunction;

public class CartPom {
	// Initializing
		ReusableFunction function;
		WebDriver driver;

		// Parametrized constructor
		public CartPom(WebDriver driver) {
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
		
		@FindBy(xpath = "(//div[@class='st-col-md-35 st-product'])[1]")
		WebElement selectashirt;
		
		@FindBy(css = "h1[class='page-title']")
		WebElement carttitle;
		
		@FindBy(css = ".size-box>label")
		public List<WebElement> size;
		
		@FindBy(id = "product-addtocart-button")
		WebElement clickonaddtocart;
		
		@FindBy(css = "a#navbardrop")
		WebElement whishlist;
		
		@FindBy(xpath = "(//a[@class='remove-cart'])[2]")
		WebElement remove;
		
		
		// Methods

		// click on search button
		public CartPom clickonSearchbutton() {
			function.clickOnElement(searchbox);
			function.delaySeconds(4);
			return this;
		}

		// searching a valid data
		public CartPom inputSearch() {
			function.setTextToInputField(searchbox, "shirts");
			function.delaySeconds(6);
			return this;
		}

		// selecting a valid option
		public CartPom clickonPrintedShirt() {
			function.clickOnElement(printedshirt);
			function.delaySeconds(4);
			return this;
		}
		
		//selecting a shirt 
		
		public CartPom clickonaShirt() {
			function.clickOnElement(selectashirt);
			return this;
		}
		
		//fetcing the selected shirt title
		public String getCartTitle() {
            return function.getText(carttitle);
        }
		
		
		//selecting a size
		//size range -> 0 for s,1 for M,......
		public CartPom clickonSize(int sizerange) {
			function.clickOnElement(size.get(sizerange));
			return this;
		}
		
		//adding to cart
		public CartPom clickonAddToCart() {
            function.clickOnElement(clickonaddtocart);
            function.delaySeconds(4);
            return this;
        }

		//clicking on whishlist
		public String clickonWhishlist() {
            function.clickOnElement(whishlist);
            function.delaySeconds(4);
            return driver.getCurrentUrl();
        }
		
		//removing from the cart
		public void clickonRemoveFromCart() {     	 
            function.clickOnElement(remove);
            function.delaySeconds(4);
           
        }

		public String isRemoved() {
			// TODO Auto-generated method stub
			Alert alert = driver.switchTo().alert();
			return alert.getText();
		}
		
		

}
