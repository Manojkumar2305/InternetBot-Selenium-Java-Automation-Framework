package com.srm.pages;

import org.openqa.selenium.By;

public class AlertPage extends BasePage {

    private final By jsAlertButton = By.xpath("//button[text()='Click for JS Alert']");
    private final By jsConfirmButton = By.xpath("//button[text()='Click for JS Confirm']");
    private final By jsPromptButton = By.xpath("//button[text()='Click for JS Prompt']");
    private final By resultMessage = By.id("result");

    public void open() {
        openPath("/javascript_alerts");
    }

    public void triggerAlert() {
        click(jsAlertButton);
    }

    public void triggerConfirm() {
        click(jsConfirmButton);
    }

    public void triggerPrompt() {
        click(jsPromptButton);
    }

    public String acceptActiveAlert() {
        return acceptAlert();
    }

    public String dismissActiveAlert() {
        return dismissAlert();
    }

    public String typeIntoPromptAndAccept(String value) {
        return sendTextAndAcceptAlert(value);
    }

    public String getResultText() {
        return getText(resultMessage);
    }
}
