package HW7;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class SlowCalculatorTest {
    WebDriver driver = new ChromeDriver();

    @Test
    public void testSlowCalculator() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = driver.findElement(By.id("calculator"));
        input.findElement(By.xpath("//span[text() = '7']")).click();
        input.findElement(By.xpath("//span[text() = '+']")).click();
        input.findElement(By.xpath("//span[text() = '7']")).click();
        input.findElement(By.xpath("//span[text() = '=']")).click();

        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("screen")));
        wait.until(ExpectedConditions.textToBePresentInElement(result, "14"));
        Assert.assertEquals(result.getText(), "14", "Результат неверный");

        driver.quit();
    }
}
