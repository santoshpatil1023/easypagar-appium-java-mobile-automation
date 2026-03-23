package page.user.sidebarMenu;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.TestUtils;

public class UserMyAssetPage {
    private AndroidDriver driver;
    private TestUtils testUtils;

    // Constructor
    public UserMyAssetPage(AndroidDriver driver) {
        this.driver = driver;
        this.testUtils = new TestUtils(driver);
    }

    // Locators
    private By myAssetPage = AppiumBy.xpath("//android.view.View[@text='My Asset']");
    private By searchAsset = AppiumBy.xpath("//android.widget.EditText[@resource-id='text-input-outlined']");
    private By noAssets = AppiumBy.xpath("//android.widget.TextView[@text='No Assets Have Been Assigned To You Yet.']");
    private By assetItem = AppiumBy.xpath("(//android.widget.TextView[contains(@text,'Asset')])[1]"); // Example first asset item text

    // Actions
    public boolean isMyAssetDisplayed() {
        return testUtils.isElementPresent(myAssetPage, 5);
    }

    public boolean isNoAssetMessageDisplayed() {
        boolean isVisible = testUtils.isElementPresent(noAssets, 5);

        if (isVisible) {
            System.out.println("⚠️ No Assets found. Returning to Dashboard...");
            testUtils.pressBackButton(); // 👈 Navigate back to Dashboard
        } else {
            System.out.println("✅ Assets are available for the user.");
        }

        return isVisible;
    }

    public boolean isAnyAssetDisplayed() {
        return testUtils.isElementPresent(assetItem, 5);
    }

    public void searchAsset(String asset) {
        if (testUtils.isElementPresent(searchAsset, 5)) {
            testUtils.sendKeys(searchAsset, asset, 10);
            testUtils.pressBackButton();
        } else {
            System.out.println("⚠️ Search box not found, skipping search.");
        }
    }
}
