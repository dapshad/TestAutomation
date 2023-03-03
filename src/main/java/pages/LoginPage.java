package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    //Step 1: Create a global driver
    WebDriver driver;


    // Step 2: Create a constructor as below which will be called the moment i create an object, and it will accept a driver

    public LoginPage(WebDriver driver){
        //Now i am assigning the constructor driver to the global driver above( which i now call: this.driver)
        this.driver = driver;
    }

    //Step 3: I can now create my locators using a By class

    By user = By.id("email1");
    By pass = By.name("password1");
    By login = By.xpath("//button[@class='submit-btn']");

    public void loginToApplication(String username , String password) {

            System.out.println("calling my locators and methods");
            driver.findElement(user).sendKeys(username);
            driver.findElement(pass).sendKeys(password);
            driver.findElement(login).click();

//I can still add my Logout , Home page details here as well

        }
    }
