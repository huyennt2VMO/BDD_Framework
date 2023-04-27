package utils.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManagerT extends DriverManagerT {

	@Override
	public void createDriver() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}

}
