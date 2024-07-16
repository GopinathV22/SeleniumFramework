package FrameWorks.SeleniumFrameworkDesign;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import FrameWorks.Testcomponents.BaseTest;
import FrameWorks.Testcomponents.RetryAnalyser;
import SeleniumFrameworkDesign.pageobjects.CartPage;
import SeleniumFrameworkDesign.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(retryAnalyzer=RetryAnalyser.class)
	public void loginErrorValidation() throws IOException {

		lp.loginToSite("gopinathv@gmail.com", "Gopiecom@12/");
		Assert.assertEquals(lp.getErrorMessage(), "Incorrect email or password.");

	}
	
	@Test(groups = {"ProductErrorValidation"})
	public void productErrorValidation() throws InterruptedException {
		
		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue = lp.loginToSite("gopinathv@gmail.com", "Gopiecom@123");		
		productCatalogue.getProducts();
		productCatalogue.getProductByName(productName);
		CartPage addToCart = productCatalogue.addToCart(productName);
		addToCart.viewCart();
		boolean verifyCorrectProductIsAdded = addToCart.verifyCorrectProductIsAdded("Adidas Original");
		Assert.assertFalse(verifyCorrectProductIsAdded);
	}

}
