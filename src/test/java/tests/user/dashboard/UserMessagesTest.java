package tests.user.dashboard;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import page.common.LoginPage;
import page.user.dashboard.UserDashboardPage;
import page.user.dashboard.UserMessagePage;
import page.user.sidebarMenu.UserSidebarMenuPage;

import utils.ConfigReader;

public class UserMessagesTest extends BaseTest {
    private LoginPage loginPage;
    private UserDashboardPage userDashboard;
    private UserMessagePage messagesPage;
    private UserSidebarMenuPage sidebarMenuPage;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        userDashboard = new UserDashboardPage(driver);
        messagesPage = new UserMessagePage(driver);
        sidebarMenuPage = new UserSidebarMenuPage(driver);
    }

    String username = ConfigReader.get("user.username");
    String password = ConfigReader.get("user.password");

    // ✅ Utility method used in both tests
    private void loginAssUser() {
        loginPage.loginAsUser(username, password);
    }

    @Test(priority = 1)
    public void userLoginTest() {
        loginAssUser();
        System.out.println("🔐 User logged in successfully.");
    }

    @Test(priority = 2, dependsOnMethods = "userLoginTest")
    public void openMessageSection() {

        userDashboard.clickMessages();

        Assert.assertTrue(messagesPage.isMessagesPageDisplayed(),
                "Message page was not displayed!");
        System.out.println("✅ Messsage Leave page opened successfully.");

    }

    @Test(priority = 3, dependsOnMethods = "openMessageSection")
    public void testSendReplayMessageToAdmin() {

        messagesPage.clickOnFirstMessage();

        // If no data, method already ended test
        if (messagesPage.hasNoDataFound())
            return;

        messagesPage.clickAndReplay();
        messagesPage.sendMessage("Hello from user automation");

        System.out.println("Message sent successfully.");
    }

    @Test(priority = 4, dependsOnMethods = "testSendReplayMessageToAdmin")
    public void backToDashBoard() {
        // messagesPage.back1();
        messagesPage.backTodashBoard();
        messagesPage.back1();

        Assert.assertTrue(userDashboard.isDashboardDisplayed(),
                "❌ Failed to return to dashboard Page!");
    }

    @Test(priority = 5, dependsOnMethods = "backToDashBoard")
    public void logOutAterRepliedToMessage() {
        sidebarMenuPage.openSidebarMenu();
        sidebarMenuPage.selectLogoutOption();
        sidebarMenuPage.confirmLogout();

        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "❌ Failed to return to Login Page after logout!");
    }
}