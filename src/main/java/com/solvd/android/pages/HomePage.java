package com.solvd.android.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractAndroidPage {

    @FindBy(xpath = "//android.widget.TextView[@text='WEBDRIVER']")
    private ExtendedWebElement title;

    @FindBy(xpath = "//android.widget.TextView[@text='Login']")
    private ExtendedWebElement loginMenu;

    @FindBy(xpath = "//android.widget.TextView[@text='Forms']")
    private ExtendedWebElement formsMenu;

    @FindBy(xpath = "//android.widget.TextView[@text='Swipe']")
    private ExtendedWebElement swipeMenu;

    @FindBy(xpath = "//android.widget.TextView[@text='Drag']")
    private ExtendedWebElement dragMenu;

    @FindBy(xpath = "//android.widget.TextView[@text='Webview']")
    private ExtendedWebElement webviewMenu;

    @FindBy(xpath = "//android.widget.TextView[@text='Sign up']")
    private ExtendedWebElement signUpMenu;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return title.isElementPresent();
    }

    public LoginPage clickLoginMenu() {
        loginMenu.click();
        return new LoginPage(driver);
    }

    public FormsPage clickFormsMenu() {
        formsMenu.click();
        return new FormsPage(driver);
    }

    public SwipePage clickSwipeMenu() {
        swipeMenu.click();
        return new SwipePage(driver);
    }

    public DragPage clickDragMenu() {
        dragMenu.click();
        return new DragPage(driver);
    }

    public WebViewPage clickWebviewMenu() {
        webviewMenu.click();
        return new WebViewPage(driver);
    }

    public SignUpPage clickSignUpMenu() {
        signUpMenu.click();
        return new SignUpPage(driver);
    }

    public String getTitleText() {
        return title.getText();
    }
}