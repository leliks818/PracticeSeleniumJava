package HW7;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragAndDropTest {
    WebDriver driver = new ChromeDriver();

    @Test
    public void dragAndDropTest() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html");

        WebElement draggable = driver.findElement(By.id("draggable"));
        Point beforeDrag = draggable.getLocation();
        WebElement target = driver.findElement(By.id("target"));

        Actions actions = new Actions(driver);

        actions.clickAndHold(draggable).moveToElement(target).perform();
        Thread.sleep(3000);

        Point afterDrag = draggable.getLocation();

        Assert.assertNotEquals(beforeDrag, afterDrag);

     driver.quit();
}
}