package HW7;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class IframeTest {
    WebDriver driver = new ChromeDriver();

    @Test
    public void testIframe() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/iframes.html");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.switchTo().frame("my-iframe");

        WebElement paragraph = driver.findElement(By.xpath("//p[contains(text(),'Magnis feugiat natoque ')]"));
        js.executeScript("arguments[0].scrollIntoView(true);", paragraph);

        wait.until(ExpectedConditions.visibilityOf(paragraph));
        Assert.assertTrue(paragraph.isDisplayed(), "Элемент не найден");

        driver.switchTo().defaultContent();
        driver.quit();
    }
}