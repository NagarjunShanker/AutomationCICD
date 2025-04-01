package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericPackage.Utility;

public class ProductCatalog extends Utility {

	// Driver istance of the class
	WebDriver driver;

	// Construct to iniialize the driver and the element of the class
	public ProductCatalog(WebDriver driver) {

		// Instance for the parent class
		super(driver);
		// Initializing the class driver instance
		this.driver = driver;
		// PageFactory class to Initialize the elements
		PageFactory.initElements(driver, this);
	}

	// Element Block
	@FindBy(xpath = "//div[contains(@class,'mb-3')]")
	List<WebElement> productListEle;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toast = By.cssSelector("#toast-container");

	// Action Function block
	public List<WebElement> productList() {
		return productListEle;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = productList().stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().equals(productName)).findAny().orElse(null);
		
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toast, 5);
		waitForElementToDisappear(spinner, 5);
	}

}
