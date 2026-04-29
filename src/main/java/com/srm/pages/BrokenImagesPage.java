package com.srm.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BrokenImagesPage extends BasePage {

    private final By images = By.cssSelector(".example img");

    public void open() {
        openPath("/broken_images");
    }

    public int getBrokenImageCount() {
        List<WebElement> imageElements = driver.findElements(images);
        int brokenCount = 0;
        for (WebElement imageElement : imageElements) {
            Long naturalWidth = (Long) ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("return arguments[0].naturalWidth;", imageElement);
            if (naturalWidth == null || naturalWidth == 0L) {
                brokenCount++;
            }
        }
        return brokenCount;
    }
}
