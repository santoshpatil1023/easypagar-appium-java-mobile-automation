package tests.user.dashboard;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import page.common.LoginPage;
import page.user.dashboard.UserDashboardPage;
import page.user.dashboard.UserMyAttendancePage;
import page.user.sidebarMenu.UserSidebarMenuPage;

import utils.ConfigReader;
import utils.TestUtils;

public class UserAttendanceTest extends BaseTest {
    private LoginPage loginPage;
    private UserDashboardPage userDashboard;
    private UserSidebarMenuPage sidebarMenuPage;
    private UserMyAttendancePage myAttendancePage;
    private TestUtils utils;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        userDashboard = new UserDashboardPage(driver);
        sidebarMenuPage = new UserSidebarMenuPage(driver);
        myAttendancePage = new UserMyAttendancePage(driver);
    }   
    
    String username = ConfigReader.get("user.username");
    String password = ConfigReader.get("user.password");

    private void loginAssUser() {
        loginPage.loginAsUser(username, password);
    }
    
    @Test(priority = 1)
    public void userLoginTest() {
        loginAssUser();
        System.out.println("🔐 User logged in successfully.");
    }
    
    @Test(priority = 2, dependsOnMethods = "userLoginTest")
    public void openAttendanceSection() {
        userDashboard.clickMyAttendance();
        myAttendancePage.handleLocationEnablePopup();
        
        Assert.assertTrue(myAttendancePage.isAttendanceDetailsDisplayed(),
                "❌ Attendance page did not open as expected.");
        System.out.println("✅ Attendance page opened successfully.");
    }
    
    @Test(priority = 3, dependsOnMethods = "openAttendanceSection")
    public void testCheckInFlow() {

        // Fresh check-in
        myAttendancePage.clickForCheckInButton();
        myAttendancePage.takePhoto();
        System.out.println("takephoto");
        myAttendancePage.confirmPhoto();
        System.out.println("confirmPopup");
        myAttendancePage.confirmPopup();

        System.out.println("✅ Check-in successful at: ");
    }

    
    @Test(priority = 4, dependsOnMethods = "testCheckInFlow")
    public void testAlreadyCheckedInState() {

        userDashboard.clickMyAttendance();

        Assert.assertTrue(myAttendancePage.isAlreadyCheckedIn(),
                "❌ Expected user to be already checked in");

        Assert.assertTrue(myAttendancePage.isAlreadyCheckedIn(),
                "❌ Check-out button must be visible");

        System.out.println("✅ Already checked-in UI validated successfully.");

    }

    @Test(priority = 5, dependsOnMethods = "testAlreadyCheckedInState")
    public void testCheckOutFlow() {

        myAttendancePage.clickCheckOutButton();
        myAttendancePage.takePhoto();
        myAttendancePage.confirmPhoto();
        myAttendancePage.confirmPopup();
        
        System.out.println("✅ Check-Out successful.");
    }

    @Test(priority = 6,dependsOnMethods = "testCheckOutFlow")
    public void verifyAttendanceRecord() {
    	
        // Navigate to attendance page to verify the record
        userDashboard.clickMyAttendance();
        myAttendancePage.clickAttendancedetails();
        myAttendancePage.isAttendanceDetailsDisplayed();
        
        Assert.assertTrue(myAttendancePage.isAttendanceDetailsDisplayed(),
                "❌ Attendance page did not open as expected.");
        
        System.out.println("✅ Attendance recordeds Displayed.");
//        myAttendancePage.pressBackButton();

    }
    @Test(priority = 7,dependsOnMethods = "verifyAttendanceRecord")
    public void logOutAterAttendanceProcess()  {
        
        myAttendancePage.backTodashBoard();
        sidebarMenuPage.openSidebarMenu();
        sidebarMenuPage.selectLogoutOption();
        sidebarMenuPage.confirmLogout();

        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "❌ Failed to return to Login Page after logout!");
    }
}