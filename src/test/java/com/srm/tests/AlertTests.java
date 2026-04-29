package com.srm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.srm.core.ConfigReader;
import com.srm.pages.AlertPage;

public class AlertTests extends BaseTest {

    @Test
    public void verifyJsAlertAcceptsAndShowsSuccessMessage() {
        AlertPage alertPage = new AlertPage();
        alertPage.open();

        alertPage.triggerAlert();
        alertPage.acceptActiveAlert();

        Assert.assertEquals(alertPage.getResultText(), ConfigReader.getProperty("expected.alert.result"),
                "JS Alert result was not correct.");
    }

    @Test
    public void verifyJsConfirmDismissShowsDismissedMessage() {
        AlertPage alertPage = new AlertPage();
        alertPage.open();

        alertPage.triggerConfirm();
        alertPage.dismissActiveAlert();

        Assert.assertEquals(alertPage.getResultText(), ConfigReader.getProperty("expected.confirm.dismiss.result"),
                "JS Confirm dismiss result was not correct.");
    }

    @Test
    public void verifyJsPromptAcceptsTextAndDisplaysIt() {
        AlertPage alertPage = new AlertPage();
        alertPage.open();

        alertPage.triggerPrompt();
        alertPage.typeIntoPromptAndAccept(ConfigReader.getProperty("expected.prompt.text"));

        Assert.assertEquals(alertPage.getResultText(),
                ConfigReader.getProperty("expected.prompt.result.prefix") + " "
                        + ConfigReader.getProperty("expected.prompt.text"),
                "JS Prompt result was not correct.");
    }

    @Test
    public void verifyResultTextUpdatesAcrossDifferentAlertTypes() {
        AlertPage alertPage = new AlertPage();
        alertPage.open();

        alertPage.triggerAlert();
        alertPage.acceptActiveAlert();
        String alertResult = alertPage.getResultText();

        alertPage.triggerConfirm();
        alertPage.dismissActiveAlert();
        String confirmResult = alertPage.getResultText();

        Assert.assertNotEquals(confirmResult, alertResult, "Result text should update when a different alert is handled.");
        Assert.assertEquals(confirmResult, ConfigReader.getProperty("expected.confirm.dismiss.result"),
                "Result text did not reflect the most recent alert interaction.");
    }
}
