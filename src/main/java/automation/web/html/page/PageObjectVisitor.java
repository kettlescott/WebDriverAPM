package automation.web.html.page;

import com.automation.agent.annotation.TracePage;
import automation.web.base.page.PageVisitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class PageObjectVisitor {

    private PageVisitor visitor;

    private static final Logger logger = LogManager.getLogger();

    public PageObjectVisitor(WebDriver driver) {
        visitor = new PageVisitor(driver);
    }

    @TracePage
    public <T> T gotoPage(Class<T> c) {
        return visitor.visit(c, new Class[]{WebDriver.class}, new Object[] {});
    }

    @TracePage
    public <T> T gotoPage(Class<T> c, String title) {
        return visitor.visit(c, new Class[]{WebDriver.class,String.class}, new Object[] {title});
    }

    public void clearCache() {
        visitor.clearCache();
    }
}
