package utils.drivers;

public class DriverManagerFactoryT {

	public enum DriverType {
		FIREFOX, FIREFOX_HEADLESS, CHROME, CHROME_HEADLESS, IE, SAFARI, EDGE
	}

	public static DriverManagerT getManager(DriverType type) {

		DriverManagerT driverManagerT = null;

		switch (type) {
		case CHROME:
			driverManagerT = new ChromeDriverManagerT();
			break;
		case CHROME_HEADLESS:
			driverManagerT = new ChromeHeadlessDriverManager();
			break;
		case FIREFOX:
			driverManagerT = new FirefoxDriverManagerT();
			break;
		case FIREFOX_HEADLESS:
			driverManagerT = new FirefoxHeadlessDriverManager();
			break;
		case IE:
			driverManagerT = new IEDriverManager();
			break;
		case SAFARI:
			driverManagerT = new SafariDriverManager();
			break;
		case EDGE:
			driverManagerT = new EdgeDriverManager();
			break;
		}
		return driverManagerT;

	}
}
