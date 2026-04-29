package com.srm.pages;

import java.nio.file.Path;
import java.util.Arrays;

import org.openqa.selenium.By;

import com.srm.core.ConfigReader;
import com.srm.utils.ProcessBuilderFileUpload;

public class UploadPage extends BasePage {

    private final By pageTitle = By.cssSelector("div.example h3");
    private final By uploadSuccessTitle = By.cssSelector("div.example h3");
    private final By fileInput = By.id("file-upload");
    private final By uploadButton = By.id("file-submit");
    private final By uploadedFiles = By.id("uploaded-files");
    private final By formContainer = By.id("drag-drop-upload");

    public void open() {
        openPath("/upload");
    }

    public boolean isPageReady() {
        return isDisplayed(pageTitle) && isDisplayed(fileInput) && isDisplayed(uploadButton) && isDisplayed(formContainer);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public void uploadFile(String absolutePath) {
        click(fileInput);
        ProcessBuilderFileUpload.selectFile(absolutePath);
        click(uploadButton);
    }

    public boolean uploadIfSupported(Path filePath) {
        String allowedTypes = ConfigReader.getProperty("allowed.file.types");
        String fileName = filePath.getFileName().toString().toLowerCase();
        boolean supported = Arrays.stream(allowedTypes.split(","))
                .map(String::trim)
                .anyMatch(fileName::endsWith);
        if (supported) {
            uploadFile(filePath.toAbsolutePath().toString());
        }
        return supported;
    }

    public String getUploadedFileName() {
        return getText(uploadedFiles);
    }

    public String getUploadSuccessTitle() {
        return getText(uploadSuccessTitle);
    }

    public boolean isUploadSuccessPageDisplayed() {
        return isDisplayed(uploadSuccessTitle) && isDisplayed(uploadedFiles);
    }
}
