package page.user.dashboard;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import utils.TestUtils;

public class UserApplyLoanAdvancePage {
    private AndroidDriver driver;
    private TestUtils utils;

    // Locators
    private By applyLoanAdvanceHeader = AppiumBy.androidUIAutomator("new UiSelector().text(\"Apply Loan/Advance\")");
    private By clickFab = AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"fab-content\"]");
    private By cickDropdown = AppiumBy.xpath("//android.widget.TextView[@text=\"Select Type *\"]");
    private By amountField = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"text-input-outlined\").instance(0)");
    private By detailsField = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"text-input-outlined\").instance(1)");
    private By submit = AppiumBy.androidUIAutomator("new UiSelector().description(\"Submit\")");
    private By loanApplied = AppiumBy.androidUIAutomator("new UiSelector().text(\"Loan Request Created Successfully\")");
    private By advanceApplied = AppiumBy.androidUIAutomator("new UiSelector().text(\"Advance Request Created Successfully\")");
    private By subOK = AppiumBy.androidUIAutomator("new UiSelector().description(\"Ok\")");

    // Constructor
    public UserApplyLoanAdvancePage(AndroidDriver driver) {
        this.driver = driver;
        this.utils = new TestUtils(driver);
    }
    
    // Actions
    public boolean isApplyLoanAdvanceDisplayed() {
        return utils.isElementPresent(applyLoanAdvanceHeader, 10);
    }

	public void clickApplyExpenseButton() {
        utils.clickWhenClickable(clickFab, 10);
	}

	public boolean isExpenseApplyLoanAdvanceFormDisplayed() {
        return utils.isElementPresent(applyLoanAdvanceHeader, 10);
	}
	
	public void clickDropDownToChoose() {
        utils.clickWhenClickable(cickDropdown, 10);
	}
	
    private By chooseAdvance = AppiumBy.androidUIAutomator("new UiSelector().text(\"Advance\")");
    private By chooseLoan = AppiumBy.androidUIAutomator("new UiSelector().text(\"Loan\")");
	
	public void selectLoanType(String type) {
	      utils.clickWhenClickable(AppiumBy.xpath("//android.widget.TextView[@text='" + type+ "']"), 10);
	}
	
	public void enterAmount(String amount) {
        utils.sendKeys(amountField, amount, 10);
    }
	

    public void enterDetails(String reason) {
        utils.sendKeys(detailsField, reason, 10);
    }

	public boolean isLoanApplied() {
        return utils.isElementPresent(loanApplied, 10);
	}
    
	public boolean isAdvanceApplied() {
        return utils.isElementPresent(advanceApplied, 10);
	}
    
    public void clickSubmitButton() {
        utils.clickWhenClickable(submit, 10);
    }
    
    public void clickOkSuccessfully() {
        utils.clickWhenClickable(subOK, 20);
    }
    
	public void navigateBackToDashboard() {
      utils.pressBackButton();		
     }

}