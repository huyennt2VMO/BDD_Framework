package utils.keywords;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import utils.configs.ConfigSettings;
import utils.drivers.DriverManagerFactoryT;
import utils.drivers.DriverManagerFactoryT.DriverType;
import utils.drivers.DriverManagerT;
import utils.helpers.WebCommonHelper;
import utils.log.LogHelper;
import utils.testobject.TestObject;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.function.Function;

public class WebKeywords {
	private static Logger logger = LogHelper.getLogger();
	public static DriverManagerT driverManagerT;
	private ConfigSettings config;
	private static String browser;
	private static int defaultTimeout;

	public WebKeywords() {
		config = new ConfigSettings(System.getProperty("user.dir"));
		browser = config.getBrowser();
		defaultTimeout = Integer.parseInt(config.getDefaultTimeout());
	}

	public void openBrowser(String... url) {
		logger.info("Running with " + browser);
		driverManagerT = DriverManagerFactoryT.getManager(DriverType.valueOf(browser.toUpperCase()));
		WebDriver driver = driverManagerT.getDriver();
		logger.info(MessageFormat.format("Open ''{0}'' successfully", browser.toUpperCase()));
		String rawUrl = url.length > 0 ? url[0] : "";
		if (rawUrl != null && !rawUrl.isEmpty()) {
			driver.get(rawUrl);
			logger.info(MessageFormat.format("Navigate to ''{0}'' successfully", rawUrl));
		}
	}

	public WebElement getElement(Object locator, int... timeout) {
		final By by = WebCommonHelper.findBy(locator);
		int waitTime = timeout.length > 0 ? timeout[0] : defaultTimeout;
		int poolingTime = waitTime / 10;
		WebDriver driver = driverManagerT.getDriver();
		String weId = WebCommonHelper.findTestObject(locator);
		WebElement we = null;
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(waitTime))
					.pollingEvery(Duration.ofSeconds(poolingTime)).ignoring(NoSuchElementException.class);
			we = (WebElement) wait.until(new Function<WebDriver, WebElement>() {

				@Override
				public WebElement apply(WebDriver driver) {
					// TODO Auto-generated method stub
					return driver.findElement(by);
				}
			});
			if (we != null) {
				logger.info(MessageFormat.format(
						"Found {0} web elements with id: ''{1}'' located by ''{2}'' in ''{3}'' second(s)", 1, weId, by,
						waitTime));
				return we;
			}
		} catch (TimeoutException e) {
			logger.error(MessageFormat.format("Reached Timeout. Unable to find web element located by ''{0}''", by));
		}
		return null;
	}

	public void click(Object locator, int... timeout) {
		try {
			TestObject to = new TestObject();
			String weId = to.getObjectName();
			WebElement we = getElement(locator, timeout);
			if (we.isEnabled()) {
				logger.info(MessageFormat.format("Clicking web element ''{0}''", weId));
				we.click();
				logger.info(MessageFormat.format("Clicked web element ''{0}'' successfully", weId));
			} else {
				logger.error(MessageFormat
						.format("Unable to click web element ''{0}'' because the web element is not enable", weId));
			}
		} catch (Exception e) {
			logger.error(MessageFormat.format("Unable to click web element because ''{0}''", e.getMessage()));
		}
	}

	public void setText(Object locator, String text, int... timeout) {
		TestObject to = new TestObject();
		String weId = to.getObjectName();
		WebElement we = getElement(locator, timeout);
		try {
			if (we.isEnabled()) {
				logger.info(MessageFormat.format("Clearing web element ''{0}''", weId));
				we.clear();
				logger.info(MessageFormat.format("Cleared web element ''{0}'' successfully", weId));
				we.sendKeys(text);
				;
				logger.info(MessageFormat.format("Set text ''{0}'' to web element ''{1}'' successfully", text, weId));
			} else {
				logger.error(MessageFormat.format(
						"Unable to set text ''{0}'' to web element ''{1}'' because the web element is not enable", text,
						weId));
			}
		} catch (Exception e) {
			logger.error(MessageFormat.format("Unable to set text ''{0}'' to web element ''{1}'' because ''{2}''", text,
					weId, e.getMessage()));
		}
	}
	
	public void closeBrowser() {
		logger.info("Closing browser");
		WebDriver driver = driverManagerT.getDriver();
		driver.quit();
		logger.info("Close browser successfully");
	}

	public String getText(Object locator, int... timeout) {
		TestObject to = new TestObject();
		String weId = to.getObjectName();
		String actualText = "";
		try {
			WebElement we = getElement(locator, timeout);
			if (we != null) {
				logger.info(MessageFormat.format("Getting text of web element ''{0}''", weId));
				actualText = we.getText();
				logger.info(MessageFormat.format("Text of web element ''{0}'' is ''{1}''", weId, actualText));
			} else {
				logger.error(MessageFormat.format("Unable to get text of web element ''{0}''", weId));
			}
		} catch (Exception e) {
			logger.error(MessageFormat.format("Unable to get text of web element because ''{0}''", e.getMessage()));
		}
		return actualText;
	}
	
	public boolean verifyElementText(Object locator, String expectedText, int... timeout) {
		TestObject to = new TestObject();
		String weId = to.getObjectName();
		try {
			WebElement we = getElement(locator, timeout);
			if (we != null) {
				String actualtText = we.getText().trim();
				if (actualtText.equals(expectedText)) {
					logger.info(MessageFormat.format(
							"Actual text ''{0}'' and Expected text ''{1}'' of web element ''{2}'' are the same",
							actualtText, expectedText, weId));
					return true;
				} else {
					logger.error(MessageFormat.format(
							"Actual text ''{0}'' and Expected text ''{1}'' of web element ''{2}'' are not the same",
							actualtText, expectedText, weId));
				}
			} else {
				logger.error("Unable to find web element");
			}

		} catch (Exception e) {
			logger.error(MessageFormat.format("Unable to verify web element text because ''{0}''", e.getMessage()));
		}
		return false;
	}

	public void maximizeWindow() {
		WebDriver driver = driverManagerT.getDriver();
		driver.manage().window().maximize();
		logger.info("Maximized window successfully");
	}

}
