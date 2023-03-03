package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import helper.Utility;

//use case: This Listener methods i created below which is : createInstance will be used during all my tests to generate
//my Extent reports for me.

public class ExtentManager {

    public static ExtentReports extent; //creating a global variable here so i can use it all through this class & methods below

    //Creating If-else condition of getInstance so my 'createInstance method' below can be re-used when needed & attach
    // all my several tests reports into one or a new one created by itself where it is not available.


    public static ExtentReports getInstance()
    {
        if (extent==null){
           extent= createInstance(); //I am able to call this method below because it is static and also in the same class
        }
        return extent;
    }

    //So what this getInstance method above does is to help me create a new Instance Extent report as below where it is
    //not available or re-use this method below where it is available.


    public static ExtentReports createInstance(){

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./Reports/MyAutomationHtmlReport"+ Utility.getCurrentDateAndTime() +".html");
        sparkReporter.config().setTheme(Theme.DARK); //set the theme
        sparkReporter.config().setReportName("Automation Report"); //set report name that i want
        sparkReporter.config().setDocumentTitle("Sprint 1 Report"); //set my doc title

        //Then create object of extent report below

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(sparkReporter); //here i attach my report which is my spark reporter above that has my file

        //Then i have to return this extent report so i can use it in my listener, if it's void, then i can't use it again
        //And because i am returning extent, my method name above will be ExtentReports as extent is a type of ExtentReports

        return extent;

        //Now because, i don't want to keep creating Instance reports, i will need this method i created to be re-used when
        //needed or a new one created where not available, this means, i will create another method above with if-else condition.
    }
}
