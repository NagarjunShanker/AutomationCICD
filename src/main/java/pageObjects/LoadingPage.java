package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericPackage.Utility;

public class LoadingPage extends Utility {

	// Driver instance for the class
	WebDriver driver;

	// Construct to handle the driver and PageFactory Impliementation.
	public LoadingPage(WebDriver driver) {
		
		// Initializing the parent driver
		super(driver);
		// Initalizing the class instance variable
		this.driver = driver;
		// Initializing the pagefactory
		PageFactory.initElements(driver, this);
	}

	// WebELement Sections
	@FindBy(id = "userEmail")
	WebElement emailEle;

	// For Password Element
	@FindBy(id = "userPassword")
	WebElement pwdEle;

	// For Login button.
	@FindBy(id = "login")
	WebElement loginBtn;
	
	@FindBy(css=".toast-message")
	WebElement errorMessage;

	// Action fuction
	public ProductCatalog login(String Username, String Password) {

		emailEle.sendKeys(Username);
		pwdEle.sendKeys(Password);

		loginBtn.click();

		ProductCatalog product = new ProductCatalog(driver);

		return product;

	}

	// This method will get the URL, you want to navigate to
	public void gotTo() {

		driver.get("https://rahulshettyacademy.com/client");
	}
	
	// This Method will fetch the error message
	// Return type, is the string type error message
	public String getLoginErrorMessage() {
		// Wait for the toast message be visible
		waitforWebElementToAppear(errorMessage, 5);
		// Fetching the string msg from the toast.
		String errorMsg = errorMessage.getText();
		
		return errorMsg;
	}

}
