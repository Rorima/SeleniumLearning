package com.basicstudy.module6.waits;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class WaitsTest {
    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testWait() {
        driver.get("https://demoqa.com/accordian");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        // Presence of element
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("app")));

        // Element to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("img[src='/images/Toolsqa.jpg']")));
        WebElement accordianContainer = driver.findElement(By.id("accordianContainer"));
        WebElement secondCard = driver.findElement(By.xpath("//div[text()='Where does it come from?']/ancestor::div[contains(@class,'card')]"));
        executor.executeScript("arguments[0].scrollIntoView(true)", accordianContainer);

        // Visibility of element
        wait.until(ExpectedConditions.visibilityOf(secondCard));
        secondCard.click();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
