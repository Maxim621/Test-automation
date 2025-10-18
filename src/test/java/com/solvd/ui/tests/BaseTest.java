package com.solvd.ui.tests;

import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.utils.R;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest implements IAbstractTest {

    @BeforeClass
    public void setupDriver() {
        getDriver().manage().window().maximize(); // Максимізуємо вікно
        getDriver();
    }

    @BeforeSuite
    public void setup() {
        R.CONFIG.put("selenium_url", "http://localhost:4444", true);
        R.CONFIG.put("url", "https://demoqa.com", true);
        R.CONFIG.put("browser", "chrome", true);
        R.CONFIG.put("capabilities.browserName", "chrome", true);
        R.CONFIG.put("capabilities.platformName", "Windows", true);
    }
}