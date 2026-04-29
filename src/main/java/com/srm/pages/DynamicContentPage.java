package com.srm.pages;

import org.openqa.selenium.By;

public class DynamicContentPage extends BasePage {

    private final By contentBlocks = By.cssSelector("#content .example > .row");

    public void open() {
        openPath("/dynamic_content");
    }

    public int getContentBlockCount() {
        return driver.findElements(contentBlocks).size();
    }
}
