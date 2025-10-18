package com.solvd.ui.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.JavascriptExecutor;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//h5[text()='Elements']")
    private ExtendedWebElement elementsCard;

    @FindBy(xpath = "//h5[text()='Forms']")
    private ExtendedWebElement formsCard;

    @FindBy(xpath = "//h5[text()='Alerts, Frame & Windows']")
    private ExtendedWebElement alertsCard;

    @FindBy(xpath = "//h5[text()='Widgets']")
    private ExtendedWebElement widgetsCard;

    @FindBy(xpath = "//h5[text()='Interactions']")
    private ExtendedWebElement interactionsCard;

    @FindBy(className = "banner-image")
    private ExtendedWebElement bannerImage;

    @FindBy(xpath = "//img[@alt='Selenium Online Training']")
    private ExtendedWebElement seleniumBanner;

    public HomePage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL("https://demoqa.com");
    }

    public void clickElementsCard() {
        elementsCard.click();
    }

    public void clickFormsCard() {
        formsCard.click();
    }

    public void clickAlertsCard() {
        alertsCard.click();
    }

    public void clickWidgetsCard() {
        widgetsCard.click();
    }

    public void clickInteractionsCard() {
        interactionsCard.click();
    }

    public void scrollToCard(ExtendedWebElement card) {
        ((JavascriptExecutor) getDriver()).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                card.getElement()
        );
        pause(1); // коротка пауза для стабільності
    }

    private void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void scrollToElements() {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", elementsCard.getElement());
    }

    public void scrollToBanner() {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", bannerImage.getElement());
    }

    public boolean isBannerDisplayed() {
        return bannerImage.isElementPresent();
    }

    public boolean isSeleniumBannerDisplayed() {
        return seleniumBanner.isElementPresent();
    }

    public String getElementsCardText() {
        return elementsCard.getText();
    }

    public String getPageTitle() {
        return getDriver().getTitle();
    }

    public ExtendedWebElement getElementsCard() {
        return elementsCard;
    }

    public ExtendedWebElement getFormsCard() {
        return formsCard;
    }

    public ExtendedWebElement getAlertsCard() {
        return alertsCard;
    }

    public ExtendedWebElement getWidgetsCard() {
        return widgetsCard;
    }

    public ExtendedWebElement getInteractionsCard() {
        return interactionsCard;
    }
}