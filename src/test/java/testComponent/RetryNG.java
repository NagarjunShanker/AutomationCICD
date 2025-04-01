package testComponent;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/*
 * This class handles the retesting of the application, based on the time required.
 */
public class RetryNG implements IRetryAnalyzer {

	int count = 0;
	int max_count = 1;

	@Override
	public boolean retry(ITestResult result) {

		if (count < max_count) {

			count++;
			return true;

		}

		return false;
	}

}
