package SeleniumFrameworkDesign.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	public WebDriver driver;
	

	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement emailId;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement login;
	
	@FindBy(css = "[class*='trigger']")
	WebElement errorMessage;

	public ProductCatalogue loginToSite(String emailID, String password) {
		
		driver.get("https://rahulshettyacademy.com/client");
		emailId.sendKeys(emailID);
		userPassword.sendKeys(password);
		login.click();
		return new ProductCatalogue(driver);
	}
	
	public String getErrorMessage() {
		String message = errorMessage.getText();
		return message;
	}

}
