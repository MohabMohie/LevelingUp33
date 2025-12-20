package pages.sauceDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {

    // variables
    String url = "https://www.saucedemo.com/";
    WebDriver driver;

    // locators
    By usernameInput = By.id("user-name");
    By passwordInput = By.id("password");
    By loginButton = By.id("login-button");

    // methods / actions / functions
    public Login(WebDriver driver){
        this.driver = driver;
    }

    public void navigateToLoginPage() {
        driver.navigate().to(url);
    }

    public void login(String username, String password) {
        driver.findElement(usernameInput).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

}
