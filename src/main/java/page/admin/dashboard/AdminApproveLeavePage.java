package page.admin.dashboard;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import utils.TestUtils;

public class AdminApproveLeavePage {
	private AndroidDriver driver;
	private TestUtils utils;

	// Locators for Admin Messages
	private By approveLeaveHeader = AppiumBy.xpath("//android.view.View[@text=\"Approve Leave\"]");
	private By successMessage = AppiumBy.xpath("//android.widget.TextView[@text='Banner Created Successfully']");

	public AdminApproveLeavePage(AndroidDriver driver) {
		this.driver = driver;
		this.utils = new TestUtils(driver);
	}

	public boolean isApproveLeaveHeaderPageDisplayed() {
		return utils.isElementPresent(approveLeaveHeader, 10);
	}

}