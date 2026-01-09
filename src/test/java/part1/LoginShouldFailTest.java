package part1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginShouldFailTest {

    WebDriver driver;

    /*
    The @BeforeClass makes sure this code will happen after the constructor,
    but before any tests are done
    */
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @AfterClass
    public void tearDown() {
        //driver.quit();
    }

    @Test
    public void testLoggingIntoApplication() throws InterruptedException {

        Thread.sleep(4000);

        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.tagName("button"));

        username.sendKeys("Admin");
        password.sendKeys("admin123");
        loginButton.click();

        Thread.sleep(3000);

        String actualResult = driver.findElement(By.tagName("h6")).getText();
        String expectedResult = "Dashboard";

        Assert.assertNotEquals(actualResult, expectedResult);
    }
}
