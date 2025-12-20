package a1linear;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WorkshopTests {
//    private static final By textInputLocator = By.name("my-text");

    /**
     * Open Google Chrome
     * Navigate to [http://the-internet.herokuapp.com/checkboxes]
     * Check Checkbox 1
     * Assert that both Checkboxes are checked
     * Close Google Chrome
     */
    @Test
    public void checkboxesTask() {
        WebDriver driver;
        // Open Google Chrome
        driver = new ChromeDriver();
        // Navigate to [http://the-internet.herokuapp.com/checkboxes]
        driver.navigate().to("http://the-internet.herokuapp.com/checkboxes");
        // Check Checkbox 1
        By firstCheckbox = By.xpath("//input[@type='checkbox'][1]");
        driver.findElement(firstCheckbox).click();
        // Assert that both Checkboxes are checked
        var isFirstCheckboxSelected = driver.findElement(firstCheckbox).isSelected();
        By secondCheckbox = By.xpath("//input[@type='checkbox'][2]");
        var isSecondCheckboxSelected = driver.findElement(secondCheckbox).isSelected();
        Assert.assertTrue(isFirstCheckboxSelected && isSecondCheckboxSelected, "Both checkboxes should be selected. The first checkbox selected: " + isFirstCheckboxSelected + ", the second checkbox selected: " + isSecondCheckboxSelected);
        driver.quit();
    }
}
