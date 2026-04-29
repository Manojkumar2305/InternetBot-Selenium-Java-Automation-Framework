package com.srm.pages;
public class BasicAuthPage extends BasePage {

    public void open(String username, String password) {
        String baseUrl = com.srm.core.ConfigReader.getBaseUrl().replace("https://", "").replace("http://", "");
        driver.get("https://" + username + ":" + password + "@" + baseUrl + "/basic_auth");
    }

    public String getPageSourceText() {
        return driver.getPageSource();
    }
}
