package page.user.sidebarMenu;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.TestUtils;

public class UserEditProfilePage {
	private static final Logger logger = LoggerFactory.getLogger(UserEditProfilePage.class);
	private AndroidDriver driver;
	private TestUtils testUtils;

	// Constructor
	public UserEditProfilePage(AndroidDriver driver) {
		this.driver = driver;
		this.testUtils = new TestUtils(driver);
	}

	// Locators for Sidebar Menu
	private By editprofilePage = AppiumBy.androidUIAutomator("new UiSelector().text(\"Edit Profile\")");

	private By guardianNameInput = AppiumBy.xpath("(//android.widget.EditText[@resource-id='text-input-outlined'])[3]");
	private By updateButton = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Update']");
	private By saveConfermationMsg = AppiumBy
			.xpath("//android.widget.TextView[@resource-id='com.pagarplus.app:id/snackbar_text']");

	private By EmailIdInput = AppiumBy.xpath("(//android.view.ViewGroup[@resource-id='text-input-outline'])[4]");
	private By EducationInput = AppiumBy.xpath("(//android.view.ViewGroup[@resource-id='text-input-outline'])[6]");

	// Actions
	public boolean isEditProfileDisplayed() {
		return testUtils.isElementPresent(editprofilePage, 5);
	}

	public void updateGuardianName(String guardianName) {
		System.out.println("Updating Guardian Name to: " + guardianName);

		testUtils.waitForVisible(guardianNameInput, 10);
		testUtils.clickWhenClickable(guardianNameInput, 10);
		driver.findElement(guardianNameInput).clear();
		testUtils.sendKeys(guardianNameInput, guardianName, 5);
		testUtils.pressBackButton();
	}

	public boolean isSaveConfirmationVisible() {
		return testUtils.isElementPresent(saveConfermationMsg, 10);
	}

	public void updateEmailId(String newEmail) {
		testUtils.sendKeys(EmailIdInput, newEmail, 10);
		testUtils.pressBackButton();
	}

	public void education(String education) {
		testUtils.sendKeys(EducationInput, education, 10);
		testUtils.pressBackButton();
	}

	public void clickUpdate() {
		System.out.println("Attempting to click Update button...");

		// First try to click directly
		if (testUtils.isElementPresent(updateButton, 2)) {
			System.out.println("Update button is visible, clicking directly...");
			testUtils.clickWhenClickable(updateButton, 10);
			System.out.println("Update button clicked");
			return;
		}

		System.out.println("scroll to text: 'Update'");
		testUtils.scrollToText("Update", 2);

		if (testUtils.isElementPresent(updateButton, 2)) {
			testUtils.clickWhenClickable(updateButton, 10);
			System.out.println("✅ Update button clicked after text scroll");
		} else {
			System.out.println("❌ Update button not found even after scrolling");
		}

		System.out.println("Update button not visible, scrolling to find it...");

		for (int i = 1; i <= 3; i++) {
			if (testUtils.isElementPresent(updateButton, 1)) {
				testUtils.clickWhenClickable(updateButton, 10);
				System.out.println("✅ Update button clicked after scroll");
				return;
			}
			testUtils.scrollDown();
		}

	}

	public void selectDate(String date) {
		String selector = "new UiSelector().resourceId(\"undefined.day_" + date + "\")";
		testUtils.clickWhenClickable(AppiumBy.androidUIAutomator(selector), 10);
	}

	public void selectGender(String gender) {
		String uiSelector = "new UiSelector().text(\"" + gender + "\")";
		testUtils.clickWhenClickable(AppiumBy.androidUIAutomator(uiSelector), 10);
		logger.info("Selected gender: " + gender);
	}

	// public void selectDateOfBirth(String year) {
	// String dateOfBirth = "new UiSelector().text(\"Date of Birth *\")";
	// testUtils.clickWhenClickable(AppiumBy.androidUIAutomator(dateOfBirth), 10);
	// String yearDropDown = "new
	// UiSelector().resourceId(\"android:id/date_picker_header_year\")";
	// testUtils.clickWhenClickable(AppiumBy.androidUIAutomator(yearDropDown), 10);
	// String select2000year = "new UiSelector().text(\"" + year + "\")";
	// testUtils.clickWhenClickable(AppiumBy.androidUIAutomator(select2000year),
	// 10);
	// }

	// public void selectDateOfBirth(String year) {
	// // Click Date of Birth
	// String dateOfBirth = "new UiSelector().text(\"Date of Birth *\")";
	// testUtils.clickWhenClickable(AppiumBy.androidUIAutomator(dateOfBirth), 10);
	//
	// // Open year selector
	// String yearDropDown = "new
	// UiSelector().resourceId(\"android:id/date_picker_header_year\")";
	// testUtils.clickWhenClickable(AppiumBy.androidUIAutomator(yearDropDown), 10);
	//
	// // Keep scrolling until we find the year, then click it
	// boolean yearFound = false;
	// int maxScrolls = 20;
	//
	// for (int i = 0; i < maxScrolls; i++) {
	// // Try to click the year if visible
	// try {
	// String yearSelector = "new UiSelector().text(\"" + year + "\")";
	// WebElement yearElement =
	// driver.findElement(AppiumBy.androidUIAutomator(yearSelector));
	// yearElement.click();
	// yearFound = true;
	// break;
	// } catch (Exception e) {
	// // Year not visible yet, scroll up
	// scrollUpOnce();
	// }
	// }
	//
	// if (!yearFound) {
	// throw new RuntimeException("Could not find year " + year + " after " +
	// maxScrolls + " scrolls");
	// }
	// }
	//
	// private void scrollUpOnce() {
	// Dimension size = driver.manage().window().getSize();
	//
	// // Simple scroll: drag from middle to bottom (scrolls list up)
	// new TouchAction<>(driver)
	// .press(PointOption.point(size.width/2, size.height/2))
	// .waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
	// .moveTo(PointOption.point(size.width/2, size.height - 100))
	// .release()
	// .perform();
	//
	// // Wait for scroll to complete
	// try {
	// Thread.sleep(300);
	// } catch (InterruptedException e) {
	// Thread.currentThread().interrupt();
	// }
	// }

	public void selectDateOfBirth(String year) {
		try {
			logger.info("Starting date of birth selection for year: " + year);

			// 1. Click Date of Birth field
			logger.debug("Clicking Date of Birth field...");
			WebElement dobField = driver.findElement(AppiumBy.androidUIAutomator(
					"new UiSelector().text(\"Date of Birth *\")"));
			dobField.click();

			// Wait for date picker to open
			Thread.sleep(2000);

			// 2. Open year selector
			logger.debug("Opening year selector...");
			WebElement yearHeader = driver.findElement(By.id("android:id/date_picker_header_year"));
			yearHeader.click();

			// Wait for year list to appear
			Thread.sleep(2000);

			// 3. Scroll to target year
			logger.debug("Scrolling to year: " + year);
			scrollToYearSimple(year);

			// 4. Select the year
			logger.debug("Selecting year: " + year);
			WebElement yearElement = driver.findElement(By.xpath("//android.widget.TextView[@text='" + year + "']"));
			yearElement.click();

			// 5. Click OK button
			logger.debug("Clicking OK button...");
			WebElement okButton = driver.findElement(By.id("android:id/button1"));
			okButton.click();

			logger.info("Date of Birth set to year: " + year);

		} catch (Exception e) {
			logger.error("Error selecting date of birth: " + e.getMessage());
			throw new RuntimeException("Failed to select date of birth", e);
		}
	}

	private void scrollToYearSimple(String targetYear) {
		int maxScrollAttempts = 20;
		int scrollCount = 0;

		while (scrollCount < maxScrollAttempts) {
			try {
				// Check if year is visible
				WebElement yearElement = driver
						.findElement(By.xpath("//android.widget.TextView[@text='" + targetYear + "']"));
				if (yearElement.isDisplayed()) {
					logger.debug("Year " + targetYear + " found after " + scrollCount + " scrolls");
					return;
				}
			} catch (Exception e) {
				// Year not visible yet, need to scroll
			}

			// Scroll up (for older years like 1990)
			scrollUp();
			scrollCount++;

			// Wait a bit after scrolling
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

			logger.debug("Scrolled " + scrollCount + " times");
		}

		throw new RuntimeException("Could not find year " + targetYear + " after " + maxScrollAttempts + " scrolls");
	}

	private void scrollUp() {
		try {
			Dimension size = driver.manage().window().getSize();

			// Simple swipe from center to bottom (scrolls up)
			int startX = size.width / 2;
			int startY = size.height / 3; // Start at 1/3 from top
			int endY = size.height * 2 / 3; // End at 2/3 from top

			// Using Appium's executeScript for more reliable scrolling
			Map<String, Object> params = new HashMap<>();
			params.put("startX", startX);
			params.put("startY", startY);
			params.put("endX", startX);
			params.put("endY", endY);
			params.put("duration", 0.5);

			driver.executeScript("mobile: swipe", params);

		} catch (Exception e) {
			logger.warn("Scroll failed: " + e.getMessage());
		}
	}

	public void clickOK() {
		String okSelector = "new UiSelector().resourceId(\"android:id/button1\")";
		testUtils.clickWhenClickable(AppiumBy.androidUIAutomator(okSelector), 10);
	}

}