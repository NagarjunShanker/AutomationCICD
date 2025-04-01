package nagarjunPractice.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import testComponent.BaseTest;
import testComponent.RetryNG;

public class ErrorVerification extends BaseTest {

	@Test(groups = { "ErrorValidation" }, retryAnalyzer=RetryNG.class)
	public void loginError() {

		// sending the cerdentials to login
		login.login("nagarjunPratice@gmail.com", "Nagarjun@1243");

		// Fetching the error message
		String errorMsg = login.getLoginErrorMessage();

		// Validating the error message
		Assert.assertEquals(errorMsg, "Incorrect email  password.");

	}

}
