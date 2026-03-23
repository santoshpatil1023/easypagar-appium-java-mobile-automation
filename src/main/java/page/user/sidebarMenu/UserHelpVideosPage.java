package page.user.sidebarMenu;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.TestUtils;

public class UserHelpVideosPage {
    private AndroidDriver driver;
    private TestUtils testUtils;

    // Constructor
    public UserHelpVideosPage(AndroidDriver driver) {
        this.driver = driver;
        this.testUtils = new TestUtils(driver);
    }

    // Locators
    private By helpVideosPage = AppiumBy.xpath("//android.view.View[@text='Help videos']");
    
    private By attendance = AppiumBy.xpath("//android.widget.TextView[@text='Attendance']");
    private By attendanceVideos = AppiumBy.xpath("//android.widget.TextView[@text='Employee-How to Mark  Attendance.']");
  
    private By leave = AppiumBy.xpath("//android.widget.TextView[@text='Leave']");
    private By leaveVideos = AppiumBy.xpath("//android.widget.TextView[@text='How to Apply For Leave.']");
    
    private By loan = AppiumBy.xpath("//android.widget.TextView[@text='Loan']");
    private By loanVideos = AppiumBy.xpath("//android.widget.TextView[@text='How Employee Request Loan/Advances']");
    
    private By report = AppiumBy.xpath("//android.widget.TextView[@text='Report']");
    private By reportVideos = AppiumBy.xpath("//android.widget.TextView[@text='Reports -How to Check Different Reports']");

    private By expense = AppiumBy.xpath("//android.widget.TextView[@text='Expense']");
    private By expenseVideos = AppiumBy.xpath("//android.widget.TextView[@text='How to Apply For Expenses.']");
    
    private By message = AppiumBy.xpath("//android.widget.TextView[@text='Message']");
    private By messageVideos = AppiumBy.xpath("//android.widget.TextView[@text='Messages']");

  
    // Actions
    public boolean isHelpVideosDisplayed() {
        return testUtils.isElementPresent(helpVideosPage, 5);
    }

    //1 attendance
    public void selectAttendanceHelpVideos() {
    	testUtils.clickWhenClickable(attendance, 10);
    }
    
    public boolean isAttendanceHelpVideoAvailable() {
        return testUtils.isElementPresent(attendanceVideos, 5);
    }
    
    //2 leave
    public void selectLeaveHelpVideos() {
    	testUtils.clickWhenClickable(leave, 10);
    }
    
    public boolean isLeaveHelpVideosAvailable() {
    	return testUtils.isElementPresent(leaveVideos, 10);
    }
    //3 loan
    public void selectLoanHelpVideos() {
    	testUtils.clickWhenClickable(loan, 10);
    }
    
    public boolean isLoanHelpVideosAvailable() {
    	return testUtils.isElementPresent(loanVideos, 10);
    }
    //4 report
    public void selectReportHelpVideos() {
    	testUtils.clickWhenClickable(report, 10);
    }
    
    public boolean isReportHelpVideosAvailable() {
    	return testUtils.isElementPresent(reportVideos, 10);
    }
    //5 expense
    public void selectExpenseHelpVideos() {
    	testUtils.clickWhenClickable(expense, 10);
    }
    public boolean isExpenseVideosAvailale() {
    	return testUtils.isElementPresent(expenseVideos, 10);
    }
    //6 messages
    public void selectMessageHelpVideos() {
    	testUtils.clickWhenClickable(message, 10);
    }
    public boolean isMessagesHelpVideosAvailable() {
    	return testUtils.isElementPresent(messageVideos, 10);
    }

}
