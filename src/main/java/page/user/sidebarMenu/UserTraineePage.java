package page.user.sidebarMenu;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.TestUtils;

public class UserTraineePage {
    private AndroidDriver driver;
    private TestUtils testUtils;

    // Constructor
    public UserTraineePage(AndroidDriver driver) {
        this.driver = driver;
        this.testUtils = new TestUtils(driver);
    }

    // Locators
    private By traineePage = AppiumBy.xpath("//android.view.View[@text='Trainee']");
    private By searchTraining = AppiumBy.xpath("//android.widget.EditText[@resource-id='text-input-outlined']");
    
    private By noTrainings = AppiumBy.xpath("//android.widget.TextView[@text='No Trainings Found In This Category']");

    // Actions
    public boolean isTraineeDisplayed() {
        return testUtils.isElementPresent(traineePage, 5);
    }

    public boolean isNoTrainingsDisplayed() {
        boolean isVisible = testUtils.isElementPresent(noTrainings, 5);

        if (isVisible) {
            System.out.println("⚠️ No Trainings are found. Returning to Dashboard...");
            testUtils.pressBackButton(); // 👈 Navigate back to Dashboard
        } else {
            System.out.println("✅ Assets are available for the user.");
        }

        return isVisible;
    }


    public void searchTrainings(String asset) {
        if (testUtils.isElementPresent(searchTraining, 5)) {
            testUtils.sendKeys(searchTraining, asset, 10);
            testUtils.pressBackButton();
        } else {
            System.out.println("⚠️ Search box not found, skipping search.");
        }
    }
}
