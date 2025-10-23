package com.solvd.ui.tests;

import com.solvd.ui.pages.*;
import com.solvd.ui.components.*;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UITests extends BaseTest {

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
    public void testElementsCardText() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.scrollToElements();

        String elementsText = homePage.getElementsCardText();
        Assert.assertEquals(elementsText, "Elements", "Elements card text is incorrect!");
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

        // Scroll and pause before click
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        elementsPage.clickSubmit();

        // Waiting for output with limited time
        String output = elementsPage.getOutputText(10); // 10 seconds maximum

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

        // Testing the Yes radio button
        elementsPage.clickYesRadioButton();
        String result1 = elementsPage.getRadioButtonResult();
        Assert.assertEquals(result1, "Yes", "Radio button should show 'Yes'");

        // Testing Impressive radio button
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

    @Test
    @MethodOwner(owner = "Maksym")
    public void testHeaderComponentSimple() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();

        Assert.assertTrue(homePage.isBannerDisplayed(), "Banner should be displayed");

        String pageTitle = homePage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("ToolsQA") || pageTitle.contains("DEMOQA"),
                "Page title should contain 'ToolsQA' or 'DEMOQA'");
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testFooterComponentSimple() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();

        ((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        pause(1);

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("demoqa.com"), "Should be on demoqa site");

        Assert.assertTrue(homePage.isPageOpened(), "Home page should be opened");
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testSidebarMenuSimple() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.clickElementsCard();

        pause(2);

        ElementsPage elementsPage = new ElementsPage(getDriver());

        elementsPage.clickTextBoxMenu();

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("text-box") || currentUrl.contains("elements"),
                "Should be on Text Box or Elements page");

        try {
            elementsPage.fillTextBoxForm("Test", "test@example.com");
            Assert.assertTrue(true, "Text box form should be accessible");
        } catch (Exception e) {
            Assert.fail("Text box form should be accessible: " + e.getMessage());
        }
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testDynamicElementsWithWait() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.clickElementsCard();

        ElementsPage elementsPage = new ElementsPage(getDriver());
        elementsPage.clickDynamicPropertiesMenu();

        // Wait for button to become present
        ExtendedWebElement enableAfterButton = elementsPage.getEnableAfterButton();

        // Carina wait for element to be present
        boolean isButtonPresent = enableAfterButton.isElementPresent(10);
        Assert.assertTrue(isButtonPresent, "Button should be present");

        // Additional wait for button to become enabled (if needed)
        try {
            Thread.sleep(6000); // Wait 6 seconds for button to enable
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assert.assertTrue(enableAfterButton.isClickable(), "Button should become clickable after wait");
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testFileUploadWithWait() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.clickElementsCard();

        ElementsPage elementsPage = new ElementsPage(getDriver());
        elementsPage.clickUploadDownloadMenu();

        // Create temporary file
        String filePath = System.getProperty("user.dir") + "/src/test/resources/test_upload.txt";

        try {
            java.nio.file.Files.write(java.nio.file.Paths.get(filePath),
                    "Test content for upload".getBytes());
        } catch (java.io.IOException e) {
            throw new RuntimeException("Failed to create test file", e);
        }

        // Wait for upload input to be present
        elementsPage.getUploadInput().isElementPresent(10);

        // Upload file
        elementsPage.uploadFile(filePath);

        // Wait for upload confirmation
        ExtendedWebElement uploadMessage = elementsPage.getUploadMessage();
        boolean isMessagePresent = uploadMessage.isElementPresent(10);
        Assert.assertTrue(isMessagePresent, "Upload message should be present");

        String messageText = uploadMessage.getText();
        Assert.assertTrue(messageText.contains("test_upload.txt"),
                "Upload message should contain file name. Actual: " + messageText);

        // Cleanup
        new java.io.File(filePath).delete();
    }
}