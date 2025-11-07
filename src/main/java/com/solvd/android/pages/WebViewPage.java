package com.solvd.android.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class WebViewPage extends AbstractAndroidPage {

    @FindBy(xpath = "//android.widget.TextView[@text='Webview']")
    private ExtendedWebElement pageTitle;

    @FindBy(xpath = "//android.webkit.WebView")
    private ExtendedWebElement webView;

    public WebViewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return pageTitle.isElementPresent();
    }

    public boolean isWebViewDisplayed() {
        return webView.isElementPresent();
    }
}