package page.admin.dashboard;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.TestUtils;

public class AdminDashboardPage {
    private AndroidDriver driver;
    private TestUtils utils;
    

    // Sidebar 
    private By menuButton = AppiumBy.xpath("//android.widget.TextView[@text='󰚈']");
    
    // Main Dashboard Elements
    private By efficiencyText = AppiumBy.xpath("//android.widget.TextView[contains(@text, 'Efficiency')]");
    private By branchName = AppiumBy.xpath("//android.widget.TextView[contains(@text, 'Aditya Birla Branch')]");
    private By moreBranchesBtn = AppiumBy.xpath("//android.widget.TextView[@text='More Branches +']");

    // Locators     // Dashboard Quick Action Buttons
    private By myEmployee = AppiumBy.xpath("//android.widget.TextView[@text='My Employee']");
    private By messages = AppiumBy.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[4]/android.view.ViewGroup");
//    private By banners = AppiumBy.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[5]/android.view.ViewGroup");
    private By banners = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(3)");
    
  // cards
    private By approveLeave = AppiumBy.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[6]/android.view.ViewGroup");
    
    private By approveExpense = AppiumBy.xpath("//android.widget.TextView[@text='Approve Expense']");
    private By approveLoanAdvance = AppiumBy.xpath("//android.widget.TextView[@text='Approve Loan/Advance']");
    private By dutyRoster = AppiumBy.xpath("//android.widget.TextView[@text='Duty Roster']");
    private By reports = AppiumBy.xpath("//android.widget.TextView[@text='Reports']");
    private By easyTracker = AppiumBy.xpath("//android.widget.TextView[@text='EasyTracker']");

    public AdminDashboardPage(AndroidDriver driver) {
        this.driver = driver;
        this.utils = new TestUtils(driver);
    }
    
//     Sidebar Access
    public void openSidebarMenu() {
        utils.clickWhenClickable(menuButton, 10);
    }

    public void clickMyEmployee() {
        utils.clickWhenClickable(myEmployee, 10);
    }

    //1
    public void clickMessages() {
        utils.clickWhenClickable(messages, 10);
    }

    public void clickBanners() {
        utils.clickWhenClickable(banners, 10);
    }

    public void clickApproveLeave() {
        utils.clickWhenClickable(approveLeave, 10);
    }

    public void clickApproveExpense() {
        utils.clickWhenClickable(approveExpense, 10);
    }

    public void clickApproveLoanAdvance() {
        utils.clickWhenClickable(approveLoanAdvance, 10);
    }

    public void clickDutyRoster() {
        utils.clickWhenClickable(dutyRoster, 10);
    }

    public void clickReports() {
        utils.clickWhenClickable(reports, 10);
    }

    public void clickEasyTracker() {
        utils.clickWhenClickable(easyTracker, 10);
    }

    public boolean isEfficiencyDisplayed() {
        return utils.isElementPresent(efficiencyText, 5);
    }

    public boolean isAdminDashboardDisplayed() {
        return utils.isElementPresent(myEmployee, 10);
    }
}