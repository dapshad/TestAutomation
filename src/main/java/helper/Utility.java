package helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


    public class Utility {



        //here, i'm creating my own method and it will accept driver as an argument as seen in line 12 & my return type of method is Alert

        public static WebDriver startBrowser(String browserName, String applicationURL)
        {

            WebDriverManager.chromedriver().setup();
            WebDriver driver = null;
            if(browserName.contains("Chrome")){
                driver= new ChromeDriver();
            } else if (browserName.contains("Firefox")) {
                driver = new FirefoxDriver();
            } else if (browserName.contains("Edge")) {
                driver= new EdgeDriver();
            }else{
                Reporter.log("Sorry "+ browserName + "is not supported,Running test in default browser as chrome");
                driver= new ChromeDriver();
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(applicationURL);
            return driver;
        }


        public static WebDriver startBrowserInHeadlessMode(String browserName, String applicationURL,boolean headless)
        {
            WebDriverManager.chromedriver().setup();
            WebDriver driver = null; //assigning null to this driver because it is a local variable
            if(browserName.contains("Chrome")||browserName.contains("Google Chrome")){
                ChromeOptions opt = new ChromeOptions();
                opt.setHeadless(true);
                driver= new ChromeDriver();

            } else if (browserName.contains("Firefox")) {
                FirefoxOptions opt1 = new FirefoxOptions();
                opt1.setHeadless(true);
                driver = new FirefoxDriver();

            } else if (browserName.contains("Edge")) {
                EdgeOptions opt2 = new EdgeOptions();
                opt2.setHeadless(true);
                driver= new EdgeDriver();
            }else{
                Reporter.log("Sorry "+ browserName + "is not supported,Running test in default browser as chrome");
                driver= new ChromeDriver();
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(applicationURL);
            return driver;
        }
        public static Alert waitForAlert(WebDriver driver)
        {
            Alert alt = null;

            for (int i=0;i<15;i++)
            {
                try{driver.switchTo().alert();break;

                } catch (NoAlertPresentException e){
                    System.out.println("Waiting until alert to be present ");
                    waitForSeconds(1);//here i am calling the method created below to replace Thread.sleep here,
                    //So, anytime i need to insert Thread.sleep across my package going forward, i can call waitForSeconds method below
                }
            }
            return alt;
        }

        //Here i will be creating an overloading method to make time flexible and not hard-coded like above
        // where time is 15sec and i have to change it always depending on the need
        public static Alert waitForAlert(WebDriver driver, int time) {
            Alert alt = null;
            for (int i = 0; i < time; i++)
                try {
                    alt = driver.switchTo().alert();
                    break;
                } catch (NoAlertPresentException e) {
                    System.out.println("Waiting until alert to be present ");
                    waitForSeconds(1);
                }
            return alt;

        }

        public static void waitForSeconds(int seconds)
        {
            try{
                Thread.sleep(seconds*1000);
            }catch (InterruptedException e){
            }
        }


        public static void captureScreenshot( WebDriver driver)
        {
            try
            {
                //I can optimize my screenshot code and add current time to it. See the line 63 below vs line 61(without time)
                // FileHandler.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), new File("./MyScreenshot2.png"));

                FileHandler.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), new File("./MyScreenshot4" +getCurrentDateAndTime()+".png"));
            }catch (IOException e){
                System.out.println("Something went wrong "+ e.getMessage());
            }

        }


        public static String getCurrentDateAndTime() //Keeping this as String return type so, it prints the date , if i use void, it will return nothing
        {
            String date = new SimpleDateFormat("EEE, d MMM yyyy HH_mm_ss ").format(new Date());
            return date;
        }

        public static String captureScreenshotinBase64Format(WebDriver driver)
        {
          TakesScreenshot ts = (TakesScreenshot)driver;
          String base64 = ts.getScreenshotAs(OutputType.BASE64);
          return base64;
            }


        public static void captureScreenshotOfWebElement( WebDriver driver,WebElement element)
        {
            try
             {
            //Write a logic for screenshot of a Webelement below
            FileHandler.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), new File("./MyScreenshot4" +getCurrentDateAndTime()+".png"));
            }catch (IOException e){
               System.out.println("Something went wrong "+ e.getMessage());
            }

        }

        public static void multipleScreenshot(int timeInterval, int totalDuration)
        {
            for (int i =0; i<totalDuration; i++)
                try{
                    Thread.sleep(timeInterval);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
        }

        public static WebElement highlightWebElement(WebDriver driver, WebElement element)
        {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style','background:yellow;border:2px solid red;')",element); //this is for higlighting

            //to remove the highlights:
            waitForSeconds(2);
            js.executeScript("arguments[0].setAttribute('style','border:2px solid white;')",element);// to remove the highlighted element

            return element;
        }

        public static WebElement highlightWebElement(WebDriver driver, By locator)
        {
            WebElement element = driver.findElement(locator);
            JavascriptExecutor js = (JavascriptExecutor) driver;

            js.executeScript("arguments[0].setAttribute('style','background:yellow;border:2px solid red;')",element); //this is for higlighting

            //to remove the highlights:
            waitForSeconds(2);
            js.executeScript("arguments[0].setAttribute('style','border:2px solid white;')",element);// to remove the highlighted element

            return element;
        }

    }


