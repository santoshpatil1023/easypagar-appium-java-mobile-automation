package listeners;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.*;
import utils.TestUtils;
import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import java.io.File;

public class ExtentReportListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
        spark.config().setDocumentTitle("EasyPagar Automation Report");
        spark.config().setReportName("Mobile Automation Test Results");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Santosh Patil");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("✅ Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());

        // Take screenshot on failure
        String screenshotPath = new TestUtils((AndroidDriver) BaseTest.driver)
                .takeScreenshot(result.getMethod().getMethodName());

        test.get().addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("⚠️ Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        try {
            java.awt.Desktop.getDesktop()
                    .browse(new File(System.getProperty("user.dir") + "/reports/ExtentReport.html").toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
