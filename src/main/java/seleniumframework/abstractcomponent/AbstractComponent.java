package seleniumframework.abstractcomponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFrameworkDesign.pageobjects.OrdersPage;

public class AbstractComponent {

	WebDriver driver;

	@FindBy(css = "[routerlink*='myorders']")
	WebElement btnMyOrders;
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement selectCountry;

	public AbstractComponent(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public OrdersPage goToOrderHistoryPage() {
		btnMyOrders.click();
		return new OrdersPage(driver);

	}

	public void waitForElementToAppear(By findBy) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	
	public void waitForElementToDisappear(By findBy) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.invisibilityOfElementLocated(findBy));

	}

	public void scrollDown() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(1000);
		js.executeScript("window.scroll(0,1000)");
	}

}
