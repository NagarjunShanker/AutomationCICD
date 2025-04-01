package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericPackage.Utility;

public class CheckoutPage extends Utility {

	// Driver instance of the class
	WebDriver driver;

	// Constructor of the class to initialize the page factory and drivers.
	public CheckoutPage(WebDriver driver) {

		// Initalize the parent driver
		super(driver);
		// Initialize the class driver
		this.driver = driver;
		// Initialize an pagefactory
		PageFactory.initElements(driver, this);
	}

	// WebElement Block

	By detailsBlock = By.cssSelector(".details__user");
	By autoOptions = By.cssSelector(".ta-results button span");

	@FindBy(css = ".form-group input")
	WebElement countryOption;
	
	@FindBy(xpath = "//a[contains(@class,'action__submit')]")
	WebElement submitBtn;
	
	// Action functions
	public List<WebElement> selectCountry(String countryName) throws InterruptedException {
		
		waitForElementToAppear(detailsBlock, 5);
		navigateToElement(driver.findElement(detailsBlock));
		Thread.sleep(2000);
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector(".form-group input")), countryName).build().perform();
		waitForElementToAppear(autoOptions, 5);
		List<WebElement> autoList = driver.findElements(autoOptions);
		
		return autoList;
	}
	
	public OrderStatusPage placeOrder() throws InterruptedException {
		
		Thread.sleep(2000);
		submitBtn.click();
		return new OrderStatusPage(driver);
	}

}
