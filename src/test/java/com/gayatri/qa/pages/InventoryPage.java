package com.gayatri.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {

    private WebDriver driver;

    private By pageTitle = By.cssSelector(".title");
    private By addBackpackButton = By.id("add-to-cart-sauce-labs-backpack");
    private By cartBadge = By.cssSelector(".shopping_cart_badge");
    private By cartLink = By.cssSelector(".shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public void addBackpackToCart() {
        driver.findElement(addBackpackButton).click();
    }

    public String getCartCount() {
        return driver.findElement(cartBadge).getText();
    }
    public void openCart() {
        driver.findElement(cartLink).click();
    }

}