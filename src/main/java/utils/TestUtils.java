package utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class TestUtils {
    private AndroidDriver driver;
    private WebDriverWait customWait;

    public TestUtils(AndroidDriver driver) {
        this.driver = driver;
        this.customWait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Default timeout
    }

    // Basic element interaction methods
    public WebElement waitForVisible(By locator, int timeoutSeconds) {
        customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void clickWhenClickable(By locator, int timeoutSeconds) {
        customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        WebElement el = customWait.until(ExpectedConditions.elementToBeClickable(locator));
        el.click();
    }

    public void sendKeys(By locator, String text, int timeoutSeconds) {
        WebElement el = waitForVisible(locator, timeoutSeconds);
        el.clear();
        el.sendKeys(text);
    }

    public boolean isElementPresent(By locator, int timeoutSeconds) {
        try {
            waitForVisible(locator, timeoutSeconds);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getElementText(By locator, int timeoutSeconds) {
        try {
            WebElement element = waitForVisible(locator, timeoutSeconds);
            return element != null ? element.getText() : null;
        } catch (Exception e) {
            return "";
        }
    }

    // Enhanced wait methods
    public void waitForElementToBeInvisible(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public boolean waitForTextToBePresent(By locator, String text, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public void waitForElementToContainText(By locator, String text, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(ExpectedConditions.textToBe(locator, text));
    }

    // 🔑 Keyboard utility methods
    public void pressBackButton() {
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    public void pressEnter() {
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    public void pressHome() {
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }

    public void pressRecentApps() {
        driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
    }

    public void pressKey(AndroidKey key) {
        driver.pressKey(new KeyEvent(key));
    }

    public String takeScreenshot(String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/reports/screenshots/" + testName + ".png";
        try {
            FileUtils.copyFile(srcFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    // SIMPLIFIED SCROLL METHOD
    public void scrollDown() {
        Map<String, Object> params = new HashMap<>();
        params.put("left", 100);
        params.put("top", 400);
        params.put("width", 500);
        params.put("height", 900);
        params.put("direction", "down");
        params.put("percent", 0.75);

        driver.executeScript("mobile: scrollGesture", params);
    }

    public void scrollUp() {
        Map<String, Object> params = new HashMap<>();
        params.put("left", 100);
        params.put("top", 1200);
        params.put("width", 500);
        params.put("height", 800);
        params.put("direction", "up");
        params.put("percent", 0.75);

        driver.executeScript("mobile: scrollGesture", params);
    }

    public void scrollDown1() {
        Map<String, Object> params = new HashMap<>();
        params.put("left", 100);
        params.put("top", 400);
        params.put("width", 500);
        params.put("height", 900);
        params.put("direction", "down");
        params.put("percent", 0.75);

        driver.executeScript("mobile: scrollGesture", params);
    }

    // Method 1: Simple scroll down
    public void scrollDown11() {
        try {
            org.openqa.selenium.Dimension size = driver.manage().window().getSize();
            int startX = size.width / 2;
            int startY = (int) (size.height * 0.8);
            int endY = (int) (size.height * 0.2);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            Map<String, Object> params = new HashMap<>();
            params.put("startX", startX);
            params.put("startY", startY);
            params.put("endX", startX);
            params.put("endY", endY);
            params.put("duration", 500);

            js.executeScript("mobile: swipeGesture", params);
            Thread.sleep(1000);

        } catch (Exception e) {
            System.out.println("Scroll down failed: " + e.getMessage());
        }
    }

    // Method 2: Scroll to specific text
    public boolean scrollToText(String text, int maxAttempts) {
        System.out.println("Scrolling to text: '" + text + "'");

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollTextIntoView(\"" + text + "\")"));
                System.out.println("✅ Found text: " + text);
                return true;

            } catch (Exception e) {
                System.out.println("Scroll attempt " + attempt + " failed");
                scrollDown();
            }
        }

        System.out.println("❌ Text not found: " + text);
        return false;
    }

    // Redmi
    // admin
    // ✅ Scroll DOWN inside sidebar
    public void scrollSidebarDown() {
        try {
            System.out.println("🔽 Scrolling down inside the sidebar...");
            // Option 2: Using UIAutomator with more reliable selector
            WebElement sidebar = driver.findElement(
                    AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(78)"));

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
            // WebElement sidebar = driver.findElement(AppiumBy.androidUIAutomator(
            // "new UiSelector().className(\"android.view.ViewGroup\").scrollable(true)"
            // ));

            WebElement sidebar = driver.findElement(
                    AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(78)"));

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
