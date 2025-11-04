package com.solvd.android.core;

import com.zebrunner.carina.core.AbstractTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseAndroidTest extends AbstractTest {
    private static final Logger logger = LogManager.getLogger(BaseAndroidTest.class);

    protected AndroidDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        logger.info("Setting up Android test...");

        String projectDir = System.getProperty("user.dir");
        String appPath = projectDir + "/src/main/resources/android/apps/app.apk";

        logger.info("APK path: {}", appPath);

        File appFile = new File(appPath);
        if (!appFile.exists()) {
            logger.error("APK file not found: {}", appPath);
            throw new RuntimeException("APK file not found: " + appPath);
        }
        logger.info("APK file exists: {} bytes", appFile.length());

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("emulator-5554")
                .setApp(appPath)
                .setAppPackage("com.wdiodemoapp")
                .setAppActivity("com.wdiodemoapp.MainActivity")
                .setAutomationName("UiAutomator2")
                .setAutoGrantPermissions(true)
                .setNoReset(false)
                .setNewCommandTimeout(Duration.ofSeconds(300));

        String appiumUrl = System.getenv().getOrDefault("APPIUM_URL", "http://localhost:4723");
        logger.info("Connecting to Appium server: {}", appiumUrl);

        try {
            driver = new AndroidDriver(new URL(appiumUrl), options);
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            logger.info("Appium session started successfully");
            logger.info("Appium is working!");

        } catch (Exception e) {
            logger.error("Failed to start Appium session: {}", e.getMessage());
            throw e;
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            logger.info("Closing Appium session...");
            driver.quit();
            logger.info("Appium session closed");
        }
    }

    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void clickElement(By locator) {
        waitForElement(locator).click();
    }

    protected void sendKeysToElement(By locator, String text) {
        waitForElement(locator).sendKeys(text);
    }

    protected String getElementText(By locator) {
        return waitForElement(locator).getText();
    }
}