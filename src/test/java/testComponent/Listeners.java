package testComponent;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReport;

public class Listeners extends BaseTest implements ITestListener {

	// Creating the global instances
	ExtentReports er = ExtentReport.getRepotObject();
	// An ExtentReports variable
	ExtentTest test;

	// Instance for the thread local class
	ThreadLocal<ExtentTest> tl = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {

		// Calling the createTest method to listen to the test execution
		test = er.createTest(result.getMethod().getMethodName());
		tl.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		// Providing the log information
		tl.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		// Porviding the log information
		tl.get().fail(result.getThrowable());

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Taking screenshoot on the failure
		String screenshot = "";
		try {
			screenshot = getEntireScreenShot(result.getMethod().getMethodName(), driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Adding screenshot to the report
		test.addScreenCaptureFromPath(screenshot, result.getMethod().getMethodName());

	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

		// Create the report
		er.flush();
	}

}
