package pages.sauceDemo4Abstraction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Login extends Page {
    // variables
    private static final String url = "https://www.saucedemo.com/";
    // locators
    private static final By usernameInput = By.id("user-name");
    private static final By passwordInput = By.id("password");
    private static final By loginButton = By.id("login-button");

    public Login(WebDriver driver) {
        super(driver);
    }

    // methods / actions / functions

    public Login navigateToLoginPage() {
        driver.navigate().to(url);
        return this;
    }

    public ProductList login(String username, String password) {
        driver.findElement(usernameInput).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
        return new ProductList(driver);
    }

    public void assertCurrentUrl() {
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

}
