package page.common;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import utils.TestUtils;
import org.openqa.selenium.By;

public class LoginPage {
    private AndroidDriver driver;
    private TestUtils utils;

    // Constructor
    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
        this.utils = new TestUtils(driver);
    }

    // Locators
    private By easyPagarApp1 = AppiumBy.xpath(
            "//android.widget.FrameLayout[@resource-id='android:id/content']" +
                    "/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup" +
                    "/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup" +
                    "/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup" +
                    "/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup" +
                    "/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup" +
                    "/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]" +
                    "/android.widget.ImageView");

    private By easyPagarApp = AppiumBy
            .androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(1)");
    private By cancelUpdateApp = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"android:id/button2\")");

    private By usernameField = AppiumBy.xpath(
            "//android.widget.EditText[@resource-id='text-input-flat' and @text='Enter E-Mail ID/Mobile Number *']");
    private By passwordField = AppiumBy
            .xpath("//android.widget.EditText[@resource-id='text-input-flat' and @text='Password *']");
    private By rememberMeCheckbox = AppiumBy.xpath("//android.widget.TextView[@text='Remember Me?']");
    private By loginButton = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Log In']");
    public By yesButton = AppiumBy.id("android:id/button1");
    private By verifyButton = AppiumBy.xpath("//android.widget.TextView[@text='Verify']");

    private By MyEmployee = AppiumBy.xpath("//android.widget.TextView[@text='My Employee']");
    private By MyAttendance = AppiumBy.xpath("//android.widget.TextView[@text='My Attendance']");

    private By invalidLoginMessage = AppiumBy
            .xpath("//android.widget.TextView[@text='Invalid Mobile Number / EmailID!!']");

    // Actions using your TestUtils methods
    public void selectEasyPagar() {
        utils.clickWhenClickable(easyPagarApp, 20);
    }

    public void cancelUpdate() {
        if (utils.isElementPresent(cancelUpdateApp, 5)) {
            utils.clickWhenClickable(cancelUpdateApp, 10);
        }
    }

    public void enterUsername(String username) {
        utils.sendKeys(usernameField, username, 10);
    }

    public void enterPassword(String password) {
        utils.sendKeys(passwordField, password, 10);
    }

    public void selectRememberMe() {
        utils.clickWhenClickable(rememberMeCheckbox, 10);
    }

    public void tapLogin() {
        utils.clickWhenClickable(loginButton, 10);
    }

    public void handleActiveDevicePopup() {
        if (utils.isElementPresent(yesButton, 5)) {
            utils.clickWhenClickable(yesButton, 5);
        }
    }

    public void verifyOtp() {
        if (utils.isElementPresent(verifyButton, 20)) {
            utils.clickWhenClickable(verifyButton, 20);
        }
    }

    public boolean isAdminDashboardDisplayed() {
        return utils.isElementPresent(MyEmployee, 10);
    }

    public boolean isUserDashboardDisplayed() {
        return utils.isElementPresent(MyAttendance, 10);
    }

    public boolean isInvalidLoginMessageDisplayed() {
        return utils.isElementPresent(invalidLoginMessage, 5);
    }

    public String getInvalidLoginMessage() {
        return utils.getElementText(invalidLoginMessage, 5);
    }

    public boolean isLoginPageDisplayed() {
        return utils.isElementPresent(loginButton, 10) ||
                utils.isElementPresent(usernameField, 10);
    }

    public boolean isLoginButtonDisplayed() {
        return utils.isElementPresent(loginButton, 10);
    }

    public boolean isUsernameFieldDisplayed() {
        return utils.isElementPresent(usernameField, 10);
    }

    public void clearCredentials() {
        // Using your TestUtils methods
        if (utils.isElementPresent(usernameField, 5)) {
            utils.sendKeys(usernameField, "", 5); // Clear by sending empty string
        }
        if (utils.isElementPresent(passwordField, 5)) {
            utils.sendKeys(passwordField, "", 5); // Clear by sending empty string
        }
    }

    // Additional utility methods using your TestUtils
    public void waitForLoginPageToLoad() {
        utils.waitForVisible(loginButton, 10);
    }

    public void pressBackIfNeeded() {
        utils.pressBackButton();
    }

    public void navigateToLoginScreen() {
        System.out.println("Navigating to Login Screen...");

        if (!isLoginPageDisplayed()) {
            System.out.println("App selection screen detected. Selecting EasyPagar...");
            selectEasyPagar();
            cancelUpdate();
        }

        waitForLoginPageToLoad();
        System.out.println("Login screen ready.");
    }

    public void loginAsUser(String username, String password) {
        System.out.println("=== Starting Login Flow ===");

        navigateToLoginScreen();

        enterUsername(username);
        enterPassword(password);
        // selectRememberMe();
        tapLogin();

        handleActiveDevicePopup();
        verifyOtp();

        System.out.println("Waiting for dashboard...");
        if (isUserDashboardDisplayed()) {
            System.out.println("✓ User dashboard displayed successfully!");
        } else {
            throw new RuntimeException("❌ Login failed: User dashboard not displayed");
        }
    }

    public void loginAsAdmin(String username, String password) {
        System.out.println("=== Starting Login Flow ===");

        navigateToLoginScreen();

        enterUsername(username);
        enterPassword(password);
        // selectRememberMe();
        tapLogin();

        handleActiveDevicePopup();
        verifyOtp();

        System.out.println("Waiting for dashboard...");
        if (isAdminDashboardDisplayed()) {
            System.out.println("✓ User dashboard displayed successfully!");
        } else {
            throw new RuntimeException("❌ Login failed: User dashboard not displayed");
        }

    }

}