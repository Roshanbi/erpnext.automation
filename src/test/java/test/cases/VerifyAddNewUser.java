package test.cases;

import org.testng.Assert;
import org.testng.annotations.Test;

import page.objects.HomePage;
import page.objects.LoginPage;
import page.objects.NewUserModal;
import page.objects.UserListPage;
import page.objects.UsersPage;
import test.base.BaseClass;
import test.base.DriverFactory;
import utilities.TestUtil;

public class VerifyAddNewUser extends BaseClass {
	@Test
	public void verifyLoginTest() throws InterruptedException {

		logger.info("===test started: verifyLogin Test===");
		// login page
		logger.info("===Login with the email and password===");
		LoginPage lp = new LoginPage(DriverFactory.getDriver());
		lp.login(properties.getProperty("email"), properties.getProperty("password"));

		// Home page
		logger.info("verifying home page is displayed");
		HomePage hp = new HomePage(DriverFactory.getDriver());
		boolean homePage_Status = hp.isHomePageDisplayed();

		// validation

		Assert.assertTrue(homePage_Status, "Login failed: Home page not displayed.");

		// navigate to Users
		logger.info("clicking users label");
		hp.clickUsers();

		// users Page
		logger.info("verifying user modal page is displayed");
		UsersPage up = new UsersPage(DriverFactory.getDriver());
		boolean usersModule_Status = up.isUsersModuleDisplayed();
		Assert.assertTrue(usersModule_Status, "Users module page not displayed");

		// clicking on user submodule
		logger.info("Clicking user submodule");
		up.clickUserSubModule();

		// UserListPage
		logger.info("verifying user list page title");
		UserListPage ulp = new UserListPage(DriverFactory.getDriver());
		boolean userListTitle_Status = ulp.isUserTitleDisplayed();
		Assert.assertTrue(userListTitle_Status, "User List Page not displayed");

		// Add User Button
		logger.info("Verifying Add user button visibility");
		boolean addUserButton_Status = ulp.isAddUserButtonDisplayed();
		Assert.assertTrue(addUserButton_Status, "Add User Button is not displayed");

		// Clicking on Add User
		logger.info("Clicking on AddUser button");
		ulp.clickAddUser();

		// NewUserModal

		NewUserModal num = new NewUserModal(DriverFactory.getDriver());
		String rawEmail = TestUtil.randomString(5) + "@gmail.com";
		// converting UpperCase email to LowerCase as ERP storing email as LowerCase
		String email = rawEmail.toLowerCase();
		System.out.println(email);
		String firstName = TestUtil.randomString(8);
		System.out.println(firstName);
		logger.info("Generated new User details");
		num.setEmail(email.toLowerCase());
		num.setFirstNameField(firstName);
		String role = "Sales";
		Thread.sleep(2000);
		logger.info("Selecting role option");
		num.selectRoleOption(role);

		logger.info("Clicking save button");
		num.clickSave();

		// refreshing after adding User
		logger.info("Refreshing page after adding User");
		ulp.waitForModalCloseAndRefresh();

		// validating newUser creation
		logger.info("Validating if user is present in  the table", email);
		boolean isUserAdded = ulp.isUserPresentInTheTable(email);
		System.out.println("Looking for email: " + email);
		System.out.println("User added? " + isUserAdded);

		logger.info("User added status", isUserAdded);
		Assert.assertTrue(isUserAdded, "User email was not found in the table!");
		logger.info("===== Test Completed: verifyLoginTest =====");
	}

}
