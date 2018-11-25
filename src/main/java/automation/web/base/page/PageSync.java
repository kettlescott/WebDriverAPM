package automation.web.base.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageSync {

    private PageSync(){

    }

    public static boolean waitForJSandJQueryToLoad(WebDriver driver) {
        return waitForJSandJQueryToLoad(driver,30);
    }

    public static boolean waitForJSandJQueryToLoad(WebDriver driver, int timeOutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver1 -> {
            try {
                return ((Long)((JavascriptExecutor) driver1).executeScript("return jQuery.active") == 0);
            }
            catch (Exception e) {
                // no jQuery present
                return true;
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver12 -> ((JavascriptExecutor) driver12).executeScript("return document.readyState")
                .toString().equals("complete");

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }



}
