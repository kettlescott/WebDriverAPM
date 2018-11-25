package automation.web.base.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Visitor {

    private PageVisitor visitor;

    private static final Logger logger = LogManager.getLogger();

    public Visitor(WebDriver driver) {
        visitor = new PageVisitor(driver);
    }

    public <T> T gotoPage(Class<T> c) {
        T t = visitor.visit(c, new Class[] {WebDriver.class}, new Object[] {});
        logger.debug("[APM]{NODE:PAGE_" + c.getSimpleName() + ", TIME :" + System.nanoTime() + "};");
        return t;
    }

    public void clearCache() {
        visitor.clearCache();
    }
}
