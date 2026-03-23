package page.user.dashboard;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.TestUtils;

public class UserDashboardPage {
    private AndroidDriver driver;
    private TestUtils utils;

    // Locators
    private By myAttendance = AppiumBy.xpath(
            "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup");
    private By myAttendance1 = AppiumBy
            .androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(50)");

    private By dutyRoaster = AppiumBy.xpath(
            "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup");
    private By myDocuments = AppiumBy.xpath("//android.widget.TextView[@text='My Document's']");

    private By applyLeave = AppiumBy.xpath(
            "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[5]/android.view.ViewGroup");
    private By applyLeave1 = AppiumBy
            .androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(56)");

    private By applyExpense = AppiumBy.xpath(
            "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[6]/android.view.ViewGroup");
    private By applyLoanAdvance = AppiumBy.xpath(
            "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[7]/android.view.ViewGroup");

    private By messages = AppiumBy.xpath(
            "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[8]/android.view.ViewGroup");

    private By myReports = AppiumBy.xpath("//android.widget.TextView[@text='My Reports']");
    private By easyTracker = AppiumBy.xpath("//android.widget.TextView[@text='EasyTracker']");
    private By welcomeText = AppiumBy.xpath("//android.widget.TextView[contains(@text, 'Hi,')]");
    private By backToDashboard = AppiumBy.xpath("//android.widget.TextView[@text='Back']");

    public UserDashboardPage(AndroidDriver driver) {
        this.driver = driver;
        this.utils = new TestUtils(driver);
    }

    public void clickMyAttendance() {
        utils.clickWhenClickable(myAttendance, 10);
    }

    public void clickDutyRoaster() {
        utils.clickWhenClickable(dutyRoaster, 10);
    }

    public void clickMyDocuments() {
        utils.clickWhenClickable(myDocuments, 10);
    }

    public void clickApplyLeave() {
        utils.clickWhenClickable(applyLeave, 10);
    }

    public void clickApplyExpense() {
        utils.clickWhenClickable(applyExpense, 10);
    }

    public void clickApplyLoanAdvance() {
        utils.clickWhenClickable(applyLoanAdvance, 10);
    }

    // 1
    public void clickMessages() {
        utils.clickWhenClickable(messages, 10);
    }

    public void clickMyReports() {
        utils.clickWhenClickable(myReports, 10);
    }

    public void clickEasyTracker() {
        utils.clickWhenClickable(easyTracker, 10);
    }

    public boolean isWelcomeTextDisplayed() {
        return utils.isElementPresent(welcomeText, 5);
    }

    public boolean isDashboardDisplayed() {
        return utils.isElementPresent(myAttendance, 10);
    }

    public void goToDashboard() {
        utils.clickWhenClickable(backToDashboard, 10);
    }
}