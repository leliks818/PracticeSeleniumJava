package HW7;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DialogBoxesTest {
    private WebDriver driver;
    private static WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

        @Test
        public void testAlert () {
            driver.findElement(By.id("my-alert")).click();
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                Assert.assertEquals(alert.getText(), "Hello world!");
                alert.accept();
            }

    @Test
    public void testConfirm() {
        driver.findElement(By.id("my-confirm")).click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert confirm = wait.until(ExpectedConditions.alertIsPresent());
        confirm.dismiss();

        WebElement confirmText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirm-text")));
        Assert.assertEquals(confirmText.getText().trim(), "You chose: false", "Текст не совпадает или не появился!");
    }

    @Test
    public void testPrompt () {
        driver.findElement(By.id("my-prompt")).click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert prompt = wait.until(ExpectedConditions.alertIsPresent());
        prompt.sendKeys("YULIYA");
        prompt.accept();
        WebElement confirmText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("prompt-text")));
        Assert.assertEquals(confirmText.getText().trim(), "You typed: YULIYA", "Текст не появился!");
    }

    @Test
    public void testModalWindow () {
        driver.findElement(By.id("my-modal")).click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
       WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));
       driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();

        WebElement confirmText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modal-text")));
        Assert.assertEquals(confirmText.getText().trim(), "You chose: Save changes", "Текст не появился!");
    }
}
