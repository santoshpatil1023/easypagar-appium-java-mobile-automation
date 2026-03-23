package tests.user.dashboard;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import page.common.LoginPage;
import page.user.dashboard.UserDashboardPage;
import page.user.dashboard.UserDutyRoasterPage;
import page.user.sidebarMenu.UserSidebarMenuPage;
import utils.ConfigReader;

public class UserDutyRoasterTest extends BaseTest {
    private LoginPage loginPage;
    private UserDashboardPage userDashboard;
    private UserDutyRoasterPage dutyRoasterPage;
    private UserSidebarMenuPage sidebarMenuPage;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        userDashboard = new UserDashboardPage(driver);
        dutyRoasterPage = new UserDutyRoasterPage(driver);
        sidebarMenuPage = new UserSidebarMenuPage(driver);
    }   
    
    String username = ConfigReader.get("user.username");
    String password = ConfigReader.get("user.password");

    private void loginAsUser() {
        loginPage.loginAsUser(username, password);
    }
    
    // ========== Basic Flow Tests ==========
    
    @Test(priority = 1, description = "Verify user can login successfully")
    public void testUserLogin() {
        loginAsUser();
        Assert.assertTrue(userDashboard.isDashboardDisplayed(), 
                "❌ Dashboard not displayed after login!");
        System.out.println("✅ User logged in successfully.");
    }
    
    @Test(priority = 2, dependsOnMethods = "testUserLogin",
            description = "Verify user can navigate to Duty Roster page")
    public void testOpenDutyRoasterSection() {
        userDashboard.clickDutyRoaster();
        Assert.assertTrue(dutyRoasterPage.isDutyRoasterPageDisplayed(),
                "❌ Duty Roster page was not displayed!");
        System.out.println("✅ Duty Roster page opened successfully.");
    }
	
    // ========== View Type Tests ==========

    @Test(priority = 3)//, dependsOnMethods = "testOpenDutyRoasterSection", description = "Test monthly calendar view of duty roster")
    public void testMonthlyDutyRoasterCalendarView() {
    	dutyRoasterPage.clickMonthDropDown();
        dutyRoasterPage.selectMonth("March");
     
        Assert.assertTrue(dutyRoasterPage.isCalendarViewDisplayed(),
                "❌ Monthly calendar view not displayed!");
        
        System.out.println("✅ Monthly calendar view displayed successfully.");
    }
    
    @Test(priority = 4, dependsOnMethods = "testMonthlyDutyRoasterCalendarView",
            description = "Test monthly table view of duty roster")
    public void testMonthlyDutyRoasterTableView() {
    	
    	dutyRoasterPage.clickToSelectViewFormatforMonth();
        dutyRoasterPage.selectViewType("Table View");
        
        Assert.assertTrue(dutyRoasterPage.isTableViewDisplayed(),
                "❌ Monthly table view not displayed!");

        System.out.println("✅ Monthly table view displayed successfully.");
    }
    
    @Test(priority = 5, dependsOnMethods = "testMonthlyDutyRoasterTableView", description = "Test weekly table view of duty roster")
    public void testWeeklyDutyRoasterTableView() {
    	dutyRoasterPage.clickDutyRoasterDropDown();
        dutyRoasterPage.selectRosterType("Weekly Duty Roster");
    	dutyRoasterPage.clickToSelectWeek();
        dutyRoasterPage.selectWeek("First Week ");
        
        Assert.assertTrue(dutyRoasterPage.isWeeklyTableViewDisplayed(),
                "❌ Weekly table view not displayed!");
        
        System.out.println("✅ Weekly table view displayed successfully.");
    }
    
    @Test(priority = 6, dependsOnMethods = "testWeeklyDutyRoasterTableView", description = "Test weekly calendar view of duty roster")
    public void testWeeklyDutyRoasterCalendarView() {

    	dutyRoasterPage.clickToSelectViewFormatForWeek();
        dutyRoasterPage.selectViewType("Calendar View");
        
        Assert.assertTrue(dutyRoasterPage.isWeeklyViewDisplayed(),
                "❌ Weekly calendar view not displayed!");

        System.out.println("✅ Weekly calendar view displayed successfully.");
    }

    @Test(priority = 7,dependsOnMethods = "testWeeklyDutyRoasterCalendarView")
    public void logOutAterDutyRoasterView() {

    	dutyRoasterPage.navigateBackToDashboard();
        sidebarMenuPage.openSidebarMenu();
        sidebarMenuPage.selectLogoutOption();
        sidebarMenuPage.confirmLogout();

        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "❌ Failed to return to Login Page after logout!");
    }
}