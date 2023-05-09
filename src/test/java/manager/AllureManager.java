package manager;

import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.configs.ConfigSettings;
import utils.drivers.DriverManagerFactoryT;
import utils.keywords.WebKeywords;

import java.util.Objects;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class AllureManager {
    private ConfigSettings config;
    private String browser;

    private AllureManager() {
        this.config = new ConfigSettings(System.getProperty("user.dir"));
        browser = config.getBrowser();
    }

    private static AllureManager allureManager;

    public static AllureManager getInstants() {

        if (Objects.isNull(allureManager)) {
            allureManager = new AllureManager();
        }
        return allureManager;
    }

    public void setAllureEnv(String url, String env) {
//        Allure.addAttachment("Environment", "Environment: " + env);
//        Allure.addAttachment("Environment", "Browser: " + browser);
//        Allure.addAttachment("Environment", "URL: " + url);
//        Allure.addAttachment("Environment", "App Version: " + EnvironmentInfo.appVersion);
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder().put("Browser", browser).put("Browser.Version", "70.0.3538.77")
                        .put("URL", url).build(),
                System.getProperty("user.dir") + "/allure-results/");

    }

    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public String saveTextLog(String message) {
        return message;
    }

    //Screenshot attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG() {
//        WebDriver driver = DriverManagerFactoryT.getManager(DriverManagerFactoryT.DriverType.valueOf(browser.toUpperCase())).getDriver();
        WebDriver driver = new WebKeywords().driverManagerT.getDriver();
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
