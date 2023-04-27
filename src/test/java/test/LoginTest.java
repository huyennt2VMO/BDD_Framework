package test;

import io.qameta.allure.Step;
import listener.TestNGListener;
import manager.AllureManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utils.keywords.WebKeywords;
import utils.log.LogHelper;
import utils.page.Home;
import utils.page.Login;

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
//
//    @AfterSuite
//    public void afterSuite() {
//        logger.info("After Suite");
//    }
//
//    @BeforeTest
//    public void beforeTest() throws Throwable {
//        this.action.openBrowser("https://www.saucedemo.com/");
//        this.action.maximizeWindow();
//        logger.info("Before Test");
//    }


//    @AfterMethod
//    public void afterMethod() {
//
//        logger.info("After Method");
//    }
//
//    public LoginTest() {
//        this.objLogin = new Login(this.action);
//        this.homePage = new Home(this.action);
//    }

    public LoginTest(WebKeywords action) {
        this.action = action;
    }

    public LoginTest() {
        this.action = new WebKeywords();
        this.objLogin = new Login(this.action);
    }

    @Step("Login successfully {1}")
    @Test(priority = 1, description = "SC_Login successfully")
    public void SC_LoginSuccessfully() throws Throwable {
        objLogin.inputUsername("standard_user");
        objLogin.inputPassword("secret_sauce");
        objLogin.clickButtonSignUp();
        AllureManager.getInstants().saveScreenshotPNG();
        AllureManager.getInstants().saveTextLog("login is success");
    }

//    @Step("check title")
//    public void SC_checkTitle() {
//        assertTrue(this.homePage.checkTitle("Swag Labs"));
//    }
//
//    @Step("SC_ADD_TO_CARD")
//    public void addProductToCard() {
//        assertTrue(this.homePage.addToProductToCart());
////        this.homePage.addToCartV2();
//    }
//
//    @Step("TO_CART")
//    public void toCart() throws InterruptedException {
//        this.homePage.navigateToCart();
//    }
//
//        @Step( "check item in cart")
//    public void checkItemInCart() throws InterruptedException {
//
//        this.homePage.checkCart();
//    }
//    @Step("checkout")
//    public void toCheckout() {
//        this.homePage.checkout();
//    }
//    @Step("Input info")
//    public void InputInfor() {
//        this.homePage.inputInfo();
//    }
//    @Step("chekcout continue")
//    public void checkoutContinue() {
//        this.homePage.checkoutContinue();
//    }
//    @Step("chekcout finish")
//    public void finishChekcout() {
//        this.homePage.finish();
//    }
//    @Step("back to list")
//    public void backToList() {
//        this.homePage.backTOList();
//    }
}
