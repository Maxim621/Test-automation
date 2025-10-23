package com.solvd.ui.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class SidebarMenuComponent extends AbstractUIObject {

    @FindBy(xpath = "//div[contains(@class,'element-list')]//li")
    private List<ExtendedWebElement> menuItems;

    @FindBy(xpath = "//div[contains(@class,'element-list')]//span[text()='Elements']")
    private ExtendedWebElement elementsMenuItem;

    @FindBy(xpath = "//span[text()='Text Box']")
    private ExtendedWebElement textBoxMenuItem;

    public SidebarMenuComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public int getMenuItemsCount() {
        return menuItems.size();
    }

    public void clickElementsMenuItem() {
        elementsMenuItem.click();
    }

    public boolean isElementsMenuVisible() {
        return elementsMenuItem.isVisible(5);
    }

    public ExtendedWebElement getElementsMenuItem() {
        return elementsMenuItem;
    }

    public void clickTextBoxMenuItem() {
        textBoxMenuItem.click();
    }

    public ExtendedWebElement getTextBoxMenuItem() {
        return textBoxMenuItem;
    }
}