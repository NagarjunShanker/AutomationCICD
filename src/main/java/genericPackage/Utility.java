package genericPackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {

	// An driver instance for the class
	WebDriver driver;

	// Contructor to initialize the driver
	public Utility(WebDriver driver) {

		// Initializing the driver instance of class
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = ".fa-handshake-o")
	WebElement orderHeader;

	// This method is used to wait for the element using the webElement
	// Arguments: WebElement ele, that need to be waited for.
	// int time, the duration of wait in seconds
	public void waitForElementToBeClickable(WebElement ele, int time) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	/*
	 * This method is used to wait for the element using the webElement Arguments:
	 * By findBy, that element using by locator need to be waited for. int time, the
	 * duration of wait in seconds
	 */
	public void waitForElementToAppear(By findBy, int time) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}

	/*
	 * This method is used to wait till the elemnt is visible using webElement
	 * Argument : duration in seconds and the webElement
	 */
	public void waitforWebElementToAppear(WebElement ele, int time) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	// This method is used to wait for the element using the webElement
	// Arguments: WebElement ele, that need to be waited for.
	// int time, the duration of wait in seconds
	public void waitForElementToDisappear(WebElement ele, int time) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	// This method is used to navigate to the card page
	public void goToCartPage() throws InterruptedException {
		Thread.sleep(2000);
		cartHeader.click();
	}
	
	// This method will navigate you to the order page
	public void goToOrdersPage() throws InterruptedException {
		Thread.sleep(3000);
		orderHeader.click();
	}

	// This method is used to scroll into a particular element
	// Arguments : Element to navigate to
	public void navigateToElement(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", ele);
	}

	// This method will scroll to bottom of the webpage
	public void scrollToEndOFPage() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
}
