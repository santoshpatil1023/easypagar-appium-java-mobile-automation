package page.admin.sidebarMenu;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.TestUtils;

public class AdminEditProfilePage {
    private AndroidDriver driver;
    private TestUtils utils;

    // Edit Profile Page Elements
    private By editProfileHeader = AppiumBy.xpath("//android.view.View[@text='Edit Profile']");
    
    // Form fields
    private By nameField = AppiumBy.xpath("//android.widget.EditText[@text='Enter Name']");
    private By emailField = AppiumBy.xpath("//android.widget.EditText[@text='Enter Email']");
    private By updateButton = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Update']");
    private By successMessage = AppiumBy.xpath("//android.widget.TextView[@resource-id='com.pagarplus.app:id/snackbar_text']");

    public AdminEditProfilePage(AndroidDriver driver) {
        this.driver = driver;
        this.utils = new TestUtils(driver);
    }

    public boolean isAdminEditProfileDisplayed() {
        return utils.isElementPresent(editProfileHeader, 15);
    }

    public void clickUpdate() {
        System.out.println("Attempting to click Update button...");
        
        // First try to click directly
        if (utils.isElementPresent(updateButton, 2)) {
            System.out.println("Update button is visible, clicking directly...");
            utils.clickWhenClickable(updateButton, 10);
            System.out.println("Update button clicked");
            return;
        }
        
        System.out.println("Update button not visible, scrolling to find it...");
        
        for (int i = 1; i <= 3; i++) {
            if (utils.isElementPresent(updateButton, 1)) {
                utils.clickWhenClickable(updateButton, 10);
                System.out.println("✅ Update button clicked after scroll");
                return;
            }
            utils.scrollDown();
        }
        
        utils.scrollToText("Update", 2);
        
        if (utils.isElementPresent(updateButton, 2)) {
            utils.clickWhenClickable(updateButton, 10);
            System.out.println("✅ Update button clicked after text scroll");
        } else {
            System.out.println("❌ Update button not found even after scrolling");
        }
    }
    
    public boolean isUpdateSuccessful() {
        boolean success = utils.isElementPresent(successMessage, 5);
        if (success) {
            System.out.println("✅ Profile update successful - success message found!");
        } else {
            System.out.println("⚠️ No success message found, but update may still have completed");
        }
        return success;
    }

    // Optional: If you need to update fields later
    public void updateName(String name) {
        if (utils.isElementPresent(nameField, 10)) {
            utils.sendKeys(nameField, name, 10);
        }
    }

    public void updateEmail(String email) {
        if (utils.isElementPresent(emailField, 10)) {
            utils.sendKeys(emailField, email, 10);
        }
    }
}