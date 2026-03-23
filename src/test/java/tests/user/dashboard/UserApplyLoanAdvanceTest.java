package tests.user.dashboard;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import page.common.LoginPage;
import page.user.dashboard.UserDashboardPage;
import page.user.dashboard.UserApplyLoanAdvancePage;
import page.user.sidebarMenu.UserSidebarMenuPage;
import utils.ConfigReader;

public class UserApplyLoanAdvanceTest extends BaseTest {
    private LoginPage loginPage;
    private UserDashboardPage userDashboard;
    private UserApplyLoanAdvancePage applyLoanAdvacePage;
    private UserSidebarMenuPage sidebarMenuPage;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        userDashboard = new UserDashboardPage(driver);
        applyLoanAdvacePage = new UserApplyLoanAdvancePage(driver);
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

    @Test(priority = 2, dependsOnMethods = "testUserLogin", description = "Verify user can navigate to Apply Loan page")
    public void testOpenApplyLoanSection() {
        userDashboard.clickApplyLoanAdvance();
        Assert.assertTrue(applyLoanAdvacePage.isApplyLoanAdvanceDisplayed(),
                "❌ Apply Loan page was not displayed!");
        System.out.println("✅ Apply Loan page opened successfully.");
    }

    // ========== Functinal Test Cases ==========

    @Test(priority = 3, dependsOnMethods = "testOpenApplyLoanSection", description = "Test applying personal loan with valid data")
    public void testApplyPersonalLoan() {
        applyLoanAdvacePage.clickApplyExpenseButton();
        Assert.assertTrue(applyLoanAdvacePage.isExpenseApplyLoanAdvanceFormDisplayed(),
                "❌ Loan application form not displayed!");

        applyLoanAdvacePage.clickDropDownToChoose();
        applyLoanAdvacePage.selectLoanType("Loan");
        applyLoanAdvacePage.enterAmount("50000");
        applyLoanAdvacePage.enterDetails("Home renovation");
        applyLoanAdvacePage.clickSubmitButton();

        Assert.assertTrue(applyLoanAdvacePage.isLoanApplied(),
                "❌ Personal loan application failed!");

        applyLoanAdvacePage.clickOkSuccessfully();
        System.out.println("✅ loan applied successfully.");

    }

    @Test(priority = 4, dependsOnMethods = "testOpenApplyLoanSection", description = "Test applying personal advance with valid data")
    public void testApplyForAdvance() {
        applyLoanAdvacePage.clickApplyExpenseButton();
        Assert.assertTrue(applyLoanAdvacePage.isExpenseApplyLoanAdvanceFormDisplayed(),
                "❌ Loan application form not displayed!");

        applyLoanAdvacePage.clickDropDownToChoose();
        applyLoanAdvacePage.selectLoanType("Advance");
        applyLoanAdvacePage.enterAmount("15000");
        applyLoanAdvacePage.enterDetails("Emergency Situation need Advance of 15000");
        applyLoanAdvacePage.clickSubmitButton();

        Assert.assertTrue(applyLoanAdvacePage.isAdvanceApplied(),
                "❌ Advance application failed!");

        applyLoanAdvacePage.clickOkSuccessfully();
        System.out.println("✅ Advance Amount applied successfully.");

    }

    @Test(priority = 5, dependsOnMethods = "testApplyForAdvance")
    public void logOutAterApplyLoanAdvance() {
        Assert.assertTrue(applyLoanAdvacePage.isApplyLoanAdvanceDisplayed(),
                "❌ Apply Loan page was not displayed!");
        applyLoanAdvacePage.navigateBackToDashboard();
        sidebarMenuPage.openSidebarMenu();
        sidebarMenuPage.selectLogoutOption();
        sidebarMenuPage.confirmLogout();

        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "❌ Failed to return to Login Page after logout!");
    }
}