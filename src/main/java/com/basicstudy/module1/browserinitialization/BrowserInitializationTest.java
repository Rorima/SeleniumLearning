package com.basicstudy.module1.browserinitialization;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class BrowserInitializationTest {

    public static WebDriver driver;
    private static ChromeOptions options;
    private String mainWindow;
    private WebDriverWait wait;

    @BeforeMethod
    public void initializeBrowser() {
        options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.manage().window().maximize();
        mainWindow = driver.getWindowHandle();
    }

    @Test
    public void openBrowserTest() {
        driver.get("https://www.google.com");
        wait.until(ExpectedConditions.urlContains("https://"));

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.perplexity.ai/");
        wait.until(ExpectedConditions.urlContains("https://"));
        driver.close();

        driver.switchTo().window(mainWindow);
        driver.get("https://g1.globo.com/");
        wait.until(ExpectedConditions.urlContains("https://"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
