package page.admin.sidebarMenu;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.TestUtils;

public class AdminCreateBranchPage {
    private AndroidDriver driver;
    private TestUtils utils;

    // Branch Page Elements
    private By branchHeader = AppiumBy.xpath("//android.view.View[@text='Branch']");
    private By searchField = AppiumBy.xpath("//android.widget.EditText[@resource-id='text-input-outlined']");
    private By createNewBranchButton = AppiumBy.xpath("//android.widget.TextView[@text='']");

    // Branch List Items
    private By clickFirstBranchEdit = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=', Edit'])[1]");    
    private By accessDeniedPopup = AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/message']");
    private By okBtnforAccessDenied = AppiumBy.xpath("//android.widget.Button[@resource-id='android:id/button1']");
    
    public AdminCreateBranchPage(AndroidDriver driver) {
        this.driver = driver;
        this.utils = new TestUtils(driver);
    }

    public boolean isAdminCreateBranchDisplayed() {
        return utils.isElementPresent(branchHeader, 15);
    }
    
    
    public void searchBranch(String branchName) {
        utils.sendKeys(searchField, branchName, 10);
    }
    
    public boolean isBranchPresent(String branchName) {
        By branchLocator = AppiumBy.xpath("//android.widget.TextView[contains(@text, '" + branchName + "')]");
        return utils.isElementPresent(branchLocator, 5);
    }
    
    
    public void selectFirstBranch() {
        utils.clickWhenClickable(clickFirstBranchEdit, 10);
    }
   
    public void clickOkOnAccessDeniedPopup() {        
        // Only click YES if popup is displayed
        if (utils.isElementPresent(okBtnforAccessDenied, 5)) {
            utils.clickWhenClickable(okBtnforAccessDenied, 5);
        }
    }

    
    public void clickCreateNewBranchButton() {
        utils.clickWhenClickable(createNewBranchButton, 10);
    }
    

}