package a5abstraction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public abstract class TestScenario {
    WebDriver driver;

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
}
