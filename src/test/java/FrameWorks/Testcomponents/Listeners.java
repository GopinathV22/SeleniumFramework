package FrameWorks.Testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import seleniumframework.resources.ExtentReporterNG;


public class Listeners extends BaseTest implements ITestListener{

	ExtentTest test;
	ExtentReports report = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	
	public void onTestStart(ITestResult result) {
		 test = report.createTest(result.getMethod().getMethodName());
		 extentTest.set(test);
	}
	
	public void onTestSuccess(ITestResult result) {
		 extentTest.get().log(Status.PASS, "Test Passed");
	}
	
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		 
		 try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 String filePath = null;
		 try {
			 filePath = getScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}
	public void onFinish(ITestContext context) {
		report.flush();
	}
	
	
}
