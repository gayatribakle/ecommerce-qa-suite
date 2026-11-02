package com.gayatri.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CartPage {

    private WebDriver driver;

    private By cartItems = By.cssSelector(".cart_item");
    private By itemNames = By.cssSelector(".inventory_item_name");
    private By removeBackpack = By.id("remove-sauce-labs-backpack");
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getItemCount() {
        List<?> items = driver.findElements(cartItems);
        return items.size();
    }

    public String getFirstItemName() {
        return driver.findElement(itemNames).getText();
    }

    public void removeBackpack() {
        driver.findElement(removeBackpack).click();
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}