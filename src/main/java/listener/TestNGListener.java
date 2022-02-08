package listener;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.testng.ITestNGListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import utils.keywords.WebKeywords;
import utils.log.LogHelper;

public class TestNGListener implements ITestNGListener{
	
	public WebDriver driver;

	public WebKeywords action = new WebKeywords();

	private Logger logger = LogHelper.getLogger();

	public TestNGListener() {
		action = new WebKeywords();
	}
	
	public TestNGListener(WebKeywords action) {
		this.action = action;
	}

	@BeforeSuite
	public void beforeSuite() {
		logger.info("Before Suite");
	}

	@AfterSuite
	public void afterSuite() {
		logger.info("After Suite");
	}

	@BeforeTest
	public void beforeTest() throws Throwable {
		this.action.openBrowser("http://demo.whatacart.com/backend/index.php");
		this.action.maximizeWindow();
		logger.info("Before Test");
	}

	@AfterTest
	public void afterTest() {
		action.closeBrowser();
		logger.info("After Test");
	}

	@BeforeClass
	public void beforeClass() throws Throwable {
		logger.info("Before Class");

	}

	@AfterClass
	public void afterClass() {
		logger.info("After Class");
	}

	@BeforeMethod
	public void beforeMethod() throws Throwable {
		logger.info("Before Method");

	}

	@AfterMethod
	public void afterMethod() {

		logger.info("After Method");
	}
}
