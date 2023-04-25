package utils.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import utils.basepage.Page;
import utils.keywords.WebKeywords;
import utils.log.LogHelper;
import utils.testobject.TestObject;

import java.util.ArrayList;
import java.util.List;

public class Cart  extends Page {
    private final int selectItemCount = 2;

    private final Logger logger = LogHelper.getLogger();

    public Cart(WebKeywords action) {
        super(action);
        String objectRepoPath = "obj_repository";
        this.action = action;
        this.setRepoFile(objectRepoPath);
    }

    public boolean checkCart() {

        TestObject testObject = findTestObject("ITEM_IN_CART");
        WebDriver driverManagerT = WebKeywords.driverManagerT.getDriver();
        List<WebElement> webElements = new ArrayList<>();
        try {
            webElements = driverManagerT.findElements(By.xpath(testObject.getLocatorValue()));
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.info("elements not found {}", e.getMessage());
        }
        logger.info("item in cart {}", webElements.size());
        return this.selectItemCount == webElements.size();
    }

    public void checkout() {
//        this.action.click(findTestObject("CHECKOUT_BTN"));
        TestObject testObject = this.findTestObject("CHECKOUT_BTN");
        WebElement webElement = this.action.getElement(By.xpath(testObject.getLocatorValue()));
        webElement.click();
    }

    public void inputInfo() {
        this.action.setText(By.xpath(this.findTestObject("FIRST_NAME").getLocatorValue()),
                "huyen");
        this.action.setText(By.xpath(this.findTestObject("LAST_NAME").getLocatorValue()),
                "nguyen");
        this.action.setText(By.xpath(this.findTestObject("ZIP_CODE").getLocatorValue()),
                "10000");
    }

    public void checkoutContinue() {
        this.action.click(By.xpath(findTestObject("CHECKOUT_CONTINUE").getLocatorValue()));
    }

    public void finish() {
        this.action.click(By.xpath(findTestObject("FINISH_BTN").getLocatorValue()), 2000);
    }

    public void backTOList() {
        this.action.click(By.xpath(findTestObject("BACK_BTN").getLocatorValue()), 2000);

    }
}
