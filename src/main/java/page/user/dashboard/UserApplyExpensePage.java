package page.user.dashboard;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import utils.TestUtils;

public class UserApplyExpensePage {
    private AndroidDriver driver;
    private TestUtils utils;

    // Locators
    private By applyExpenseHeader = AppiumBy.androidUIAutomator("new UiSelector().text(\"My Expense\")");
    private By clickFab = AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"fab-content\"]");
    private By clickExpenseTypes1 = AppiumBy.androidUIAutomator("new UiSelector().text(\"Bus Expense\")");
    private By clickExpenseTypes2 = AppiumBy.androidUIAutomator("new UiSelector().text(\"Lunch Bill\")");
    private By paidLeave = AppiumBy.androidUIAutomator("new UiSelector().text(\"UnPaid Leave\")");
    
    private By imageUploadButton = AppiumBy.androidUIAutomator("new UiSelector().description(\", Upload Image\")");
    private By takePicture = AppiumBy.androidUIAutomator("new UiSelector().text(\"Take a Picture\")");
    private By chooseFromGallery = AppiumBy.androidUIAutomator("new UiSelector().text(\"Choose From a Gallery\")");
    private By image = AppiumBy.xpath("(//android.widget.ImageView[@resource-id=\"com.google.android.providers.media.module:id/icon_thumbnail\"])[1]");
    private By imageAdd = AppiumBy.xpath("//android.widget.Button[@resource-id=\"com.google.android.providers.media.module:id/button_add\"]");

    private By calender = AppiumBy.androidUIAutomator("new UiSelector().text(\"Date :\")");
    private By calOk = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"android:id/button1\")");

    private By amountField = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"text-input-outlined\")");
    private By submit = AppiumBy.androidUIAutomator("new UiSelector().description(\"Submit\")");
    private By smtPopUp = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"android:id/button1\")");
    private By addAnother = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"android:id/button2\")");
    private By subOK = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"Ok\"]");

    // Constructor
    public UserApplyExpensePage(AndroidDriver driver) {
        this.driver = driver;
        this.utils = new TestUtils(driver);
    }
    
    // Actions
    public boolean isApplyExpensePageDisplayed() {
        return utils.isElementPresent(applyExpenseHeader, 10);
    }

	public void clickApplyExpenseButton() {
        utils.clickWhenClickable(clickFab, 10);
	}

	public boolean isExpenseApplyFormDisplayed() {
        return utils.isElementPresent(calender, 10);
	}

	public void clickDatePicker() {
        utils.clickWhenClickable(calender, 10);
	}

	public void selectExpenseDate(String date) {
//	    String selector1 = "new UiSelector().resourceId(\"undefined.day_" + date + "\")";
	    String selector = "new UiSelector().text(\"" + date + "\")";
	    utils.clickWhenClickable(AppiumBy.androidUIAutomator(selector), 10);
	}
	
	public void clickDatePickerOk() {
        utils.clickWhenClickable(calOk, 10);
	}
	
	public void openExpenseTypeDropdown1() {
        utils.clickWhenClickable(clickExpenseTypes1, 10);
	}
	
	public void openExpenseTypeDropdown2() {
        utils.clickWhenClickable(clickExpenseTypes2, 10);
	}
	public void selectExpenseType(String expense) {
//        utils.clickWhenClickable(clickExpenseTypes, 10);
        utils.clickWhenClickable(AppiumBy.xpath("//android.widget.TextView[@text='" + expense + "']"), 10);
        // 🔑 Close dropdown after selecting employee
//        utils.pressBackButton();
	}
	
	public void enterAmount(String amount) {
        utils.sendKeys(amountField, amount, 10);
//        utils.pressBackButton();
    }
    
    
    private By resonField = AppiumBy.androidUIAutomator("new UiSelector().text(\" \")");

    public void enterReason(String reason) {
        utils.sendKeys(resonField, reason, 10);
//        utils.pressBackButton();
    }
	
    public void uploadReceiptFromGallery() {
	    utils.clickWhenClickable(imageUploadButton, 10);
	    utils.clickWhenClickable(chooseFromGallery, 10);
	    utils.clickWhenClickable(image, 10);
	    utils.clickWhenClickable(imageAdd, 10);    	    
    }

    
    public void clickSubmitButton() {
        utils.clickWhenClickable(submit, 10);
    }

	public void confirmSubmission() {
        utils.clickWhenClickable(smtPopUp, 10);		
	}
	
	public void addAnother() {
        utils.clickWhenClickable(addAnother, 10);		
	}
	
	public void clickSubmissionOk() {
  utils.clickWhenClickable(subOK, 10);
	}

	public void navigateBackToDashboard() {
//        utils.pressBackButton();		
        utils.pressBackButton();		
       }
	
    private By addedExpense = AppiumBy.androidUIAutomator("new UiSelector().text(\"Added Expenses\")");

	public boolean isFirstExpenseAdded() {
        return utils.isElementPresent(addedExpense, 10);
	}	        
}