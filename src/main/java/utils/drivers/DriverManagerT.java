package utils.drivers;

import org.openqa.selenium.WebDriver;

public abstract class DriverManagerT {

  protected WebDriver driver;

  public WebDriver getDriver() {
    if (null == driver) {
        createDriver();
    }
    return driver;
  }

  public void setDriver(WebDriver driver) {
    this.driver = driver;
  }

  protected abstract void createDriver();

}
