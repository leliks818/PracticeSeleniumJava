package HW7;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ShadowDomTest {
    WebDriver driver = new ChromeDriver();

    @Test
    public void testShadowDom() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/shadow-dom.html");

        WebElement content = driver.findElement(By.id("content"));
        SearchContext shadowRoot = content.getShadowRoot();
        WebElement shadowText = shadowRoot.findElement(By.cssSelector("p"));
        Assert.assertEquals(shadowText.getText(), "Hello Shadow DOM", "Текст не совпадает");

        driver.quit();
    }
}