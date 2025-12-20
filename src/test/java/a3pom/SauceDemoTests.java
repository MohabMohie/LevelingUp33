package a3pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.sauceDemo.Login;
import pages.sauceDemo.ProductDetails;
import pages.sauceDemo.ProductList;

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
    Login loginPage;
    ProductList productListPage;
    ProductDetails productDetailsPage;

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

        loginPage = new Login(driver);
        productListPage = new ProductList(driver);
        productDetailsPage = new ProductDetails(driver);
    }

    @AfterClass
    public void afterClass(){
        // close chrome
        driver.quit();
    }

    @Test
    public void navigateToSauceDemo () {
        loginPage.navigateToLoginPage();
        Assert.assertEquals(loginPage.getCurrentUrl(), "https://www.saucedemo.com/");
    }

    @Test(dependsOnMethods = {"navigateToSauceDemo"})
    public void loginUsingValidCredentials () {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productListPage.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test(dependsOnMethods = {"navigateToSauceDemo", "loginUsingValidCredentials"})
    public void openProductDetails () {
        // open the details for "Sauce Labs Fleece Jacket"
        productName = "Sauce Labs Fleece Jacket";

        expectedDescription =  productListPage.getProductDescription(productName);
        expectedPrice = productListPage.getProductPrice(productName);
        expectedImageSrc = productListPage.getProductImageSrc(productName);

        productListPage.openProductDetails(productName);

        Assert.assertTrue(Objects.requireNonNull(productDetailsPage.getCurrentUrl()).contains("https://www.saucedemo.com/inventory-item.html?id="));
    }

    @Test(dependsOnMethods = {"navigateToSauceDemo", "loginUsingValidCredentials", "openProductDetails"})
    public void validateAllProductInformation () {
        String actualProductTitle =  productDetailsPage.getProductTitle();
        String actualDescription =  productDetailsPage.getProductDescription();
        String actualPrice = productDetailsPage.getProductPrice();
        String actualImageSrc = productDetailsPage.getProductImageSrc();

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
