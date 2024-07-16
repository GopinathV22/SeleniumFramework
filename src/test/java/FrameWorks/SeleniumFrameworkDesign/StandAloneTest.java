package FrameWorks.SeleniumFrameworkDesign;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FrameWorks.Testcomponents.BaseTest;
import SeleniumFrameworkDesign.pageobjects.CartPage;
import SeleniumFrameworkDesign.pageobjects.CheckOutPage;
import SeleniumFrameworkDesign.pageobjects.ConfirmationPage;
import SeleniumFrameworkDesign.pageobjects.OrdersPage;
import SeleniumFrameworkDesign.pageobjects.ProductCatalogue;

public class StandAloneTest extends BaseTest {

	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = "Purchase")
	public void StandAlone(HashMap<String, String> input) throws IOException, InterruptedException {

		String countryName = "India";
		ProductCatalogue productCatalogue = lp.loginToSite(input.get("email"), input.get("password"));
		productCatalogue.getProducts();
		productCatalogue.getProductByName(input.get("productName"));
		CartPage addToCart = productCatalogue.addToCart(input.get("productName"));
		addToCart.viewCart();
		boolean addedProduct = addToCart.verifyCorrectProductIsAdded(input.get("productName"));
		Assert.assertTrue(addedProduct);
		CheckOutPage checkOut = addToCart.checkOut();
		checkOut.coutrySelection(countryName);
		ConfirmationPage placeOrder = checkOut.placeOrder();
		boolean verifyOrderIsPlaced = placeOrder.verifyOrderIsPlaced();
		Assert.assertTrue(verifyOrderIsPlaced);

	}

	@Test(dependsOnMethods = {"StandAlone"}, alwaysRun = true)
	public void OrderIsDisplayed() {

		ProductCatalogue productCatalogue = lp.loginToSite("gopinathv@gmail.com", "Gopiecom@123");
		OrdersPage goToOrderHistoryPage = productCatalogue.goToOrderHistoryPage();
		Assert.assertTrue(goToOrderHistoryPage.verifyCorrectOrderedProductIsPlaced(productName));

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		// Getting the Data from Json file(Another way).
		List<HashMap<String, String>> jsonData = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\Framework\\data\\PurchaseOrder.json");
		return new Object[][] { { jsonData.get(0) }, { jsonData.get(1) } };

	}

	// Oneway of providing datasets(For DataProvider)
	/*
	 * HashMap<String, String> set1 = new HashMap<>(); set1.put("email",
	 * "gopinathv@gmail.com"); set1.put("password", "Gopiecom@123");
	 * set1.put("productName", "ADIDAS ORIGINAL");
	 * 
	 * HashMap<String, String> set2 = new HashMap<>(); set2.put("email",
	 * "shetty@gmail.com"); set2.put("password", "Iamking@000");
	 * set2.put("productName", "IPHONE 13 PRO");
	 */
}
