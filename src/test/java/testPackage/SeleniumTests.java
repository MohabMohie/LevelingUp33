package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class SeleniumTests {

    /**
     * A sample Selenium test that demonstrates basic WebDriver operations.
     * This test opens a browser, navigates to Google, and performs some basic actions.
     */
    @Test
    public void sampleSeleniumTest() {
        // WebDriver interface -> RemoteWebDriver class -> ChromiumDriver class -> ChromeDriver class
        WebDriver driver;
        //default constructor of ChromeDriver class
//        driver = new ChromeDriver();

        driver = new FirefoxDriver();
        ChromeOptions options;
        options = new ChromeOptions();
//        options.setImplicitWaitTimeout(Duration.ofSeconds(30));
//        options.enableBiDi();
        options.addArguments("--window-size=1080,720");  // Example: 1920x1080 resolution
        options.setImplicitWaitTimeout(Duration.ofSeconds(10));
//        options.setPageLoadStrategy(PageLoadStrategy.NONE);
//        options.addArguments("--window-position=0,0");  // Position window at top-left corner
        driver = new ChromeDriver(options);

//        driver.manage().window().setPosition(new Point(0,0));

//        Dimension dimension;
//        dimension = new Dimension(1920, 1080);
//        driver.manage().window().setSize(dimension);
//
//        driver.manage().window().setSize(new Dimension(1080,720));

//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

//        driver.close();
//        driver.quit();

        driver.get("https://www.google.com");
        driver.navigate().to("https://www.google.com");

//        driver.findElement();
//        driver.findElements();

        driver.getTitle();
        driver.getCurrentUrl();


        driver.quit();
    }

    @Test
    public void elementActionsTest() {
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://www.selenium.dev/selenium/web/web-form.html");

        //  <input type="text" class="form-control" name="my-text" id="my-text-id" myprop="myvalue">

        By.id("my-text-id");
        By.cssSelector("#my-text-id");
        By.xpath("//*[@id='my-text-id']");

        By.tagName("input");
        By.name("my-text");
        By.className("form-control");
        By.linkText("Return to index");
        By.partialLinkText("Return");


        //  <input type="text" class="form-control" name="my-text" myprop="myvalue" id ="my-username-id-12836972315768312541623">

        By.cssSelector("input.form-control[name='my-text']");
        By.xpath("//input[@class='form-control'][@name='my-text']");

        By.xpath("//input[contains(@class,'form-')]");

        //input[@name='my-text']
        //*[@name='my-text']

        //TODO: practice element actions, advanced xpath and element identification, synchronization techniques (table task #7)

        driver.findElement(By.id("my-text-id")).click();

        driver.quit();
    }

    @Test
    public void workshopTest() {
        WebDriver driver;
        driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        // 1. WebElement Object => @FindBy
//        WebElement textInput = driver.findElement(By.xpath("//input[@name='my-text']"));
//        // javascript workibg in the background StaleElementReferenceException
//        textInput.click();
//        textInput.sendKeys("test");
//
//        // 2. By locator Object
//        By textInputLocator = By.name("my-text");
//        driver.findElement(textInputLocator).click();
//        driver.findElement(textInputLocator).sendKeys("test");
//
//        // 3. No Objects
//        driver.findElement(By.xpath("//input[@name='my-text']")).click();
//        driver.findElement(By.xpath("//input[@name='my-text']")).sendKeys("test");

        driver.quit();
    }
}
