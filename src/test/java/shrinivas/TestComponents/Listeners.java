package shrinivas.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import shrinivas.Resoruces.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener
{
	ExtentTest test;
    ExtentReports extentReports = ExtentReporterNG.getReportObject(); // Ensure this is returning a valid ExtentReports object.

    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // Thread safe
    @Override
    public void onTestStart(ITestResult result) 
    {
        // Create a new test entry in the report for the test method.
        test = extentReports.createTest(result.getMethod().getMethodName());
        extentTest.set(test); // Unique thread id (ErrorValidationTest) -> test
        System.out.println("Test Started: " + result.getMethod().getMethodName());  // Debugging log
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        // Log test success status.
        test.log(Status.PASS, "Test Passed");
        System.out.println("Test Passed: " + result.getMethod().getMethodName());  // Debugging log
    }

    @Override
    public void onTestFailure(ITestResult result) 
    {
        // Log test failure status.
        extentTest.get().log(Status.FAIL, "Test Failed");
        System.out.println("Test Failed: " + result.getMethod().getMethodName());  // Debugging log

        // Attempt to get the WebDriver instance and capture a screenshot on failure
        WebDriver driver = null;
        try 
        {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        // If WebDriver is initialized, take a screenshot.
        if (driver != null) 
        {
            String filePath = null;
            try 
            {
                filePath = getScreenShot(result.getMethod().getMethodName(), driver);  // Assuming getScreenShot is implemented in BaseTest.
                extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) 
    {
        System.out.println("Test Skipped: " + result.getName());  // Debugging log
    }

    @Override
    public void onStart(ITestContext context) 
    {
        System.out.println("Test Execution Started: " + context.getName());  // Debugging log
    }

    @Override
    public void onFinish(ITestContext context) 
    {
        System.out.println("Test Execution Finished: " + context.getName());  // Debugging log
        extentReports.flush();  // Ensure to save the report.
    }
}
