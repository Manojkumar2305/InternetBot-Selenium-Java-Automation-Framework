package com.srm.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public final class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> WAIT = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static void initDriver() {
        String browser = ConfigReader.getBrowser().toLowerCase();
        boolean headless = ConfigReader.isHeadless();
        WebDriver webDriver;

        switch (browser) {
            case "firefox":
                setupFirefoxDriver();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("-headless");
                }
                webDriver = new FirefoxDriver(firefoxOptions);
                break;
            case "chrome":
                setupChromeDriver();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--window-size=1920,1080");
                webDriver = new ChromeDriver(chromeOptions);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser configured: " + browser);
        }

        if (!headless) {
            webDriver.manage().window().maximize();
        }
        DRIVER.set(webDriver);
        WAIT.set(new WebDriverWait(webDriver, Duration.ofSeconds(ConfigReader.getTimeout())));
    }

    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    public static WebDriverWait getWait() {
        return WAIT.get();
    }

    public static void quitDriver() {
        WebDriver webDriver = DRIVER.get();
        if (webDriver != null) {
            webDriver.quit();
            DRIVER.remove();
            WAIT.remove();
        }
    }

    private static void setupChromeDriver() {
        try {
            WebDriverManager.chromedriver().setup();
        } catch (Exception exception) {
            String cachedDriver = resolveLatestCachedDriver("chromedriver");
            if (cachedDriver == null) {
                throw exception;
            }
            System.setProperty("webdriver.chrome.driver", cachedDriver);
        }
    }

    private static void setupFirefoxDriver() {
        try {
            WebDriverManager.firefoxdriver().setup();
        } catch (Exception exception) {
            String cachedDriver = resolveLatestCachedDriver("geckodriver");
            if (cachedDriver == null) {
                throw exception;
            }
            System.setProperty("webdriver.gecko.driver", cachedDriver);
        }
    }

    private static String resolveLatestCachedDriver(String driverName) {
        Path cacheDirectory = Paths.get(System.getProperty("user.home"), ".cache", "selenium", driverName, "win64");
        if (!Files.exists(cacheDirectory)) {
            return null;
        }

        try (Stream<Path> versionDirectories = Files.list(cacheDirectory)) {
            Optional<Path> driverExecutable = versionDirectories
                    .filter(Files::isDirectory)
                    .sorted(Comparator.comparing(path -> path.getFileName().toString(), Comparator.reverseOrder()))
                    .map(path -> path.resolve(driverName + ".exe"))
                    .filter(Files::exists)
                    .findFirst();
            return driverExecutable.map(Path::toString).orElse(null);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to inspect cached WebDriver binaries.", exception);
        }
    }
}
