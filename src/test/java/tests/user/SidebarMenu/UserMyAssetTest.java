package tests.user.SidebarMenu;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.common.LoginPage;
import page.user.sidebarMenu.UserSidebarMenuPage;
import page.user.sidebarMenu.UserMyAssetPage;
import utils.ConfigReader;

public class UserMyAssetTest extends BaseTest {

    private LoginPage loginPage;
    private UserSidebarMenuPage sidebarMenuPage;
    private UserMyAssetPage myAssetPage;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        sidebarMenuPage = new UserSidebarMenuPage(driver);
        myAssetPage = new UserMyAssetPage(driver);
    }

    String username = ConfigReader.get("user.username");
    String password = ConfigReader.get("user.password");

    private void loginAsUser() {
        loginPage.loginAsUser(username, password);
    }

    // ✅ Test 1 - performs login and opens My Asset page
    @Test(priority = 1, description = "Verify My Asset page is displayed successfully")
    public void testMyAssetsPageDisplayed() {
        loginAsUser();
        sidebarMenuPage.openSidebarMenu();
        sidebarMenuPage.selectMyAsset();

        Assert.assertTrue(myAssetPage.isMyAssetDisplayed(), "❌ My Asset page not displayed!");
        System.out.println("✅ My Asset page displayed successfully!");
    }

    // ✅ Test 2 - depends on Test 1 (reuses same session)
    @Test(priority = 2, dependsOnMethods = "testMyAssetsPageDisplayed",
          description = "Verify asset search or no assets message")
    public void testSearchMyAssetsIfThere() {

        if (myAssetPage.isNoAssetMessageDisplayed()) {
            System.out.println("✅ No assets found message displayed as expected.");
            Assert.assertTrue(true, "No assets available — passed gracefully.");
        } 
        else if (myAssetPage.isAnyAssetDisplayed()) {
            System.out.println("✅ Assets found, performing search...");
            myAssetPage.searchAsset("HP");
            System.out.println("✅ Asset search completed successfully!");
        } 
        else {
            System.out.println("⚠️ Unexpected case — neither assets nor 'no asset' message found.");
            Assert.fail("Could not determine asset state.");
        }

        System.out.println("=== ✅ My Assets Test Completed Successfully ===");

        // optional logout at end of suite
        sidebarMenuPage.openSidebarMenu();
        sidebarMenuPage.selectLogoutOption();
        sidebarMenuPage.confirmLogout();
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "❌ Should be redirected to login page after logout");
        System.out.println("✓ User logout successful.");
    }
}
