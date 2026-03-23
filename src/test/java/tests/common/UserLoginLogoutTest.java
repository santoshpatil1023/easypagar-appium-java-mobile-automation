package tests.common;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import page.user.sidebarMenu.UserSidebarMenuPage;
import page.common.LoginPage;
import utils.ConfigReader;

public class UserLoginLogoutTest extends BaseTest {

    private LoginPage loginPage;
    private UserSidebarMenuPage sidebarMenuPage;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        sidebarMenuPage = new UserSidebarMenuPage(driver);
    }

    private String getEmpUsername() {
        if (ConfigReader.hasProperty("user.username")) {
            return ConfigReader.get("user.username");
        } else {
            System.out.println("⚠️ user.username not found in config, using default");
            return "user@easypagar.com"; // Default fallback
        }
    }

    private String getEmpPassword() {
        if (ConfigReader.hasProperty("user.password")) {
            return ConfigReader.get("user.password");
        } else {
            System.out.println("⚠️ user.password not found in config, using default");
            return "user123"; // Default fallback
        }
    }

    // ✅ Utility method used in both tests
    private void navigateToLoginScreen() {
        loginPage.navigateToLoginScreen();
    }

    @Test(priority = 1, description = "Test successful user login and logout flow")
    public void testSuccessfulUserLoginLogout() {
        System.out.println("=== Starting user Login-Logout Test ===");

        navigateToLoginScreen();

        // Step 2: Enter user credentials
        String empUsername = getEmpUsername();
        String empPassword = getEmpPassword();

        System.out.println("Entering credentials for: " + empUsername);
        loginPage.enterUsername(empUsername);
        loginPage.enterPassword(empPassword);

        // Step 3: Perform login
        System.out.println("Clicking login button...");
        loginPage.tapLogin();
        loginPage.handleActiveDevicePopup();
        loginPage.verifyOtp();

        // Step 4: Verify successful login
        Assert.assertTrue(loginPage.isUserDashboardDisplayed(),
                "user dashboard should be displayed after successful login");
        System.out.println("✓ user login successful");

        // Step 5: Perform logout
        System.out.println("Opening sidebar menu...");
        sidebarMenuPage.openSidebarMenu();
        sidebarMenuPage.selectLogoutOption();

        Assert.assertTrue(sidebarMenuPage.isLogoutConfirmationDisplayed(),
                "Logout confirmation popup should be displayed");
        System.out.println("Logout confirmation displayed");

        sidebarMenuPage.confirmLogout();

        // Step 6: Verify redirected to login page
        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "Should be redirected to login page after logout");
        System.out.println("✓ user logout successful");

        System.out.println("=== user Login-Logout Test Completed ===");
    }

    @Test(priority = 2, description = "Test invalid login credentials")
    public void testInvalidLogin() {
        System.out.println("=== Starting Invalid Login Test ===");

        navigateToLoginScreen();

        loginPage.clearCredentials();

        // Enter invalid credentials
        String invalidUsername = "invalid_user@test.com";
        String invalidPassword = "wrong_password";

        System.out.println("Entering invalid credentials...");
        loginPage.enterUsername(invalidUsername);
        loginPage.enterPassword(invalidPassword);
        loginPage.tapLogin();

        // Verify error message
        Assert.assertTrue(loginPage.isInvalidLoginMessageDisplayed(),
                "Invalid login message should be displayed");
        String errorMessage = loginPage.getInvalidLoginMessage();
        System.out.println("Error message displayed: " + errorMessage);

        System.out.println("=== Invalid Login Test Completed ===");
    }
}
