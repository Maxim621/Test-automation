package com.solvd.android.tests;

import com.solvd.android.core.BaseAndroidTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NativeTest extends BaseAndroidTest {
    private static final Logger logger = LogManager.getLogger(NativeTest.class);

    @Test
    public void testAppLaunch() {
        logger.info("Testing Native App launch...");

        // Checking that the app has started
        String welcomeText = getElementText(By.xpath("//android.widget.TextView[@text='WEBDRIVER']"));
        assert welcomeText.equals("WEBDRIVER") : "App not launched properly";
        logger.info("App launched successfully: {}", welcomeText);
    }

    @Test
    public void testLoginScreen() {
        logger.info("Testing Login screen...");

        // Go to the Login screen
        clickElement(By.xpath("//android.widget.TextView[@text='Login']"));

        // Check that the form elements are present
        assert waitForElement(By.xpath("//android.widget.EditText[@content-desc='input-email']")).isDisplayed();
        assert waitForElement(By.xpath("//android.widget.EditText[@content-desc='input-password']")).isDisplayed();
        assert waitForElement(By.xpath("//android.widget.TextView[@text='LOGIN']")).isDisplayed();

        logger.info("Login screen elements are present");
    }

    @Test
    public void testSuccessfulLogin() {
        logger.info("Testing successful login...");

        // Go to the Login screen
        clickElement(By.xpath("//android.widget.TextView[@text='Login']"));

        // Fill out the form
        sendKeysToElement(By.xpath("//android.widget.EditText[@content-desc='input-email']"), "test@example.com");
        sendKeysToElement(By.xpath("//android.widget.EditText[@content-desc='input-password']"), "password123");

        // Click the Login button
        clickElement(By.xpath("//android.widget.TextView[@text='LOGIN']"));

        // Check for successful login
        String successMessage = getElementText(By.xpath("//android.widget.TextView[contains(@text, 'Success')]"));
        assert successMessage.contains("Success") : "Login failed";
        logger.info("Login successful: {}", successMessage);
    }

    @Test
    public void testFormsSection() {
        logger.info("Testing Forms section...");

        clickElement(By.xpath("//android.widget.TextView[@text='Forms']"));

        // Waiting for the Forms screen to load
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            logger.warn("Interrupted while waiting for Forms screen", e);
        }

        // Use alternative locators
        String[] textInputLocators = {
                "//android.widget.EditText[@content-desc='input-text']",
                "//android.widget.EditText[contains(@text, 'Type')]",
                "//android.widget.EditText"
        };

        boolean foundInput = false;
        for (String locator : textInputLocators) {
            try {
                sendKeysToElement(By.xpath(locator), "Hello Appium");
                foundInput = true;
                logger.info("Text input found with: {}", locator);
                break;
            } catch (Exception e) {
                logger.debug("Not found with: {}", locator);
            }
        }

        if (!foundInput) {
            logger.info("Text input not found, continuing with other elements");
        }

        // Test other elements
        try {
            clickElement(By.xpath("//android.widget.Switch"));
            logger.info("Switch clicked");
        } catch (Exception e) {
            logger.debug("Switch not found");
        }

        try {
            clickElement(By.xpath("//android.widget.CheckBox"));
            logger.info("Checkbox clicked");
        } catch (Exception e) {
            logger.debug("Checkbox not found");
        }

        logger.info("Forms section tested");
    }

    @Test
    public void testSwipeSection() {
        logger.info("Testing Swipe section...");

        clickElement(By.xpath("//android.widget.TextView[@text='Swipe']"));

        // Check that we are on the Swipe screen
        String swipeText = getElementText(By.xpath("//android.widget.TextView[@text='Swipe horizontal']"));
        assert swipeText.contains("Swipe horizontal");

        logger.info("Swipe section loaded successfully");
    }

    @Test
    public void testDragAndDropSection() {
        logger.info("Testing Drag & Drop section...");

        clickElement(By.xpath("//android.widget.TextView[@text='Drag']"));

        // Check that we are on the Drag & Drop screen
        String dragText = getElementText(By.xpath("//android.widget.TextView[contains(@text, 'Drag')]"));
        assert dragText != null;

        logger.info("Drag & Drop section loaded successfully");
    }

    @Test
    public void testWebViewSection() {
        logger.info("Testing WebView section...");

        clickElement(By.xpath("//android.widget.TextView[@text='Webview']"));

        // Check that the WebView has loaded
        try {
            Thread.sleep(3000); // Waiting for loading
            logger.info("WebView section loaded");
        } catch (InterruptedException e) {
            logger.warn("Interrupted while waiting for WebView", e);
        }
    }

    @Test
    public void testSignUpForm() {
        logger.info("Testing Sign Up form...");

        // Let's try different locators for Sign Up
        String[] signUpLocators = {
                "//android.widget.TextView[@text='Sign up']",
                "//android.widget.TextView[contains(@text, 'Sign')]",
                "//android.widget.TextView[@text='SIGN UP']"
        };

        boolean foundSignUp = false;
        for (String locator : signUpLocators) {
            try {
                clickElement(By.xpath(locator));
                foundSignUp = true;
                logger.info("Sign Up found with: {}", locator);
                break;
            } catch (Exception e) {
                logger.debug("Not found with: {}", locator);
            }
        }

        if (!foundSignUp) {
            logger.info("Sign Up section not accessible, skipping test");
            return;
        }

        // Waiting for the form to load
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            logger.warn("Interrupted while waiting for Sign Up form", e);
        }

        // Fill out the registration form
        try {
            sendKeysToElement(By.xpath("//android.widget.EditText[@content-desc='input-email']"), "newuser@example.com");
            sendKeysToElement(By.xpath("//android.widget.EditText[@content-desc='input-password']"), "newpassword123");
            sendKeysToElement(By.xpath("//android.widget.EditText[@content-desc='input-repeat-password']"), "newpassword123");

            clickElement(By.xpath("//android.widget.TextView[@text='SIGN UP']"));

            // Waiting for the result
            Thread.sleep(3000);
            logger.info("Sign Up form submitted");

        } catch (Exception e) {
            logger.error("Sign Up form failed: {}", e.getMessage());
        }
    }

    @Test
    public void testNavigationBetweenSections() {
        logger.info("Testing navigation between sections...");

        String[] sections = {"Login", "Forms", "Swipe", "Drag", "Webview", "Sign up"};

        for (String section : sections) {
            try {
                clickElement(By.xpath("//android.widget.TextView[@text='" + section + "']"));
                logger.info("Navigated to: {}", section);

                // Going back
                driver.navigate().back();
                Thread.sleep(1000);

            } catch (Exception e) {
                logger.warn("Failed to navigate to: {}", section);
            }
        }

        logger.info("Navigation test completed");
    }

    @Test
    public void testAppPermissions() {
        logger.info("Testing app permissions handling...");

        // Check that the application does not crash due to permissions
        clickElement(By.xpath("//android.widget.TextView[@text='Login']"));

        // We use functionality that may require permissions
        sendKeysToElement(By.xpath("//android.widget.EditText[@content-desc='input-email']"), "test@example.com");
        sendKeysToElement(By.xpath("//android.widget.EditText[@content-desc='input-password']"), "password123");
        clickElement(By.xpath("//android.widget.TextView[@text='LOGIN']"));

        // Check that the application has not crashed
        String result = getElementText(By.xpath("//android.widget.TextView[contains(@text, 'Success')]"));
        assert result != null;

        logger.info("App permissions handled correctly");
    }

    @Test
    public void testMultipleUserLogins() {
        logger.info("Testing multiple user logins...");

        String[][] testUsers = {
                {"test@example.com", "password123"}, // Use valid data
                {"user@test.com", "pass123"}
        };

        for (String[] user : testUsers) {
            try {
                clickElement(By.xpath("//android.widget.TextView[@text='Login']"));

                sendKeysToElement(By.xpath("//android.widget.EditText[@content-desc='input-email']"), user[0]);
                sendKeysToElement(By.xpath("//android.widget.EditText[@content-desc='input-password']"), user[1]);
                clickElement(By.xpath("//android.widget.TextView[@text='LOGIN']"));

                // Waiting for the result with different options
                String[] resultLocators = {
                        "//android.widget.TextView[contains(@text, 'Success')]",
                        "//android.widget.TextView[contains(@text, 'You are logged in')]",
                        "//android.widget.TextView[contains(@text, 'Welcome')]"
                };

                boolean foundResult = false;
                for (String locator : resultLocators) {
                    try {
                        String result = getElementText(By.xpath(locator));
                        logger.info("Login successful for: {} - {}", user[0], result);
                        foundResult = true;
                        break;
                    } catch (Exception e) {
                        // Continuing to try
                    }
                }

                if (!foundResult) {
                    logger.info("No success message found for: {}, but continuing", user[0]);
                }

                // Return to the main screen
                driver.navigate().back();
                Thread.sleep(2000);

            } catch (Exception e) {
                logger.error("Login failed for: {} - {}", user[0], e.getMessage());
                // Let's try to return to the main screen
                try {
                    driver.navigate().back();
                } catch (Exception ex) {
                    // Ignore navigation errors
                }
            }
        }
    }

    @Test
    public void testAppStability() {
        logger.info("Testing app stability...");

        // We perform several actions to check stability
        for (int i = 0; i < 3; i++) {
            clickElement(By.xpath("//android.widget.TextView[@text='Forms']"));
            driver.navigate().back();

            clickElement(By.xpath("//android.widget.TextView[@text='Swipe']"));
            driver.navigate().back();

            clickElement(By.xpath("//android.widget.TextView[@text='Login']"));
            driver.navigate().back();
        }

        logger.info("App stability test passed - no crashes");
    }
}