package page.user.dashboard;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import utils.TestUtils;

public class UserMessagePage {
    private AndroidDriver driver;
    private TestUtils utils;

    // Locators
    private By messagesHeader = AppiumBy.xpath("//android.view.View[@text='Messages']");

    private By messageSearchBarLocator = AppiumBy.xpath("//android.widget.EditText[@resource-id='text-input-outlined']");
    private By firstMessageViewOnList = AppiumBy.xpath("(//android.view.ViewGroup[@resource-id='card'])[1]");
    private By noDataFoundLocator = AppiumBy.xpath("//android.widget.TextView[@text=\"No Data Found\"]");
    private By clickAndReplayLocator = AppiumBy.xpath("//android.widget.EditText[@resource-id='text-input-outlined']");

    // Constructor
    public UserMessagePage(AndroidDriver driver) {
        this.driver = driver;
        this.utils = new TestUtils(driver);
    }
    
    public boolean isMessagesPageDisplayed() {
        return utils.isElementPresent(messagesHeader, 10);
    }

    // Actions
    public boolean messageSearchBar() {
        return utils.isElementPresent(messageSearchBarLocator, 10);
    }

    public boolean hasMessages() {
        return utils.isElementPresent(firstMessageViewOnList, 5);
    }

    public boolean hasNoDataFound() {
        return utils.isElementPresent(noDataFoundLocator, 5);
    }

    public void clickOnFirstMessage() {
        // Check if "No Data Found" is visible first
        if (utils.isElementPresent(noDataFoundLocator, 5)) {
            System.out.println("No messages found - 'No Data Found' displayed. Ending test flow successfully.");
//            Assert.assertTrue(true, "'No Data Found' displayed - expected scenario, marking test as PASS.");
            return; // stop gracefully
        }

        // If messages exist → then click
        if (hasMessages()) {
            utils.clickWhenClickable(firstMessageViewOnList, 10);
        } else {
            throw new RuntimeException("Neither messages nor 'No Data Found' displayed!");
        }
    }

    public void clickAndReplay() {
        utils.clickWhenClickable(clickAndReplayLocator, 10);
    }
    
    // Send button locators (ordered by likelihood)
    private By[] sendButtonLocators = {
        AppiumBy.accessibilityId(""),
        AppiumBy.xpath("//*[@content-desc='']"),
        AppiumBy.xpath("//*[@text='']"),
        AppiumBy.xpath("//android.view.ViewGroup[contains(@content-desc, '')]"),
        AppiumBy.xpath("//android.widget.EditText[@text='Write a Message']/following-sibling::android.view.ViewGroup"),
        AppiumBy.xpath("//*[contains(@content-desc, 'send') or contains(@text, 'send')]"),
        AppiumBy.xpath("//*[@clickable='true' and .//*[@text='']]")
    };

//    public void clickOnSend() {
//        utils.clickWhenClickable(sendBtn, 10);
//    }
    
    public void sendMessage(String message) {
        utils.sendKeys(clickAndReplayLocator, message, 5);
        
        if (!clickSendButton()) {
        	System.out.println("using enter");
            utils.pressEnter(); 
        }
    }
    
    public boolean clickSendButton() {
        for (By locator : sendButtonLocators) {
            if (utils.isElementPresent(locator, 1)) {
                try {
                    utils.clickWhenClickable(locator, 3);
                    System.out.println("Send button clicked using: " + locator);
                    return true;
                } catch (Exception e) {
                    System.out.println("Failed to click with locator: " + locator);
                }
            }
        }
        return false;
    }

    // Method to check if we're on messages page with either messages or no data
    public boolean isOnMessagesPage() {
        return messageSearchBar() && (hasMessages() || hasNoDataFound());
    }
    
    private By back1 = AppiumBy.androidUIAutomator("new UiSelector().text(\"\")");

	public void back1() {
      utils.clickWhenClickable(back1, 0);	
	}
		public void backTodashBoard() {
//      utils.clickWhenClickable(backAttenadce, 0);	
      ((AndroidDriver) driver).navigate().back();

//      utils.pressBackButton();	
	}
}