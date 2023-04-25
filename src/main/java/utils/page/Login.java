package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import basepage.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import utils.drivers.DriverManagerT;
import utils.log.LogHelper;
import utils.testobject.TestObject;
import utils.keywords.WebKeywords;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class Login extends Page {

    private final Logger logger = LogHelper.getLogger();

    //	private static String TXT_USER_NAME = "//input[@id='loginform-username']";
//
//	private static String LBL_MES_USERNAME = "//div[@class='form-group has-feedback field-loginform-username required has-error']//p";
//
//	private static String TXT_PASSWORD = "//input[@id='loginform-password']";
//
//	private static String LBL_MES_PASSWORD = "//div[@class='form-group has-feedback field-loginform-password required has-error']//p";
//
//	private static String BTN_SIGNIN = "//button[@class='btn btn-warning pull-right']";
    TestObject to = new TestObject();

    public Login(WebKeywords action) {
        String objectRepoPath = "obj_repository";
        this.action = action;
        this.setRepoFile(objectRepoPath);
    }

    public void pressTab() {
        action.getElement(findTestObject("TXT_USER_NAME")).sendKeys(Keys.TAB);
    }

    public void inputUsername(String username) {
        action.setText(findTestObject("TXT_USER_NAME"), username);
    }

    public void inputPassword(String password) {
        action.setText(findTestObject("TXT_PASSWORD"), password);
    }

    public void clickButtonSignUp() {
        action.click(findTestObject("BTN_SIGNIN"));
    }

//	public boolean shouldBeUserNameMessageErrorAs(String expectedResult) {
//		return action.verifyElementText(findTestObject("LBL_MES_USERNAME"), expectedResult);
//	}
//
//	public boolean shouldBePasswordMessageErrorAs(String expectedResult) {
//		return action.verifyElementText(findTestObject("LBL_MES_PASSWORD"), expectedResult);
//	}

    public boolean checkTitle(String title) {
        return action.verifyElementText(findTestObject("LBL_PAGE_TITLE"), title);
    }

    public void addToProductToCart() {
//        String xpath = "//button[contains(text(),'Add to cart')]";
        TestObject testObject = findTestObject("BTN_ADD_ITEM");
        this.logger.info("test object {}", testObject);
        int selectItemCount = 2;
        WebDriver driverManagerT = WebKeywords.driverManagerT.getDriver();
        List<WebElement> webElements = new ArrayList<>();
        try {
            webElements = driverManagerT.findElements(By.xpath(testObject.getLocatorValue()));
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.info("elements not found {}", e.getMessage());
        }
        logger.info("webElement of {} = {}", testObject.getLocatorValue(), webElements);
        List<Integer> selectNumber = new ArrayList<>();
        for (int i = 0; i < selectItemCount; i++) {
            // check number existed
            int numberPicked = 0;
            do {
                numberPicked = this.randomNumber(webElements.size());
            } while (selectNumber.contains(numberPicked));
            selectNumber.add(numberPicked);
        }
        logger.info("selectNumber : {}", selectNumber);
        // lấy 2 sản phâm bất kỳ
        List<WebElement> webElementSelected = selectNumber.stream().map(webElements::get).peek(WebElement::click).collect(Collectors.toList());
        webElementSelected.forEach(item -> {
            logger.info("item {}", item.getText());
        });
    }

    private int randomNumber(int max) {
        Random rand = new Random();
        return rand.nextInt(max);
    }

    public void navigateToCart() {
        WebElement webElement = this.action.getElement(this.findTestObject("BTN_SHOPPING_CART"));
        webElement.click();
    }
}
