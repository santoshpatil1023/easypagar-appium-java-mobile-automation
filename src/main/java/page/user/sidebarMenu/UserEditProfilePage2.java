package page.user.sidebarMenu;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import utils.TestUtils;

public class UserEditProfilePage2 {
    private AndroidDriver driver;
    private TestUtils testUtils;

    // Constructor
    public UserEditProfilePage2(AndroidDriver driver) {
        this.driver = driver;
        this.testUtils = new TestUtils(driver);
    }

    // Locators for Sidebar Menu
    private By editprofilePage = AppiumBy.xpath("//android.view.View[@text='Edit Profile']");

    private By guardianNameInput = AppiumBy.xpath("(//android.widget.EditText[@resource-id='text-input-outlined'])[3]");
    private By updateButton = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Update']");
    private By saveConfermationMsg = AppiumBy
            .xpath("//android.widget.TextView[@resource-id='com.pagarplus.app:id/snackbar_text']");

    private By EmailIdInput = AppiumBy.xpath("(//android.view.ViewGroup[@resource-id='text-input-outline'])[4]");
    private By EducationInput = AppiumBy.xpath("(//android.view.ViewGroup[@resource-id='text-input-outline'])[6]");

    // Actions
    public boolean isEditProfileDisplayed() {
        return testUtils.isElementPresent(editprofilePage, 5);
    }

    public void updateGuardianName(String guardianName) {
        System.out.println("Updating Guardian Name to: " + guardianName);

        testUtils.waitForVisible(guardianNameInput, 10);
        testUtils.clickWhenClickable(guardianNameInput, 10);
        driver.findElement(guardianNameInput).clear();
        testUtils.sendKeys(guardianNameInput, guardianName, 5);
        testUtils.pressBackButton();
    }

    public boolean isSaveConfirmationVisible() {
        return testUtils.isElementPresent(saveConfermationMsg, 10);
    }

    public void updateEmailId(String newEmail) {
        testUtils.sendKeys(EmailIdInput, newEmail, 10);
        testUtils.pressBackButton();
    }

    public void education(String education) {
        testUtils.sendKeys(EducationInput, education, 10);
        testUtils.pressBackButton();
    }

    public void clickUpdate() {
        System.out.println("Attempting to click Update button...");

        // First try to click directly
        if (testUtils.isElementPresent(updateButton, 2)) {
            System.out.println("Update button is visible, clicking directly...");
            testUtils.clickWhenClickable(updateButton, 10);
            System.out.println("Update button clicked");
            return;
        }

        System.out.println("scroll to text: 'Update'");
        testUtils.scrollToText("Update", 2);

        if (testUtils.isElementPresent(updateButton, 2)) {
            testUtils.clickWhenClickable(updateButton, 10);
            System.out.println("✅ Update button clicked after text scroll");
        } else {
            System.out.println("❌ Update button not found even after scrolling");
        }

        System.out.println("Update button not visible, scrolling to find it...");

        for (int i = 1; i <= 3; i++) {
            if (testUtils.isElementPresent(updateButton, 1)) {
                testUtils.clickWhenClickable(updateButton, 10);
                System.out.println("✅ Update button clicked after scroll");
                return;
            }
            testUtils.scrollDown();
        }

    }

}