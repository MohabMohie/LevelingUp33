package pages.sauceDemo4Abstraction;

import org.openqa.selenium.WebDriver;

public abstract class Page {
    WebDriver driver;
    public Page (WebDriver driver){
        this.driver = driver;
    }
}
