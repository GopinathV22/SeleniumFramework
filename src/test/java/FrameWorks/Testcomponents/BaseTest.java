package FrameWorks.Testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumFrameworkDesign.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage lp;

	public WebDriver InitializeDriver() throws IOException {

		Properties p = new Properties();
		FileInputStream f = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\seleniumframework\\resources\\GlobalData.properties");
		p.load(f);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : p.getProperty("browser");
		 

		if (browserName.contains("chrome")) {
 
			ChromeOptions options = new ChromeOptions();			
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
		} else if (browserName.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir")+"//reports//"+".png");
		FileUtils.copyFile(sourceFile, destFile);
		return System.getProperty("user.dir")+"//reports//"+".png";
	}

	public List<HashMap<String, String>> getJsonData(String path) throws IOException {

		String jsonContent = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = InitializeDriver();
		lp = new LandingPage(driver);
		return lp;

	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {

		driver.quit();
	}
}
