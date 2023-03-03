package testcases;

import base.BaseClass;

import dataProvider.CustomDataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseClass {

    @Test(dataProvider = "LoginData",dataProviderClass = CustomDataProvider.class)// Here using data provider to read from my excel and the class custom data provider from the package

    public void loginToApplication(String uname, String pass)//here i will pass my 2 columns title here as parameters
    {
        LoginPage page = new LoginPage(driver);
        page.loginToApplication(uname, pass);

        //Add assertion
        //Add Logout
        //Add Home
    }


    //So instead of using the method below, i will use the one above which is not hard-coded

    /* @Test
    public void loginToApplication()
    {
        LoginPage page = new LoginPage(driver);
        page.loginToApplication("ineuron@ineuron.ai","ineuron");

    }*/
}
