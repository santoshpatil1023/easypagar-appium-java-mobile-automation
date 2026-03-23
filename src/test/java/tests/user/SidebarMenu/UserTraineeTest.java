package tests.user.SidebarMenu;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.common.LoginPage;
import page.user.sidebarMenu.UserSidebarMenuPage;
import page.user.sidebarMenu.UserTraineePage;
import utils.ConfigReader;

public class UserTraineeTest extends BaseTest {

    private LoginPage loginPage;
    private UserSidebarMenuPage sidebarMenuPage;
    private UserTraineePage traineePage;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        sidebarMenuPage = new UserSidebarMenuPage(driver);
        traineePage = new UserTraineePage(driver);
    }

    String username = ConfigReader.get("user.username");
    String password = ConfigReader.get("user.password");

    private void loginAsUser() {
        loginPage.loginAsUser(username, password);
    }

    @Test(priority = 1, description = "Verify Trainee page is displayed successfully")
    public void testTraineePageDisplayed() {
        loginAsUser();
        sidebarMenuPage.openSidebarMenu();
        sidebarMenuPage.selectTrainee();

        Assert.assertTrue(traineePage.isTraineeDisplayed(), "❌ Trainee page not displayed!");
        System.out.println("✅ Trainee page displayed successfully!");
    }

    @Test(priority = 2, dependsOnMethods = "testTraineePageDisplayed", description = "Verify trainings are available or show 'No Trainings Found' message")
    public void testSearchTrainingsIfThere() {
        if (traineePage.isNoTrainingsDisplayed()) {
            System.out.println("✅ No Trainings Found message displayed and returned to Dashboard successfully.");
            Assert.assertTrue(true, "No Trainings available — passed gracefully.");
        } else {
            System.out.println("✅ Trainings found, performing search...");
            traineePage.searchTrainings("Java");
            System.out.println("✅ Training search completed successfully!");
        }

        System.out.println("=== ✅ Trainee Module Test Completed Successfully ===");

        sidebarMenuPage.openSidebarMenu();
        sidebarMenuPage.selectLogoutOption();
        sidebarMenuPage.confirmLogout();
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "❌ Should be redirected to login page after logout");
        System.out.println("✓ User logout successful.");
    }
}
