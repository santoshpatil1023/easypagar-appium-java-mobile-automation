package page.admin.dashboard;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import utils.TestUtils;

public class AdminMessagesPage {
    private AndroidDriver driver;
    private TestUtils utils;

    // Locators for Admin Messages
    private By messagesHeader = AppiumBy.xpath("//android.view.View[@text='Messages']");

    private By composeButton = AppiumBy.xpath("//android.view.ViewGroup[@resource-id='fab-content']");
    private By messageCreatePage = AppiumBy.androidUIAutomator("new UiSelector().text(\"Create Messages\")");

    // private By branchDropdown =
    // AppiumBy.xpath("//android.widget.TextView[@text=\"Select Branch *\"]");

    // Improved locators with multiple options
    private By branchDropdown = AppiumBy.xpath("//*[contains(@text, 'Select Branch')]");
    private By branchDropdownAlt1 = AppiumBy.xpath("//android.widget.TextView[contains(@text, 'Branch')]");
    private By branchDropdownAlt2 = AppiumBy.androidUIAutomator(
            "new UiSelector().textContains(\"Branch\")");

    private By departmentDropdown = AppiumBy.xpath("//android.widget.TextView[@text=\"Select Dept *\"]");

    private By employeeDropdown = AppiumBy.xpath("//android.widget.TextView[@text=\"Select Employee *\"]");

    private By messagebox = AppiumBy.xpath("//android.widget.EditText[@resource-id='text-input-outlined']");

    private By sendButton1 = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Send']");

    // Success message locator using the ID you provided
    private By successMessage = AppiumBy.id("com.pagarplus.app:id/snackbar_text");

    // android.view.ViewGroup[@content-desc='Send']

    public AdminMessagesPage(AndroidDriver driver) {
        this.driver = driver;
        this.utils = new TestUtils(driver);
    }

    public boolean isMessagesPageDisplayed() {
        return utils.isElementPresent(messagesHeader, 20);
    }

    public boolean isMessageCreationFormDisplayed() {
        return utils.isElementPresent(messageCreatePage, 10);
    }

    public void clickComposeButton() {
        utils.clickWhenClickable(composeButton, 10);
    }

    public void selectBranch(String branchName) {
        // Try multiple locators for branch dropdown
        if (utils.isElementPresent(branchDropdown, 3)) {
            utils.clickWhenClickable(branchDropdown, 10);
        } else if (utils.isElementPresent(branchDropdownAlt1, 3)) {
            utils.clickWhenClickable(branchDropdownAlt1, 10);
        } else {
            utils.clickWhenClickable(branchDropdownAlt2, 10);
        }

        // Select the branch
        By branchOption = AppiumBy.xpath("//android.widget.TextView[@text='" + branchName + "']");
        utils.clickWhenClickable(branchOption, 10);
    }

    public void selectDepartment(String deptName) {
        utils.clickWhenClickable(departmentDropdown, 10);
        utils.clickWhenClickable(AppiumBy.xpath("//android.widget.TextView[@text='" + deptName + "']"), 10);
    }

    public void selectEmployee(String empName) {
        utils.clickWhenClickable(employeeDropdown, 10);
        utils.clickWhenClickable(AppiumBy.xpath("//android.widget.TextView[@text='" + empName + "']"), 10);
        // utils.clickWhenClickable(AppiumBy.androidUIAutomator("new
        // UiSelector().text('"+empName+"')"), 10);

        // 🔑 Close dropdown after selecting employee
        utils.pressBackButton();
    }

    private By clickUploadImage = AppiumBy.androidUIAutomator("new UiSelector().description(\", Upload Image\")");
    private By takePicture = AppiumBy.androidUIAutomator("new UiSelector().text(\"Take a Picture\")");
    private By clickPhoto = AppiumBy
            .androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(34)");
    private By selectImage = AppiumBy.androidUIAutomator("new UiSelector().text(\"\")");

    public void attachImage() {
        utils.clickWhenClickable(clickUploadImage, 10);
        utils.clickWhenClickable(takePicture, 10);
        utils.clickWhenClickable(clickPhoto, 10);
        utils.clickWhenClickable(selectImage, 10);

    }

    public void typeMessage(String msg) {

        // Wait for the message box and type
        utils.sendKeys(messagebox, msg, 10);
        // utils.pressBackButton();
    }

    public void clickSendButton() {

        // Wait for the message box and type
        utils.clickWhenClickable(sendButton1, 10);

    }

    public boolean isMessageSent() {
        return utils.isElementPresent(successMessage, 10);
    }

    public void backToDashAfterSentmessage() {
        utils.pressBackButton();
    }

}