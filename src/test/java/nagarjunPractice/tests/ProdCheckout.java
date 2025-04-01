package nagarjunPractice.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.OrderStatusPage;
import pageObjects.OrdersPage;
import pageObjects.ProductCatalog;
import testComponent.BaseTest;

public class ProdCheckout extends BaseTest {

	// Global variable
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getDataByJson", groups = "checkout")
	public void checkoutProd(HashMap<String, String> data) throws IOException, InterruptedException {

		// Creating an class object for the Web pages
		CartPage cart = new CartPage(driver);

		// sending the cerdentials to login
		ProductCatalog product = login.login(data.get("email"), data.get("password"));

		// Fetching the product list.
		List<WebElement> prodList = product.productList();
		product.addProductToCart(data.get("product"));

		// Cart Page
		cart.goToCartPage();
		boolean match = cart.verfiyProductDisplay(data.get("product"));
		Assert.assertTrue(match);

		CheckoutPage checkout = cart.goToCheckout();

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

		OrderStatusPage status = checkout.placeOrder();

		String actualMsg = status.fetchTheStatusMessage();
		boolean stat = actualMsg.equalsIgnoreCase("Thankyou for the order.");
		Assert.assertTrue(stat);

	}

	@Test(dependsOnMethods = { "checkoutProd" })
	public void OrderHistoryTest() throws InterruptedException {

		// sending the cerdentials to login
		login.login("nagarjunPratice@gmail.com", "Nagarjun@123");
		// Creating an instance of the odersPage
		OrdersPage order = new OrdersPage(driver);
		// Navigating to the order page
		order.goToOrdersPage();
		// Fetch the Name in the list
		Assert.assertTrue(order.fetchOrderNameList(productName));
	}

	@DataProvider
	public Object[][] getDataByTwoDimensionalArray() {
		return new Object[][] { { "nagarjunPratice@gmail.com", "Nagarjun@123", "ZARA COAT 3" },
				{ "arjun01@gmail.com", "Arjun@123", "ADIDAS ORIGINAL" } };
	}

	@DataProvider
	public Object[][] getDataByHashMap() {

		HashMap<String, String> hm1 = new HashMap<String, String>();
		hm1.put("email", "nagarjunPratice@gmail.com");
		hm1.put("password", "Nagarjun@123");
		hm1.put("product", "ZARA COAT 3");

		HashMap<String, String> hm2 = new HashMap<String, String>();
		hm2.put("email", "arjun01@gmail.com");
		hm2.put("password", "Arjun@123");
		hm2.put("product", "ADIDAS ORIGINAL");

		return new Object[][] { { hm1 }, { hm2 } };
	}

	@DataProvider
	public Object[][] getDataByJson() throws IOException {
		// Fetch the json data
		List<HashMap<String, String>> jsonData = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\PurchaseOrder.json");

		// return the oject
		return new Object[][] { { jsonData.get(0) }, { jsonData.get(1) } };
	}

}
