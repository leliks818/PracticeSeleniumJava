package HW7;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class InfiniteScrollTest {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();

    }

    @Test
    public void testScrollToParagraph() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/infinite-scroll.html");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//b[contains(text(), 'Lorem ipsum')]")));
        Thread.sleep(3000);
        WebElement paragraph = driver.findElement(By.xpath("(//b[contains(text(), 'Lorem ipsum')])[last()]"));

        js.executeScript("arguments[0].scrollIntoView(true);", paragraph);
        wait.until(ExpectedConditions.visibilityOf(paragraph));

        Assert.assertTrue(paragraph.isDisplayed(), "Элемент не найден.");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
