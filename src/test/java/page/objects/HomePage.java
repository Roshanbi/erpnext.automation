package page.objects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	//constructor
	public HomePage(WebDriver driver) {
		super (driver);
	}
	
	//Locators
	
	@FindBy(xpath="//h3[text()='Home']")
	WebElement label_Home;
	
	@FindBy(xpath="//a[@title='Users']//span[2]")
	WebElement label_Users;
	
	//Action method
	
	public boolean isHomePageDisplayed() {
		return label_Home.isDisplayed();
		
	}
	
	public void clickUsers() {
		label_Users.click();
	}

}
