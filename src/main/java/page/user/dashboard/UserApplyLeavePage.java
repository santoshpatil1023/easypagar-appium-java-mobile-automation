package page.user.dashboard;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import utils.TestUtils;

public class UserApplyLeavePage {
    private AndroidDriver driver;
    private TestUtils utils;

    // Locators
    private By applyLeaveHeader = AppiumBy.androidUIAutomator("new UiSelector().text(\"Apply Leave\")");
    private By clickFab = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"fab-content\")");
    private By calender = AppiumBy.androidUIAutomator("new UiSelector().text(\"Select Dates\")");
    private By resonField = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"text-input-outlined\")");
    private By submit = AppiumBy.androidUIAutomator("new UiSelector().description(\"Submit\")");
    private By okpopup = AppiumBy.androidUIAutomator("new UiSelector().description(\"Ok\")");

    // Constructor
    public UserApplyLeavePage(AndroidDriver driver) {
        this.driver = driver;
        this.utils = new TestUtils(driver);
    }

    // Actions
    public boolean isApplyLeavePageDisplayed() {
        return utils.isElementPresent(applyLeaveHeader, 10);
    }

    public void clickForApplyButton() {
        utils.clickWhenClickable(clickFab, 10);
    }

    public boolean isLeaveApplyFormDisplayed() {
        return utils.isElementPresent(calender, 10);
    }

    public void openLeaveDropdown() {
        utils.clickWhenClickable(
                AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"card\"]/android.view.ViewGroup[3]"), 10);
    }

    public void selectLeaveType(String LeaveType) {
        String selector = "//android.widget.TextView[@text='" + LeaveType + "']";
        utils.clickWhenClickable(AppiumBy.xpath(selector), 10);
    }

    public void clickOnCalender() {
        utils.clickWhenClickable(calender, 10);
    }

    public void selectDate(String date) {
        String selector = "new UiSelector().resourceId(\"undefined.day_" + date + "\")";
        utils.clickWhenClickable(AppiumBy.androidUIAutomator(selector), 10);
    }

    public void eneteReason(String msg) {
        utils.sendKeys(resonField, msg, 10);
        // utils.pressBackButton();
    }

    public void submitBtn() {
        utils.clickWhenClickable(submit, 10);
    }

    public void OkPopUp() {
        utils.clickWhenClickable(okpopup, 10);
    }

    public void backTodashBoard() {
        // utils.pressBackButton();
        utils.pressBackButton();
    }
}