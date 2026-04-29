package com.srm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.srm.core.ConfigReader;
import com.srm.pages.DisappearingElementsPage;
import com.srm.pages.DynamicContentPage;
import com.srm.pages.DynamicPage;

public class DynamicLoadingTests extends BaseTest {

    @Test
    public void verifyDynamicLoadingDisplaysExpectedMessage() {
        DynamicPage dynamicPage = new DynamicPage();
        dynamicPage.open();
        dynamicPage.startLoading();

        Assert.assertEquals(dynamicPage.waitForLoadedText(), ConfigReader.getProperty("expected.dynamic.message"),
                "Loaded text did not match the expected message.");
    }

    @Test
    public void verifyDisappearingElementMenuLoadsAfterRefresh() {
        DisappearingElementsPage disappearingElementsPage = new DisappearingElementsPage();
        disappearingElementsPage.open();

        int firstLoadCount = disappearingElementsPage.getMenuItemCount();
        disappearingElementsPage.refreshPage();
        int refreshedCount = disappearingElementsPage.getMenuItemCount();

        Assert.assertTrue(firstLoadCount >= 4, "Initial menu should contain the stable disappearing element links.");
        Assert.assertTrue(refreshedCount >= 4, "Menu should still contain the stable links after refresh.");
    }

    @Test
    public void verifyDynamicContentSectionLoadsContentBlocks() {
        DynamicContentPage dynamicContentPage = new DynamicContentPage();
        dynamicContentPage.open();

        Assert.assertEquals(dynamicContentPage.getContentBlockCount(), 3,
                "Dynamic content page should render three content blocks.");
    }
}
