package page.objects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserListPage extends BasePage {

	// Constructor
	public UserListPage(WebDriver driver) {
		super(driver);
	}

	// Locators
	@FindBy(xpath = "//h3[text()='User']")
	WebElement UserTitle;

	@FindBy(xpath = "//button[@data-label='Add User']")
	WebElement AddUserBtn;

	// Action methods

	public boolean isUserTitleDisplayed() {
		return UserTitle.isDisplayed();

	}

	public boolean isAddUserButtonDisplayed() {
		return AddUserBtn.isDisplayed();

	}

	public void clickAddUser() {
		AddUserBtn.click();
	}
	
	

	public void waitForModalCloseAndRefresh() {
	    WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));

	    // Wait for modal to become invisible
	    WebElement modal = driver.findElement(By.xpath("//div[contains(@class,'modal-content')]"));
	    wait.until(ExpectedConditions.invisibilityOf(modal));

	    // Force page reload to get updated user list
	    this.driver.navigate().refresh();

	    // Wait for the list container to reload
	    wait.until(ExpectedConditions.visibilityOfElementLocated(
	        By.xpath("//div[@class='result']")));
	}

	// this is for basic email check
	public boolean isUserPresentInTheTable(String email) {

		 By userEmailLocator = By.xpath("//a[normalize-space()='" + email + "']");
		/*By userEmailLocator = By.xpath(
				"//div[@class='result']//div[@class='list-row-container']//div[1]//div[1]//div[5]//span[1]//a[text()='"
						+ email + "']");*/

		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
		try{
			WebElement userEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(userEmailLocator));
			System.out.println("User email:"+userEmail);
			return userEmail.isDisplayed();
		}catch (TimeoutException e){
			System.out.println("User not found in list: " + email);
	        return false;
		}

		

	}

	// this is for full data validation

	/*
	 * public boolean isUserRowCorrect(String expectedName, String expectedStatus,
	 * String expectedRole,String expectedEmail) { List<WebElement> rows = driver
	 * .findElements(By.xpath(
	 * "//div[@class='result']//div[@class='list-row-container']"));
	 * 
	 * WebElement row1 =
	 * driver.findElement(By.xpath("//div[@class='result']//div[1]"));
	 * 
	 * for (WebElement row : rows) { String actualFullName =
	 * row.findElement(By.xpath(
	 * "//div[@class='result']//div[@class='list-row-container']//span[2]//a")).
	 * getText(); String actualStatus =row.findElement(By.xpath(
	 * "//div[@class='result']//div[@class='list-row-container']//div[1]//div[1]//div[3]//span[1]//span"
	 * )).getText(); String actualRole = row.findElement(By.xpath(
	 * "//div[@class='result']//div[@class='list-row-container']//div[1]//div[1]//div[4]//span[1]//a"
	 * )).getText(); String actualEmail =row.findElement(By.xpath(
	 * "//div[@class='result']//div[@class='list-row-container']//div[1]//div[1]//div[5]//span[1]//a"
	 * )).getText();
	 * 
	 * if(actualFullName.equalsIgnoreCase(expectedName) &&
	 * actualStatus.equalsIgnoreCase(expectedStatus) &&
	 * actualRole.equalsIgnoreCase(expectedRole) &&
	 * actualEmail.equalsIgnoreCase(expectedEmail)) { return true; } }
	 * 
	 * return false;
	 * 
	 * }
	 */

}
