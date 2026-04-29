package com.srm.pages;

import java.util.List;

import org.openqa.selenium.By;

public class DisappearingElementsPage extends BasePage {

    private final By menuItems = By.cssSelector("ul li a");

    public void open() {
        openPath("/disappearing_elements");
    }

    public int getMenuItemCount() {
        List<?> items = driver.findElements(menuItems);
        return items.size();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }
}
