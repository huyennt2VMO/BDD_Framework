package test;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import utils.keywords.WebKeywords;
import utils.log.LogHelper;
import utils.page.Cart;

public class CartTest {
    public WebKeywords action;

    private final Logger logger = LogHelper.getLogger();

    private final Cart cartPage;

    public CartTest() {
        this.action = new WebKeywords();
        this.cartPage = new Cart(this.action);
    }

    public CartTest(WebKeywords action, Cart cart) {
        this.action = action;
        this.cartPage = cart;
    }

    @Step( "check item in cart")
    @Test(priority = 5)
    public void checkItemInCart() throws InterruptedException {

        this.cartPage.checkCart();
    }
    @Step("checkout")
    @Test(priority = 6)
    public void toCheckout() {
        this.cartPage.checkout();
    }
    @Step("Input info")
    @Test(priority = 7)
    public void InputInfor() {
        this.cartPage.inputInfo();
    }
    @Step("chekcout continue")
    @Test(priority = 8)
    public void checkoutContinue() {
        this.cartPage.checkoutContinue();
    }
    @Step("chekcout finish")
    @Test(priority = 9)
    public void finishChekcout() {
        this.cartPage.finish();
    }
    @Step("back to list")
    @Test(priority = 10)
    public void backToList() {
        this.cartPage.backTOList();
    }
}
