package com.srm.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;

public class DropdownPage extends BasePage {

    private final By dropdown = By.id("dropdown");
    private final By options = By.cssSelector("#dropdown option");

    public void open() {
        openPath("/dropdown");
    }

    public void selectOption(String optionText) {
        selectByVisibleText(dropdown, optionText);
    }

    public String getSelectedOption() {
        return getSelectedOption(dropdown);
    }

    public int getOptionCount() {
        return driver.findElements(options).size();
    }

    public List<String> getOptionTexts() {
        return driver.findElements(options)
                .stream()
                .map(option -> option.getText().trim())
                .collect(Collectors.toList());
    }
}
