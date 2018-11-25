package automation.web.base.elements.base;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import automation.web.base.elements.utils.FoundBy;
import automation.web.base.page.PageSync;
import automation.web.base.elements.utils.Locator;
import automation.web.base.elements.utils.Util;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * An implementation of the Element interface. Delegates its work to an underlying WebElement instance for
 * custom functionality.
 */
public class ElementImpl implements Element {

    private WebElement element;

    private static int TIMEOUT = 10 ;

    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public ElementImpl(final WebElement element) {
        this.element = element;
    }

    @Override
    public void click() {
        WebDriverWait wait = new WebDriverWait(getWrappedDriver(), TIMEOUT);
        element = wait.until(ExpectedConditions.elementToBeClickable(new ByChained(this.getBy())));
        element.click();
        Assert.assertTrue(PageSync.waitForJSandJQueryToLoad(getWrappedDriver()));
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        element.sendKeys(keysToSend);
    }

    @Override
    public Point getLocation() {
        return element.getLocation();
    }

    @Override
    public void submit() {
        element.submit();
    }

    @Override
    public String getAttribute(String name) {
        return element.getAttribute(name);
    }

    @Override
    public String getCssValue(String propertyName) {
        return element.getCssValue(propertyName);
    }

    @Override
    public Dimension getSize() {
        return element.getSize();
    }

    @Override
    public Rectangle getRect() {
        return element.getRect();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return element.findElements(by);
    }

    @Override
    public String getText() {
        return element.getText();
    }

    @Override
    public String getTagName() {
        return element.getTagName();
    }

    @Override
    public boolean isSelected() {
        return element.isSelected();
    }

    @Override
    public WebElement findElement(By by) {
        return element.findElement(by);
    }

    @Override
    public boolean isEnabled() {
        return element.isEnabled();
    }

    @Override
    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    @Override
    public void clear() {
        element.clear();
    }

    @Override
    public WebElement getWrappedElement() {
        return element;
    }

    @Override
    public Coordinates getCoordinates() {
        return ((Locatable) element).getCoordinates();
    }

    @Override
    public boolean elementWired() {
        return (element != null);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }

    @Override
    public WebDriver getWrappedDriver() {
        return ((RemoteWebElement) this.getWrappedElement()).getWrappedDriver();
    }


    private List<String> getLocator() {
        try {
            Object foundBy = FieldUtils.readField(getWrappedElement(), "foundBy", true);
            return Util.parse(foundBy.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private By[] getBy() {
        List<String> strLocators = getLocator();
        List<Locator> objLocators = new ArrayList<>();
        for (String sLocator : strLocators) {
            FoundBy foundBy = FoundBy.fromString(sLocator.split(": ")[0].trim());
            String using = sLocator.split(": ")[1].trim();
            objLocators.add(new Locator(foundBy,using));
        }
        List<By> bies = new ArrayList<>();
        for (Locator loc : objLocators) {
            bies.add(loc.getBy());
        }
        return bies.toArray(new By[0]);
    }

    public Element refreshElement() {
        By[] b = getBy();
        this.element = getWrappedDriver().findElement(new ByChained(b));
        return this;
    }

    public <T> T findElement(By by, Class<T> c) {
        return findElement(by, c, false);
    }

    public <T> List<T> findElements(By by, Class<T> c) {
        return findElements(by, c, false);
    }

    public <T> T findElement(By by, Class<?> c, boolean refreshParent) {
        try {
            if (refreshParent) {
                return (T) c.getConstructor(WebElement.class).newInstance(refreshElement().findElement(by));
            } else {
                return (T) c.getConstructor(WebElement.class).newInstance(getWrappedElement().findElement(by));
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return (T) new RuntimeException(e);
        }
    }

    public <T> List<T> findElements(By by, Class<T> c, boolean refreshParent) {
        List<T> elements = new ArrayList<>();
        List<WebElement> webElements;
        try {
            if (refreshParent) {
                webElements = refreshElement().findElements(by);
            } else {
                webElements = getWrappedElement().findElements(by);
            }
            for (WebElement we: webElements) {
                elements.add(c.getConstructor(WebElement.class).newInstance(we));
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return elements;
    }

}
