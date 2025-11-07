package com.solvd.android.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends AbstractAndroidPage {

    @FindBy(xpath = "//android.widget.EditText[@content-desc='input-email']")
    private ExtendedWebElement emailField;

    @FindBy(xpath = "//android.widget.EditText[@content-desc='input-password']")
    private ExtendedWebElement passwordField;

    @FindBy(xpath = "//android.widget.EditText[@content-desc='input-repeat-password']")
    private ExtendedWebElement repeatPasswordField;

    @FindBy(xpath = "//android.widget.TextView[@text='SIGN UP']")
    private ExtendedWebElement signUpButton;

    @FindBy(xpath = "//android.widget.TextView[@text='Sign up']")
    private ExtendedWebElement pageTitle;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return pageTitle.isElementPresent();
    }

    public void typeEmail(String email) {
        emailField.type(email);
    }

    public void typePassword(String password) {
        passwordField.type(password);
    }

    public void typeRepeatPassword(String password) {
        repeatPasswordField.type(password);
    }

    public void clickSignUpButton() {
        signUpButton.click();
    }

    public void signUp(String email, String password) {
        typeEmail(email);
        typePassword(password);
        typeRepeatPassword(password);
        clickSignUpButton();
    }
}