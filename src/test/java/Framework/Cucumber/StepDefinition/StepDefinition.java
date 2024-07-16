package Framework.Cucumber.StepDefinition;

import java.io.IOException;

import org.testng.Assert;

import FrameWorks.Testcomponents.BaseTest;
import SeleniumFrameworkDesign.pageobjects.CartPage;
import SeleniumFrameworkDesign.pageobjects.CheckOutPage;
import SeleniumFrameworkDesign.pageobjects.ConfirmationPage;
import SeleniumFrameworkDesign.pageobjects.LandingPage;
import SeleniumFrameworkDesign.pageobjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseTest{

	public LandingPage landingpage;
	public ProductCatalogue productCatalogue;
	public CartPage addToCart;
	public CheckOutPage checkOut;
	public ConfirmationPage placeOrder;
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingpage=launchApplication();
	}
	
	@Given("^Logged in to the website with Username (.+) and Password (.+)$")
	public void Logged_in_to_the_website_with_Username_and_Password(String username,String password) {
	    productCatalogue = landingpage.loginToSite(username,password);
	}
	
	@When("^Adding the product (.+) to the cart$")
	public void Adding_the_product_to_the_cart(String productName){
		productCatalogue.getProducts();
		productCatalogue.getProductByName(productName);
	    addToCart = productCatalogue.addToCart(productName);
	}
	
	@And("^Checkout (.+) and submitting the order with (.+)$")
	public void Checkout_and_submitting_the_order(String productName, String countryName) throws InterruptedException {
		addToCart.viewCart();
		boolean addedProduct = addToCart.verifyCorrectProductIsAdded(productName);
		Assert.assertTrue(addedProduct);
	    checkOut = addToCart.checkOut();
		checkOut.coutrySelection(countryName);
		placeOrder = checkOut.placeOrder();
	}
	
	@Then("Verify {string} message is displayed")
	public void Verify_message_is_displayed(String string) {
		boolean verifyOrderIsPlaced = placeOrder.verifyOrderIsPlaced();
		Assert.assertTrue(verifyOrderIsPlaced);
		closeBrowser();
	}
	
	@Then("Verify {string} error message is displayed")
	public void Verify_error_message_is_displayed(String string) {
		Assert.assertEquals(landingpage.getErrorMessage(), string);
		closeBrowser();
	}
	
}
