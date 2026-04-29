package com.srm.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By flashMessage = By.id("flash");
    private final By pageTitle = By.cssSelector("div.example h2");

    public void open() {
        openPath("/login");
    }

    public SecureAreaPage login(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        click(loginButton);
        return new SecureAreaPage();
    }

    public boolean isLoginFormVisible() {
        return isDisplayed(usernameInput) && isDisplayed(passwordInput) && isDisplayed(loginButton);
    }

    public String getFlashMessage() {
        return getText(flashMessage);
    }

    public String getTitleText() {
        return getText(pageTitle);
    }
}
