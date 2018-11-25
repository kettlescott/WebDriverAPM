package automation.web.base.test;


import automation.web.base.utils.ConfigManager;
import automation.web.html.utils.Helper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import static io.github.bonigarcia.wdm.DriverManagerType.IEXPLORER;

public abstract class Test {
    private WebDriver driver;

    private static DriverManagerType type;

    private String testName;

    private ExtentReports report;

    private ExtentTest test;

    private static ConfigManager configManager = ConfigManager.instance() ;

    private static String DATE_FORMAT = "dd/MM/yyyy hh/mm/ss";

    private static String root;

    private static HashMap<String,DriverManagerType> webdriver;

    //type, url will be loaded from a configuration manager
    static {
        type = getType(configManager.getByKey("DRIVER").toUpperCase());
        root = new File(configManager.getByKey("REPORT")).getAbsolutePath() + "/"
                + "report_" +
                Helper.getCurrentTimestamp(DATE_FORMAT);
        Helper.createFolder(root);
    }


    @BeforeClass
    public void beforeClass() {
        WebDriverManager manager = WebDriverManager.getInstance(type);
        manager.proxy(configManager.getByKey("PROXY"));
        manager.setup();
        report = new ExtentReports();
        report.attachReporter(new ExtentHtmlReporter(root + "/" + "report.html"));

    }



    @BeforeMethod
    public void before(Method method) {
        testName = method.getName();
        initDriver(type);
        test = report.createTest(testName);
    }


    private void initDriver(DriverManagerType type) {
        switch (type) {
            case EDGE:
                driver = new EdgeDriver();
                break;
            case CHROME:
                driver = new ChromeDriver();
                break;
            default:
                throw new RuntimeException("could not find a proper driver : " + type.toString());
        }
    }

    private static DriverManagerType getType (String driver){
        if (driver.equals("CHROME")) {
            return CHROME ;
        } else if (driver.equals("IEXPLORER")) {
            return IEXPLORER ;
        } else {
            throw new RuntimeException("cannot find driver type :" + driver + " expected : {CHROME, IEXPLORER" +
                    "}" );
        }

    }

    @AfterMethod
    public void cleanup(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass(testName + " passed") ;
        } else {
            test.fail(testName + " failed") ;
            try {
                test.addScreenCaptureFromPath(takeScreenshot());
            } catch (Exception e){ e.printStackTrace();}
        }
        report.flush();
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void given (String given){
        test.info(given);
    }

    public void when (String when){
        test.info(when);
    }

    public void then (String then){
        test.info(then);
    }


    private String takeScreenshot () throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File file = new File(root + "/" + testName + "_"
                + Helper.getCurrentTimestamp(DATE_FORMAT) + ".png");
        FileUtils.copyFile(scrFile, file);
        return file.getAbsolutePath() ;

    }

}
