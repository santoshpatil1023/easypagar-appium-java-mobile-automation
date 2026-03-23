package tests.user.SidebarMenu;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.common.LoginPage;
import page.user.sidebarMenu.UserSidebarMenuPage;
import page.user.sidebarMenu.UserHelpVideosPage;
import utils.ConfigReader;
import utils.TestUtils;

public class UserHelpVideosTest extends BaseTest {

    private LoginPage loginPage;
    private UserSidebarMenuPage sidebarMenuPage;
    private UserHelpVideosPage helpVideosPage;
    private TestUtils utils; // ✅ declare utils

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        sidebarMenuPage = new UserSidebarMenuPage(driver);
        helpVideosPage = new UserHelpVideosPage(driver);
        utils = new TestUtils(driver); // ✅ initialize utils

    }

    String username = ConfigReader.get("user.username");
    String password = ConfigReader.get("user.password");

    private void loginAsUser() {
        loginPage.loginAsUser(username, password);
    }

    // 1️⃣ Login + Open Help Videos
    @Test(priority = 1, description = "Verify Help Videos page is displayed successfully")
    public void testHelpVideosPageDisplayed() {
        loginAsUser();
        sidebarMenuPage.openSidebarMenu();
        sidebarMenuPage.selectHelpVideos();

        Assert.assertTrue(helpVideosPage.isHelpVideosDisplayed(), "❌ Help Videos page not displayed!");
        System.out.println("✅ Help Videos page displayed successfully!");
    }

    // 2️⃣ Attendance Videos
    @Test(priority = 2, dependsOnMethods = "testHelpVideosPageDisplayed", description = "Verify Attendance Help Video is displayed")
    public void testAttendanceHelpVideos() {
        helpVideosPage.selectAttendanceHelpVideos();
        Assert.assertTrue(helpVideosPage.isAttendanceHelpVideoAvailable(),
                "❌ Attendance help video not found!");
        System.out.println("✅ Attendance help video displayed successfully!");
        utils.pressBackButton(); // Return to Help Videos main page
    }

    // 3️⃣ Leave Videos
    @Test(priority = 3, dependsOnMethods = "testAttendanceHelpVideos", description = "Verify Leave Help Video is displayed")
    public void testLeaveHelpVideos() {
        helpVideosPage.selectLeaveHelpVideos();
        Assert.assertTrue(helpVideosPage.isLeaveHelpVideosAvailable(),
                "❌ Leave help video not found!");
        System.out.println("✅ Leave help video displayed successfully!");
        utils.pressBackButton();
    }

    // 4️⃣ Loan Videos
    @Test(priority = 4, dependsOnMethods = "testLeaveHelpVideos", description = "Verify Loan Help Video is displayed")
    public void testLoanHelpVideos() {
        helpVideosPage.selectLoanHelpVideos();
        Assert.assertTrue(helpVideosPage.isLoanHelpVideosAvailable(),
                "❌ Loan help video not found!");
        System.out.println("✅ Loan help video displayed successfully!");
        utils.pressBackButton();
    }

    // 5️⃣ Report Videos
    @Test(priority = 5, dependsOnMethods = "testLoanHelpVideos", description = "Verify Report Help Video is displayed")
    public void testReportHelpVideos() {
        helpVideosPage.selectReportHelpVideos();
        Assert.assertTrue(helpVideosPage.isReportHelpVideosAvailable(),
                "❌ Report help video not found!");
        System.out.println("✅ Report help video displayed successfully!");
        utils.pressBackButton();
    }

    // 6️⃣ Expense Videos
    @Test(priority = 6, dependsOnMethods = "testReportHelpVideos", description = "Verify Expense Help Video is displayed")
    public void testExpenseHelpVideos() {
        helpVideosPage.selectExpenseHelpVideos();
        Assert.assertTrue(helpVideosPage.isExpenseVideosAvailale(),
                "❌ Expense help video not found!");
        System.out.println("✅ Expense help video displayed successfully!");
        utils.pressBackButton();
    }

    // 7️⃣ Message Videos
    @Test(priority = 7, dependsOnMethods = "testExpenseHelpVideos", description = "Verify Message Help Video is displayed")
    public void testMessageHelpVideos() {
        helpVideosPage.selectMessageHelpVideos();
        Assert.assertTrue(helpVideosPage.isMessagesHelpVideosAvailable(),
                "❌ Message help video not found!");
        System.out.println("✅ Message help video displayed successfully!");
        utils.pressBackButton();
        utils.pressBackButton();
    }

    // 8️⃣ Logout at the end
    @Test(priority = 8, dependsOnMethods = "testMessageHelpVideos", description = "Verify user can logout successfully after viewing help videos")
    public void testLogoutAfterHelpVideos() {
        sidebarMenuPage.openSidebarMenu();
        sidebarMenuPage.selectLogoutOption();
        sidebarMenuPage.confirmLogout();

        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "❌ Failed to return to Login Page after logout!");
        System.out.println("✅ User logged out successfully after completing Help Videos validation!");
    }
}
