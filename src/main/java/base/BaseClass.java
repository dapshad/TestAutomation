package base;

import browserFactory.BrowserFactory;

import dataProvider.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;


public class BaseClass {

    public WebDriver driver;

  @BeforeClass
    public void setupBrowser() throws IOException {
        //Approach 1: Using config file to manage the browsername and app URL... This doesn't work for cross browser testing/ parallel execution
       driver= BrowserFactory.startBrowser(ConfigReader.getProperty("browser"),ConfigReader.getProperty("url"));
    }

    @AfterClass
    public void closeBrowser(){
      driver.quit();
    }
}


//Instead of using the below hard-coded browser name and URL, i can use the above with the help of my configuration file

    /*@BeforeClass
    public void setupBrowser()
    {
        System.out.println("Browser setup is ready");
        driver= BrowserFactory.startBrowser("Chrome", "https://ineuron-courses.vercel.app/login");
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}*/