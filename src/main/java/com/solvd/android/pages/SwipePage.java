package com.solvd.android.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SwipePage extends AbstractAndroidPage {

    @FindBy(xpath = "//android.widget.TextView[@text='Swipe horizontal']")
    private ExtendedWebElement pageTitle;

    @FindBy(xpath = "//android.widget.TextView[@text='Swipe']")
    private ExtendedWebElement menuTitle;

    public SwipePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return pageTitle.isElementPresent();
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }
}