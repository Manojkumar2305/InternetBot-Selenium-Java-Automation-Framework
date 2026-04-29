package com.srm.pages;

import org.openqa.selenium.By;

public class CheckboxPage extends BasePage {

    private final By checkboxOne = By.xpath("(//form[@id='checkboxes']/input)[1]");
    private final By checkboxTwo = By.xpath("(//form[@id='checkboxes']/input)[2]");

    public void open() {
        openPath("/checkboxes");
    }

    public void setCheckboxOne(boolean checked) {
        setCheckbox(checkboxOne, checked);
    }

    public void setCheckboxTwo(boolean checked) {
        setCheckbox(checkboxTwo, checked);
    }

    public boolean isCheckboxOneSelected() {
        return waitForPresence(checkboxOne).isSelected();
    }

    public boolean isCheckboxTwoSelected() {
        return waitForPresence(checkboxTwo).isSelected();
    }
}
