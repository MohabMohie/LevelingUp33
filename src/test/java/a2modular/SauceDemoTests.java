package a2modular;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;

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
    String productName;
    String expectedDescription;
    String expectedPrice;
    String expectedImageSrc;

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
        // navigate to saucedemo https://www.saucedemo.com/
        driver.navigate().to("https://www.saucedemo.com/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }

    @Test(dependsOnMethods = {"navigateToSauceDemo"})
    public void loginUsingValidCredentials () {
        // login using valid credentials standard_user,secret_sauce
        By usernameInput = By.id("user-name");
        By passwordInput = By.id("password");
        By loginButton = By.id("login-button");

        driver.findElement(usernameInput).sendKeys("standard_user");
        driver.findElement(passwordInput).sendKeys("secret_sauce");
        driver.findElement(loginButton).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test(dependsOnMethods = {"navigateToSauceDemo", "loginUsingValidCredentials"})
    public void openProductDetails () {
        // open the details for "Sauce Labs Fleece Jacket"
        productName = "Sauce Labs Fleece Jacket";

        By productLink = By.xpath("//a[.='" + productName + "']");

        By productDescriptionLabel = By.xpath("//a[.='"+productName+"']/following-sibling::div");
        By productPriceLabel = By.xpath("//div[@data-test='inventory-item-description'][contains(.,'"+productName+"')]//div[@data-test='inventory-item-price']");
        By productImageSrc = By.xpath("//div[@data-test='inventory-item'][contains(.,'"+productName+"')]//img");

        expectedDescription =  driver.findElement(productDescriptionLabel).getText();
        expectedPrice = driver.findElement(productPriceLabel).getText();
        expectedImageSrc = driver.findElement(productImageSrc).getDomAttribute("src");

        driver.findElement(productLink).click();
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("https://www.saucedemo.com/inventory-item.html?id="));
    }

    @Test(dependsOnMethods = {"navigateToSauceDemo", "loginUsingValidCredentials", "openProductDetails"})
    public void validateAllProductInformation () {
        // check that all the information is listed correctly
        By productLink = By.xpath("//div[@data-test='inventory-item-name']");
        By productDescriptionLabel = By.xpath("//div[@data-test='inventory-item-desc']");
        By productPriceLabel = By.xpath("//div[@data-test='inventory-item-price']");
        By productImageSrc = By.xpath("//img[@class='inventory_details_img']");

        String actualProductTitle =  driver.findElement(productLink).getText();
        String actualDescription =  driver.findElement(productDescriptionLabel).getText();
        String actualPrice = driver.findElement(productPriceLabel).getText();
        String actualImageSrc = driver.findElement(productImageSrc).getDomAttribute("src");

        Assert.assertTrue(productName.equals(actualProductTitle)
                        && expectedDescription.equals(actualDescription)
                        && expectedPrice.equals(actualPrice)
                        && expectedImageSrc.equals(actualImageSrc),
                        "Product details are not correct. \nActual details: "
                                + actualProductTitle + ", "
                                + actualDescription + ", "
                                + actualPrice + ", "
                                + actualImageSrc + ". \nExpected details: "
                                + actualProductTitle + ", "
                                + expectedDescription + ", "
                                + expectedPrice + ", "
                                + expectedImageSrc + ".");
    }
}
