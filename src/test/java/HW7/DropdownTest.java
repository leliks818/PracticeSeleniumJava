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

public class DropdownTest {
    private WebDriver driver;

    private final List<String> dropDownItems = List.of(
            "Action",
            "Another action",
            "Something else here",
            "Separated link"
    );
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void leftClick() throws InterruptedException {

        Actions actions = new Actions(driver);
        WebElement dropdownLeft = driver.findElement(By.id("my-dropdown-1"));
        actions.click(dropdownLeft).perform();
        Thread.sleep(1000);

        List<WebElement> dropdownElements = driver.findElements(By.xpath("//ul[@class='dropdown-menu show']//a"));
        for (WebElement element : dropdownElements) {
            String actualItemText = element.getText().trim();  // Получаем текст каждого элемента
            Assert.assertTrue(dropDownItems.contains(actualItemText), " список не совпадает с ожидаемым");
        }
    }

    @Test
    public void rightClick() throws InterruptedException {

        Actions actions = new Actions(driver);
        WebElement dropdownRight = driver.findElement(By.id("my-dropdown-2"));
        actions.contextClick(dropdownRight).perform();
        Thread.sleep(1000);

        List<WebElement> dropdownElements = driver.findElements(By.xpath("//ul[@class='dropdown-menu show']//a"));
        for (WebElement element : dropdownElements) {
            String actualItemText = element.getText().trim();  // Получаем текст каждого элемента
            Assert.assertTrue(dropDownItems.contains(actualItemText),
                    " список не совпадает с ожидаемым: " );
        }
    }
    @Test
    public void testDoubleClick() throws InterruptedException {
        Actions actions= new Actions(driver);
        WebElement dropDowndouble = driver.findElement(By.id("my-dropdown-3"));
        actions.doubleClick(dropDowndouble).perform();
        Thread.sleep(1000);

        List <WebElement> doubleElements = driver.findElements(By.xpath("//ul[@class='dropdown-menu show']//a"));
        for(WebElement element: doubleElements){
            String actualText =element.getText().trim();
            Assert.assertTrue(dropDownItems.contains(actualText), "Список не совпадает с ожидаемым: " );
        }
    }
}