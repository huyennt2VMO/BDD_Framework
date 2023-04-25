package test;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.testng.ITestNGListener;
import org.testng.annotations.Test;
import utils.keywords.WebKeywords;
import utils.log.LogHelper;
import utils.page.Home;
import utils.page.Login;

import static org.testng.Assert.assertTrue;

public class HomeTest  {
    private final Home homePage;
    public WebKeywords action = new WebKeywords();

    private final Logger logger = LogHelper.getLogger();


    public HomeTest() {
        this.homePage = new Home(this.action);
    }

    @Step("check title")
    @Test(priority = 2)
    public void SC_checkTitle() {
        assertTrue(this.homePage.checkTitle("Swag Labs"));
    }
    @Step("SC_ADD_TO_CARD")
    @Test(priority = 3)
    public void addProductToCard() {
        assertTrue(this.homePage.addToProductToCart());
//        this.homePage.addToCartV2();
    }

    @Step("TO_CART")
    @Test(priority = 4)
    public void toCart() throws InterruptedException {
        this.homePage.navigateToCart();
    }


}
