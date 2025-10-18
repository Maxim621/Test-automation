package com.solvd.ui.tests;

import com.solvd.ui.pages.ElementsPage;
import com.solvd.ui.pages.HomePage;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UITests extends BaseTest {

    @BeforeClass
    public void setupDriver() {
        getDriver();
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testHomePageLoading() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");
        Assert.assertTrue(homePage.isBannerDisplayed(), "Banner is not displayed!");

        String pageTitle = homePage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("ToolsQA") || pageTitle.contains("DEMOQA"),
                "Page title should contain 'ToolsQA' or 'DEMOQA'");
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testNavigationToElements() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.scrollToElements();
        String elementsText = homePage.getElementsCardText();
        Assert.assertEquals(elementsText, "Elements", "Elements card text is incorrect!");
        homePage.clickElementsCard();
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testScrollingAndBanner() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.scrollToBanner();
        Assert.assertTrue(homePage.isSeleniumBannerDisplayed(), "Selenium banner should be visible after scrolling");
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testTextBoxForm() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.clickElementsCard();

        ElementsPage elementsPage = new ElementsPage(getDriver());
        elementsPage.clickTextBoxMenu();
        elementsPage.fillTextBoxForm("John Doe", "john@example.com");

        // Скролл і пауза перед кліком
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        elementsPage.clickSubmit();

        // Чекаємо на output з обмеженим часом
        String output = elementsPage.getOutputText(10); // 10 секунд максимум

        Assert.assertTrue(output.contains("John Doe"), "Output should contain full name! Actual: " + output);
        Assert.assertTrue(output.contains("john@example.com"), "Output should contain email! Actual: " + output);
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testRadioButtons() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.clickElementsCard();

        ElementsPage elementsPage = new ElementsPage(getDriver());
        elementsPage.clickRadioButtonMenu();

        // Тестуємо Yes radio button
        elementsPage.clickYesRadioButton();
        String result1 = elementsPage.getRadioButtonResult();
        Assert.assertEquals(result1, "Yes", "Radio button should show 'Yes'");

        // Тестуємо Impressive radio button
        elementsPage.clickImpressiveRadioButton();
        String result2 = elementsPage.getRadioButtonResult();
        Assert.assertEquals(result2, "Impressive", "Radio button should show 'Impressive'");
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testCheckBoxInteraction() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.clickElementsCard();

        ElementsPage elementsPage = new ElementsPage(getDriver());
        elementsPage.clickCheckBoxMenu();
        elementsPage.clickCheckBoxOption(0);

        Assert.assertTrue(elementsPage.isCheckBoxSelected(0) ||
                        elementsPage.isCheckBoxCheckedVisible(0),
                "Checkbox should be selected!");
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testFormsNavigation() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.clickFormsCard();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("forms"), "Should navigate to forms page");
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testAlertsNavigation() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.scrollToElements();
        homePage.clickAlertsCard();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("alertsWindows"), "Should navigate to alerts page");
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testWidgetsNavigation() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.scrollToElements();
        homePage.clickWidgetsCard();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("widgets"), "Should navigate to widgets page");
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testInteractionsNavigation() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.scrollToElements();
        homePage.clickInteractionsCard();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("interaction"), "Should navigate to interactions page");
    }
}