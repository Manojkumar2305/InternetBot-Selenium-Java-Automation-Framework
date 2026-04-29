package com.srm.pages;

import org.openqa.selenium.By;

public class SecureAreaPage extends BasePage {

    private final By successBanner = By.id("flash");
    private final By logoutButton = By.cssSelector("a.button.secondary.radius");

    public String getSuccessMessage() {
        return getText(successBanner);
    }

    public LoginPage logout() {
        click(logoutButton);
        return new LoginPage();
    }

    public boolean isLogoutVisible() {
        return isDisplayed(logoutButton);
    }
}
