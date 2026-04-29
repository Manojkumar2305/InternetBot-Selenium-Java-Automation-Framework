package com.srm.pages;

import org.openqa.selenium.By;

public class HoverPage extends BasePage {

    private final By firstAvatar = By.cssSelector(".figure:nth-of-type(1)");
    private final By firstCaption = By.cssSelector(".figure:nth-of-type(1) .figcaption");

    public void open() {
        openPath("/hovers");
    }

    public void hoverOnFirstProfile() {
        hover(firstAvatar);
    }

    public boolean isFirstCaptionVisible() {
        return isDisplayed(firstCaption);
    }
}
