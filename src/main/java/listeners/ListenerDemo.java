package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerDemo implements ITestListener {

    //Step 1: I create a method (over-ride interface ITestListener's methods) to read my tests if it's passed and i can do same for failure, skip, etc

    public void onTestSuccess(ITestResult result)
    {
        System.out.println("Test Passed "+ result.getMethod().getMethodName()); //this will help me get the method of the test tha is passing
    }

    public void onTestFailure(ITestResult result){
        System.out.println("Test Failed "+result.getMethod().getMethodName());
        System.out.println("Exception Thrown by this method "+result.getThrowable().getMessage());//getThrowable will give me the exception and error msgs
    }

    public void onTestStart(ITestResult result) {
        System.out.println("Test started by "+result.getMethod().getMethodName());
    }

    //Step 2: After creating implementing all my Listeners methods that i need,i have to go to XML file and register
    //these listeners at a suite level for my entire project if i need it to execute for all my project.
    //So, i go to my XML file which i had created from my test steps(e.g. login test) and edit this by adding listeners
    //under my suite
}
