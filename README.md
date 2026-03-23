# EPapp - Test Automation Framework

A comprehensive test automation framework built with Selenium, TestNG, and Appium for testing the EasyPagar application. This project includes end-to-end tests for both admin and user functionalities across various dashboards and modules.

## Overview

EPapp is a robust test automation suite that covers:
- **Admin Dashboard Tests**: Banner management, leave approvals, messaging system
- **User Dashboard Tests**: Leave applications, expense claims, attendance tracking, duty assignments, loan advances
- **Authentication Tests**: Login/logout workflows for both admin and user roles
- **User Profile Management**: Profile editing, help videos, asset management, and trainee information

## Technologies & Dependencies

| Technology | Version | Purpose |
|-----------|---------|---------|
| **Selenium** | 4.13.0 | Web browser automation |
| **TestNG** | 7.9.0 | Test execution and reporting framework |
| **Appium** | 8.3.0 | Mobile application testing |
| **Java** | 17 | Programming language |
| **Maven** | 3.x | Build and dependency management |
| **ExtentReports** | 5.1.1 | HTML test reporting |
| **Jackson** | 2.15.2 | JSON data serialization/deserialization |
| **SLF4J** | 2.0.9 | Logging framework |
| **Apache Commons IO** | 2.11.0 | File and stream utilities |

## Project Structure

```
EPapp/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── page/              # Page Object Model classes
│   │   │   │   ├── admin/         # Admin page objects
│   │   │   │   ├── common/        # Common page objects
│   │   │   │   └── user/          # User page objects
│   │   │   └── utils/             # Utility classes
│   │   └── resources/             # Configuration files
│   └── test/
│       ├── java/
│       │   ├── base/              # Base test classes
│       │   ├── listeners/         # TestNG listeners
│       │   └── tests/             # Test classes
│       │       ├── admin/         # Admin tests
│       │       ├── common/        # Common tests (login/logout)
│       │       └── user/          # User tests
│       └── resources/             # Test data and configs
│           ├── apps/              # Application configurations
│           ├── config/            # Configuration files
│           └── Banners.easypagar/ # EasyPagar specific configs
├── reports/                       # Extent Report outputs
│   └── screenshots/               # Captured screenshots
├── test-output/                   # TestNG report outputs
├── pom.xml                        # Maven configuration
├── testng.xml                     # TestNG suite configuration
└── README.md                      # This file
```

## Prerequisites

### System Requirements
- **JDK**: Java 17 or higher
- **Maven**: 3.6.0 or higher
- **Browser Drivers**: ChromeDriver, FirefoxDriver, etc.
- **Operating System**: Windows, macOS, or Linux

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd EPapp
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Configure WebDrivers**
   - Download the appropriate WebDriver for your browser (ChromeDriver, GeckoDriver, etc.)
   - Add the driver path to your system PATH or configure it in the utility classes

4. **Set up test configuration**
   - Update configuration files in `src/test/resources/config/`
   - Configure application URLs, credentials, and other environment-specific settings

## Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Suite
```bash
mvn test -Dsuites=testng.xml
```

### Run Tests for Specific Module
```bash
# Admin tests only
mvn test -Dtest=tests.admin.**

# User tests only
mvn test -Dtest=tests.user.**

# Common tests (login/logout)
mvn test -Dtest=tests.common.**
```

### Run Tests with Chrome Browser (headless)
```bash
mvn test -Dbrowser=chrome -Dheadless=true
```

### Run Tests in Parallel
```bash
mvn test -Dthreads=4
```

## Test Categories

### Admin Tests (Admin Dashboard)
- **AdminApproveLeaveTest**: Tests leave approval workflow
- **AdminBannerTest**: Tests banner management functionality
- **AdminMessagesTest**: Tests messaging system for admins

### User Tests (User Dashboard)
- **UserApplyLeaveTest**: Tests leave application workflow
- **UserApplyExpenseTest**: Tests expense claim submission
- **UserApplyLoanAdvanceTest**: Tests loan/advance requests
- **UserAttendanceTest**: Tests attendance tracking
- **UserDutyRoasterTest**: Tests duty roster assignment
- **UserMessagesTest**: Tests user messaging system
- **UserEditProfileTest**: Tests profile editing
- **UserHelpVideosTest**: Tests help video access
- **UserMyAssetTest**: Tests asset management
- **UserTraineeTest**: Tests trainee information management

### Authentication Tests
- **AdminLoginLogoutTest**: Tests admin authentication
- **UserLoginLogoutTest**: Tests user authentication
- **LoginLogoutTest**: General login/logout tests

## Test Reporting

### Extent Reports
After test execution, detailed HTML reports are generated:
- **Main Report**: `reports/ExtentReport.html`
- **Surefire Report**: `target/surefire-reports/index.html`
- **TestNG Report**: `test-output/index.html`

Reports include:
- Test pass/fail status
- Execution time
- Error logs and stack traces
- Screenshots on failure
- Test categorization and filtering options

### Screenshots
Failed test screenshots are automatically captured and stored in:
- `reports/screenshots/`

## Page Object Model (POM)

The framework uses Page Object Model pattern for maintainability:

```
page/
├── admin/          # Admin page classes (AdminDashboard, AdminBannerPage, etc.)
├── common/         # Common elements (LoginPage, Header, Sidebar, etc.)
└── user/           # User page classes (UserDashboard, LeaveApplication, etc.)
```

Each page class encapsulates:
- Web elements (using locators)
- Page-specific actions/methods
- Element interactions

## Utilities

Common utilities provided in `src/main/java/utils/`:
- **Browser/Driver Management**: WebDriver setup and teardown
- **Waits**: Explicit and implicit waits handling
- **Screenshot Capture**: Failure screenshot functionality
- **Logging**: Test execution logging
- **Reporting**: ExtentReports integration
- **Configuration Management**: Loading test properties

## TestNG Configuration

The `testng.xml` file defines:
- Test suite grouping
- Test class definitions
- Listener configuration
- Parallel execution settings
- Parameter passing

Modify this file to:
- Include/exclude specific tests
- Enable parallel execution
- Configure retry logic
- Set thread counts

## Listeners

Custom TestNG listeners for:
- **Test Execution**: Track test start/end
- **Reporting**: Generate Extent Report entries
- **Screenshot Capture**: Capture screenshots on failure
- **Logging**: Log test execution details

## Best Practices

1. **Maintenance**: Keep page objects updated with latest UI changes
2. **Data Management**: Use external data files for test data
3. **Waits**: Use explicit waits instead of sleep commands
4. **Screenshots**: Automatically captured on test failures
5. **Naming**: Use descriptive test method names
6. **Modularization**: Keep tests focused on a single functionality
7. **Reusability**: Leverage page objects and utilities for code reuse

## Continuous Integration

To integrate with CI/CD pipelines:

```bash
# In your CI/CD configuration
mvn clean test -Dbrowser=chrome -Dheadless=true
```

Test results will be available in:
- `target/surefire-reports/`
- `reports/ExtentReport.html`

## Troubleshooting

### Common Issues

1. **WebDriver Not Found**
   - Ensure WebDriver is in system PATH or configure driver paths in utilities

2. **Tests Fail Due to Wait Timeouts**
   - Increase wait times in configuration
   - Check if application is running correctly

3. **Screenshot Not Captured**
   - Verify screenshot directory exists
   - Check write permissions

4. **Reports Not Generated**
   - Ensure Extent Reports dependency is correctly installed
   - Check report output directory path

## Contributing

1. Create a new branch for your feature/fix
2. Write tests following existing patterns
3. Ensure all tests pass before committing
4. Update this README if adding new modules

## Contact & Support

For issues, questions, or contributions, please contact the project maintainers.

---

**Last Updated**: March 2026
**Project Version**: 0.0.1-SNAPSHOT
