package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

	public static ExtentReports getRepotObject() {

		// Set the path of the report hmtl file
		String reportPath = System.getProperty("user.dir") + "\\report\\index.html";

		// Creating the instance of the ExtentSourceReporter to set the config of the report
		ExtentSparkReporter esp = new ExtentSparkReporter(reportPath);

		// Setting the execution report name
		esp.config().setReportName("Web Automation Results");

		// Setting the Web tab name
		esp.config().setDocumentTitle("Test Result");
		
		// Creating a instance of the ExtentReporter
		ExtentReports er = new ExtentReports();
		
		// Consolidating the report
		er.attachReporter(esp);
		
		// Setting the tester details
		er.setSystemInfo("Tester", "Nagarjun");
		
		// returning the extentreports instance
		return er;
	}
}
