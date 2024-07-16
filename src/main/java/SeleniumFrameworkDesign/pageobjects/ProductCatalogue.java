package SeleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumframework.abstractcomponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(className = "mb-3")
	List<WebElement> products;

	By productsList = By.className("mb-3");
	By eachProduct = By.cssSelector(".card-body b");
	By filteredProduct = By.cssSelector(".card-body button:last-of-type");

	public List<WebElement> getProducts() {

		waitForElementToAppear(productsList);
		return products;
	}

	public WebElement getProductByName(String productName) {

		WebElement prod = products.stream()
				.filter(product -> product.findElement(eachProduct).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;

	}

	public CartPage addToCart(String productName) {

		WebElement prod = getProductByName(productName);
		prod.findElement(filteredProduct).click();
		return new CartPage(driver);
	}

}
