package com.solvd.ui.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HeaderComponent extends AbstractUIObject {

    @FindBy(xpath = "//header//img[@alt='Selenium Online Training']")
    private ExtendedWebElement logo;

    @FindBy(xpath = "//header//a[contains(text(),'Home')]")
    private ExtendedWebElement homeLink;

    public HeaderComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean isLogoDisplayed() {
        return logo.isElementPresent(5);
    }

    public void clickHome() {
        homeLink.click();
    }

    public String getLogoAltText() {
        return logo.getAttribute("alt");
    }

    public ExtendedWebElement getLogo() {
        return logo;
    }
}