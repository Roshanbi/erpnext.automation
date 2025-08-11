package page.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	// constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	// Locators

	@FindBy(xpath = "//input[@id='login_email']")
	WebElement txtEmailAddress;

	@FindBy(xpath = "//input[@id='login_password']")
	WebElement txtPassword;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement btnLogin;

	// Action methods

	public void login(String email, String pwd) {
		txtEmailAddress.sendKeys(email);
		txtPassword.sendKeys(pwd);
		btnLogin.click();
	}

	

}
