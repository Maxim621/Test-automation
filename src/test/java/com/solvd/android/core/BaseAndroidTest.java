package com.solvd.android.core;

import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class BaseAndroidTest implements IAbstractTest, IMobileUtils {
    private static final Logger logger = LogManager.getLogger(BaseAndroidTest.class);

    protected WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(30));
    }

    protected WebElement waitForElement(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
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

    protected boolean isElementPresent(By locator) {
        try {
            return getDriver().findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warn("Thread interrupted while waiting", e);
        }
    }
}