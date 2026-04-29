package com.srm.tests;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.srm.core.ConfigReader;
import com.srm.pages.UploadPage;

public class FileUploadTests extends BaseTest {

    @Test
    public void verifyUploadPageTitleAndFormVisibility() {
        UploadPage uploadPage = new UploadPage();
        uploadPage.open();

        Assert.assertEquals(uploadPage.getPageTitle(), ConfigReader.getProperty("expected.upload.page.title"),
                "Upload page title did not match.");
        Assert.assertTrue(uploadPage.isPageReady(), "Upload page form should be visible before interaction.");
    }

    @Test
    public void verifySupportedFileUploadDisplaysUploadedFilename() {
        UploadPage uploadPage = new UploadPage();
        uploadPage.open();

        Path supportedFile = Paths.get(System.getProperty("user.dir"), ConfigReader.getProperty("upload.supported.file"));
        uploadPage.uploadFile(supportedFile.toAbsolutePath().toString());

        Assert.assertTrue(uploadPage.isUploadSuccessPageDisplayed(),
                "Upload should land on the File Uploaded success page.");
        Assert.assertEquals(uploadPage.getUploadSuccessTitle(), ConfigReader.getProperty("expected.upload.success.title"),
                "Upload success page title did not match.");
        Assert.assertEquals(uploadPage.getUploadedFileName(), supportedFile.getFileName().toString(),
                "Uploaded filename did not match the selected file.");
    }

    @Test
    public void verifyUnsupportedFileTypeIsHandledGracefully() {
        UploadPage uploadPage = new UploadPage();
        uploadPage.open();

        Path unsupportedFile = Paths.get(System.getProperty("user.dir"), ConfigReader.getProperty("upload.unsupported.file"));
        boolean supported = uploadPage.uploadIfSupported(unsupportedFile);

        Assert.assertFalse(supported, "Framework should block unsupported file types gracefully.");
        Assert.assertTrue(uploadPage.isPageReady(), "Upload page should remain stable after unsupported file validation.");
    }
}
