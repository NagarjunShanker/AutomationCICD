package testComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LoadingPage;

public class BaseTest {

	public WebDriver driver;
	public LoadingPage login;

	// This meethod will initialize the browser
	public WebDriver initializeBrowser() throws IOException {

		// Fetch the browser type from the global variable
		Properties pro = new Properties();
		// To convert tehe file into input stream
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\GlobalData.properties");

		// Open/Load the File to read
		pro.load(fis);

		// Fetch the value in the file using the key
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"): pro.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions  Opt = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
			Opt.addArguments("headless");
			}
			driver = new ChromeDriver(Opt);

		} else if (browserName.equalsIgnoreCase("firefox")) {
			//
		} else if (browserName.equalsIgnoreCase("edge")) {
			//
		}

		// An global wait to the script
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// Maximize the browser
		driver.manage().window().maximize();

		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	// This method will launch the application
	public LoadingPage launchApplication() throws IOException {

		driver = initializeBrowser();
		login = new LoadingPage(driver);
		login.gotTo();

		return login;

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		// close the browser session
		driver.quit();
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		// This will read the json file and convert it into an string format
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// Convert the string to hashsets

		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		// Return the list.
		return data;
	}

	/*
	 * Take the screenshot of the screen and save it into an Reports folder
	 * Arguments are the Name of the screenshot to be saved and driver to pass the
	 * instance to the screenshot
	 */

	public String getEntireScreenShot(String testCaseName, WebDriver driver) throws IOException {

		// Converting the path to file
		File dst = new File(System.getProperty("user.dir") + "\\report\\" + testCaseName + ".png");

		// Create the take screenshot class instance
		TakesScreenshot ts = (TakesScreenshot) driver;

		// Store the screenshot file.
		File source = ts.getScreenshotAs(OutputType.FILE);

		// Store the screenshot to particular location
		FileUtils.copyFile(source, dst);
		
		// return the file path as string
		return System.getProperty("user.dir") + "\\report\\" + testCaseName + ".png";
	}

}
