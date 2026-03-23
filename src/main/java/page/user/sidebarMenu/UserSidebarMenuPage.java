package page.user.sidebarMenu;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import utils.TestUtils;
import utils.UserTestUtilsScroles;

public class UserSidebarMenuPage {
    private AndroidDriver driver;
    private TestUtils testUtils;
    private UserTestUtilsScroles scrolUtils;

    // Constructor
    public UserSidebarMenuPage(AndroidDriver driver) {
        this.driver = driver;
        this.testUtils = new TestUtils(driver);
        this.scrolUtils = new UserTestUtilsScroles(driver);
    }

    // Locators for Sidebar Menu
    private By sidebarMenuButton = AppiumBy.xpath("//android.widget.Button/android.widget.ImageView");    
    private By editProfile = AppiumBy.xpath("//android.widget.TextView[@text=\"Edit Profile\"]");    
    private By myAsset = AppiumBy.xpath("//android.widget.TextView[@text='My Asset']");    
    private By trainee = AppiumBy.xpath("//android.widget.TextView[@text='Trainee']");    
  
    private By helpVideos = AppiumBy.xpath("//android.widget.TextView[@text='Help videos']");    

    private By logoutOption = AppiumBy.xpath("//android.widget.TextView[@text='Log Out']");  
    private By logoutConfirmYes = AppiumBy.id("android:id/button1");  
    private By alertPopup = AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/message']");
  
    // Actions
    public void openSidebarMenu() {
    	testUtils.clickWhenClickable(sidebarMenuButton, 10);
    }

    public void selectEditProfile() {
    	testUtils.clickWhenClickable(editProfile, 10);
    }
    
    public void selectMyAsset() {
    	testUtils.clickWhenClickable(myAsset, 10);
    }
    
    public void selectTrainee() {
    	testUtils.clickWhenClickable(trainee, 10);
    }
    
    public void selectHelpVideos() {
    	testUtils.clickWhenClickable(helpVideos, 10);
    }
    
    public void selectLogoutOption() {
        System.out.println("Attempting to click Logout button in sidebar...");

//    	testUtils.clickWhenClickable(logoutOption, 5);
//        System.out.println("Logout clicked directly");
        
        if (testUtils.isElementPresent(logoutOption, 2)) {
        	testUtils.clickWhenClickable(logoutOption, 5);
            System.out.println("Logout clicked directly");
            return;
        }
        
        System.out.println("Scrolling sidebar UP...");
        for (int i = 0; i < 3; i++) {
        	scrolUtils.scrollSidebarUp();
            if (testUtils.isElementPresent(logoutOption, 2)) {
            	testUtils.clickWhenClickable(logoutOption, 5);
                System.out.println("✅ Logout clicked after UP scroll");
                return;
            }
        }
        
        System.out.println("Scrolling sidebar DOWN...");
        for (int i = 0; i < 3; i++) {
        	scrolUtils.scrollSidebarDown();
            if (testUtils.isElementPresent(logoutOption, 2)) {
            	testUtils.clickWhenClickable(logoutOption, 5);
                System.out.println("✅ Logout clicked after DOWN scroll");
                return;
            }
        }
        

        System.out.println("❌ Logout not found even after both DOWN and UP scrolling");
    }

    public void confirmLogout() {
        	testUtils.clickWhenClickable(logoutConfirmYes, 10);
    }

    public boolean isLogoutConfirmationDisplayed() {
        return testUtils.isElementPresent(alertPopup, 5);
    }
    
    public void logOutUtilMethod() {
    	openSidebarMenu();
    	selectLogoutOption();
    	confirmLogout();
    }
}