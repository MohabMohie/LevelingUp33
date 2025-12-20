package pages.sauceDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductList {
    WebDriver driver;

    private By productDescriptionLabel = null;
    private By productPriceLabel = null;
    private By productImageSrc = null;
    private By productLink = null;

    public ProductList(WebDriver driver){
        this.driver = driver;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getProductDescription(String productName){
        productDescriptionLabel = By.xpath("//a[.='"+productName+"']/following-sibling::div");
        return driver.findElement(productDescriptionLabel).getText();
    }
    public String getProductPrice(String productName){
        productPriceLabel = By.xpath("//div[@data-test='inventory-item-description'][contains(.,'"+productName+"')]//div[@data-test='inventory-item-price']");
        return driver.findElement(productPriceLabel).getText();
    }
    public String getProductImageSrc(String productName){
        productImageSrc = By.xpath("//div[@data-test='inventory-item'][contains(.,'"+productName+"')]//img");
        return driver.findElement(productImageSrc).getDomAttribute("src");
    }

    public void openProductDetails(String productName){
        productLink = By.xpath("//a[.='" + productName + "']");
        driver.findElement(productLink).click();
    }
}
