package com.saucedemo.pages;

import com.base.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("#login_button_container h3");

    /**
     * Enters username in the username input field
     *
     * @param username valid username
     */
    public void setUsername(String username) {
        set(usernameField, username);
    }

    /**
     * Enters password in the password input field
     *
     * @param password valid password
     */
    public void setPassword(String password) {
        set(passwordField, password);
    }

    /**
     * Clicks the login button
     *
     * @return a ProductsPage instance, which shows whether the Products Page is displayed or not
     */
    public ProductsPage clickLoginButton() {
        click(loginButton);
        return new ProductsPage();
    }

    /**
     * Logs into the page
     *
     * @param username username
     * @param password password
     * @return a ProductsPage instance, which shows whether the Products Page is displayed or not
     */
    public ProductsPage logIntoApplication(String username, String password) {
        setUsername(username);
        setPassword(password);
        return clickLoginButton();
    }

    /**
     * Shows error message
     *
     * @return error message
     */
    public String getErrorMessage() {
        return find(errorMessage).getText();
    }
}
