package SeleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumframework.abstractcomponent.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "h3")
	List<WebElement> cartProducts;

	@FindBy(css = "button[routerlink*='cart']")
	WebElement cartIcon;

	@FindBy(css = ".totalRow button")
	WebElement checkOut;

	By toastMessage = By.cssSelector(".toast-message");
	By continueShopping = By.cssSelector(".btn-primary");

	public void viewCart() throws InterruptedException {

		waitForElementToAppear(toastMessage);
		Thread.sleep(1000);
		cartIcon.click();

	}

	public boolean verifyCorrectProductIsAdded(String productName) {

		waitForElementToAppear(continueShopping);
		Boolean productCart = cartProducts.stream().anyMatch(product -> product.getText().equals(productName));
		return productCart;

	}

	public CheckOutPage checkOut() throws InterruptedException {
		scrollDown();
		checkOut.click();
		return new CheckOutPage(driver);
	}

}
