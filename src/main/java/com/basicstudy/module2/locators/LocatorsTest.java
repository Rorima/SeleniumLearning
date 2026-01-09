package com.basicstudy.module2.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LocatorsTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLocators() {
        driver.get("https://elefen.uber.space/disionario/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement searchBox = driver.findElement(By.id("search_box"));

        // The input field will be enabled when the language selector becomes enabled
        wait.until(ExpectedConditions.elementToBeClickable(By.id("language")));

        searchBox.clear();
        searchBox.sendKeys(".saison");

        // Must wait until output is visible so that it can find the definition class
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("output")));

        WebElement definitionElement = driver.findElement(By.className("def"));
        String definition = definitionElement.getText();

        String resultText = driver.findElement(By.cssSelector(".res")).getText();
        String h1Text = driver.findElement(By.tagName("h1")).getText();
        String linkText = driver.findElement(By.linkText("(Lia a esta xerca.)")).getText();
        String partialLinkText = driver.findElement(By.partialLinkText("Lia a esta")).getText();
        String xpath = driver.findElement(By.xpath("//*[@id=\"output\"]/p[3]/span[2]")).getText();

        System.out.println("Selecting elements by:");
        System.out.println("id: " + searchBox.getText());
        System.out.println("name: " + "none in this page");
        System.out.println("cssSelector: " + resultText);
        System.out.println("className: " + definition);
        System.out.println("tagName: " + h1Text);
        System.out.println("linkText: " + linkText);
        System.out.println("partialLinkText: " + partialLinkText);
        System.out.println("xpath: " + xpath);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
