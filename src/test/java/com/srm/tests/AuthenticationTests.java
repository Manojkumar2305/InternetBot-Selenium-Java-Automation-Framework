package com.srm.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.srm.core.ConfigReader;
import com.srm.pages.BasicAuthPage;
import com.srm.pages.LoginPage;
import com.srm.pages.SecureAreaPage;

public class AuthenticationTests extends BaseTest {

    @DataProvider(name = "formLoginData")
    public Object[][] formLoginData() {
        return new Object[][] {
                {ConfigReader.getProperty("form.valid.username"), ConfigReader.getProperty("form.valid.password"), true},
                {ConfigReader.getProperty("form.invalid.username"), ConfigReader.getProperty("form.invalid.password"), false}
        };
    }

    @Test(dataProvider = "formLoginData")
    public void verifyFormAuthenticationScenarios(String username, String password, boolean shouldLoginSucceed) {
        LoginPage loginPage = new LoginPage();
        loginPage.open();

        Assert.assertTrue(loginPage.isLoginFormVisible(), "Login form should be visible before authentication.");

        SecureAreaPage secureAreaPage = loginPage.login(username, password);
        if (shouldLoginSucceed) {
            String successMessage = secureAreaPage.getSuccessMessage();
            Assert.assertTrue(successMessage.contains(ConfigReader.getProperty("expected.login.success.message")),
                    "Successful login message did not match.");
            Assert.assertTrue(secureAreaPage.isLogoutVisible(), "Logout button should be visible after successful login.");
        } else {
            Assert.assertTrue(loginPage.getFlashMessage().contains(ConfigReader.getProperty("expected.login.failure.message")),
                    "Invalid login error message did not match.");
        }
    }

    @Test
    public void verifyLogoutRedirectsBackToLoginPage() {
        LoginPage loginPage = new LoginPage();
        loginPage.open();

        SecureAreaPage secureAreaPage = loginPage.login(
                ConfigReader.getProperty("form.valid.username"),
                ConfigReader.getProperty("form.valid.password"));

        LoginPage redirectedPage = secureAreaPage.logout();
        Assert.assertTrue(redirectedPage.getFlashMessage().contains(ConfigReader.getProperty("expected.logout.message")),
                "Logout message did not match.");
        Assert.assertEquals(redirectedPage.getTitleText(), ConfigReader.getProperty("expected.login.page.title"),
                "Login page title after logout was not correct.");
    }

    @Test
    public void verifyBasicAuthenticationWorks() {
        BasicAuthPage basicAuthPage = new BasicAuthPage();
        basicAuthPage.open(
                ConfigReader.getProperty("basic.auth.username"),
                ConfigReader.getProperty("basic.auth.password"));

        Assert.assertTrue(basicAuthPage.getPageSourceText().contains(ConfigReader.getProperty("expected.basic.auth.text")),
                "Basic auth confirmation text was not present.");
    }
}
