package com.basicstudy.module5.advancedactions;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AdvancedInteractionsTest {
    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void actionsTest() {
        driver.get("https://demoqa.com/sortable");
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        // Mouse hover
        WebElement item1 = driver.findElement(By.xpath("//*[@id=\"demo-tabpane-list\"]/div/div[1]"));
        wait.until(ExpectedConditions.visibilityOf(item1));
        executor.executeScript("arguments[0].scrollIntoView(true)", item1);
        actions.moveToElement(item1).perform();

        // Drag and drop
        WebElement target = driver.findElement(By.xpath("//*[@id=\"demo-tabpane-list\"]/div/div[5]"));
        actions.clickAndHold(item1).moveToElement(target).release().perform();

        // Right click
        driver.get("https://demoqa.com/buttons");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[3]")));
        WebElement rightClickBtn = driver.findElement(By.id("rightClickBtn"));
        wait.until(ExpectedConditions.visibilityOf(rightClickBtn));
        executor.executeScript("arguments[0].scrollIntoView(true)", rightClickBtn);
        actions.contextClick(rightClickBtn).perform();

        // Double click
        WebElement doubleClickBtn = driver.findElement(By.id("doubleClickBtn"));
        executor.executeScript("arguments[0].scrollIntoView(true)", doubleClickBtn);
        actions.doubleClick(doubleClickBtn).perform();

        // Click
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[normalize-space()='Click Me']")));
        WebElement clickBtn = driver.findElement(By.xpath("//button[normalize-space()='Click Me']"));
        actions.click(clickBtn).perform();

        // Keystrokes
        driver.get("https://demoqa.com/text-box");
        WebElement textArea = wait.until(ExpectedConditions.elementToBeClickable(By.id("currentAddress")));
        executor.executeScript("arguments[0].scrollIntoView(true)", textArea);
        textArea.click();
        actions.sendKeys("This is a test");
        actions.keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys("Testing")
                .perform();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
