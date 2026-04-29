package com.srm.pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.srm.core.ConfigReader;
import com.srm.core.DriverFactory;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = DriverFactory.getWait();
    }

    protected void openPath(String path) {
        driver.get(ConfigReader.getBaseUrl() + path);
    }

    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitForPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void click(By locator) {
        waitForClickable(locator).click();
    }

    protected void type(By locator, String value) {
        WebElement element = waitForElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    protected String getText(By locator) {
        return waitForElement(locator).getText().trim();
    }

    protected boolean isDisplayed(By locator) {
        return waitForPresence(locator).isDisplayed();
    }

    protected void setCheckbox(By locator, boolean shouldBeChecked) {
        WebElement checkbox = waitForClickable(locator);
        if (checkbox.isSelected() != shouldBeChecked) {
            checkbox.click();
        }
    }

    protected void selectByVisibleText(By locator, String optionText) {
        Select select = new Select(waitForElement(locator));
        select.selectByVisibleText(optionText);
    }

    protected String getSelectedOption(By locator) {
        Select select = new Select(waitForElement(locator));
        return select.getFirstSelectedOption().getText().trim();
    }

    protected Alert waitForAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    protected String acceptAlert() {
        Alert alert = waitForAlert();
        String text = alert.getText();
        alert.accept();
        return text;
    }

    protected String dismissAlert() {
        Alert alert = waitForAlert();
        String text = alert.getText();
        alert.dismiss();
        return text;
    }

    protected String sendTextAndAcceptAlert(String value) {
        Alert alert = waitForAlert();
        String text = alert.getText();
        alert.sendKeys(value);
        alert.accept();
        return text;
    }

    protected void hover(By locator) {
        new Actions(driver).moveToElement(waitForElement(locator)).perform();
    }

    protected WebElement waitWithFluent(By locator) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(ConfigReader.getTimeout()))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class)
                .until(webDriver -> webDriver.findElement(locator));
    }
}
