package tests.admin.dashboard;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import page.admin.dashboard.AdminDashboardPage;
import page.admin.dashboard.AdminMessagesPage;
import page.admin.sidebarMenu.AdminSidebarMenuPage;
import page.common.LoginPage;
import utils.ConfigReader;

public class AdminMessagesTest extends BaseTest {
	private LoginPage loginPage;
	private AdminDashboardPage adminDashboard;
	private AdminMessagesPage aminMessagesPage;
	private AdminSidebarMenuPage adminSidebarMenuPage;

	@BeforeMethod
	public void setUpPages() {
		loginPage = new LoginPage(driver);
		adminDashboard = new AdminDashboardPage(driver);
		aminMessagesPage = new AdminMessagesPage(driver);
		adminSidebarMenuPage = new AdminSidebarMenuPage(driver);

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
	public void openMessagesSection() {
		adminDashboard.clickMessages();

		Assert.assertTrue(aminMessagesPage.isMessagesPageDisplayed(),
				"Messages page was not displayed!");
		System.out.println("✅ Messages page opened successfully.");
	}

	@Test(priority = 3, dependsOnMethods = "openMessagesSection")
	public void testSendMessageToUser() {
		aminMessagesPage.clickComposeButton();
		Assert.assertTrue(aminMessagesPage.isMessageCreationFormDisplayed(),
				"Meessage creation form was not displayed!");
		System.out.println("✓ Message creation form opened");
		aminMessagesPage.selectEmployee("Select All");
		aminMessagesPage.typeMessage("Automation Test Message to Ravi teja");
		aminMessagesPage.attachImage();

		aminMessagesPage.clickSendButton();

		Assert.assertTrue(aminMessagesPage.isMessagesPageDisplayed(),
				"Messages page was not displayed!");

	}

	@Test(priority = 4) // , dependsOnMethods = "testSendMessageToUser")
	public void logoutAfterMessageSent() {
		aminMessagesPage.backToDashAfterSentmessage();
		adminSidebarMenuPage.openSidebar();
		adminSidebarMenuPage.selectLogoutOption();
	}
}