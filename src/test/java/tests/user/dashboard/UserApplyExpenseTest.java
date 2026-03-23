package tests.user.dashboard;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import page.common.LoginPage;
import page.user.dashboard.UserDashboardPage;
import page.user.dashboard.UserApplyExpensePage;
import page.user.sidebarMenu.UserSidebarMenuPage;
import utils.ConfigReader;

public class UserApplyExpenseTest extends BaseTest {
    private LoginPage loginPage;
    private UserDashboardPage userDashboard;
    private UserApplyExpensePage applyExpensePage;
    private UserSidebarMenuPage sidebarMenuPage;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        userDashboard = new UserDashboardPage(driver);
        applyExpensePage = new UserApplyExpensePage(driver);
        sidebarMenuPage = new UserSidebarMenuPage(driver);
    }

    String username = ConfigReader.get("user.username");
    String password = ConfigReader.get("user.password");

    private void loginAsUser() {
        loginPage.loginAsUser(username, password);
    }

    @Test(priority = 1, description = "Verify user can login successfully")
    public void testUserLogin() {
        loginAsUser();
        Assert.assertTrue(userDashboard.isDashboardDisplayed(),
                "❌ Dashboard not displayed after login!");
        System.out.println("✅ User logged in successfully.");
    }

    @Test(priority = 2, dependsOnMethods = "testUserLogin", description = "Verify user can navigate to Apply Expense page")
    public void testOpenApplyExpenseSection() {
        userDashboard.clickApplyExpense();
        Assert.assertTrue(applyExpensePage.isApplyExpensePageDisplayed(),
                "❌ Apply Expense page was not displayed!");
        System.out.println("✅ Apply Expense page opened successfully.");
    }

    @Test(priority = 3, dependsOnMethods = "testOpenApplyExpenseSection")
    public void testApplyFuelExpense() {

        applyExpensePage.clickApplyExpenseButton();
        System.out.println("✓ Apply Expense form opened");
        applyExpensePage.clickDatePicker();
        applyExpensePage.selectExpenseDate("3");
        applyExpensePage.clickDatePickerOk();
        applyExpensePage.openExpenseTypeDropdown1();
        applyExpensePage.selectExpenseType("Fuel Expense");
        applyExpensePage.enterAmount("345");
        applyExpensePage.enterReason("Fuel Expense");
        applyExpensePage.uploadReceiptFromGallery();
        applyExpensePage.clickSubmitButton();
        applyExpensePage.confirmSubmission();
        applyExpensePage.clickSubmissionOk();

        Assert.assertTrue(applyExpensePage.isApplyExpensePageDisplayed(),
                "Apply Expense page was not displayed!");

        System.out.println("Returned back to the Apply Expense HomePage after Successful Expense apply.");
    }

    @Test(priority = 4, dependsOnMethods = "testApplyFuelExpense", description = "Test applying multiple expenses on same date")
    public void testMultipleExpensesSameDate() {

        applyExpensePage.clickApplyExpenseButton();
        System.out.println("✓ Apply Expense form opened");
        applyExpensePage.clickDatePicker();
        applyExpensePage.selectExpenseDate("2");
        applyExpensePage.clickDatePickerOk();
        applyExpensePage.openExpenseTypeDropdown1();
        applyExpensePage.selectExpenseType("Lunch Bill");
        applyExpensePage.enterAmount("345");
        applyExpensePage.enterReason("Lunch Expense");
        applyExpensePage.uploadReceiptFromGallery();
        applyExpensePage.clickSubmitButton();
        applyExpensePage.addAnother();
        Assert.assertTrue(applyExpensePage.isFirstExpenseAdded(),
                "❌ First expense submission failed!");

        System.out.println("First expense submitted successfuly");
        applyExpensePage.openExpenseTypeDropdown2();
        applyExpensePage.selectExpenseType("Auto Expense");
        applyExpensePage.enterAmount("345");
        applyExpensePage.enterReason("Auto Expense");
        applyExpensePage.uploadReceiptFromGallery();
        applyExpensePage.clickSubmitButton();
        applyExpensePage.confirmSubmission();
        applyExpensePage.clickSubmissionOk();
    }

    @Test(priority = 4, dependsOnMethods = "testMultipleExpensesSameDate")
    public void logOutAterApplyExpense() {
        applyExpensePage.navigateBackToDashboard();
        sidebarMenuPage.openSidebarMenu();
        sidebarMenuPage.selectLogoutOption();
        sidebarMenuPage.confirmLogout();

        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "❌ Failed to return to Login Page after logout!");
    }
}