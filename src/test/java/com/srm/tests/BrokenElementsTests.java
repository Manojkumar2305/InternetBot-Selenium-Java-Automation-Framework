package com.srm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.srm.pages.BrokenImagesPage;

public class BrokenElementsTests extends BaseTest {

    @Test
    public void verifyBrokenImagesCanBeDetected() {
        BrokenImagesPage brokenImagesPage = new BrokenImagesPage();
        brokenImagesPage.open();

        Assert.assertTrue(brokenImagesPage.getBrokenImageCount() > 0,
                "At least one broken image should be detected on the page.");
    }
}
