package Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	
public static ExtentReports extent;
    
    // ✅ Dynamic path with timestamp — new report each run
    public static String ReportPath = System.getProperty("user.dir")
            + "\\src\\test\\Luxepolis_TestCase_Report\\Luxepolis_"
            + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")
                .format(new Date())
            + ".html";

    public static ExtentReports getExtentReports() {

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
