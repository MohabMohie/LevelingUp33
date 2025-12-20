package pages.sauceDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetails {
    WebDriver driver;

    By productLink = By.xpath("//div[@data-test='inventory-item-name']");
    By productDescriptionLabel = By.xpath("//div[@data-test='inventory-item-desc']");
    By productPriceLabel = By.xpath("//div[@data-test='inventory-item-price']");
    By productImageSrc = By.xpath("//img[@class='inventory_details_img']");

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
