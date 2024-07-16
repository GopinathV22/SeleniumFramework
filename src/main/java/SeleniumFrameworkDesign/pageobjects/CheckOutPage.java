package SeleniumFrameworkDesign.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumframework.abstractcomponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement selectCountry;

	@FindBy(xpath = "//button[@type='button'][2]")
	WebElement clickCountry;

	@FindBy(css = ".btnn")
	WebElement placeOrder;

	By coutryList = By.cssSelector(".ta-results");
	By country = By.cssSelector("[placeholder='Select Country']");

	public void coutrySelection(String countryName) throws InterruptedException {

		selectCountry.sendKeys(countryName);
		waitForElementToAppear(coutryList);
		scrollDown();
		clickCountry.click();
	}

	public ConfirmationPage placeOrder() {

		placeOrder.click();
		return new ConfirmationPage(driver);
	}

}
