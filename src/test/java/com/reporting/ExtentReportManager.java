package com.reporting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.constants.FrameworkConstants;

public class ExtentReportManager implements ITestListener{
	
	// Listener Class to Integrate Extent Reports With Test Classes
	
	private ExtentSparkReporter sparkReporter;
	private ExtentReports extent;
	private ExtentTest test;
	private String reportName;
	private String methodName;
	
	public void onStart(ITestContext context) {
		String timeStamp=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss"));
		reportName="Test_report"+"_"+timeStamp+".html";
		
		sparkReporter=new ExtentSparkReporter(FrameworkConstants.REPORT_FILEPATH+reportName);
		
		sparkReporter.config().setDocumentTitle("PayPal API Automation Framework");
		sparkReporter.config().setReportName("Test Automation Report");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "PayPal Sandbox APIs");
		extent.setSystemInfo("Module", "Payments");
		extent.setSystemInfo("User", System.getProperty("user.name"));
	}
	
	public void onTestStart(ITestResult result) {
		methodName=result.getMethod().getMethodName();
		test=extent.createTest(methodName);
		test.assignCategory(result.getClass().getName());
		//setExtentTest(test);
	}
	
	public void onTestSuccess(ITestResult result) {
		test.pass("Test Passed");
	}
	
	public void onTestFailure(ITestResult result) {
		test.fail("Test Failed"+result.getThrowable().getMessage());
	}
	
	public void onTestSkipped(ITestResult result) {
		test.skip("Test Skipped");
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
