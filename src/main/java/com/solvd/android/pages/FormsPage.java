package com.solvd.android.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class FormsPage extends AbstractAndroidPage {

    @FindBy(xpath = "//android.widget.EditText[@content-desc='input-text']")
    private ExtendedWebElement textInput;

    @FindBy(xpath = "//android.widget.Switch")
    private ExtendedWebElement switchElement;

    @FindBy(xpath = "//android.widget.CheckBox")
    private ExtendedWebElement checkbox;

    @FindBy(xpath = "//android.widget.TextView[@text='Forms']")
    private ExtendedWebElement pageTitle;

    public FormsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return pageTitle.isElementPresent();
    }

    public void typeText(String text) {
        textInput.type(text);
    }

    public void clickSwitch() {
        switchElement.click();
    }

    public void clickCheckbox() {
        checkbox.click();
    }

    public String getInputText() {
        return textInput.getText();
    }

    public boolean isSwitchActive() {
        return "ON".equals(switchElement.getText());
    }

    public boolean isCheckboxChecked() {
        return checkbox.isChecked();
    }
}