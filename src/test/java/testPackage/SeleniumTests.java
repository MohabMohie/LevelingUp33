package testPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SeleniumTests {
    @Test
    public void sampleSeleniumTest() {
        // WebDriver interface -> RemoteWebDriver class -> ChromiumDriver class -> ChromeDriver class
        WebDriver driver;
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
        driver.quit();
    }
}
