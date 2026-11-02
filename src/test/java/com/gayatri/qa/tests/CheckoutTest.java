package com.gayatri.qa.tests;

import com.gayatri.qa.base.BaseTest;
import com.gayatri.qa.pages.CartPage;
import com.gayatri.qa.pages.CheckoutPage;
import com.gayatri.qa.pages.InventoryPage;
import com.gayatri.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    InventoryPage inventoryPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeMethod(alwaysRun = true)
    public void loginAndAddItem() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        inventoryPage.addBackpackToCart();
        inventoryPage.openCart();
    }

    @Test(groups = {"regression"})
    public void cartShowsAddedItemTest() {
        Assert.assertEquals(cartPage.getItemCount(), 1);
        Assert.assertEquals(cartPage.getFirstItemName(), "Sauce Labs Backpack");
    }

    @Test(groups = {"regression"})
    public void removeItemFromCartTest() {
        cartPage.removeBackpack();
        Assert.assertEquals(cartPage.getItemCount(), 0);
    }

    @Test(groups = {"regression"})
    public void checkoutWithoutDetailsShowsErrorTest() {
        cartPage.clickCheckout();
        checkoutPage.clickContinueOnly();
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("First Name is required"));
    }

    @Test(groups = {"smoke", "regression"})
    public void completePurchaseTest() {
        cartPage.clickCheckout();
        checkoutPage.enterDetails("Gayatri", "Test", "422001");
        checkoutPage.clickFinish();
        Assert.assertEquals(checkoutPage.getConfirmationMessage(), "Thank you for your order!");
    }
}