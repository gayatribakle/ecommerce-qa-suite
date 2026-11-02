package com.gayatri.qa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.gayatri.qa.base.BaseTest;
import com.gayatri.qa.utils.ExtentManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final ThreadLocal<ExtentTest> currentTest = new ThreadLocal<>();
    private final ExtentReports extent = ExtentManager.getInstance();

    @Override
    public void onTestStart(ITestResult result) {
        currentTest.set(extent.createTest(result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        currentTest.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        currentTest.get().fail(result.getThrowable());

        Object instance = result.getInstance();
        if (instance instanceof BaseTest) {
            WebDriver driver = ((BaseTest) instance).getDriver();
            String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            currentTest.get().addScreenCaptureFromBase64String(base64, "Failure screenshot");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        currentTest.get().skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}