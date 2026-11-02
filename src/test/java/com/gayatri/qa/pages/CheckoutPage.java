package com.gayatri.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private WebDriver driver;

    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By errorMessage = By.cssSelector("[data-test='error']");
    private By completeHeader = By.cssSelector(".complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriverWait waiter() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterDetails(String first, String last, String zip) {
        waiter().until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(first);
        driver.findElement(lastName).sendKeys(last);
        driver.findElement(postalCode).sendKeys(zip);
        waiter().until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        waiter().until(ExpectedConditions.urlContains("checkout-step-two"));
    }

    public void clickContinueOnly() {
        waiter().until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public void clickFinish() {
        WebElement finish = waiter().until(ExpectedConditions.elementToBeClickable(finishButton));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", finish);
        try {
            finish.click();
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.urlContains("checkout-complete"));
        } catch (TimeoutException e) {
            // the normal click did not register, so retry with a JavaScript click
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();", driver.findElement(finishButton));
            waiter().until(ExpectedConditions.urlContains("checkout-complete"));
        }
    }

    public String getErrorMessage() {
        return waiter().until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public String getConfirmationMessage() {
        return waiter().until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).getText();
    }
}