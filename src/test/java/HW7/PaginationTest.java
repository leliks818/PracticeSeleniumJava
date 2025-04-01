package HW7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class PaginationTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/navigation1.html");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testPagination() throws InterruptedException {
        List<String> expectedResult = List.of(
                "Lorem ipsum dolor sit amet",
                "Ut enim ad minim veniam",
                "Excepteur sint occaecat cupidatat non proident"
        );

        for (int i = 1; i <= 3; i++) {
            driver.findElement(By.xpath("//a[text()='" + i + "']")).click();
            Thread.sleep(1000);
            String pageText = driver.findElement(By.tagName("p")).getText();
            Assert.assertTrue(pageText.contains(expectedResult.get(i - 1)),
                    "Текст на странице " + i + " не совпадает!");
        }
    }

    @Test
    public void clickPreviousButton() throws InterruptedException {
        Actions actions = new Actions(driver);

        driver.findElement(By.xpath("//a[text()='2']")).click();
        Thread.sleep(1000);
        String currentUrl = driver.getCurrentUrl();
        WebElement buttonPrevious = driver.findElement(By.xpath("//li[not(contains(@class, 'disabled'))]//a[text()='Previous']"));
        actions.moveToElement(buttonPrevious).click().perform();
        Thread.sleep(2000);
        String newUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(currentUrl, newUrl, "URL не изменился после клика по 'Next'!");

    }

    @Test
    public void clickNextButton() throws InterruptedException {
        Actions actions = new Actions(driver);
        String currentUrl = driver.getCurrentUrl();
        WebElement nextButton = driver.findElement(By.xpath("//li[not(contains(@class, 'disabled'))]//a[text()='Next']"));
        actions.moveToElement(nextButton).click().perform();
        Thread.sleep(2000);
        String newUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(currentUrl, newUrl, "URL не изменился после клика по 'Next'!");
    }
}