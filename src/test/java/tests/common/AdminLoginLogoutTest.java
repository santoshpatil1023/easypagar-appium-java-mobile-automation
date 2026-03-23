package tests.common;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import page.admin.sidebarMenu.AdminSidebarMenuPage;
import page.common.LoginPage;
import utils.ConfigReader;

public class AdminLoginLogoutTest extends BaseTest {

    private LoginPage loginPage;
    private AdminSidebarMenuPage sidebarMenuPage;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        sidebarMenuPage = new AdminSidebarMenuPage(driver);
    }

    private String getAdminUsername() {
        if (ConfigReader.hasProperty("admin.username")) {
            return ConfigReader.get("admin.username");
        } else {
            System.out.println("⚠️ admin.username not found in config, using default");
            return "admin@easypagar.com"; // Default fallback
        }
    }

    private String getAdminPassword() {
        if (ConfigReader.hasProperty("admin.password")) {
            return ConfigReader.get("admin.password");
        } else {
            System.out.println("⚠️ admin.password not found in config, using default");
            return "admin123"; // Default fallback
        }
    }

    // ✅ Utility method used in both tests
    private void navigateToLoginScreen() {
        loginPage.navigateToLoginScreen();
    }

    @Test(priority = 1, description = "Test successful admin login and logout flow")
    public void testSuccessfulAdminLoginLogout() {
        System.out.println("=== Starting Admin Login-Logout Test ===");

        navigateToLoginScreen();

        // Step 2: Enter admin credentials
        String adminUsername = getAdminUsername();
        String adminPassword = getAdminPassword();

        System.out.println("Entering credentials for: " + adminUsername);
        loginPage.enterUsername(adminUsername);
        loginPage.enterPassword(adminPassword);

        // Step 3: Perform login
        System.out.println("Clicking login button...");
        loginPage.tapLogin();
        loginPage.handleActiveDevicePopup();
        loginPage.verifyOtp();

        // Step 4: Verify successful login
        Assert.assertTrue(loginPage.isAdminDashboardDisplayed(),
                "Admin dashboard should be displayed after successful login");
        System.out.println("✓ Admin login successful");

        // Step 5: Perform logout
        System.out.println("Opening sidebar menu...");
        sidebarMenuPage.openSidebar();
        sidebarMenuPage.selectLogoutOption();

        Assert.assertTrue(sidebarMenuPage.isLogoutConfirmationDisplayed(),
                "Logout confirmation popup should be displayed");
        System.out.println("Logout confirmation displayed");

        sidebarMenuPage.confirmLogout();

        // Step 6: Verify redirected to login page
        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "Should be redirected to login page after logout");
        System.out.println("✓ Admin logout successful");

        System.out.println("=== Admin Login-Logout Test Completed ===");
    }

    @Test(priority = 2, description = "Test invalid login credentials")
    public void testInvalidLogin() {
        System.out.println("=== Starting Invalid Login Test ===");

        navigateToLoginScreen();

        loginPage.clearCredentials();

        // Enter invalid credentials
        String invalidUsername = "invalid_Admin@test.com";
        String invalidPassword = "Adminwrong_password";

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
