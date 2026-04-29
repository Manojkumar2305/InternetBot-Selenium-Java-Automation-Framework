package com.srm.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.srm.pages.CheckboxPage;
import com.srm.pages.DropdownPage;
import com.srm.pages.HoverPage;

public class CheckboxDropdownTests extends BaseTest {

    @Test
    public void verifyCheckboxOneCanBeCheckedAndUnchecked() {
        CheckboxPage checkboxPage = new CheckboxPage();
        checkboxPage.open();

        checkboxPage.setCheckboxOne(true);
        Assert.assertTrue(checkboxPage.isCheckboxOneSelected(), "Checkbox 1 should be checked.");

        checkboxPage.setCheckboxOne(false);
        Assert.assertFalse(checkboxPage.isCheckboxOneSelected(), "Checkbox 1 should be unchecked.");
    }

    @Test
    public void verifyCheckboxTwoCanBeToggledAndReverified() {
        CheckboxPage checkboxPage = new CheckboxPage();
        checkboxPage.open();

        boolean initialState = checkboxPage.isCheckboxTwoSelected();
        checkboxPage.setCheckboxTwo(!initialState);

        Assert.assertEquals(checkboxPage.isCheckboxTwoSelected(), !initialState,
                "Checkbox 2 should reflect the toggled state.");
    }

    @Test
    public void verifyDropdownSelectionAndOptionCount() {
        DropdownPage dropdownPage = new DropdownPage();
        dropdownPage.open();

        dropdownPage.selectOption("Option 1");
        Assert.assertEquals(dropdownPage.getSelectedOption(), "Option 1",
                "Dropdown should show Option 1 as selected.");

        dropdownPage.selectOption("Option 2");
        Assert.assertEquals(dropdownPage.getSelectedOption(), "Option 2",
                "Dropdown should show Option 2 as selected.");

        List<String> optionTexts = dropdownPage.getOptionTexts();
        Assert.assertEquals(dropdownPage.getOptionCount(), 3, "Dropdown should contain the placeholder and two values.");
        Assert.assertTrue(optionTexts.contains("Option 1") && optionTexts.contains("Option 2"),
                "Expected dropdown options were not present.");
    }

    @Test
    public void verifyHoverInteractionRevealsCaption() {
        HoverPage hoverPage = new HoverPage();
        hoverPage.open();
        hoverPage.hoverOnFirstProfile();

        Assert.assertTrue(hoverPage.isFirstCaptionVisible(), "Hover caption should be visible after hovering.");
    }
}
