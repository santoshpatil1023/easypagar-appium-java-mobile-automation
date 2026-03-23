package utils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class UserTestUtilsScroles {
    private AndroidDriver driver;
    private WebDriverWait customWait;

    public UserTestUtilsScroles(AndroidDriver driver) {
        this.driver = driver;
        this.customWait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Default timeout
    }

    // Redmi
    // user
    // ✅ Scroll DOWN inside sidebar
    public void scrollSidebarDown() {
        try {
            System.out.println("🔽 Scrolling down inside the sidebar...");

            WebElement sidebar = driver.findElement(AppiumBy.xpath(
                    "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[5]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup"));

            Map<String, Object> params = new HashMap<>();
            params.put("elementId", ((RemoteWebElement) sidebar).getId());
            params.put("direction", "down");
            params.put("percent", 0.7);
            driver.executeScript("mobile: swipeGesture", params);

            System.out.println("✅ Scrolled down inside sidebar");
        } catch (Exception e) {
            System.out.println("⚠️ Failed to scroll sidebar down: " + e.getMessage());
        }
    }

    // ✅ Scroll UP inside sidebar
    public void scrollSidebarUp() {
        try {
            System.out.println("🔼 Scrolling up inside the sidebar...");

            WebElement sidebar = driver.findElement(AppiumBy.xpath(
                    "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[5]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup"));

            Map<String, Object> params = new HashMap<>();
            params.put("elementId", ((RemoteWebElement) sidebar).getId());
            params.put("direction", "up");
            params.put("percent", 0.7);
            driver.executeScript("mobile: swipeGesture", params);

            System.out.println("✅ Scrolled up inside sidebar");
        } catch (Exception e) {
            System.out.println("⚠️ Failed to scroll sidebar up: " + e.getMessage());
        }
    }
}
