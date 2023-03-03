package browserFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class BrowserFactory {

    static  WebDriver driver;

    public static  WebDriver getBrowserInstance()
    {
        return driver;
    }

    public static WebDriver startBrowser(String browserName, String applicationURL)
    {
        WebDriverManager.chromedriver().setup();

        //making use of the global driver above

        if(browserName.contains("Chrome")){
            driver= new ChromeDriver();
        } else if (browserName.contains("Firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.contains("Edge")) {
            driver= new EdgeDriver();
        }else{
            driver= new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(applicationURL);
        return driver;
    }

    }
