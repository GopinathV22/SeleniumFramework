package seleniumframework.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter extent = new ExtentSparkReporter(path);
		extent.config().setDocumentTitle("Ecommerse Automation");
		extent.config().setReportName("Test Results");
		
		ExtentReports report = new ExtentReports();
		report.attachReporter(extent);
		report.setSystemInfo("Tester", "Gopinath");
		return report;
	}
}
