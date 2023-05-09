package test;

import static org.testng.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import listener.TestNGListener;
import manager.AllureManager;
import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.testng.ITestNGListener;
import org.testng.annotations.*;

import utils.page.Home;
import utils.page.Login;
import utils.keywords.WebKeywords;
import utils.log.LogHelper;

import java.sql.DriverManager;

public class LoginTest extends TestNGListener {

    private Login objLogin;
    private Home homePage;
    //	public WebKeywords action;
    public WebDriver driver;

    public WebKeywords action;

    private final Logger logger = LogHelper.getLogger();

    @BeforeSuite
    public void beforeSuite() {
        logger.info("Before Suite");
        AllureManager.getInstants().setAllureEnv("https://www.saucedemo.com", "NO-ENV");
    }

    public LoginTest(WebKeywords action) {
        this.action = action;
    }

    public LoginTest() {
        this.action = new WebKeywords();
        this.objLogin = new Login(this.action);
    }

//    @Step("Login successfully {1}")
//    @Test(priority = 1, description = "SC_Login successfully")
//    public void SC_LoginSuccessfully() throws Throwable {
//        objLogin.inputUsername("standard_user");
//        objLogin.inputPassword("secret_sauce");
//        objLogin.clickButtonSignUp();
//        AllureManager.getInstants().saveScreenshotPNG();
//        AllureManager.getInstants().saveTextLog("login is success");
//    }


    @BeforeTest
    @Given("I am on the login page")
    @Override
    public void beforeTest() throws Throwable {
        this.action.openBrowser("https://www.saucedemo.com/");
        this.action.maximizeWindow();
        logger.info("Before Test");
    }

    @When("User logged in with email {string} and password {string}")
    @Step("Login successfully {2}")
    public void SC_userLogin(String userName, String password) {
        objLogin.inputUsername(userName);
        objLogin.inputPassword(password);
        objLogin.clickButtonSignUp();
        AllureManager.getInstants().saveScreenshotPNG();
        AllureManager.getInstants().saveTextLog("login is success");
    }

    @Then("I should see the dashboard page")
    public void SC_ValidUser() {

    }
}
