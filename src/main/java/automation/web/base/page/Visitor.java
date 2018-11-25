package automation.web.base.page;

import com.automation.agent.annotation.TracePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Visitor {

    private PageVisitor visitor;

    private static final Logger logger = LogManager.getLogger();

    public Visitor(WebDriver driver) {
        visitor = new PageVisitor(driver);
    }

    @TracePage
    public <T> T gotoPage(Class<T> c) {
        return visitor.visit(c, new Class[] {WebDriver.class}, new Object[] {}) ;
    }

    public void clearCache() {
        visitor.clearCache();
    }
}
