package com.gayatri.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    private WebDriver driver;

    private By cartItems = By.cssSelector(".cart_item");
    private By itemNames = By.cssSelector(".inventory_item_name");
    private By removeBackpack = By.id("remove-sauce-labs-backpack");
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriverWait waiter() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public int getItemCount() {
        return driver.findElements(cartItems).size();
    }

    public String getFirstItemName() {
        return waiter().until(ExpectedConditions.visibilityOfElementLocated(itemNames)).getText();
    }

    public void removeBackpack() {
        waiter().until(ExpectedConditions.elementToBeClickable(removeBackpack)).click();
        waiter().until(ExpectedConditions.invisibilityOfElementLocated(cartItems));
    }

    public void clickCheckout() {
        waiter().until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
        waiter().until(ExpectedConditions.urlContains("checkout-step-one"));
    }
}