package com.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    public static WebDriver driver;

    /**
     * Sets the driver that'll be used (it will be ChromeDriver)
     *
     * @param driver The chosen driver (ChromeDriver)
     */
    public void setDriver(WebDriver driver) {
        BasePage.driver = driver;
    }

    /**
     * Finds a WebElement and return it
     *
     * @param locator id, class, name, etc. (any identifier)
     * @return a WebElement that will be used later for editing, clicking, etc.
     */
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    /**
     * Sets a value for an input field. It clears the input field before entering the text.
     *
     * @param locator id, class, name, etc. (any identifier)
     * @param text text that will be placed inside the input field
     */
    protected void set(By locator, String text) {
        find(locator).clear();
        find(locator).sendKeys(text);
    }

    /**
     * Clicks a button
     *
     * @param locator id, class, name, etc. (any identifier) of the button
     */
    protected void click(By locator) {
        find(locator).click();
    }

    public static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch(InterruptedException exc) {
            exc.printStackTrace();
        }
    }
}
