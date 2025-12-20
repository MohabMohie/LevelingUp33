package pages.sauceDemo2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductList {
    WebDriver driver;

    private static final String productDescriptionLabelXPath = "//a[.='%s']/following-sibling::div";
    private static final String productPriceLabelXPath = "//div[@data-test='inventory-item-description'][contains(.,'%s')]//div[@data-test='inventory-item-price']";
    private static final String productImageSrcXPath = "//div[@data-test='inventory-item'][contains(.,'%s')]//img";
    private static final String productLinkXPath = "//a[.='%s']";

    public ProductList(WebDriver driver){
        this.driver = driver;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getProductDescription(String productName){
        return driver.findElement(By.xpath(String.format(productDescriptionLabelXPath, productName))).getText();
    }
    public String getProductPrice(String productName){
        return driver.findElement(By.xpath(String.format(productPriceLabelXPath, productName))).getText();
    }
    public String getProductImageSrc(String productName){
        return driver.findElement(By.xpath(String.format(productImageSrcXPath, productName))).getDomAttribute("src");
    }

    public void openProductDetails(String productName){
        driver.findElement(By.xpath(String.format(productLinkXPath, productName))).click();
    }
}
