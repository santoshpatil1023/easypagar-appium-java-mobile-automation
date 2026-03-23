	package page.admin.dashboard;
	
	import io.appium.java_client.AppiumBy;
	import io.appium.java_client.android.AndroidDriver;
	
	import java.io.File;
	import java.io.IOException;

import org.openqa.selenium.By;
	import utils.TestUtils;
	
	public class AdminBannersPage {
	    private AndroidDriver driver;
	    private TestUtils utils;
	
	    // Locators for Admin Messages
	    private By bannersHeader = AppiumBy.xpath("//android.view.View[@text='Banners']");
	    private By bannerCreatePage = AppiumBy.androidUIAutomator("new UiSelector().text(\"Create Banner\")");
	    
//	    private By composeButton = AppiumBy.xpath("//android.view.ViewGroup[@resource-id='fab-content']");    
	    private By composeButton = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"fab-content\")");    
	    
	    private By branchDropdown = AppiumBy.xpath("//android.widget.TextView[@text='All Branch']");
	
	    private By departmentDropdown = AppiumBy.xpath("//android.widget.TextView[@text='All Department']");
	    
	    private By titleBox = AppiumBy.xpath("(//android.widget.EditText[@resource-id='text-input-outlined'])[1]");
	 
	    private By descriptionBox = AppiumBy.xpath("(//android.widget.EditText[@resource-id='text-input-outlined'])[2]");
	    
	    
	    private By imageUploadButton = AppiumBy.xpath("//android.widget.TextView[@text='Click Here']");
	    private By takePicture = AppiumBy.androidUIAutomator("new UiSelector().text(\"Take a Picture\")");
//	    private By clickPhoto = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(40)");
	    private By clickPhoto = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(39)");
	    private By acceptPhoto = AppiumBy.androidUIAutomator("new UiSelector().text(\"\")");
	    	    
	    private By okPopup = AppiumBy.androidUIAutomator("new UiSelector().text(\"Ok\")");
	  
	    // Image selection flow locators
	    private By chooseFromGallery = AppiumBy.xpath("//android.widget.TextView[@text='Choose From a Gallery']");
	    private By clickByCamera = AppiumBy.androidUIAutomator("new UiSelector().text(\"Take a Picture\")");

	    
 	    		
	    private By firstImage = AppiumBy.xpath("//android.support.v7.widget.RecyclerView[@resource-id='com.android.documentsui:id/dir_list']/android.widget.LinearLayout[1]/android.widget.LinearLayout");
	    private By cropButton = AppiumBy.xpath("//android.widget.Button[@content-desc='Crop']");
	    private By postButton = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Post']");
	    private By successMessage = AppiumBy.xpath("//android.widget.TextView[@text='Banner Created Successfully']");
	
	    
	    public AdminBannersPage(AndroidDriver driver) {
	        this.driver = driver;
	        this.utils = new TestUtils(driver);
	    }
	
	    public boolean isBannersPageDisplayed() {
	        return utils.isElementPresent(bannersHeader, 10);
	    }

	    public boolean isBannerCreationFormDisplayed() {
	        return utils.isElementPresent(bannerCreatePage, 10);
	    }
	
	    public void clickComposeButton() {
	        utils.clickWhenClickable(composeButton, 10);
	    }
	    
	    public void selectBranch(String branchName) {
	        // Try multiple locators for branch dropdown
			if (utils.isElementPresent(branchDropdown, 3)) {
			    utils.clickWhenClickable(branchDropdown, 10);
			}
//            else if (utils.isElementPresent(branchDropdownAlt1, 3)) {
//                utils.clickWhenClickable(branchDropdownAlt1, 10);
//            } else {
//                utils.clickWhenClickable(branchDropdownAlt2, 10);
//            }
			
			// Select the branch
			By branchOption = AppiumBy.xpath("//android.widget.TextView[@text='" + branchName + "']");
			utils.clickWhenClickable(branchOption, 10);
	    }
	    
	    public void selectDepartment(String deptName) {
	        utils.clickWhenClickable(departmentDropdown, 10);
	        utils.clickWhenClickable(AppiumBy.xpath("//android.widget.TextView[@text='" + deptName + "']"), 10);
	    }   
	
	    public void enterTitle(String msg) {
	        utils.sendKeys(titleBox, msg, 10);
	//        utils.pressBackButton();
	    }
	    
	    public void enterDescription(String msg) {
	        utils.sendKeys(descriptionBox, msg, 10);
	//        utils.pressBackButton();
	    }
	    
	    public void bannerByCamera() {
    	    utils.clickWhenClickable(imageUploadButton, 10);
    	    utils.clickWhenClickable(takePicture, 10);
    	    utils.clickWhenClickable(clickPhoto, 10);
    	    utils.clickWhenClickable(acceptPhoto, 10);    	    
	    }
	    
	    public void okPopUp() {
    	    utils.clickWhenClickable(okPopup, 10);
	    }
	    
	    public void uploadImage(String fileName) throws IOException, InterruptedException {
	   
	    	    // Push image to DCIM folder where gallery looks for recent images
	    	    String projectPath = System.getProperty("user.dir");
	    	    File imageFile = new File(projectPath + "/src/test/resources/Banners.easypagar/" + fileName);
	    	    
	    	    String devicePath = "/sdcard/DCIM/" + fileName;
	    	    driver.pushFile(devicePath, imageFile);
	    	    
	    	    // Wait a bit for the image to be indexed
	    	    Thread.sleep(5000);
	
	    	    System.out.println("Step 1: Clicking 'Click Here' button");
	    	    utils.clickWhenClickable(imageUploadButton, 10);
	    	    Thread.sleep(2000);	    	    	    	    
	
	    	    System.out.println("Step 2: Selecting 'Choose From a Gallery'");
	    	    utils.clickWhenClickable(chooseFromGallery, 10);
	    	    Thread.sleep(5000); // Wait longer for gallery to load with new image
	
	    	    System.out.println("Step 3: Clicking Search button in file browser");
	    	    // Click the search button in the file browser
	    	    By searchButton = AppiumBy.xpath("//android.widget.TextView[@content-desc='Search']");
	    	    utils.clickWhenClickable(searchButton, 10);
	    	    Thread.sleep(2000);
	    	    
	    	    System.out.println("Step 4: Entering file name in search box: " + fileName);
	    	    // Enter the file name in search box
	    	    By searchBox = AppiumBy.xpath("//android.widget.EditText[@resource-id='android:id/search_src_text']");
	    	    utils.sendKeys(searchBox, fileName, 10);
	    	    Thread.sleep(3000);
	    	    
	    	    System.out.println("Step 5: Selecting first image from search results");
	    	    utils.clickWhenClickable(firstImage, 10);
	    	    Thread.sleep(3000);
	    	    
	    	    System.out.println("Step 6: Handling crop if needed");
	    	    // Handle cropping
	    	    if (utils.isElementPresent(cropButton, 5)) {
	    	        System.out.println("Crop button found, clicking it");
	    	        utils.clickWhenClickable(cropButton, 10);
	    	        Thread.sleep(3000);
	    	    }
	    	    
	    	    System.out.println("✓ Image upload completed successfully");
	    	}
		    
		    // CORRECTED: Click Post instead of Submit
		    public void clickPost() {
		        utils.clickWhenClickable(postButton, 10);
		    }
		    
		    public boolean isBannerCreatedSuccessfully() {
		        return utils.isElementPresent(successMessage, 15);
		    }
		    
	    
	    // Method to handle the complete banner creation flow
	    public void createBanner(String branch, String department, String title, String description, String imageFileName) throws IOException, InterruptedException {
	        System.out.println("=== Starting Banner Creation Flow ===");
	        
	        // Fill banner details
	        selectBranch(branch);
	        selectDepartment(department);
	        enterTitle(title);
	        enterDescription(description);
	        System.out.println("✓ Banner details filled");
	        
	        // Upload image
	        uploadImage(imageFileName);
	        System.out.println("✓ Image uploaded successfully");
	        
	        // Click Post
	        clickPost();
	        System.out.println("✓ Post button clicked");
	        
	        // Verify success
	        if (isBannerCreatedSuccessfully()) {
	            System.out.println("✓ Banner created successfully!");
	        } else {
	            throw new RuntimeException("Banner creation failed - success message not found");
	        }
	    }
	
	}