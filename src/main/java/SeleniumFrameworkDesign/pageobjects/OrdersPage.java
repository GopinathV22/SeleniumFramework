package SeleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage {

	WebDriver driver;

	public OrdersPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//tr/td[2]")
	List<WebElement> myOrderedProducts;

	public boolean verifyCorrectOrderedProductIsPlaced(String productName) {

		Boolean myProducts = myOrderedProducts.stream().anyMatch(product -> product.getText().equals(productName));
		return myProducts;

	}
}
