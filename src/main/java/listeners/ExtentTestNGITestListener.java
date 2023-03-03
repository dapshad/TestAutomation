package listeners;

import browserFactory.BrowserFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import helper.Utility;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestNGITestListener implements ITestListener {

    //Step 1: creating the object of my extent reports and calling the method getInstance from my listeners package and Extent Manager class
    ExtentReports extent = ExtentManager.getInstance();

    //Step 2: Use Thread Local for each extent test type. so each thread local is assigned to each test.E.g if we are running
    //3 tests, instead of having 3 extent reports, i will use 3 thread locals with each one specific to each test storing
    //all the results, reports, logs, etc for me to see uniquely. Same logic,100 test cases, 100 thread locals
    //So, i create ThreadLocal for each ExtentTest type as below:

    ThreadLocal<ExtentTest> parentTest = new ThreadLocal<>(); //the parentTest helps us to maintain the test ref for all our test cases


    //Step 3: Implement all methods from ITestListener Interface which i need for my reports


    //Create my test, get the method name and store in a variable then use that variable in my Threadlocal above using the object parentTest
    public void onTestStart(ITestResult result) {
        System.out.println("test started");
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        parentTest.set(extentTest);
    }

    public void onTestSuccess(ITestResult result) { //Add pass logs
        parentTest.get().pass("Test passed", MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.captureScreenshotinBase64Format(BrowserFactory.getBrowserInstance())).build());
    }

    //Add failure logs, exception trace, add screenshots(we use MediaEntitiBuilder.createcapturefromBase64String)
    public void onTestFailure(ITestResult result) {

        WebDriver driver = BrowserFactory.getBrowserInstance();
       String base64= Utility.captureScreenshotinBase64Format(driver);

        parentTest.get().fail("Test Failed " + result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String("base64").build());
    }

    //Add skip logs
    public void onTestSkipped(ITestResult result) {
        parentTest.get().skip("Test Skipped " + result.getThrowable().getMessage());
    }

    //Test finish flush
    public void onFinish(ITestContext context) {

       extent.flush();
    }
}