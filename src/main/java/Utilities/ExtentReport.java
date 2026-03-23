package Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	
	public static ExtentReports extent;
    public static String ReportPath; // ← Don't initialize here

    public static ExtentReports getExtentReports() {
        // ✅ Set path only once, when reports are actually initialized
        ReportPath = System.getProperty("user.dir")
            + File.separator + "src" + File.separator + "test"
            + File.separator + "Luxepolis_TestCase_Report"
            + File.separator + "Luxepolis_"
            + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date())
            + ".html";

        ExtentSparkReporter Report = new ExtentSparkReporter(ReportPath);
        Report.config().setReportName("Luxepolis UAT Report");
        Report.config().setDocumentTitle("Test Results");
        extent = new ExtentReports();
        extent.attachReporter(Report);
        extent.setSystemInfo("Tester", "Ajinkya");
        extent.setSystemInfo("Environment", "UAT");
        return extent;
    }

}
