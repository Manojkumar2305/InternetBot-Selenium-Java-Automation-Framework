package com.srm.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public final class ScreenshotUtils {

    private ScreenshotUtils() {
    }

    public static String capture(WebDriver driver, String testName) {
        try {
            Path screenshotDirectory = Paths.get(System.getProperty("user.dir"), "screenshots");
            Files.createDirectories(screenshotDirectory);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            Path targetPath = screenshotDirectory.resolve(testName + "_" + timestamp + ".png");
            Files.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).toPath(),
                    targetPath,
                    StandardCopyOption.REPLACE_EXISTING);
            return targetPath.toString();
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to capture screenshot.", exception);
        }
    }
}
