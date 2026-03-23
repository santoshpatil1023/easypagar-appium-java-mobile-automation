package page.user.dashboard;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import utils.TestUtils;

public class UserDutyRoasterPage {
    private AndroidDriver driver;
    private TestUtils utils;

    // Locators
    private By dutyRoasterHeader = AppiumBy.xpath("//android.view.View[@text=\"Duty Roster\"]");
    
    // Dropdown selectors (using text we can see)
    private By rosterTypeDropdown = AppiumBy.xpath("(//android.widget.TextView[@text=\"\"])[1]");
    private By monthDropdown = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(33)");
    private By viewFormatDropdownWhenforWeek = AppiumBy.xpath("(//android.widget.TextView[@text=\"\"])[4]");
    private By viewFormatDropdownWhenforMonth = AppiumBy.xpath("(//android.widget.TextView[@text=\"\"])[3]");
    private By weekDropdown = AppiumBy.xpath("(//android.widget.TextView[@text=\"\"])[2]");
    
    private By tableview = AppiumBy.xpath("(//android.view.ViewGroup[@resource-id=\"card-container\"])[1]");
    private By calenderview = AppiumBy.xpath("//android.widget.TextView[@text=\"F\"]");


    // Constructor
    public UserDutyRoasterPage(AndroidDriver driver) {
        this.driver = driver;
        this.utils = new TestUtils(driver);
    }
    
    public boolean isDutyRoasterPageDisplayed() {
        return utils.isElementPresent(dutyRoasterHeader, 10);
    }

    // Actions
	public void clickDutyRoasterDropDown() {
        utils.clickWhenClickable(rosterTypeDropdown, 10);
	}
	
	public void selectRosterType(String type) {
	      utils.clickWhenClickable(AppiumBy.xpath("//android.widget.TextView[@text='" + type + "']"), 10);
	}
	
	public void clickMonthDropDown() {
        utils.clickWhenClickable(monthDropdown, 10);
	}
	
	public void selectMonth(String type) {
	      utils.clickWhenClickable(AppiumBy.xpath("//android.widget.TextView[@text='" + type + "']"), 10);
	}

	public void clickToSelectViewFormatforMonth() {
        utils.clickWhenClickable(viewFormatDropdownWhenforMonth, 10);
	}
	
	public void clickToSelectViewFormatForWeek() {
        utils.clickWhenClickable(viewFormatDropdownWhenforWeek, 10);
	}
	
	public void selectViewType(String type) {
	      utils.clickWhenClickable(AppiumBy.xpath("//android.widget.TextView[@text='" + type + "']"), 10);
	}	

	public void clickToSelectWeek() {
        utils.clickWhenClickable(weekDropdown, 10);
	}
	
	public void selectWeek(String week) {
	      utils.clickWhenClickable(AppiumBy.xpath("//android.widget.TextView[@text='" + week + "']"), 10);
	}
	
    public boolean isCalendarViewDisplayed() {
        return utils.isElementPresent(calenderview, 10);
    }
    
    public boolean isTableViewDisplayed() {
        return utils.isElementPresent(tableview, 10);
    }
    
    public boolean isWeeklyViewDisplayed() {
        return utils.isElementPresent(calenderview, 10);
    }
    
    public boolean isWeeklyTableViewDisplayed() {
        return utils.isElementPresent(tableview, 10);
    }

	public void navigateBackToDashboard() {
	      utils.pressBackButton();		
	}    


}