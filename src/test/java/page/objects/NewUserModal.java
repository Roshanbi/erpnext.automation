package page.objects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.base.DriverFactory;

public class NewUserModal extends BasePage {

	// constructor
	public NewUserModal(WebDriver driver) {
		super(driver);
	}

	// Locators
	@FindBy(xpath = "//input[@data-fieldname='email']")
	WebElement emailField;

	@FindBy(xpath = "//input[@data-fieldname='first_name']")
	WebElement firstNameField;

	@FindBy(xpath = "//div[@role='dialog']//div[@class='modal-dialog']//div[@class='modal-content']//div[@class='modal-body ui-front']//div//input[@role='combobox']")
	WebElement roleProfileField;

	@FindBy(xpath = "//button[text()='Save']")
	WebElement btnSave;

	// Action methods

	public void setEmail(String email) {
		emailField.sendKeys(email);
	}

	public void setFirstNameField(String firstName) {
		firstNameField.sendKeys(firstName);
	}

	public void selectRoleOption(String profileName) throws InterruptedException {
		try {

			
			roleProfileField.click();
			Thread.sleep(3000);

			By optionLocator = By.xpath(" //strong[normalize-space()='" + profileName + "']");
			WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
			WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator));

			System.out.println(option.getText());
			option.click();
			System.out.println(" Successfully selected role: " + profileName);
		} catch (Exception e) {
			System.out.println(" Failed to select role: " + profileName);
		}
	}

	public void clickSave() {
		btnSave.click();
	}
}
