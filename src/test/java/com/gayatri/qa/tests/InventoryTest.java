package com.gayatri.qa.tests;

import com.gayatri.qa.base.BaseTest;
import com.gayatri.qa.pages.InventoryPage;
import com.gayatri.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InventoryTest extends BaseTest {

    InventoryPage inventoryPage;

    @BeforeMethod(alwaysRun = true)
    public void loginFirst() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        inventoryPage = new InventoryPage(driver);
    }

    @Test(groups = {"smoke", "regression"})
    public void pageTitleTest() {
        Assert.assertEquals(inventoryPage.getPageTitle(), "Products");
    }

    @Test(groups = {"regression"})
    public void addToCartTest() {
        inventoryPage.addBackpackToCart();
        Assert.assertEquals(inventoryPage.getCartCount(), "1");
    }
}