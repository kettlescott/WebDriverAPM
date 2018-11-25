package automation.web.html.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavascriptLib {


    private WebDriver driver ;
    private JavascriptExecutor js;

    public JavascriptLib(WebDriver driver){
        this.driver = driver ;
    }

    public Object sendJSCommand (String command, Object ... objects){
        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor)driver;
            return js.executeScript(command, objects) ;
        }
        throw new RuntimeException("driver is not an instance of  JavascriptExecutor") ;
    }
}
