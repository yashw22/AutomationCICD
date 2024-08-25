package yashwardhan.SFD.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import yashwardhan.SFD.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
//		System.out.println("Listener: onTestStart");

		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
//		System.out.println("Listener: onTestSuccess");

//		test.log(Status.PASS, "Test passed.");
	}

	public void onTestFailure(ITestResult result) {
//		System.out.println("Listener: onTestFailure");
//		System.out.println("Listener: " + result.getName());

//		test.log(Status.FAIL, "Test failed.");
//		test.fail(result.getThrowable());
		extentTest.get().fail(result.getThrowable());
		String path = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			path = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			e.printStackTrace();
		}

		extentTest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
	}

	public void onTestSkipped(ITestResult result) {
//		System.out.println("Listener: onTestSkipped");
		
		extentTest.get().log(Status.SKIP, "Test skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//		System.out.println("Listener: onTestFailedButWithinSuccessPercentage");
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
//		System.out.println("Listener: onTestFailedWithTimeout");
	}

	public void onStart(ITestContext context) {
//		System.out.println("Listener: onStart");
	}

	public void onFinish(ITestContext context) {
//		System.out.println("Listener: onFinish");

		extent.flush();
	}
}
