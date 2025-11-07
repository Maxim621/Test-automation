package com.solvd.android.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractAndroidPage {

    @FindBy(xpath = "//android.widget.EditText[@content-desc='input-email']")
    private ExtendedWebElement emailField;

    @FindBy(xpath = "//android.widget.EditText[@content-desc='input-password']")
    private ExtendedWebElement passwordField;

    @FindBy(xpath = "//android.widget.TextView[@text='LOGIN']")
    private ExtendedWebElement loginButton;

    @FindBy(xpath = "//android.widget.TextView[contains(@text, 'Success')]")
    private ExtendedWebElement successMessage;

    @FindBy(xpath = "//android.widget.TextView[@text='Login']")
    private ExtendedWebElement pageTitle;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return emailField.isElementPresent() && passwordField.isElementPresent();
    }

    public void typeEmail(String email) {
        emailField.type(email);
    }

    public void typePassword(String password) {
        passwordField.type(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public boolean isSuccessMessagePresent() {
        return successMessage.isElementPresent();
    }

    public String getSuccessMessage() {
        return successMessage.getText();
    }

    public void login(String email, String password) {
        typeEmail(email);
        typePassword(password);
        clickLoginButton();
    }
}