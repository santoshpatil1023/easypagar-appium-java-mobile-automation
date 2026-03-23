package tests.admin.dashboard;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import page.common.LoginPage;
import page.admin.dashboard.AdminDashboardPage;
import page.admin.dashboard.AdminApproveLeavePage;
import utils.ConfigReader;

public class AdminApproveLeaveTest extends BaseTest {
    private LoginPage loginPage;
    private AdminDashboardPage adminDashboard;
    private AdminApproveLeavePage adminApproveLeavePage;

    @BeforeMethod
    public void setUpPages() {
	        loginPage = new LoginPage(driver);
	        adminDashboard = new AdminDashboardPage(driver);
	        adminApproveLeavePage = new AdminApproveLeavePage(driver);
        }  
    
	    String adminname = ConfigReader.get("admin.username");
	    String password = ConfigReader.get("admin.password");
	    
	    private void loginAsAdmin() {
	        loginPage.loginAsAdmin(adminname, password);
	    }
	    
	    @Test(priority = 1)
	    public void adminLoginTest() {
	    	loginAsAdmin();
	        System.out.println("🔐 Admin logged in successfully.");
	    }
	    
	    @Test(priority = 2, dependsOnMethods = "adminLoginTest")
	    public void openApproveLeaveSection() {
	        adminDashboard.clickApproveLeave();

	        Assert.assertTrue(adminApproveLeavePage.isApproveLeaveHeaderPageDisplayed(),
	                "❌ Approve Leave page did not open as expected.");
	        System.out.println("✅ Approve Leave page opened successfully.");
	    }
}