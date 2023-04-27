package utils.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import utils.log.LogHelper;

import java.lang.reflect.InvocationTargetException;

public class SafariDriverManager extends DriverManagerT {

  private static final Logger log = LogHelper.getLogger();

  @Override
  protected void createDriver() {
    DriverManagerType safari = DriverManagerType.SAFARI;
    WebDriverManager.getInstance(safari).setup();
    try {
      Class<?> safariClass = Class.forName(safari.browserClass());
      driver = (WebDriver) safariClass.getDeclaredConstructor().newInstance();
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException | NoSuchMethodException | SecurityException
        | ClassNotFoundException e) {
      log.error(
          "Class WebKeywords | Method openBrowser | for safari, error message: " + e.getMessage());
    }
  }

}
