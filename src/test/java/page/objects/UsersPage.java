package page.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UsersPage extends BasePage {
	
	//constructor
	public UsersPage(WebDriver driver) {
		super(driver);
	}
	
	//Locators	
	
	@FindBy(xpath="//h3[text()='Users']")
	WebElement usersHeading;
	
	@FindBy(xpath="//a[@title='User']//span[1]//span")
	WebElement userSubModule;
	
	//Action methods
	
	public boolean isUsersModuleDisplayed() {
		return usersHeading.isDisplayed();
		
	}

	public void clickUserSubModule() {
		userSubModule.click();
	}
}
