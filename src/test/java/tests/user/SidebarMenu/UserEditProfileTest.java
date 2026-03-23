package tests.user.SidebarMenu;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.common.LoginPage;
import page.user.sidebarMenu.UserSidebarMenuPage;
import page.user.sidebarMenu.UserEditProfilePage;
import utils.ConfigReader;

public class UserEditProfileTest extends BaseTest {

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

    // ✅ Utility method used in tests
    private void loginAsUser() {
        loginPage.loginAsUser(username, password);
    }

    @Test(priority = 1, description = "Verify user can login successfully")
    public void testUserLogin() {
        loginAsUser();
        System.out.println("✅ User logged in successfully.");
    }

    @Test(priority = 2, dependsOnMethods = "testUserLogin",
            description = "Verify user can navigate to Edit Profile page")
    public void testNavigateToEditProfile() {
        sidebarMenuPage.openSidebarMenu();
        sidebarMenuPage.selectEditProfile();
        
        Assert.assertTrue(editProfilePage.isEditProfileDisplayed(),
                "❌ Edit Profile page not displayed!");
        System.out.println("✅ Edit Profile page opened successfully.");
    }
    
    @Test(priority = 3, dependsOnMethods = "testNavigateToEditProfile", description = "update Guardian Name successfully")
    public void testUpdateGuardianName() {

        editProfilePage.updateGuardianName("Ramesh Patil");
        
        System.out.println("=== user Guardian Name upadted ===");
    }
    
    @Test(priority = 4)//, dependsOnMethods = "testUpdateGuardianName",description = "Update all mandatory fields")
    public void testUpdateProfileWithAllMandatoryFields() {
        editProfilePage.selectGender("Male");
        editProfilePage.selectDateOfBirth("1990"); // This will scroll and select 1990
        editProfilePage.clickUpdate();
        
        Assert.assertTrue(editProfilePage.isSaveConfirmationVisible(), 
                "❌ Profile not updated successfully!");
        System.out.println("✅ Profile updated successfully with all mandatory fields.");
    }
    
    @Test(priority = 5, dependsOnMethods = "testUpdateProfileWithAllMandatoryFields",
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
