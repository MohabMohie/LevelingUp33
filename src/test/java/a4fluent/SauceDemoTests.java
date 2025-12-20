package a4fluent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.sauceDemo3Fluent.Login;

import java.time.Duration;

/**
 * open chrome
 * navigate to saucedemo <a href="https://www.saucedemo.com/">https://www.saucedemo.com/</a>
 * login using valid credentials standard_user,secret_sauce
 * open the details for "Sauce Labs Fleece Jacket"
 * check that all the information is listed correctly
 * close chrome
 */
public class SauceDemoTests {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        // open chrome
        ChromeOptions options;
        options = new ChromeOptions();
        options.addArguments("--window-position=0,0");
        options.addArguments("--window-size=1080,720");
        options.setImplicitWaitTimeout(Duration.ofSeconds(5));
        driver = new ChromeDriver(options);
    }

    @AfterClass
    public void afterClass(){
        // close chrome
        driver.quit();
    }

    @Test
    public void navigateToSauceDemo () {
        new Login(driver)
                .navigateToLoginPage()
                .assertCurrentUrl();
    }

    @Test(dependsOnMethods = {"navigateToSauceDemo"})
    public void loginUsingValidCredentials () {
        new Login(driver)
                .login("standard_user", "secret_sauce")
                .assertCurrentUrl();
    }
}
