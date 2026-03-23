package tests.user.SidebarMenu;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.common.LoginPage;
import page.user.sidebarMenu.UserSidebarMenuPage;
import page.user.sidebarMenu.UserEditProfilePage;
import utils.ConfigReader;

public class UserEditProfileTest2 extends BaseTest {

    private LoginPage loginPage;
    private UserSidebarMenuPage sidebarMenuPage;
    private UserEditProfilePage editProfilePage;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        sidebarMenuPage = new UserSidebarMenuPage(driver);
        editProfilePage = new UserEditProfilePage(driver);
    }   
    
    String username = ConfigReader.get("user.username");
    String password = ConfigReader.get("user.password");

    // ✅ Utility method used in both tests
    private void loginAssUser() {
        loginPage.loginAsUser(username, password);
    }

    @Test(priority = 1, description = "Verify user can update Guardian Name successfully")
    public void testUpdateGuardianName() {
        // Login + Open Sidebar
        loginAssUser();

        sidebarMenuPage.openSidebarMenu();

        //Navigate to Edit Profile
        sidebarMenuPage.selectEditProfile();

        //Update Guardian Name
        editProfilePage.updateGuardianName("Ramesh Patil");
        editProfilePage.clickUpdate();

        Assert.assertTrue(editProfilePage.isSaveConfirmationVisible(), "❌ Profile not updated!");
        
        System.out.println("=== user profile upadte Test Completed ===");
    }
    
    @Test(priority = 2, dependsOnMethods = "testUpdateGuardianName",
            description = "Verify user can logout successfully after editing the profile")
      public void testLogoutAfterEditProfile() {
          sidebarMenuPage.openSidebarMenu();
          sidebarMenuPage.selectLogoutOption();
          sidebarMenuPage.confirmLogout();

          Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                  "❌ Failed to return to Login Page after logout!");
          System.out.println("✅ User logged out successfully after completing Edit Profile validation!");
      }
}
