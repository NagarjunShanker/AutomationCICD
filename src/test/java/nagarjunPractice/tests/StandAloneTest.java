package nagarjunPractice.tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {

		// Calling the webDriverManager
		WebDriverManager.chromedriver().setup();
		// Initializing an Driver instance
		WebDriver driver = new ChromeDriver();

		// An global wait to the script
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// Maximize the browser
		driver.manage().window().maximize();

		// Navigate to the URL
		driver.get("https://rahulshettyacademy.com/client");

		// Entering the cerdentials
		// Enter the Email ID in the email text field
		driver.findElement(By.id("userEmail")).sendKeys("nagarjunPratice@gmail.com");
		// Enter the Password in the password text field
		driver.findElement(By.id("userPassword")).sendKeys("Nagarjun@123");
		// Click on the Login button
		driver.findElement(By.id("login")).click();

		// Fetching the details of the card to be added to the cart
		Scanner scan = new Scanner(System.in);

		// Explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// div[contains(@class,'mb-3 ')]/div/div/h5/b[text()='ZARA COAT
		// 3']/../../button[contains(@class, 'w-10')]
		// b[text()=\""+prodName+"\"]
		List<String> in_prodName = new ArrayList<String>();
		// Fetching all the card listed
		List<WebElement> cardList = driver.findElements(By.xpath("//div[contains(@class,'mb-3')]"));
		System.out.println(cardList.size());
		// Iterate through the list and fetch the name
		for (int i = 0; i < cardList.size(); i++) {
			WebElement cardName = cardList.get(i).findElements(By.xpath("//div/div/h5")).get(i);
			wait.until(ExpectedConditions.elementToBeClickable(cardName));
			System.out.println("Enter the product Name else enter Done to end");
			String prodName = scan.nextLine();

			if (!prodName.equalsIgnoreCase("done")) {
				// Click on the Add a cart
				cardList.get(i)
						.findElement(By.xpath(
								"//div/div/h5/b[text()=\"" + prodName + "\"]/../../button[contains(@class, 'w-10')]"))
						.click();
				in_prodName.add(prodName);
			} else {
				System.out.println("You have ended adding product to the cart");
				break;
			}
		}
		
		Thread.sleep(2000);
		// navigate to the cart page
		driver.findElement(By.xpath("//li/button[contains(text(),'Cart')]")).click();

		// Compare the product name in cart added are same as user added.
		List<WebElement> cartList = driver.findElements(By.cssSelector(".cartSection h3"));
		Iterator<String> listNames = in_prodName.iterator();
//		in_prodName.stream().forEach(s-> s.toString());
		String nameList = "";
		if(listNames.hasNext()) {
			System.out.println("In");
			boolean match = cartList.stream().anyMatch(list -> list.getText().equalsIgnoreCase(listNames.next()));
			Assert.assertTrue(match);
		}
		
		JavascriptExecutor js= (JavascriptExecutor) driver;
//		
		// Click ont he Checkout 
		WebElement checkBtn = driver.findElement(By.cssSelector(".totalRow button"));
		js.executeScript("arguments[0].scrollIntoView(true);", checkBtn);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".totalRow button")));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		// Tap on the Select country text box and entire IND
		String country = "India";
		String autoTxt = "Ind";
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".details__user")));
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(".details__user")));
		Thread.sleep(2000);
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector(".form-group input")), autoTxt).build().perform();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results button span")));
		// Fetch all the auto suggested list
		List<WebElement> autoList = driver.findElements(By.cssSelector(".ta-results button span"));
		Iterator<WebElement> suggestionName = autoList.iterator();
		while(suggestionName.hasNext()) {
			WebElement it = suggestionName.next();
			System.out.println(it.getText());
			if(it.getText().equalsIgnoreCase(country)) {
				it.click();
				break;
			}
		}
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(@class,'action__submit')]")).click();
		// The title element
		WebElement title = driver.findElement(By.cssSelector(".hero-primary"));
		// Wait for the element to be visible
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".hero-primary")));
		//scrolling to the element title
		js.executeScript("arguments[0].scrollIntoView(true);", title);
		// Fetch the text
		String actualText = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(actualText);
		boolean status = actualText.equalsIgnoreCase("Thankyou for the order.");
		Assert.assertTrue(status);

		// close the browser session
		driver.quit();
	}

}
