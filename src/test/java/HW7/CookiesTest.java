package HW7;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Set;

public class CookiesTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/cookies.html");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testGetCookie() {
        Set<Cookie> cookies = driver.manage().getCookies();
        //Cookie cookie = driver.manage().getCookieNamed("John Doe");
        System.out.println("Исходная кука: " + cookies);
        Assert.assertNotNull(cookies, "Куки не найдены");
    }

    @Test
    public void testUpdateCookie() {
        Cookie newCookie = new Cookie("Yuliya", "01/04/2026");
        driver.manage().addCookie(newCookie);

        Cookie modifiedCookie = driver.manage().getCookieNamed("Yuliya");
        System.out.println("Обновленная кука: " + modifiedCookie);
        Assert.assertNotNull(modifiedCookie, "Кука 'Yuliya' не добавлена");

    }

    @Test
    public void testDeleteAllCookies() throws InterruptedException {
        driver.manage().deleteAllCookies();
        driver.findElement(By.id("refresh-cookies")).click();
        Thread.sleep(2000);

        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println("Куки после удаления всех: " + cookies);
        Assert.assertTrue(cookies.isEmpty(), " куки не были удалены");
    }
}
