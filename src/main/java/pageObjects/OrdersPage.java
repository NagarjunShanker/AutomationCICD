package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericPackage.Utility;

public class OrdersPage extends Utility {

	// Driver instance of the class
	WebDriver driver;

	// Construct to impliment the drivers and page factory
	public OrdersPage(WebDriver driver) {

		// Initialize parent driver
		super(driver);
		// Initialize the class driver
		this.driver = driver;
		// Initialize the page factory
		PageFactory.initElements(driver, this);
	}

	// Page Elements block
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> orderNamesList;

	// Actions class of the page block

	public boolean fetchOrderNameList(String orderName) {
		boolean match = orderNamesList.stream().anyMatch(name -> name.getText().equalsIgnoreCase(orderName));
		return match;
	}

}
