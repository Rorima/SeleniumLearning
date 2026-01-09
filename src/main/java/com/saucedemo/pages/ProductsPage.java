package com.saucedemo.pages;

import com.base.BasePage;
import org.openqa.selenium.By;

public class ProductsPage extends BasePage {
    private By productsHeader = By.xpath("//span[text()='Products']");

    /**
     * Shows whether the products header is displayed or not
     *
     * @return boolean value
     */
    public boolean isProductsHeaderDisplayed() {
        return find(productsHeader).isDisplayed();
    }
}
