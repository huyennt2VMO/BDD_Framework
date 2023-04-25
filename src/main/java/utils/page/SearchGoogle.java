package utils.page;

import utils.basepage.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utils.testobject.TestObject;
import utils.keywords.WebKeywords;

import java.util.Objects;

public class SearchGoogle extends Page {
    TestObject to = new TestObject();

    public SearchGoogle(String url) {
       this.action = new WebKeywords();
        String objectRepoPath = "obj_repository";
//        this.action = action;
        this.action.openBrowser(url);
        this.action.maximizeWindow();
        this.setRepoFile(objectRepoPath);
    }

    public void pressTab() {
        action.getElement(By.xpath("//textarea[@name='q']")).sendKeys(Keys.ENTER);
    }

    public void inputText(String text) {
        //
        action.setText(By.xpath("//textarea[@name='q']"), text);
    }

    public void clickButtonSearch() {
        action.click(By.xpath("//div[@class='lJ9FBc']//input[@name='btnK']"));
    }

//    public boolean shouldBeUserNameMessageErrorAs(String exptectedResult) {
//        return action.verifyElementText(findTestObject("LBL_MESSAGE_RESULT"), exptectedResult);
//    }
//
    public boolean shouldBePasswordMessageErrorAs(String exptectedResult) {
        return action.verifyElementText(findTestObject("LBL_MESSAGE_RESULT"), exptectedResult);
    }
//
//    public boolean verifyResult(String exptectedResult){
//
//    }

    public String getTotalResult() {
        WebElement webElement = this.action.getElement(By.xpath("//div[@id='result-stats']"), 2000);
        if (Objects.isNull(webElement)) {
            return "";
        }
        return webElement.getText().trim();
    }
}
