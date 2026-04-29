package com.srm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DynamicPage extends BasePage {

    private final By startButton = By.cssSelector("#start button");
    private final By finishText = By.cssSelector("#finish h4");

    public void open() {
        openPath("/dynamic_loading/1");
    }

    public void startLoading() {
        click(startButton);
    }

    public String waitForLoadedText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(finishText)).getText().trim();
    }
}
