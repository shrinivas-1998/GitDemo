package shrinivas.Resoruces;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG 
{
	public static ExtentReports getReportObject() 
	{

		// methods used for generating Extent reports
		// ExtentReports, ExtentSparkReporter
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("TestResults");

		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Tester", "Shrinivas Bodhale");
		return extentReports;
	}
}
