package com.srm.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;
import com.srm.core.DriverFactory;
import com.srm.utils.ExtentManager;
import com.srm.utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    private static final ThreadLocal<ExtentTest> CURRENT_TEST = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        ExtentManager.getInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentManager.getInstance().createTest(result.getMethod().getMethodName());
        CURRENT_TEST.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        CURRENT_TEST.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String screenshotPath = ScreenshotUtils.capture(DriverFactory.getDriver(), result.getMethod().getMethodName());
        CURRENT_TEST.get().log(Status.FAIL, result.getThrowable());
        try {
            CURRENT_TEST.get().fail("Screenshot on failure",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception exception) {
            CURRENT_TEST.get().warning("Screenshot attachment failed: " + exception.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        CURRENT_TEST.get().log(Status.SKIP, "Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush();
        CURRENT_TEST.remove();
    }
}
