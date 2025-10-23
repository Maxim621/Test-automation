package com.solvd.ui.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class FooterComponent extends AbstractUIObject {

    @FindBy(xpath = "//footer//p[contains(text(),'©')]")
    private ExtendedWebElement copyrightText;

    @FindBy(xpath = "//footer//a[contains(text(),'About')]")
    private ExtendedWebElement aboutLink;

    public FooterComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean isCopyrightDisplayed() {
        return copyrightText.isElementPresent(10); // Wait до 10 секунд
    }

    public String getCopyrightText() {
        return copyrightText.getText();
    }

    public void clickAbout() {
        aboutLink.click();
    }
}