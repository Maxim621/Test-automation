package com.solvd.android.pages;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class AbstractAndroidPage extends AbstractPage {

    public AbstractAndroidPage(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isPageOpened();
}