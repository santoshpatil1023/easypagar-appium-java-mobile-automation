package page.admin.sidebarMenu;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import utils.TestUtils;

public class AdminSidebarMenuPage {
    private AndroidDriver driver;
    private TestUtils utils;

    // Sidebar Elements
    private By menuButton = AppiumBy.xpath("//android.widget.TextView[@text='󰚈']");
    private By editProfile = AppiumBy.xpath("//android.widget.TextView[@text='Edit Profile']");
    private By createBranch = AppiumBy.xpath("  //android.widget.TextView[@text='Create Branch']");
    private By createDepartment = AppiumBy.xpath("//android.widget.TextView[@text='Create Department']");
    private By role = AppiumBy.xpath("//android.widget.TextView[@text='Role']");
    private By Subscription_Addons = AppiumBy.xpath("//android.widget.TextView[@text='Subscription/Addons']");
    private By Settings = AppiumBy.xpath("//android.widget.TextView[@text='Settings']");
    private By help_videos = AppiumBy.xpath("//android.widget.TextView[@text='Help videos']");
    private By Support = AppiumBy.xpath("//android.widget.TextView[@text='Support']");
    
    private By referral_code = AppiumBy.xpath("//android.widget.TextView[@text='Referral Code']");
    private By map_to_Tracker = AppiumBy.xpath("//android.widget.TextView[@text='Map To Easy Tracker']");
    private By rate_us = AppiumBy.xpath("//android.widget.TextView[@text='Rate Us']");
    private By logout = AppiumBy.xpath("//android.widget.Button[@content-desc=', Log Out']");
    
  
    private By alertPopup = AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/message']");
    private By logoutConfirmYes = AppiumBy.xpath("//android.widget.Button[@resource-id='android:id/button1']");  

    public AdminSidebarMenuPage(AndroidDriver driver) {
        this.driver = driver;
        this.utils = new TestUtils(driver);
    }

    // Sidebar Operations
    public void openSidebar() {
        utils.clickWhenClickable(menuButton, 10);
    }

    // Navigation Methods
    public void navigateToEditProfile() {
        utils.clickWhenClickable(editProfile, 10);
    }
    
    public void navigateToCreateBranch() {
        utils.clickWhenClickable(createBranch, 10);
    }

    public void selectLogoutOption() {
        System.out.println("Attempting to click Logout button in sidebar...");

        if (utils.isElementPresent(logout, 2)) {
        	utils.clickWhenClickable(logout, 5);
            System.out.println("Logout clicked directly");
            return;
        }
        
        System.out.println("Scrolling sidebar UP...");
        for (int i = 0; i < 3; i++) {
        	utils.scrollUp();
        	utils.scrollSidebarUp();
            if (utils.isElementPresent(logout, 2)) {
            	utils.clickWhenClickable(logout, 5);
                System.out.println("✅ Logout clicked after DOWN scroll");
                return;
            }
        }
        
        System.out.println("Scrolling sidebar DOWN...");
        for (int i = 0; i < 3; i++) {
//        	utils.scrollDown();
        	utils.scrollSidebarDown();
            if (utils.isElementPresent(logout, 2)) {
            	utils.clickWhenClickable(logout, 5);
                System.out.println("✅ Logout clicked after UP scroll");
                return;
            }
        }
        
        System.out.println("❌ Logout not found even after both DOWN and UP scrolling");
    }

    public boolean scrollUntilLogoutFound(int maxScrolls) {
        for (int i = 0; i < maxScrolls; i++) {

            if (utils.isElementPresent(logout, 2)) {
                return true;
            }

            utils.scrollDown(); // coordinate-based scroll
        }
        return false;
    }
    
    public void clickLogout() {
        System.out.println("Attempting to click Logout button in sidebar...");

        boolean found = scrollUntilLogoutFound(5);

        if (!found) {
            throw new RuntimeException("❌ Logout not found after scrolling");
        }

        utils.clickWhenClickable(logout, 5);
        System.out.println("✅ Logout clicked successfully");
    }

	public boolean isLogoutConfirmationDisplayed() {
        return utils.isElementPresent(alertPopup, 5);

	}

    public void confirmLogout() {
        if (utils.isElementPresent(alertPopup, 5)) {
        	utils.clickWhenClickable(logoutConfirmYes, 10);
        }
    }

 }