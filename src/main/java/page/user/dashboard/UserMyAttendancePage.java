package page.user.dashboard;

import org.openqa.selenium.By;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import utils.TestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserMyAttendancePage {
    private static final Logger logger = LoggerFactory.getLogger(UserMyAttendancePage.class);

    private AndroidDriver driver;
    private TestUtils utils;

    // Locators
    private By locationAllowButtonLocator = AppiumBy.xpath("//android.widget.Button[@resource-id='android:id/button1']");
    private By attendancePage = AppiumBy.xpath("//android.view.View[@text='Attendance']");
    private By attendanceDetailsClick = AppiumBy.xpath("//android.widget.TextView[@text='Attendance Details']");
    private By attendanceDetailsView = AppiumBy.androidUIAutomator("new UiSelector().text(\"Attendance Details\")");

    private By checkInButton1 = AppiumBy.xpath("//android.widget.TextView[@text='Check In']");
    private By checkInButton = AppiumBy.androidUIAutomator("new UiSelector().description(\"Check In\")");
    
    private By checkInOutCameraIconAction = AppiumBy.xpath("//android.widget.TextView[@text='']");
    private By punchCheckInButton = AppiumBy.xpath("//android.widget.TextView[@text='Punch']");
  
    
//    private By checkOutButton = AppiumBy.accessibilityId("Check Out");
    private By checkOutButton = AppiumBy.xpath("//android.widget.TextView[@text='Check Out']");
   

    private By cameraShutter1 = AppiumBy.xpath("new UiSelector().className(\"android.view.ViewGroup\").instance(40)");
    private By cameraShutter2 = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[12]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup");
    private By cameraShutter = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[11]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup");

    
    private By cameraConfirm1 = AppiumBy.xpath(
  "//android.widget.TextView[@text='']"
    );
    private By cameraConfirm = AppiumBy.androidUIAutomator("new UiSelector().description(\"\")");
    
    private By popupOkButton1 = AppiumBy.accessibilityId("Ok");
    private By popupOkButton = AppiumBy.androidUIAutomator("new UiSelector().description(\"Ok\")");
    
    private By checkOutPopupMessage1 = AppiumBy.xpath("//android.widget.TextView[@text='Your Trying To  Leave Early , Please Contact Your  Manager']");
    private By checkOutPopupMessage2 = AppiumBy.xpath("  //android.widget.TextView[@text='Organization appreciates your extra efforts. Good work!']");

    private By backAttenadce = AppiumBy.androidUIAutomator("new UiSelector().text(\"Back\")");

    // Time only → "09:15 AM"
    private By checkInTimeOnly1 = AppiumBy.xpath(
            "//android.widget.TextView[contains(@text, 'AM') or contains(@text, 'PM')]");

    private By checkInTimeOnly = AppiumBy.xpath(
            "//android.widget.TextView[@text='First Half']/following::android.widget.TextView[contains(@text,'AM') or contains(@text,'PM')][1]"
        );


    private By checkInTimeDynamic = AppiumBy.xpath("//android.widget.TextView[contains(@text, ':') and (contains(@text,'AM') or contains(@text,'PM'))]");

    
    // Constructor
    public UserMyAttendancePage(AndroidDriver driver) {
        this.driver = driver;
        this.utils = new TestUtils(driver);
    }
    
    public void handleLocationEnablePopup() {
        if (utils.isElementPresent(locationAllowButtonLocator, 5)) {
            utils.clickWhenClickable(locationAllowButtonLocator, 5);
            logger.info("Location permission allowed");
        }
    }
    
    public boolean isAttendanceDetailsDisplayed() {
        return utils.isElementPresent(attendanceDetailsView, 10);
    }
    
    public boolean isAlreadyCheckedIn() {
        // If checkout button is visible, user is already checked in
        if (utils.isElementPresent(checkOutButton, 5)) {
            System.out.println("ℹ User already checked in.");
            return true;
        }

        // If check-in button is visible → Not checked-in
        if (utils.isElementPresent(checkInButton, 5)) {
            return false;
        }

        // Neither button found → page not loaded properly
        throw new RuntimeException("❌ Attendance screen not loaded → No Check-In/Check-Out button found.");
    }

    
    public String getCheckInTimeValue() {
        return utils.getElementText(checkInTimeOnly, 10);
    }
    
    public boolean isCheckInWithinFiveMinutes() {
        try {
            String checkInTimeStr = getCheckInTimeValue();
            if (checkInTimeStr.equals("No check-in time found")) {
                return false;
            }
            
            // Parse the check-in time (assuming format like "11:13 AM 27-11-2025")
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a dd-MM-yyyy");
            // Extract time part for simplicity (you might need to adjust based on actual format)
            String timePart = checkInTimeStr.split(" ")[0] + " " + checkInTimeStr.split(" ")[1];
            LocalTime checkInTime = LocalTime.parse(timePart, DateTimeFormatter.ofPattern("hh:mm a"));
            LocalTime currentTime = LocalTime.now();
            
            Duration duration = Duration.between(checkInTime, currentTime);
            return duration.toMinutes() <= 5;
            
        } catch (Exception e) {
            logger.warn("Could not parse check-in time, assuming it's within 5 minutes: " + e.getMessage());
            return true; // Default to safe assumption
        }
    }
    
    public long getRemainingWaitTimeForCheckOut() {
        try {
            String checkInTimeStr = getCheckInTimeValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a dd-MM-yyyy");
            String timePart = checkInTimeStr.split(" ")[0] + " " + checkInTimeStr.split(" ")[1];
            LocalTime checkInTime = LocalTime.parse(timePart, DateTimeFormatter.ofPattern("hh:mm a"));
            LocalTime currentTime = LocalTime.now();
            
            Duration duration = Duration.between(checkInTime, currentTime);
            long minutesPassed = duration.toMinutes();
            long remainingMinutes = 5 - minutesPassed;
            
            return remainingMinutes > 0 ? remainingMinutes * 60000 : 0; // Convert to milliseconds
        } catch (Exception e) {
            logger.warn("Could not calculate remaining time, returning default 5 minutes");
            return 300000; // Default 5 minutes
        }
    }
    
    public void clickForCheckInButton() {

        if (utils.isElementPresent(checkInButton, 10)) {
            utils.clickWhenClickable(checkInButton, 10);
            logger.info("Clicked Check-in button");
        } 
        	
    }
    
    public void clickCheckOutButton() {
        if (utils.isElementPresent(checkOutButton, 10)) {
            utils.clickWhenClickable(checkOutButton, 10);
            logger.info("Clicked Check-out button");
        }
    }

    public boolean canCheckOut() {
        return utils.isElementPresent(checkOutButton, 5);
    }


    
    public void takePhoto() {
        utils.clickWhenClickable(cameraShutter, 10);
        logger.info("Photo taken using camera");
    }
    
    public void confirmPhoto() {
        utils.clickWhenClickable(cameraConfirm, 10);
        logger.info("Photo confirmed");
    }
    
    public void confirmPopup() {
        utils.clickWhenClickable(popupOkButton, 20);
        logger.info("Popup confirmed");
    }
    
    public String getCheckOutPopupMessageImmaieate() {
        return utils.getElementText(checkOutPopupMessage1, 10);
    }

	public String getCheckOutPopupMessage() {
        return utils.getElementText(checkOutPopupMessage2, 10);
	}

	public void clickAttendancedetails() {
        utils.clickWhenClickable(attendanceDetailsClick, 10);		
	}
	
	public void backTodashBoard() {
//        utils.clickWhenClickable(backAttenadce, 0);	
//      utils.pressBackButton();	
        ((AndroidDriver) driver).navigate().back();
//
        utils.pressBackButton();	
	}

    // Method 1: Using AndroidDriver back()
    public void pressBackButton() {
//        utils.pressBackButton();	
//        Thread.sleep(1000);
        ((AndroidDriver) driver).navigate().back();        
//        logger.info("Pressed back button using navigate().back()");
    }

}