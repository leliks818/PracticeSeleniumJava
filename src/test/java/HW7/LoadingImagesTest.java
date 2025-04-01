package HW7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoadingImagesTest {
    WebDriver driver = new ChromeDriver();
    @Test
    public void testImageLoading() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement image = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("img")));

        Assert.assertTrue(image.isDisplayed(), "Изображения не загрузилось");

        driver.quit();
    }
}