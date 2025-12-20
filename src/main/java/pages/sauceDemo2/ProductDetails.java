package pages.sauceDemo2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetails {
    WebDriver driver;

    private static final By productLink = By.xpath("//div[@data-test='inventory-item-name']");
    private static final By productDescriptionLabel = By.xpath("//div[@data-test='inventory-item-desc']");
    private static final By productPriceLabel = By.xpath("//div[@data-test='inventory-item-price']");
    private static final By productImageSrc = By.xpath("//img[@class='inventory_details_img']");

    public ProductDetails(WebDriver driver){
        this.driver = driver;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getProductTitle() {
        return driver.findElement(productLink).getText();
    }
    public String getProductDescription() {
        return driver.findElement(productDescriptionLabel).getText();
    }
    public String getProductPrice() {
        return driver.findElement(productPriceLabel).getText();
    }
    public String getProductImageSrc() {
        return driver.findElement(productImageSrc).getDomAttribute("src");
    }

}
