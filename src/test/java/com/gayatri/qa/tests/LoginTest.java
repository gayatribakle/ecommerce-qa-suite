package com.gayatri.qa.tests;

import com.gayatri.qa.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class LoginTest {


    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void validLoginTest() {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void invalidLoginTest() {
        loginPage.login("standard_user", "wrong_password");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"));
    }
    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][] {
                {"standard_user", "wrong_password", "Username and password do not match"},
                {"locked_out_user", "secret_sauce", "locked out"},
                {"", "secret_sauce", "Username is required"},
                {"standard_user", "", "Password is required"}
        };
    }

    @Test(dataProvider = "invalidLoginData")
    public void invalidLoginDataTest(String user, String pass, String expectedError) {
        loginPage.login(user, pass);
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedError));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
