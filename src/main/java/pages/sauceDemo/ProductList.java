package pages.sauceDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductList {
    WebDriver driver;

    public ProductList(WebDriver driver){
        this.driver = driver;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getProductDescription(String productName){
        By productDescriptionLabel = By.xpath("//a[.='"+productName+"']/following-sibling::div");
        return driver.findElement(productDescriptionLabel).getText();
    }
    public String getProductPrice(String productName){
        By productPriceLabel = By.xpath("//div[@data-test='inventory-item-description'][contains(.,'"+productName+"')]//div[@data-test='inventory-item-price']");
        return driver.findElement(productPriceLabel).getText();
    }
    public String getProductImageSrc(String productName){
        By productImageSrc = By.xpath("//div[@data-test='inventory-item'][contains(.,'"+productName+"')]//img");
        return driver.findElement(productImageSrc).getDomAttribute("src");
    }

    public void openProductDetails(String productName){
        By productLink = By.xpath("//a[.='" + productName + "']");
        driver.findElement(productLink).click();
    }
}
