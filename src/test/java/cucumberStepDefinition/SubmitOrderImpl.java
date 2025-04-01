package cucumberStepDefinition;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.LoadingPage;
import pageObjects.OrderStatusPage;
import pageObjects.ProductCatalog;
import testComponent.BaseTest;

public class SubmitOrderImpl extends BaseTest {

	// Global Declaration
	public LoadingPage login;
	public ProductCatalog product;
	public CartPage cart;
	public CheckoutPage checkout;

	// Creating an implimentation for the Background keyword
	@Given("I land on Ecommerce page")
	public void i_land_on_Ecommerce_page() throws IOException {
		login = launchApplication();
	}

	// Creating an implimentation for the Scenario outline
	@Given("^User logged in with Username (.+) and Password (.+)$")
	public void user_logged_in_with_username_password(String username, String password) {
		product = login.login(username, password);
	}

	@When("^User add product (.+) to the cart$")
	public void user_add_product_to_the_cart(String productname) {
		List<WebElement> prodList = product.productList();
		product.addProductToCart(productname);
	}

	@When("Checkout (.+) and sumbit the order")
	public void checkout_and_sumbit_the_order(String productname) throws InterruptedException {
		cart.goToCartPage();
		boolean match = cart.verfiyProductDisplay(productname);
		Assert.assertTrue(match);

		checkout = cart.goToCheckout();

		// Tap on the Select country text box and entire IND
		String country = "India";
		String autoTxt = "Ind";

		List<WebElement> autoList = checkout.selectCountry(autoTxt);
		Iterator<WebElement> suggestionName = autoList.iterator();
		while (suggestionName.hasNext()) {
			WebElement it = suggestionName.next();
			if (it.getText().equalsIgnoreCase(country)) {
				it.click();
				break;
			}
		}
		}

	@Then("^(.+) message is displayed on the CurrentPage$")
	public void messge_is_displayed_on_the_currentpage(String msg) throws InterruptedException {
		OrderStatusPage status = checkout.placeOrder();

		String actualMsg = status.fetchTheStatusMessage();
		boolean stat = actualMsg.equalsIgnoreCase(msg);
		Assert.assertTrue(stat);
		driver.close();

	}
}
