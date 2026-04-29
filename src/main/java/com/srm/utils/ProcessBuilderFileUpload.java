package com.srm.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import com.srm.core.ConfigReader;

public final class ProcessBuilderFileUpload {

    private ProcessBuilderFileUpload() {
    }

    public static void selectFile(String absolutePath) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(buildUploadCommand(absolutePath));
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new IllegalStateException("File dialog helper exited with code " + exitCode);
            }
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to start file upload helper.", exception);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("File upload helper was interrupted.", exception);
        }
    }

    private static List<String> buildUploadCommand(String absolutePath) throws IOException {
        String helperPath = ConfigReader.getOptionalProperty("upload.helper.path");
        List<String> command = new ArrayList<>();

        if (!helperPath.isBlank()) {
            command.add(helperPath);
            command.add(absolutePath);
            return command;
        }

        Path scriptPath = extractScriptToTempLocation();
        command.add("powershell.exe");
        command.add("-ExecutionPolicy");
        command.add("Bypass");
        command.add("-File");
        command.add(scriptPath.toString());
        command.add(absolutePath);
        return command;
    }

    private static Path extractScriptToTempLocation() throws IOException {
        Path tempScript = Files.createTempFile("upload-file-", ".ps1");
        tempScript.toFile().deleteOnExit();

        try (InputStream inputStream = ProcessBuilderFileUpload.class.getClassLoader()
                .getResourceAsStream("scripts/upload-file.ps1")) {
            if (inputStream == null) {
                throw new IllegalStateException("upload-file.ps1 was not found on the classpath.");
            }
            Files.copy(inputStream, tempScript, StandardCopyOption.REPLACE_EXISTING);
        }
        return tempScript;
    }
}
