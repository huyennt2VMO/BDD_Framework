package utils.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class IEDriverManager extends DriverManagerT {

  @Override
  protected void createDriver() {
    WebDriverManager.iedriver().setup();
    driver = new InternetExplorerDriver();
  }

}
