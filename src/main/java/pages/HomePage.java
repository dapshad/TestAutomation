package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    //Step 1: Create a global driver
    WebDriver driver;


    // Step 2: Create a constructor as below which will be called the moment i create an object, and it will accept a driver

    public HomePage(WebDriver driver){
        //Now i am assigning the constructor driver to the global driver above( which i now call: this.driver)
        this.driver = driver;
    }

    //Step 3: I can now create my locators using a By class

    By manage = By.xpath("//span[text()='Manage']");
    By pass = By.name("password1");
    By login = By.xpath("//button[@class='submit-btn']");

    public void loginToApplication(String username , String password) {

            System.out.println("on the manage courses page");
            driver.findElement(manage).click();
            driver.findElement(pass).sendKeys(password);
            driver.findElement(login).click();

//I can still add my Logout , Home page details here as well

        }
    }
