package tests.user.dashboard;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import page.common.LoginPage;
import page.user.dashboard.UserDashboardPage;
import page.user.dashboard.UserApplyLeavePage;
import page.user.sidebarMenu.UserSidebarMenuPage;

//import utils.ConfigReader;

public class UserApplyLeaveTest extends BaseTest {
    private LoginPage loginPage;
    private UserDashboardPage userDashboard;
    private UserApplyLeavePage applyLeavePage;
    private UserSidebarMenuPage sidebarMenuPage;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        userDashboard = new UserDashboardPage(driver);
        applyLeavePage = new UserApplyLeavePage(driver);
        sidebarMenuPage = new UserSidebarMenuPage(driver);
    }   
    
//    String username = ConfigReader.get("user.username");
//    String password = ConfigReader.get("user.password");
    
    String username = "9999993331";
    String password = "123456";

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
	public void openApplyLeaveSection() {

	    userDashboard.clickApplyLeave();
	
	    Assert.assertTrue(applyLeavePage.isApplyLeavePageDisplayed(),
	            "Apply Leave  page was not displayed!");
        System.out.println("✅ Apply Leave page opened successfully.");

	}
	
	@Test(priority = 3, dependsOnMethods = "openApplyLeaveSection")
	public void applyForLeaveTest() {

		applyLeavePage.clickForApplyButton();
//        Assert.assertTrue(applyLeavePage.isLeaveApplyFormDisplayed(),
//                "Apply leave form was not displayed!");
        System.out.println("✓ Apply leave form opened");
        
        applyLeavePage.clickOnCalender();
        applyLeavePage.selectDate("2026-01-25");
        applyLeavePage.clickOnCalender();
        applyLeavePage.openLeaveDropdown();
        applyLeavePage.selectLeaveType("UnPaid Leave");
        applyLeavePage.eneteReason("Need a leave to enjoy");
        applyLeavePage.submitBtn();
        applyLeavePage.OkPopUp();
        
        Assert.assertTrue(applyLeavePage.isApplyLeavePageDisplayed(),
                "Messages page was not displayed!");
        
        System.out.println("Returned back to the Apply leave HomePage after Successful leave apply.");
	}
	
    @Test(priority = 4)//,dependsOnMethods = "applyForLeaveTest")
    public void logOutAterApplyLeave() {
//    	applyLeavePage.backTodashBoard();
        sidebarMenuPage.openSidebarMenu();
        sidebarMenuPage.selectLogoutOption();
        sidebarMenuPage.confirmLogout();

        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "❌ Failed to return to Login Page after logout!");
    }
}