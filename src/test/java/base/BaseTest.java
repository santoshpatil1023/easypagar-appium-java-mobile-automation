package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import utils.ConfigReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)
public class BaseTest {

    public static AndroidDriver driver;

    @BeforeClass
    public void setup() throws MalformedURLException {
        try {
            UiAutomator2Options options = new UiAutomator2Options();

            // Basic capabilities (as you had)
            options.setPlatformName(ConfigReader.get("platformName"));
            options.setAutomationName(ConfigReader.get("automationName"));
            options.setDeviceName(ConfigReader.get("deviceName"));
            options.setUdid(ConfigReader.get("udid"));

            // App path
            String appPath = ConfigReader.get("appPath");
            options.setApp(appPath);

            // Your extra capabilities (kept as-configurable)
            options.setFullReset(Boolean.parseBoolean(ConfigReader.get("fullReset")));
            options.setNoReset(Boolean.parseBoolean(ConfigReader.get("noReset")));
            options.setIgnoreHiddenApiPolicyError(Boolean.parseBoolean(ConfigReader.get("ignoreHiddenApiPolicyError")));
            options.setNewCommandTimeout(Duration.ofSeconds(Long.parseLong(ConfigReader.get("newCommandTimeout"))));
            options.setAutoGrantPermissions(Boolean.parseBoolean(ConfigReader.get("autoGrantPermissions")));

            // --- Small set of non-invasive stability capabilities ---
            // Helps prevent UiAutomator2 crashes/timeouts on some devices
            options.setCapability("uiautomator2ServerInstallTimeout", 60000); // ms
            options.setCapability("uiautomator2ServerLaunchTimeout", 60000); // ms
            options.setCapability("adbExecTimeout", 60000); // ms
            options.setCapability("allowTestPackages", true); // prevents some OEM kills
            options.setCapability("skipDeviceInitialization", true); // speeds startup
            // Do NOT set unicodeKeyboard/resetKeyboard here (avoids keyboard issues)

            // Appium server URL
            URL appiumServerUrl = new URL(ConfigReader.get("appiumServer"));

            System.out.println("Starting AndroidDriver with options:");
            System.out.println("App: " + appPath);
            System.out.println("Device: " + ConfigReader.get("deviceName"));
            System.out.println("UDID: " + ConfigReader.get("udid"));

            driver = new AndroidDriver(appiumServerUrl, options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            System.out.println("Driver initialized successfully!");
        } catch (Exception e) {
            System.err.println("Failed to initialize AndroidDriver: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
                System.out.println("Driver quit successfully!");
            } catch (Exception e) {
                System.err.println("Error while quitting driver: " + e.getMessage());
            }
        }
    }

    // Helper to get driver
    public AndroidDriver getDriver() {
        return driver;
    }
}
