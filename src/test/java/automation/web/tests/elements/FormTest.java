package automation.web.tests.elements;

import automation.web.tests.elements.helpers.FormTestObject;
import automation.web.base.elements.base.Element;
import automation.web.base.elements.widget.Label;
import automation.web.html.test.AutomationTest;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Test the form element types.
 */
public class FormTest extends AutomationTest {

    private static FormTestObject testObject;


    @BeforeMethod
    public void beforeMethod() {
        testObject = pageObjectVisitor.gotoPage(FormTestObject.class);
        testObject.get();
    }

    @org.testng.annotations.Test
    public void isChecked() {
        Assert.assertFalse(testObject.checkbox.isChecked());
    }

    @org.testng.annotations.Test
    public void check() {
        testObject.checkbox.check();
        Assert.assertTrue(testObject.checkbox.isChecked());
    }

    @org.testng.annotations.Test
    public void uncheck() {
        testObject.checkbox.click();
        testObject.checkbox.uncheck();
        Assert.assertFalse(testObject.checkbox.isChecked());
    }

    @org.testng.annotations.Test
    public void selectWiredProperly() {
        testObject.selectFragment.getOption1().selectByIndex(0);
        Assert.assertEquals(1, testObject.selectFragment.getOption1().getAllSelectedOptions().size());
        Assert.assertNotNull(testObject.selectFragment.getSubElement(By.id("option1")));
    }

    @org.testng.annotations.Test
    public void getLabelsList() {
        for (Label label : testObject.labels) {
            Assert.assertNotNull(label.getFor());
        }
    }

    @org.testng.annotations.Test
    public void getElementLabelsList() {
        for (Element webElementLabel : testObject.elementLabels) {
            Assert.assertNotNull(webElementLabel.getAttribute("for"));
        }
    }

    @org.testng.annotations.Test
    public void getWebElementLabelsList() {
        for (WebElement webElementLabel : testObject.webElementLabels) {
            Assert.assertNotNull(webElementLabel.getAttribute("for"));
        }
    }

    @org.testng.annotations.Test
    public void getWebElement() {
        Assert.assertTrue(testObject.labelForTextB.isDisplayed());
    }

    @org.testng.annotations.Test
    public void formElement() {
        Assert.assertTrue(testObject.element.elementWired());
    }

    @org.testng.annotations.Test
    public void textInputSet() {
        testObject.texta.set("TestText");
        Assert.assertEquals("TestText", testObject.texta.getAttribute("value"));
    }

    @org.testng.annotations.Test
    public void textInputClear() {
        testObject.texta.set("TestText");
        testObject.texta.clear();
        Assert.assertEquals("", testObject.texta.getAttribute("value"));
    }

    @org.testng.annotations.Test
    public void textInputGetValue() {
        testObject.texta.set("TestText");
        Assert.assertEquals("TestText", testObject.texta.getText());
    }

    @org.testng.annotations.Test
    public void formWebElement() {
        Assert.assertTrue(testObject.webElement.isDisplayed());
    }

    @org.testng.annotations.Test
    public void tableRowCount() {
        Assert.assertEquals(4, testObject.table.getRowCount());
    }

    @org.testng.annotations.Test
    public void tableColumnCount() {
        Assert.assertEquals(2, testObject.table.getColumnCount());
    }

    @org.testng.annotations.Test
    public void tableGetHeaderCell() {
        Assert.assertEquals("Month", testObject.table.getCellAtIndex(0, 0).getText());
    }

    @org.testng.annotations.Test
    public void tableGetBodyCell() {
        Assert.assertEquals("$80", testObject.table.getCellAtIndex(2, 1).getText());
    }

    @org.testng.annotations.Test
    public void tableGetFooterCell() {
        Assert.assertEquals("Sum", testObject.table.getCellAtIndex(3, 0).getText());
    }

    @org.testng.annotations.Test(expectedExceptions = InvalidElementStateException.class)
    public void selectDisabledElement() {
        Assert.assertEquals("option1", testObject.option1.getFirstSelectedOption().getText());
        final String disabledOptionText = "Disabled option";
        testObject.option1.selectByVisibleText(disabledOptionText);
    }

    @org.testng.annotations.Test(expectedExceptions = InvalidElementStateException.class)
    public void selectDisabledElementByValue() {
        Assert.assertEquals("option1", testObject.option1.getFirstSelectedOption().getText());
        testObject.option1.selectByValue("option3");
    }


    @org.testng.annotations.Test(expectedExceptions = InvalidElementStateException.class)
    public void selectDisabledElementByIndex() {
        Assert.assertEquals("option1", testObject.option1.getFirstSelectedOption().getText());
        testObject.option1.selectByIndex(2);
    }


    @org.testng.annotations.Test(expectedExceptions = NoSuchElementException.class)
    public void selectNonExistingElementSelectByIndex() {
        Assert.assertEquals("option1", testObject.option1.getFirstSelectedOption().getText());
        testObject.option1.selectByIndex(10);
    }

    @org.testng.annotations.Test(expectedExceptions = NoSuchElementException.class)
    public void selectNonExistingElementSelectByValue() {
        Assert.assertEquals("option1", testObject.option1.getFirstSelectedOption().getText());
        testObject.option1.selectByValue("foofoo");
    }

    @org.testng.annotations.Test(expectedExceptions = NoSuchElementException.class)
    public void selectNonExistingElementSelectByVisibleText() {
        Assert.assertEquals("option1", testObject.option1.getFirstSelectedOption().getText());
        testObject.option1.selectByVisibleText("fooBar");
    }

}
