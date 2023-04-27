package utils.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import utils.basepage.Page;
import utils.keywords.WebKeywords;
import utils.log.LogHelper;
import utils.testobject.TestObject;

import java.util.*;
import java.util.stream.Collectors;

public class Home extends Page {

    private final Logger logger = LogHelper.getLogger();
    private final int selectItemCount = 2;

    private Map<String, WebElement> itemSelected = new HashMap<>();

    public Home(WebKeywords action) {
        String objectRepoPath = "obj_repository";
        this.action = action;
        this.setRepoFile(objectRepoPath);
    }

    public boolean checkTitle(String title) {
        return action.verifyElementText(findTestObject("LBL_TITLE"), title);
    }

    public void addToCartV2() {
        TestObject testObject = findTestObject("INVENTORY_ITEM");
        int selectItemCount = 2;
        WebDriver driverManagerT = WebKeywords.driverManagerT.getDriver();
        List<WebElement> webElements = new ArrayList<>();

        try {
            webElements = driverManagerT.findElements(By.xpath(testObject.getLocatorValue()));
            logger.info("existed element {}", webElements.size());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        // random item
        List<Integer> selectNumber = new ArrayList<>();
        for (int i = 0; i < selectItemCount; i++) {
            // check number existed
            int numberPicked = 0;
            do {
                numberPicked = this.randomNumber(webElements.size() - 1);
            } while (selectNumber.contains(numberPicked));
            selectNumber.add(numberPicked);
        }
        logger.info("selectNumber : {}", selectNumber);
        // item selected
        TestObject itemLabelXpath = findTestObject("LABEL_NAME");

        Map<String, WebElement> elementSelecteds = selectNumber.stream()
                .map(webElements::get)
                .collect(Collectors.toMap((w) -> {
                    List<WebElement> itemLabelName = w.findElements(By.xpath(itemLabelXpath.getLocatorValue()));
                    logger.info("name {}", itemLabelName);
                    return itemLabelName.get(0).getText();
                }, webElement -> webElement));
        logger.info("elementSelecteds map {}", elementSelecteds);
        // get element
        // button : (//div[@class='inventory_item'])//button
        // name : (//div[@class='inventory_item'])//div[@class="inventory_item_name"]

    }

    public boolean addToProductToCart() {
//        String xpath = "//button[contains(text(),'Add to cart')]";
        TestObject testObject = findTestObject("BTN_ADD_ITEM");
        this.logger.info("test object {}", testObject);
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
        // lấy 2 sản phâm b.ất kỳ
        selectNumber.stream().map(webElements::get)
                .forEach(WebElement::click);
        // check
        try {
            webElements = driverManagerT.findElements(By.xpath(testObject.getLocatorValue()));
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.info("elements not found {}", e.getMessage());
        }
        return selectNumber.stream().map(webElements::get)
                .allMatch(item -> Objects.equals(item.getText(), "Remove"));
    }

    private int randomNumber(int max) {
        Random rand = new Random();
        return rand.nextInt(max);
    }

    public void navigateToCart() {
//        WebElement webElement = this.action.getElement(this.findTestObject("BTN_SHOPPING_CART"));
//        webElement.click();

        action.click(findTestObject("BTN_SHOPPING_CART"));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
