package a1linear;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * open chrome
 * navigate to saucedemo <a href="https://www.saucedemo.com/">https://www.saucedemo.com/</a>
 * login using valid credentials standard_user,secret_sauce
 * open the details for "Sauce Labs Fleece Jacket"
 * check that all the information is listed correctly
 */
public class SauceDemoTests {
    @Test
    public void sauceDemoScenario () {
        WebDriver driver;
        // open chrome
        ChromeOptions options;
        options = new ChromeOptions();
        options.addArguments("--window-position=0,0");
        options.addArguments("--window-size=1080,720");
//        options.setImplicitWaitTimeout(Duration.ofSeconds(5));
        driver = new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // navigate to saucedemo https://www.saucedemo.com/
        driver.navigate().to("https://www.saucedemo.com/");

        // login using valid credentials standard_user,secret_sauce
        By usernameInput = By.id("user-name");
        By passwordInput = By.id("password");
        By loginButton = By.id("login-button");

        driver.findElement(usernameInput).sendKeys("standard_user");
        driver.findElement(passwordInput).sendKeys("secret_sauce");
        driver.findElement(loginButton).click();

        // TODO: Investigate "change your password" popup that sometimes appears here
        // TODO: wait until the products page is loaded

//        Thread.sleep(Duration.ofSeconds(5));
//        Thread.sleep(5000);


        // open the details for "Sauce Labs Fleece Jacket"
//        By productLink = By.xpath("//*[.='Sauce Labs Fleece Jacket'][contains(@data-test,'title')]");
//        By productLink = By.xpath("//div[text()='Sauce Labs Fleece Jacket']/parent::a");

        String productName = "Sauce Labs Onesie";

        By productLink = By.xpath("//a[.='" + productName + "']");

        By productDescriptionLabel =
                By.xpath("//a[.='"+productName+"']/following-sibling::div");
//                By.xpath("//a[.='Sauce Labs Fleece Jacket']/parent::div/div");
//                By.xpath("//div[@class='inventory_item_label'][contains(.,'Sauce Labs Fleece Jacket')]/div");
        By productPriceLabel = By.xpath("//div[@data-test='inventory-item-description'][contains(.,'"+productName+"')]//div[@data-test='inventory-item-price']");
        By productImageSrc = By.xpath("//div[@data-test='inventory-item'][contains(.,'"+productName+"')]//img");

        Wait<WebDriver> wait;

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait = new FluentWait<>(driver)
//                .withTimeout(Duration.ofSeconds(5))
//                .pollingEvery(Duration.ofMillis(500))
//                .ignoring(NotFoundException.class);


        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NotFoundException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(StaleElementReferenceException.class);

//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gg")));
        wait.until(d -> d.findElement(By.id("gg")).isDisplayed());
        wait.until(d -> d.findElement(By.id("gg")).isEnabled());
        driver.findElement(By.id("gg")).getText();

        wait.until(d -> {
            String text = d.findElement(By.id("gg")).getText();
            return text.equals(productName);
        });

        String expectedDescription =  driver.findElement(productDescriptionLabel).getText();
        String expectedPrice = driver.findElement(productPriceLabel).getText();
        String expectedImageSrc = driver.findElement(productImageSrc).getDomAttribute("src");

        driver.findElement(productLink).click();

        // TODO: wait until the product details page is loaded
        // check that all the information is listed correctly
        productLink = By.xpath("//div[@data-test='inventory-item-name']");
        productDescriptionLabel = By.xpath("//div[@data-test='inventory-item-desc']");
        productPriceLabel = By.xpath("//div[@data-test='inventory-item-price']");
        productImageSrc = By.xpath("//img[@class='inventory_details_img']");
//        By.cssSelector("img.inventory_details_img");

        String actualProductTitle =  driver.findElement(productLink).getText();
        String actualDescription =  driver.findElement(productDescriptionLabel).getText();
        String actualPrice = driver.findElement(productPriceLabel).getText();
        String actualImageSrc =
//                driver.findElement(productImageSrc).getAttribute("src");
                driver.findElement(productImageSrc).getDomAttribute("src");
//                driver.findElement(productImageSrc).getDomProperty("src");
        Assert.assertTrue(productName.equals(actualProductTitle)
                        && expectedDescription.equals(actualDescription)
                        && expectedPrice.equals(actualPrice)
                        && expectedImageSrc.equals(actualImageSrc),
                        "Product details are not correct. \nActual details: "
                                + actualProductTitle + ", "
                                + actualDescription + ", "
                                + actualPrice + ", "
                                + actualImageSrc + ". \nExpected details: "
                                + "Sauce Labs Fleece Jacket" + ", "
                                + expectedDescription + ", "
                                + expectedPrice + ", "
                                + expectedImageSrc + ".");
        driver.quit();
    }
}
