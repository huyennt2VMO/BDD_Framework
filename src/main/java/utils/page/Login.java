package utils.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import utils.basepage.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import utils.log.LogHelper;
import utils.testobject.TestObject;
import utils.keywords.WebKeywords;

import java.util.ArrayList;
import java.util.List;
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




}
