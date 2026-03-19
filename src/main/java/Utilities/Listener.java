package Utilities;

import java.util.ArrayList;
import java.util.List;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class Listener implements ITestListener{
	
	
	ExtentReports extent = ExtentReport.getExtentReports();
    ExtentTest Test;

    // ✅ Track pass/fail counts
    static int passCount = 0;
    static int failCount = 0;
    static int skipCount = 0;

    // ✅ Track failed test names & reasons
    static List<String> failedTests = new ArrayList<>();

    @Override
    public void onTestStart(ITestResult result) {
        Test = extent.createTest(result.getName());
        Test.log(Status.INFO, "Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        passCount++;
        Test.log(Status.PASS, "Test Case Passed ✅");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        failCount++;

        // ✅ Capture failed test name + error reason
        String failInfo = "❌ " + result.getName()
            + " → " + result.getThrowable().getMessage();
        failedTests.add(failInfo);

        Test.log(Status.FAIL, "Test Case Failed ❌");
        Test.log(Status.FAIL, result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        skipCount++;
        Test.log(Status.SKIP, "Test Case Skipped ⚠️");
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        extent.flush();
        System.out.println("Report Generated: "
            + ExtentReport.ReportPath);

        // ✅ Send email with pass/fail summary
        EmailUtils.sendTestReport(
            ExtentReport.ReportPath,
            passCount,
            failCount,
            skipCount,
            failedTests
        );
    }
}
