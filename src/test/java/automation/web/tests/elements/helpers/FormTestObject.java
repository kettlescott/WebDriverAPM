package automation.web.tests.elements.helpers;

import automation.web.base.elements.widget.*;
import automation.web.html.pageobject.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import automation.web.base.elements.base.Element;

import java.io.File;
import java.util.List;

/**
 * declare elements of a form.
 */
public class FormTestObject extends Page {

    private static final String FORM_PATH = System.getProperty("user.dir")+"\\src\\test\\resources\\"+"forms.html";
    public TextInput texta;

    @FindBy(id = "test1")
    public Element element;

    @FindBy(id = "test1")
    public WebElement webElement;
    
    public Select option1;

    @FindBy(id = SelectFragment.ID_LOCATOR)
    public SelectFragment selectFragment;

    @FindBy(id = "checkbox")
    public CheckBox checkbox;
    
    @FindBy(id = "table")
    public Table table;

    @FindBy(tagName = "label")
    public List<Label> labels;

    @FindBy(tagName = "label")
    public List<Element> elementLabels;

    @FindBy(tagName = "label")
    public List<WebElement> webElementLabels;

    @FindBy(css = "label[for='textb']")
    public WebElement labelForTextB;

    public FormTestObject(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean exists() {
        File f = new File(FORM_PATH);
        return (f.exists() && !f.isDirectory());
    }

    /**
     * Static factory for generating the class.
     *
     * @param driver The WebDriver for the session.
     * @return FormTestObject.
     */
//    public static FormTestObject initialize(WebDriver driver) {
//        return ElementFactory.initElements(driver, FormTestObject.class);
//    }

    public void get() {
        driver.get(System.getProperty("user.dir")+"\\src\\test\\resources\\"+"forms.html");
    }

    public void close() {
        driver.close();
    }

}
