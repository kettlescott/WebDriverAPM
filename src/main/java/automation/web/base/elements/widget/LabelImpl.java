package automation.web.base.elements.widget;

import automation.web.base.elements.base.ElementImpl;
import org.openqa.selenium.WebElement;

/**
 * Wraps a label on a html form with some behavior.
 */
public class LabelImpl extends ElementImpl implements Label {

    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public LabelImpl(WebElement element) {
        super(element);
    }

    @Override
    public String getFor() {
        return getWrappedElement().getAttribute("for");
    }

    @Override
    public Label refreshElement() {
        return (Label) super.refreshElement();
    }
}
