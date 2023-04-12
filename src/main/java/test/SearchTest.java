package test;

import listener.TestNGListener;
import org.testng.annotations.Test;
import page.SearchGoogle;
import utils.keywords.WebKeywords;

public class SearchTest  {

//    ;

    public SearchTest(){

    }

//    public SearchTest(WebKeywords action){
//        super(action);
//    }

    @Test(description = "Test search successfull")
    public void SearchGoogleTest() throws InterruptedException {
        SearchGoogle   objSearch =new SearchGoogle("https://www.google.com/");
        objSearch.inputText("Selenium");
        objSearch.clickButtonSearch();
//        Thread.sleep(2000);
        System.out.println(" total result : " + objSearch.getTotalResult());

    }

}
