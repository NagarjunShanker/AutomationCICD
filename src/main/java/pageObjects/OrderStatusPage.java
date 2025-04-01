package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import genericPackage.Utility;

public class OrderStatusPage extends Utility {

	// Driver instance of the class
	WebDriver driver;

	// Constructor to initialize the drivers and PageFactory
	public OrderStatusPage(WebDriver driver) {

		// Initializing the parent driver
		super(driver);
		// Initializing the class driver
		this.driver = driver;
		// Initializing an page factory using the driver and class
		PageFactory.initElements(driver, this);
	}

	// Web Elements blocks

	By statusMessage = By.cssSelector(".hero-primary");

	// Action Class block
	public String fetchTheStatusMessage() throws InterruptedException {

		waitForElementToAppear(statusMessage, 10);
		Thread.sleep(3000);
		navigateToElement(driver.findElement(statusMessage));
		String actualMsg = driver.findElement(statusMessage).getText();

		return actualMsg;

	}
}
