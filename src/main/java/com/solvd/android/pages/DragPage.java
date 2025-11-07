package com.solvd.android.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class DragPage extends AbstractAndroidPage {

    @FindBy(xpath = "//android.widget.TextView[contains(@text, 'Drag')]")
    private ExtendedWebElement pageTitle;

    @FindBy(xpath = "//android.widget.TextView[@text='Drag']")
    private ExtendedWebElement menuTitle;

    public DragPage(WebDriver driver) {
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