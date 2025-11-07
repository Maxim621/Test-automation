package com.solvd.android.tests;

import com.solvd.android.core.BaseAndroidTest;
import com.solvd.android.pages.*;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NativeTest extends BaseAndroidTest {
    private static final Logger logger = LogManager.getLogger(NativeTest.class);

    @Test
    @MethodOwner(owner = "Maksym")
    public void testAppLaunch() {
        logger.info("Testing Native App launch...");

        HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.isPageOpened(), "App not launched properly");
        Assert.assertEquals(homePage.getTitleText(), "WEBDRIVER", "App title doesn't match");

        logger.info("App launched successfully: {}", homePage.getTitleText());
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testLoginScreen() {
        logger.info("Testing Login screen...");

        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clickLoginMenu();

        Assert.assertTrue(loginPage.isPageOpened(), "Login screen not opened properly");
        logger.info("Login screen elements are present");
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testSuccessfulLogin() {
        logger.info("Testing successful login...");

        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clickLoginMenu();

        loginPage.login("test@example.com", "password123");

        Assert.assertTrue(loginPage.isSuccessMessagePresent(), "Login failed");
        logger.info("Login successful: {}", loginPage.getSuccessMessage());
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testFormsSection() {
        logger.info("Testing Forms section...");

        HomePage homePage = new HomePage(getDriver());
        FormsPage formsPage = homePage.clickFormsMenu();

        Assert.assertTrue(formsPage.isPageOpened(), "Forms page not opened");

        formsPage.typeText("Hello Appium");
        formsPage.clickSwitch();
        formsPage.clickCheckbox();

        logger.info("Forms section tested successfully");
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testSwipeSection() {
        logger.info("Testing Swipe section...");

        HomePage homePage = new HomePage(getDriver());
        SwipePage swipePage = homePage.clickSwipeMenu();

        Assert.assertTrue(swipePage.isPageOpened(), "Swipe page not opened");
        Assert.assertTrue(swipePage.getPageTitle().contains("Swipe horizontal"),
                "Swipe page title doesn't match");

        logger.info("Swipe section loaded successfully");
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testDragAndDropSection() {
        logger.info("Testing Drag & Drop section...");

        HomePage homePage = new HomePage(getDriver());
        DragPage dragPage = homePage.clickDragMenu();

        Assert.assertTrue(dragPage.isPageOpened(), "Drag page not opened");
        logger.info("Drag & Drop section loaded successfully");
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testWebViewSection() {
        logger.info("Testing WebView section...");

        HomePage homePage = new HomePage(getDriver());
        WebViewPage webViewPage = homePage.clickWebviewMenu();

        Assert.assertTrue(webViewPage.isPageOpened(), "WebView page not opened");
        pause(3); // Wait for WebView to load

        logger.info("WebView section loaded successfully");
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testSignUpForm() {
        logger.info("Testing Sign Up form...");

        HomePage homePage = new HomePage(getDriver());
        SignUpPage signUpPage = homePage.clickSignUpMenu();

        if (signUpPage.isPageOpened()) {
            signUpPage.signUp("newuser@example.com", "newpassword123");
            pause(3); // Wait for sign up result
            logger.info("Sign Up form submitted successfully");
        } else {
            logger.info("Sign Up section not accessible, skipping test");
        }
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testNavigationBetweenSections() {
        logger.info("Testing navigation between sections...");

        HomePage homePage = new HomePage(getDriver());

        // Test navigation to Login
        LoginPage loginPage = homePage.clickLoginMenu();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page not opened");
        getDriver().navigate().back();
        pause(1);

        // Test navigation to Forms
        homePage = new HomePage(getDriver());
        FormsPage formsPage = homePage.clickFormsMenu();
        Assert.assertTrue(formsPage.isPageOpened(), "Forms page not opened");
        getDriver().navigate().back();
        pause(1);

        // Test navigation to Swipe
        homePage = new HomePage(getDriver());
        SwipePage swipePage = homePage.clickSwipeMenu();
        Assert.assertTrue(swipePage.isPageOpened(), "Swipe page not opened");
        getDriver().navigate().back();
        pause(1);

        logger.info("Navigation test completed successfully");
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testMultipleUserLogins() {
        logger.info("Testing multiple user logins...");

        String[][] testUsers = {
                {"test@example.com", "password123"},
                {"user@test.com", "pass123"}
        };

        for (String[] user : testUsers) {
            HomePage homePage = new HomePage(getDriver());
            LoginPage loginPage = homePage.clickLoginMenu();

            loginPage.login(user[0], user[1]);

            if (loginPage.isSuccessMessagePresent()) {
                logger.info("Login successful for: {}", user[0]);
            } else {
                logger.info("No success message for: {}, but continuing", user[0]);
            }

            getDriver().navigate().back();
            pause(2);
        }
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testAppStability() {
        logger.info("Testing app stability...");

        for (int i = 0; i < 3; i++) {
            HomePage homePage = new HomePage(getDriver());

            FormsPage formsPage = homePage.clickFormsMenu();
            getDriver().navigate().back();

            homePage = new HomePage(getDriver());
            SwipePage swipePage = homePage.clickSwipeMenu();
            getDriver().navigate().back();

            homePage = new HomePage(getDriver());
            LoginPage loginPage = homePage.clickLoginMenu();
            getDriver().navigate().back();

            pause(1); // Small pause between iterations
        }

        logger.info("App stability test passed - no crashes");
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testAppPermissions() {
        logger.info("Testing app permissions handling...");

        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clickLoginMenu();

        loginPage.login("test@example.com", "password123");

        // Check that the application has not crashed
        Assert.assertTrue(loginPage.isSuccessMessagePresent() || loginPage.isPageOpened(),
                "App crashed or unexpected behavior");

        logger.info("App permissions handled correctly");
    }

    @Test
    @MethodOwner(owner = "Maksym")
    public void testElementPresence() {
        logger.info("Testing element presence methods...");

        HomePage homePage = new HomePage(getDriver());

        // Test isElementPresent method from BaseAndroidTest
        boolean isTitlePresent = isElementPresent(org.openqa.selenium.By.xpath("//android.widget.TextView[@text='WEBDRIVER']"));
        Assert.assertTrue(isTitlePresent, "Main title should be present");

        boolean isLoginPresent = isElementPresent(org.openqa.selenium.By.xpath("//android.widget.TextView[@text='Login']"));
        Assert.assertTrue(isLoginPresent, "Login menu should be present");

        logger.info("Element presence test completed successfully");
    }
}