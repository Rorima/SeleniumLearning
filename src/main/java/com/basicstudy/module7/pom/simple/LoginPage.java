package com.basicstudy.module7.pom.simple;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor executor;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        this.executor = (JavascriptExecutor) driver;
    }

    // Locators
    By userNameField = By.id("userName");
    By passwordField = By.id("password");
    By loginButton = By.id("login");
    By invalidLoginCredentialsMessage = By.id("name");

    // Actions
    public void enterUsername(String username) {
        WebElement user = wait.until(
                ExpectedConditions.visibilityOfElementLocated(userNameField)
        );
        user.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement pass = wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordField)
        );
        pass.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(loginButton)
        );
        executor.executeScript("arguments[0].scrollIntoView(true);", btn);
        btn.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean loginWasNotSuccessful() {
        return wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(invalidLoginCredentialsMessage)
        ).isDisplayed();
    }
}
