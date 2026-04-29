package com.srm.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentManager {

    private static ExtentReports extentReports;

    private ExtentManager() {
    }

    public static ExtentReports getInstance() {
        if (extentReports == null) {
            Path reportPath = Paths.get(System.getProperty("user.dir"), "reports", "extent-report.html");
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath.toString());
            sparkReporter.config().setReportName("Internet Herokuapp Automation Report");
            sparkReporter.config().setDocumentTitle("Selenium Hackathon Report");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Framework", "Selenium + TestNG + POM");
        }
        return extentReports;
    }
}
