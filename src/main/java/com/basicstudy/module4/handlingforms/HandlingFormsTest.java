package com.basicstudy.module4.handlingforms;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HandlingFormsTest {
    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void formsTest() {
        driver.get("https://demoqa.com/automation-practice-form");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Handling input
        WebElement currentAddressTextArea = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("currentAddress"))
        );
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(
                "arguments[0].scrollIntoView(true);",
                currentAddressTextArea
        );
        currentAddressTextArea.sendKeys("Random Address");

        // Handling checkboxes
        WebElement sportsCheckbox = driver.findElement(By.id("hobbies-checkbox-1"));
        executor.executeScript("arguments[0].scrollIntoView(true)", sportsCheckbox);
        executor.executeScript("arguments[0].click();", sportsCheckbox);
        Assert.assertTrue(sportsCheckbox.isSelected(), "Sports checkbox is not selected!");

        // Handling radio buttons
        WebElement maleRadioButton = driver.findElement(By.id("gender-radio-1"));
        executor.executeScript("arguments[0].scrollIntoView(true)", maleRadioButton);
        executor.executeScript("arguments[0].click()", maleRadioButton);
        Assert.assertTrue(maleRadioButton.isSelected());

        // Handling select
        driver.get("https://elefen.uber.space/disionario/");
        WebElement languageDropDown = wait.until(ExpectedConditions.elementToBeClickable(By.id("language")));
        Select select = new Select(languageDropDown);
        select.selectByValue("pt");
        String selectedText = select.getFirstSelectedOption().getText();
        Assert.assertEquals(selectedText, "portuges");
        WebElement searchBox = driver.findElement(By.id("search_box"));
        searchBox.click();
        searchBox.sendKeys("porta");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
