package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericPackage.Utility;

public class CartPage extends Utility {

	// driver instance for class
	WebDriver driver;

	// Constructor to initalize the driver and pagefactory to initial the elements
	public CartPage(WebDriver driver) {

		// intiailize the parent driver
		super(driver);
		// intialize the driver
		this.driver = driver;
		// Initialize the webelement
		PageFactory.initElements(driver, this);
	}

	// WebElement List
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	@FindBy(css = ".cartSection h3")
	private List<WebElement> productTitles;

	// Action Methods
	public boolean verfiyProductDisplay(String prodName) {
		boolean status = productTitles.stream().anyMatch(product -> product.getText().equalsIgnoreCase(prodName));
		return status;
	}

	public CheckoutPage goToCheckout() throws InterruptedException {

		scrollToEndOFPage();
		Thread.sleep(3000);
		checkoutEle.click();
		return new CheckoutPage(driver);
	}

}
