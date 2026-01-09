package com.basicstudy.module3.webelementactions;

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

public class WebElementActionsTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void webElementTest() {
        driver.get("https://demoqa.com/");


        // Using click()
        WebElement elements = findByXpath("//h5[normalize-space(text())='Elements']");
        click(elements);

        // Clicking on the text box option
        WebElement textBox = findByXpath("//*[@id=\"item-0\"]");
        click(textBox);

        // Using sendKeys()
        WebElement inputField = findByCssSelector("#userName");
        inputField.sendKeys("Testing");

        // Using clear()
        inputField.clear();

        // Using getText()
        WebElement submitButton = findByCssSelector("#submit");
        String submitButtonText = submitButton.getText();
        System.out.println("Submit Button Text: " + submitButtonText);

        // Using getAttribute()
        WebElement emailField = findByCssSelector("#userEmail");
        String emailFieldText = emailField.getAttribute("placeholder");
        emailField.sendKeys(emailFieldText);

        // Using isDisplayed()
        boolean rightSideAdvertisement = findByCssSelector("#RightSide_Advertisement").isDisplayed();
        System.out.println("Is Right Side Advertisement being displayed: " + rightSideAdvertisement);

        // Using isEnabled()
        boolean submitButtonIsEnabled = submitButton.isEnabled();
        System.out.println("Is Submit Button Enabled: " + submitButtonIsEnabled);

        // Using isSelected()
        WebElement checkBoxButton = findByXpath("//*[@id=\"item-1\"]/span");
        click(checkBoxButton);
        WebElement checkBoxLabel = findByXpath("//*[@id=\"tree-node\"]/ol/li/span/label");
        click(checkBoxLabel);

        // I used the normal drive.findElement here because my own method waited until the element to be clickable,
        // and the checkbox would never be clickable because of its styling
        WebElement checkBox = driver.findElement(By.id("tree-node-home"));
        System.out.println("Is checkbox selected: " + checkBox.isSelected());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void click(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public WebElement findByXpath(String locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement el = driver.findElement(By.xpath(locator));
        wait.until(ExpectedConditions.elementToBeClickable(el));
        return el;
    }

    public WebElement findByCssSelector(String locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement el = driver.findElement(By.cssSelector(locator));
        wait.until(ExpectedConditions.elementToBeClickable(el));
        return el;
    }
}
