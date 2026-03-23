package tests.admin.dashboard;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
//import page.admin.MessagesPage;
import page.common.LoginPage;
import page.admin.dashboard.AdminDashboardPage;
import page.admin.dashboard.AdminBannersPage;
import utils.ConfigReader;

public class AdminBannerTest extends BaseTest {
	private LoginPage loginPage;
	private AdminDashboardPage adminDashboard;
	private AdminBannersPage adminBannersPage;

	@BeforeMethod
	public void setUpPages() {
		loginPage = new LoginPage(driver);
		adminDashboard = new AdminDashboardPage(driver);
		adminBannersPage = new AdminBannersPage(driver);
	}

	String adminname = ConfigReader.get("admin.username");
	String password = ConfigReader.get("admin.password");

	private void loginAsAdmin() {
		loginPage.loginAsAdmin(adminname, password);
	}

	@Test(priority = 1)
	public void adminLoginTest() {
		loginAsAdmin();
		System.out.println("🔐 Admin logged in successfully.");
	}

	@Test(priority = 2, dependsOnMethods = "adminLoginTest")
	public void openBannersSection() {
		adminDashboard.clickBanners();

		Assert.assertTrue(adminBannersPage.isBannersPageDisplayed(),
				"❌ banners page did not open as expected.");
		System.out.println("✅ Banners page opened successfully.");
	}

	@Test(priority = 3, dependsOnMethods = "openBannersSection")
	public void postNewBannerFromCamera() throws IOException, InterruptedException {
		adminBannersPage.clickComposeButton();
		Assert.assertTrue(adminBannersPage.isBannerCreationFormDisplayed(),
				"Banner creation form was not displayed!");
		System.out.println("✓ Banner creation form opened");

		// Step 2: Fill banner details
		adminBannersPage.enterTitle("Test Banner - Automated");
		adminBannersPage.enterDescription("This is an automated test banner description");
		System.out.println("✓ Banner details filled");

		adminBannersPage.bannerByCamera();
		// adminBannersPage.uploadImage("bn1.jpg");
		// System.out.println("✓ Image uploaded and cropped");

		adminBannersPage.clickPost();
		System.out.println("✓ Post button clicked");

		// Step 5: Verify success message (Bn9)
		Assert.assertTrue(adminBannersPage.isBannerCreatedSuccessfully(),
				"Banner was not created successfully! Expected success message not found.");
		adminBannersPage.okPopUp();

		Assert.assertTrue(adminBannersPage.isBannersPageDisplayed(),
				"❌ banners page did not open as expected.");

		System.out.println("✓ Banner created successfully! Returned back to Banners page");
		System.out.println("=== Banner Creation Completed ===");

	}

	// @Test(priority = 2, dependsOnMethods="testNavigateToBannersPage")
	// public void testAdminCanCreateBanner() throws IOException,
	// InterruptedException {
	// adminDashboard.clickBanners();
	// adminBannersPage = new AdminBannersPage(driver);
	// Assert.assertTrue(adminBannersPage.isBannersPageDisplayed(),
	// "Banners page was not displayed!");
	//
	// System.out.println("=== Starting Banner Creation ===");
	//
	// // Step 1: Click compose button (Bn1 → Bn2)
	// adminBannersPage.clickComposeButton();
	// Assert.assertTrue(adminBannersPage.isBannerCreationFormDisplayed(),
	// "Banner creation form was not displayed!");
	// System.out.println("✓ Banner creation form opened");
	//
	// // Step 2: Fill banner details
	// adminBannersPage.selectBranch("All Branch");
	// adminBannersPage.selectDepartment("All Department");
	// adminBannersPage.enterTitle("Test Banner - Automated");
	// adminBannersPage.enterDescription("This is an automated test banner
	// description");
	// System.out.println("✓ Banner details filled");
	//
	// adminBannersPage.uploadImage("bn1.jpg");
	// System.out.println("✓ Image uploaded and cropped");
	//
	// adminBannersPage.clickPost();
	// System.out.println("✓ Post button clicked");
	//
	// // Step 5: Verify success message (Bn9)
	// Assert.assertTrue(adminBannersPage.isBannerCreatedSuccessfully(),
	// "Banner was not created successfully! Expected success message not found.");
	//
	// System.out.println("✓ Banner created successfully!");
	// System.out.println("=== Banner Creation Completed ===");
	// }
	//

	// @Test(priority = 3)
	// public void testCreateBannerWithDifferentImage() throws IOException,
	// InterruptedException {
	// adminDashboard.clickBanners();
	// adminBannersPage = new AdminBannersPage(driver);
	//
	// adminBannersPage.clickComposeButton();
	//
	// // Test with different parameters
	// adminBannersPage.createBanner(
	// "All Branch",
	// "All Department",
	// "Second Test Banner",
	// "Another automated test banner",
	// "bn3.jpg" // Make sure this file exists in your resources
	// );
	// }
}