package a5abstraction;

import org.testng.annotations.Test;
import pages.sauceDemo4Abstraction.Login;

/**
 * open chrome
 * navigate to saucedemo <a href="https://www.saucedemo.com/">https://www.saucedemo.com/</a>
 * login using valid credentials standard_user,secret_sauce
 * open the details for "Sauce Labs Fleece Jacket"
 * check that all the information is listed correctly
 * close chrome
 */
public class SauceDemoTests extends TestScenario {

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
