package automation.web.html.test;

import com.automation.agent.annotation.TraceTestNGAfterAgent;
import com.automation.agent.annotation.TraceTestNGBeforeAgent;
import automation.web.base.test.Test;
import automation.web.html.page.PageObjectVisitor;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;




public abstract class AutomationTest extends Test {


    protected PageObjectVisitor pageObjectVisitor;



    @TraceTestNGBeforeAgent(productID = 1)
    @BeforeMethod
    public void before(Method method) {
        super.before(method);
        pageObjectVisitor = new PageObjectVisitor(getDriver());
    }



    @TraceTestNGAfterAgent
    @AfterMethod
    public void cleanup(ITestResult result) {
        super.cleanup(result);
        if (pageObjectVisitor != null) {
            pageObjectVisitor.clearCache();
        }
    }


}
